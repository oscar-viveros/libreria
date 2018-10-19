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
@Table(name = "usuario_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u"),
    @NamedQuery(name = "UsuarioRol.findByIdUsuarioRol", query = "SELECT u FROM UsuarioRol u WHERE u.idUsuarioRol = :idUsuarioRol"),
    @NamedQuery(name = "UsuarioRol.findByUrEstado", query = "SELECT u FROM UsuarioRol u WHERE u.urEstado = :urEstado")})
public class UsuarioRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_rol")
    private Integer idUsuarioRol;
    @Size(max = 45)
    @Column(name = "ur_estado")
    private String urEstado;
//    @OneToMany(mappedBy = "idUsuariorol", fetch = FetchType.EAGER)
//    private List<Prestamo> prestamoList;
    @JoinColumn(name = "ur_rol", referencedColumnName = "id_rol")
    @ManyToOne(fetch = FetchType.EAGER)
    private Rol urRol;
    @JoinColumn(name = "ur_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario urUsuario;

    public UsuarioRol() {
    }

    public UsuarioRol(Integer idUsuarioRol) {
        this.idUsuarioRol = idUsuarioRol;
    }

    public Integer getIdUsuarioRol() {
        return idUsuarioRol;
    }

    public void setIdUsuarioRol(Integer idUsuarioRol) {
        this.idUsuarioRol = idUsuarioRol;
    }

    public String getUrEstado() {
        return urEstado;
    }

    public void setUrEstado(String urEstado) {
        this.urEstado = urEstado;
    }

//    @XmlTransient
//    public List<Prestamo> getPrestamoList() {
//        return prestamoList;
//    }
//
//    public void setPrestamoList(List<Prestamo> prestamoList) {
//        this.prestamoList = prestamoList;
//    }

    public Rol getUrRol() {
        return urRol;
    }

    public void setUrRol(Rol urRol) {
        this.urRol = urRol;
    }

    public Usuario getUrUsuario() {
        return urUsuario;
    }

    public void setUrUsuario(Usuario urUsuario) {
        this.urUsuario = urUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioRol != null ? idUsuarioRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.idUsuarioRol == null && other.idUsuarioRol != null) || (this.idUsuarioRol != null && !this.idUsuarioRol.equals(other.idUsuarioRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UsuarioRol[ idUsuarioRol=" + idUsuarioRol + " ]";
    }
    
}
