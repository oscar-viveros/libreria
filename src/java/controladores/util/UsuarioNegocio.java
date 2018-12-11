/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.util;

import entidades.Usuario;
import entidades.UsuarioRol;
import fachadas.UsuarioRolFacade;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Oscar Viveros Egas
 */
public class UsuarioNegocio {

    /*
     * 
     * 01/05/2018
     * Propiedades que componen el objeto Usuario para la aplicación
     */
    private List<UsuarioRol> listaUsuarioRols;
    private Usuario usuarioBD;
    private Boolean usuarioLogeado;
    private Integer idRolSeleccionado;

    /**
     * Permite autenticar un usuario a partir del login y password.
     *
     * @param login login del usuario
     * @param password password del usuario
     * @param usuarioRolFacade fachada en la cual se hará la consulta sobre la
     * base de datos
     *
     * @return una lista de la relación UsuarioRol cuyo Usuario coincida con el
     * login y el password
     */
    public List<UsuarioRol> autenticar(String login, String password, UsuarioRolFacade usuarioRolFacade) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        listaUsuarioRols = usuarioRolFacade.buscarUsuario(login, password);
        if (listaUsuarioRols.isEmpty()) {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales Inválidas");
            usuarioLogeado = false;
        } else {
            // EL USUARIO EXISTE
            loggedIn = true;
            usuarioLogeado = true;
            usuarioBD = listaUsuarioRols.get(0).getUrUsuario();
            idRolSeleccionado = listaUsuarioRols.get(0).getUrRol().getIdRol();
            return listaUsuarioRols;
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        return null;
    }

    public Usuario getUsuarioBD() {
        return usuarioBD;
    }

     public void cambioRol(ValueChangeEvent event){
         
        idRolSeleccionado = (Integer) event.getNewValue();
    }
    
    public Boolean getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public List<UsuarioRol> getListaUsuarioRols() {
        return listaUsuarioRols;
    }

    public void setListaUsuarioRols(List<UsuarioRol> listaUsuarioRols) {
        this.listaUsuarioRols = listaUsuarioRols;
    }

    public Integer getIdRolSeleccionado() {
        return idRolSeleccionado;
    }

    public void setIdRolSeleccionado(Integer idRolSeleccionado) {
        this.idRolSeleccionado = idRolSeleccionado;
    }

}
