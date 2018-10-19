/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ove
 */
@Entity
@Table(name = "libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l"),
    @NamedQuery(name = "Libro.findByIdLibro", query = "SELECT l FROM Libro l WHERE l.idLibro = :idLibro"),
    @NamedQuery(name = "Libro.findByLibTitulo", query = "SELECT l FROM Libro l WHERE l.libTitulo = :libTitulo"),
    @NamedQuery(name = "Libro.findByLibImagen", query = "SELECT l FROM Libro l WHERE l.libImagen = :libImagen")})
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_libro")
    private Integer idLibro;
    @Size(max = 45)
    @Column(name = "lib_titulo")
    private String libTitulo;
    @Size(max = 45)
    @Column(name = "lib_imagen")
    private String libImagen;
    @JoinColumn(name = "lib_autor", referencedColumnName = "id_persona")
    @ManyToOne(fetch = FetchType.EAGER)
    private Persona libAutor;
    @JoinColumn(name = "lib_genero", referencedColumnName = "id_genero")
    @ManyToOne(fetch = FetchType.EAGER)
    private Genero libGenero;
//    @OneToMany(mappedBy = "idLibro", fetch = FetchType.EAGER)
//    private List<Prestamo> prestamoList;

    public Libro() {
    }

    public Libro(Integer idLibro) {
        this.idLibro = idLibro;
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

    public Persona getLibAutor() {
        return libAutor;
    }

    public void setLibAutor(Persona libAutor) {
        this.libAutor = libAutor;
    }

    public Genero getLibGenero() {
        return libGenero;
    }

    public void setLibGenero(Genero libGenero) {
        this.libGenero = libGenero;
    }

//    @XmlTransient
//    public List<Prestamo> getPrestamoList() {
//        return prestamoList;
//    }
//
//    public void setPrestamoList(List<Prestamo> prestamoList) {
//        this.prestamoList = prestamoList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibro != null ? idLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.idLibro == null && other.idLibro != null) || (this.idLibro != null && !this.idLibro.equals(other.idLibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Libro[ idLibro=" + idLibro + " ]";
    }
    
}
