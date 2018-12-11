/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.vo;

/**
 *
 * @author Oscar Viveros Egas
 */
public class UsuarioVO {
    
    private String usuLogin;
    private String usuPassword;
    private String rol;
    private String nombre;
    private String apellido;

    public UsuarioVO() {
    }

    public UsuarioVO(String rol, String nombre, String apellido) {
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
}
