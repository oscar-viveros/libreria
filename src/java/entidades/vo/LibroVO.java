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
public class LibroVO {
    
    private Integer idLibro;
    private String libTitulo;
    private String libImagen;
    private String libAutor;
    private String libGenero;

    public LibroVO() {
    }

    public LibroVO(Integer idLibro, String libTitulo, String libImagen, String libAutor, String libGenero) {
        this.idLibro = idLibro;
        this.libTitulo = libTitulo;
        this.libImagen = libImagen;
        this.libAutor = libAutor;
        this.libGenero = libGenero;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getLibTitulo() {
        return libTitulo;
    }

    public void setLibTitulo(String libTitulo) {
        this.libTitulo = libTitulo;
    }

    public String getLibImagen() {
        return libImagen;
    }

    public void setLibImagen(String libImagen) {
        this.libImagen = libImagen;
    }

    public String getLibAutor() {
        return libAutor;
    }

    public void setLibAutor(String libAutor) {
        this.libAutor = libAutor;
    }

    public String getLibGenero() {
        return libGenero;
    }

    public void setLibGenero(String libGenero) {
        this.libGenero = libGenero;
    }
    
    
}
