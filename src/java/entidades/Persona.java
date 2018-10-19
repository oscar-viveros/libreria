/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ove
 */
@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Persona.findByPerNombre", query = "SELECT p FROM Persona p WHERE p.perNombre = :perNombre"),
    @NamedQuery(name = "Persona.findByPerApellido", query = "SELECT p FROM Persona p WHERE p.perApellido = :perApellido"),
    @NamedQuery(name = "Persona.findByPerIdentificacion", query = "SELECT p FROM Persona p WHERE p.perIdentificacion = :perIdentificacion"),
    @NamedQuery(name = "Persona.findByPerFechanacimiento", query = "SELECT p FROM Persona p WHERE p.perFechanacimiento = :perFechanacimiento")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Size(max = 45)
    @Column(name = "per_nombre")
    private String perNombre;
    @Size(max = 45)
    @Column(name = "per_apellido")
    private String perApellido;
    @Size(max = 45)
    @Column(name = "per_identificacion")
    private String perIdentificacion;
    @Column(name = "per_fechanacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date perFechanacimiento;
//    @OneToMany(mappedBy = "libAutor", fetch = FetchType.EAGER)
//    private List<Libro> libroList;
//    @OneToMany(mappedBy = "usuPersona", fetch = FetchType.EAGER)
//    private List<Usuario> usuarioList;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public String getPerApellido() {
        return perApellido;
    }

    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    public String getPerIdentificacion() {
        return perIdentificacion;
    }

    public void setPerIdentificacion(String perIdentificacion) {
        this.perIdentificacion = perIdentificacion;
    }

    public Date getPerFechanacimiento() {
        return perFechanacimiento;
    }

    public void setPerFechanacimiento(Date perFechanacimiento) {
        this.perFechanacimiento = perFechanacimiento;
    }

//    @XmlTransient
//    public List<Libro> getLibroList() {
//        return libroList;
//    }
//
//    public void setLibroList(List<Libro> libroList) {
//        this.libroList = libroList;
//    }
//
//    @XmlTransient
//    public List<Usuario> getUsuarioList() {
//        return usuarioList;
//    }
//
//    public void setUsuarioList(List<Usuario> usuarioList) {
//        this.usuarioList = usuarioList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Persona[ idPersona=" + idPersona + " ]";
    }
    
}
