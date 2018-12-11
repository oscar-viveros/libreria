/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.util;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oscar Viveros Egas
 */
public class Session {

    private HttpServletRequest oRequest;
    private HttpSession oSession;
    private FacesContext oFctx;
    private ExternalContext oEctx;

    public HttpSession getoSession() {
        return oSession;
    }

    public Session() {
    }

    /**
     * Crear Sesiones a través del acceso a las propiedades de la aplicación y de la petición HTTP,
     * por medio del contexto JSF o por medio del contexto HTTP.
     * 
     * @param blnCrearSesion bandera para validar si la sesión ya ha sido creada
     */
    public void crearSesion_JSF_HTTP(boolean blnCrearSesion) {
        oRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (!blnCrearSesion) {
            oSession.invalidate();
        }
        oSession = oRequest.getSession(blnCrearSesion);
    }

    /**
     * Redirecciona la Url que entra como parámetro a su página respectiva
     * 
     * @param strUrlMenu la url se va a direccionar
     */
    public void redireccionarUrl(String strUrlMenu) {
        oFctx = FacesContext.getCurrentInstance();
        oEctx = oFctx.getExternalContext();
        String strUrlBase = oEctx.getRequestContextPath();
        try {
            oEctx.redirect(strUrlBase + strUrlMenu);
        } catch (IOException e) {
            e.getMessage();
        }
        oFctx.responseComplete();
    }

    /**
     * Método que obtiene al usuario que se encuentra logueado
     * 
     * @return un objeto de la clase {@link UsuarioNegocio}, el cual contiene al usuario logueado
     */
    public UsuarioNegocio obtenerUsuarioLogueado() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        UsuarioNegocio usuarioBusiness = (UsuarioNegocio) session.getAttribute("oSesionUsuario");
        return usuarioBusiness;
    }
}
