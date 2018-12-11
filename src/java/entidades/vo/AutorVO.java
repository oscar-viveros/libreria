/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.vo;

import java.util.List;

/**
 *
 * @author Oscar Viveros Egas
 */
public class AutorVO {
    
    private String autNombre;
    private List<LibroVO> listaLibrosVO;

    public AutorVO() {
    }

    public AutorVO(String autNombre, List<LibroVO> listaLibrosVO) {
        this.autNombre = autNombre;
        this.listaLibrosVO = listaLibrosVO;
    }

    public String getAutNombre() {
        return autNombre;
    }

    public void setAutNombre(String autNombre) {
        this.autNombre = autNombre;
    }

    public List<LibroVO> getListaLibrosVO() {
        return listaLibrosVO;
    }

    public void setListaLibrosVO(List<LibroVO> listaLibrosVO) {
        this.listaLibrosVO = listaLibrosVO;
    }
    
    
    
}
