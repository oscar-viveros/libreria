/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import com.google.gson.Gson;
import entidades.Libro;
import entidades.vo.LibroVO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author ove
 */
@Stateless
@Path("entidades.libro")
public class LibroFacade extends AbstractFacade<Libro> {
    @PersistenceContext(unitName = "LibreriaPU")
    private EntityManager em;
    private Gson gson;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LibroFacade() {
        super(Libro.class);
    }
    
    public List<Libro> buscarPorAutor(Integer idAutor){
        em = getEntityManager();
        Query q = em.createQuery("SELECT l FROM Libro l WHERE l.libAutor.idPersona = :idAutor");
        q.setParameter("idAutor", idAutor);
        return q.getResultList();
    }
    
    // DECLARACION DEL SERVICIO WEB
    @GET
    @Path("findLibros")
    @Produces({"application/json"})
    public String listar() {
        // Inicializo gson
        gson = new Gson();
        // Consulto los libros
        List<Libro> lista = super.findAll();
        
        // Obtengo los datos necesarios para el json
        List<LibroVO> listaVO = new ArrayList<>();
        for(Libro l : lista){
            LibroVO lVO = new LibroVO(l.getIdLibro(),
                    l.getLibTitulo(), 
                    l.getLibImagen(), 
                    l.getLibAutor().getPerNombre(), 
                    l.getLibGenero().getGenNombre());
            listaVO.add(lVO);
        }
        // Convierto la lista (objeto) a json
        String json = gson.toJson(listaVO);
        
        return json;
    }
    
    @POST
    @Path("iniciarMovil")
    @Produces({"application/json"})
    public boolean iniciarMovil(@PathParam("libroJson") String libroJson){
        
        return false;
    }
    
}
