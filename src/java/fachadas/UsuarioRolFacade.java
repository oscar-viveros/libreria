/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import com.google.gson.Gson;
import controladores.util.AlgoritmoMD5;
import entidades.Usuario;
import entidades.UsuarioRol;
import entidades.vo.UsuarioVO;
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
import javax.ws.rs.QueryParam;

/**
 *
 * @author ove
 */
@Stateless
@Path("entidades.usuarioRol")
public class UsuarioRolFacade extends AbstractFacade<UsuarioRol> {

    public static final String ACTIVO = "Activo";
    public static final String INACTIVO = "Inactivo";
    @PersistenceContext(unitName = "LibreriaPU")
    private EntityManager em;
    private Gson gson;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioRolFacade() {
        super(UsuarioRol.class);
    }

    public List<UsuarioRol> buscarUsuario(String login, String password) {
        Query query = em.createQuery("SELECT ur FROM UsuarioRol ur"
                + " WHERE ur.urUsuario.usuLogin = :login"
                + " AND ur.urUsuario.usuPassword = :password AND ur.urEstado = :estado");
        query.setParameter("login", login);
        query.setParameter("password", password);
        query.setParameter("estado", ACTIVO);
        return query.getResultList();
    }

    @POST
    @Path("validar")
    @Produces({"application/json"})
    public String iniciarMovil(String userJson) {
        gson = new Gson();

        // Convierto el JSON a un objeto
        UsuarioVO uVo = gson.fromJson(userJson, UsuarioVO.class);
        // Encripto la contraseña en el formato MD5
        AlgoritmoMD5 algoritmoMD5 = new AlgoritmoMD5();
        String strContrasenaMD5 = algoritmoMD5.cifrarMD5(uVo.getUsuPassword());

        // Obtengo el usuario (en mi caso una lista por la relación muchos a 
        // muchos entre Usuario y Rol
        List<UsuarioRol> lista = buscarUsuario(
                uVo.getUsuLogin(), strContrasenaMD5);
        // Si no encuentra resultado envío como respuesta el código ERR01
        // de lo contrario envío los datos del usuario
        if (lista.isEmpty()) {
            return "ERR01";
        }
        
        // Obtengo el primer dato de la lista, ya que todos los UsuarioRol 
        // tienen el mismo usuario: 
        Usuario u = lista.get(0).getUrUsuario();
        uVo = new UsuarioVO(lista.get(0).getUrRol().getRolNombre(),
                u.getUsuPersona().getPerNombre(), u.getUsuPersona().getPerApellido());
        // Retorno el usuario en formato JSON
        return gson.toJson(uVo);

    }

}
