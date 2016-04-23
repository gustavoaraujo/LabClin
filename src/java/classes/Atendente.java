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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cliente
 */
@Entity
@Table(name = "atendente", catalog = "laboratorio", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atendente.findAll", query = "SELECT a FROM Atendente a"),
    @NamedQuery(name = "Atendente.findByIdatendente", query = "SELECT a FROM Atendente a WHERE a.idatendente = :idatendente"),
    @NamedQuery(name = "Atendente.findByLogin", query = "SELECT a FROM Atendente a WHERE a.login = :login"),
    @NamedQuery(name = "Atendente.findBySenha", query = "SELECT a FROM Atendente a WHERE a.senha = :senha")})
public class Atendente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idatendente", nullable = false)
    private Integer idatendente;
    @Basic(optional = false)
    @Column(name = "login", nullable = false, length = 45)
    private String login;
    @Basic(optional = false)
    @Column(name = "senha", nullable = false, length = 45)
    private String senha;

    public Atendente() {
    }

    public Atendente(Integer idatendente) {
        this.idatendente = idatendente;
    }

    public Atendente(Integer idatendente, String login, String senha) {
        this.idatendente = idatendente;
        this.login = login;
        this.senha = senha;
    }

    public Integer getIdatendente() {
        return idatendente;
    }

    public void setIdatendente(Integer idatendente) {
        this.idatendente = idatendente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatendente != null ? idatendente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atendente)) {
            return false;
        }
        Atendente other = (Atendente) object;
        if ((this.idatendente == null && other.idatendente != null) || (this.idatendente != null && !this.idatendente.equals(other.idatendente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Atendente[ idatendente=" + idatendente + " ]";
    }
    
}
