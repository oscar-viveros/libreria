/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.util.GeneradorReportes;
import controladores.util.JsfUtil;
import entidades.Libro;
import entidades.Persona;
import entidades.vo.AutorVO;
import entidades.vo.LibroVO;
import fachadas.LibroFacade;
import fachadas.PersonaFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author ove
 */
@ManagedBean
@SessionScoped
public class ReportesController {

    @EJB
    private LibroFacade libroFacade;
    @EJB
    private PersonaFacade personaFacade;

    private Integer idTipoReporteSelec;
    private String exportaComo;

    public ReportesController() {
        libroFacade = new LibroFacade();
        personaFacade = new PersonaFacade();
    }

    public void generarReporte() {
        if (idTipoReporteSelec == 1) {
            reporteTodosLibros("libros");
        } else if (idTipoReporteSelec == 2) {
            reporteLibrosPorAutor("librosPorAutor");
        } else if (idTipoReporteSelec == 3) {

        }
    }

    private void reporteTodosLibros(String reporte) {
        List<Libro> lista = libroFacade.findAll();
        List<LibroVO> listaVO = new ArrayList<>();
        for (Libro l : lista) {
            LibroVO lVO = new LibroVO(l.getIdLibro(),
                    l.getLibTitulo(), l.getLibImagen(),
                    l.getLibAutor().getPerNombre() + " " + l.getLibAutor().getPerApellido(),
                    l.getLibGenero().getGenNombre());
            listaVO.add(lVO);
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaVO);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("idLibro", "idLibro");
        parameters.put("libTitulo", "libTitulo");
        parameters.put("libAutor", "libAutor");
        parameters.put("libGenero", "libGenero");
        parameters.put("libImagen", "libImagen");
        GeneradorReportes.generarReporte(dataSource, parameters, exportaComo, reporte);
    }

    private void reporteLibrosPorAutor(String reporte) {
        ServletContext sContext = (ServletContext) FacesContext.getCurrentInstance().
                getExternalContext().getContext();

        List<Persona> listaPersonasAut = personaFacade.buscarAutores();
        System.out.println("lista autores = "+listaPersonasAut.size());
        List<AutorVO> listaAutores = new ArrayList< >();
        for(Persona p : listaPersonasAut){
            AutorVO autorVO = new AutorVO();
            autorVO.setAutNombre(p.getPerNombre()+" "+p.getPerApellido());
            
            List<Libro> listaLibro = libroFacade.buscarPorAutor(p.getIdPersona());
            System.out.println("libros de "+p.getPerNombre()+": "+listaLibro.size());
            List<LibroVO> listaLibrosVO = new ArrayList<>();
            for(Libro l : listaLibro){
                LibroVO libroVO = new LibroVO(l.getIdLibro(), l.getLibTitulo(), 
                        l.getLibImagen(), "", l.getLibGenero().getGenNombre());
                listaLibrosVO.add(libroVO);
            }
            autorVO.setListaLibrosVO(listaLibrosVO);
            listaAutores.add(autorVO);
        }
        
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaAutores);
        HashMap<String, Object> parameters = new HashMap<>();
            
        parameters.put("autNombre", "autNombre");
        parameters.put("listaLibrosVO", "listaLibrosVO");
//        parameters.put("libAutor", "libAutor");
//        parameters.put("libGenero", "libGenero");
        parameters.put("SUBREPORT_DIR", sContext.getRealPath("/reportes/"));
        System.out.println("reporte:"+reporte);
        GeneradorReportes.generarReporte(dataSource, parameters, exportaComo, reporte);
    }

    public Integer getIdTipoReporteSelec() {
        return idTipoReporteSelec;
    }

    public void setIdTipoReporteSelec(Integer idTipoReporteSelec) {
        this.idTipoReporteSelec = idTipoReporteSelec;
    }

    public String getExportaComo() {
        return exportaComo;
    }

    public void setExportaComo(String exportaComo) {
        this.exportaComo = exportaComo;
    }

}
