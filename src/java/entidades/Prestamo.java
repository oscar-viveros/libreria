/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ove
 */
@Entity
@Table(name = "prestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p"),
    @NamedQuery(name = "Prestamo.findByIdPrestamo", query = "SELECT p FROM Prestamo p WHERE p.idPrestamo = :idPrestamo"),
    @NamedQuery(name = "Prestamo.findByPresFechaInicio", query = "SELECT p FROM Prestamo p WHERE p.presFechaInicio = :presFechaInicio"),
    @NamedQuery(name = "Prestamo.findByPresEstado", query = "SELECT p FROM Prestamo p WHERE p.presEstado = :presEstado")})
public class Prestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prestamo")
    private Integer idPrestamo;
    @Column(name = "pres_fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date presFechaInicio;
    @Size(max = 45)
    @Column(name = "pres_estado")
    private String presEstado;
    @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")
    @ManyToOne(fetch = FetchType.EAGER)
    private Libro idLibro;
    @JoinColumn(name = "id_usuariorol", referencedColumnName = "id_usuario_rol")
    @ManyToOne(fetch = FetchType.EAGER)
    private UsuarioRol idUsuariorol;

    public Prestamo() {
    }

    public Prestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Date getPresFechaInicio() {
        return presFechaInicio;
    }

    public void setPresFechaInicio(Date presFechaInicio) {
        this.presFechaInicio = presFechaInicio;
    }

    public String getPresEstado() {
        return presEstado;
    }

    public void setPresEstado(String presEstado) {
        this.presEstado = presEstado;
    }

    public Libro getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Libro idLibro) {
        this.idLibro = idLibro;
    }

    public UsuarioRol getIdUsuariorol() {
        return idUsuariorol;
    }

    public void setIdUsuariorol(UsuarioRol idUsuariorol) {
        this.idUsuariorol = idUsuariorol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrestamo != null ? idPrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.idPrestamo == null && other.idPrestamo != null) || (this.idPrestamo != null && !this.idPrestamo.equals(other.idPrestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Prestamo[ idPrestamo=" + idPrestamo + " ]";
    }
    
}
