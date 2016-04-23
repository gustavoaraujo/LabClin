/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cliente
 */
@Entity
@Table(name = "planosaude", catalog = "laboratorio", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planosaude.findAll", query = "SELECT p FROM Planosaude p"),
    @NamedQuery(name = "Planosaude.findByIdplano", query = "SELECT p FROM Planosaude p WHERE p.idplano = :idplano"),
    @NamedQuery(name = "Planosaude.findByNome", query = "SELECT p FROM Planosaude p WHERE p.nome = :nome")})
public class Planosaude implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplano", nullable = false)
    private Integer idplano;
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;
    @JoinColumn(name = "idplano", referencedColumnName = "codplano", nullable = true, insertable = false, updatable = false)
    @OneToOne(optional = true)
    private Exame exame;

    public Planosaude() {
    }

    public Planosaude(Integer idplano) {
        this.idplano = idplano;
    }

    public Planosaude(Integer idplano, String nome) {
        this.idplano = idplano;
        this.nome = nome;
    }

    public Integer getIdplano() {
        return idplano;
    }

    public void setIdplano(Integer idplano) {
        this.idplano = idplano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplano != null ? idplano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planosaude)) {
            return false;
        }
        Planosaude other = (Planosaude) object;
        if ((this.idplano == null && other.idplano != null) || (this.idplano != null && !this.idplano.equals(other.idplano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Planosaude[ idplano=" + idplano + " ]";
    }
    
}
