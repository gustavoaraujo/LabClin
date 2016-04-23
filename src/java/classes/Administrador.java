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
@Table(name = "administrador", catalog = "laboratorio", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a"),
    @NamedQuery(name = "Administrador.findByIdadministrador", query = "SELECT a FROM Administrador a WHERE a.idadministrador = :idadministrador"),
    @NamedQuery(name = "Administrador.findByLogin", query = "SELECT a FROM Administrador a WHERE a.login = :login"),
    @NamedQuery(name = "Administrador.findBySenha", query = "SELECT a FROM Administrador a WHERE a.senha = :senha")})
public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idadministrador", nullable = false)
    private Integer idadministrador;
    @Basic(optional = false)
    @Column(name = "login", nullable = false, length = 45)
    private String login;
    @Basic(optional = false)
    @Column(name = "senha", nullable = false, length = 45)
    private String senha;

    public Administrador() {
    }

    public Administrador(Integer idadministrador) {
        this.idadministrador = idadministrador;
    }

    public Administrador(Integer idadministrador, String login, String senha) {
        this.idadministrador = idadministrador;
        this.login = login;
        this.senha = senha;
    }

    public Integer getIdadministrador() {
        return idadministrador;
    }

    public void setIdadministrador(Integer idadministrador) {
        this.idadministrador = idadministrador;
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
        hash += (idadministrador != null ? idadministrador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.idadministrador == null && other.idadministrador != null) || (this.idadministrador != null && !this.idadministrador.equals(other.idadministrador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Administrador[ idadministrador=" + idadministrador + " ]";
    }
    
}
