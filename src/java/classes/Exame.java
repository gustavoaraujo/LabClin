/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cliente
 */
@Entity
@Table(name = "exame", catalog = "laboratorio", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exame.findAll", query = "SELECT e FROM Exame e"),
    @NamedQuery(name = "Exame.findByIdexame", query = "SELECT e FROM Exame e WHERE e.idexame = :idexame"),
    @NamedQuery(name = "Exame.findByPreco", query = "SELECT e FROM Exame e WHERE e.preco = :preco"),
    @NamedQuery(name = "Exame.findByDescricao", query = "SELECT e FROM Exame e WHERE e.descricao = :descricao"),
    @NamedQuery(name = "Exame.findByTempojejum", query = "SELECT e FROM Exame e WHERE e.tempojejum = :tempojejum"),
    @NamedQuery(name = "Exame.findByDatapedido", query = "SELECT e FROM Exame e WHERE e.datapedido = :datapedido"),
    @NamedQuery(name = "Exame.findByDataentrega", query = "SELECT e FROM Exame e WHERE e.dataentrega = :dataentrega"),
    @NamedQuery(name = "Exame.findByPlanosaude", query = "SELECT e FROM Exame e WHERE e.planosaude = :planosaude")})
public class Exame implements Serializable {

    @Basic(optional = false)
    @Column(name = "codplano", nullable = true)
    private int codplano;
    @Basic(optional = false)
    @Column(name = "senha", nullable = false, length = 45)
    private String senha;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "exame")
    private Planosaude planosaude1;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idexame", nullable = false)
    private Integer idexame;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "preco", nullable = false, precision = 8, scale = 2)
    private BigDecimal preco;
    @Basic(optional = false)
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;
    @Basic(optional = false)
    @Column(name = "tempojejum", nullable = false, length = 45)
    private String tempojejum;
    @Basic(optional = false)
    @Column(name = "datapedido", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datapedido;
    @Basic(optional = false)
    @Column(name = "dataentrega", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataentrega;
    @Basic(optional = false)
    @Column(name = "planosaude", nullable = false)
    private boolean planosaude;
    @JoinColumn(name = "idcliente", referencedColumnName = "idCliente", nullable = false)
    @ManyToOne(optional = false)
    private Cliente idcliente;

    public Exame() {
    }

    public Exame(Integer idexame) {
        this.idexame = idexame;
    }

    public Exame(Integer idexame, BigDecimal preco, String descricao, String tempojejum, Date datapedido, Date dataentrega, boolean planosaude) {
        this.idexame = idexame;
        this.preco = preco;
        this.descricao = descricao;
        this.tempojejum = tempojejum;
        this.datapedido = datapedido;
        this.dataentrega = dataentrega;
        this.planosaude = planosaude;
    }

    public Integer getIdexame() {
        return idexame;
    }

    public void setIdexame(Integer idexame) {
        this.idexame = idexame;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTempojejum() {
        return tempojejum;
    }

    public void setTempojejum(String tempojejum) {
        this.tempojejum = tempojejum;
    }

    public Date getDatapedido() {
        return datapedido;
    }

    public void setDatapedido(Date datapedido) {
        this.datapedido = datapedido;
    }

    public Date getDataentrega() {
        return dataentrega;
    }

    public void setDataentrega(Date dataentrega) {
        this.dataentrega = dataentrega;
    }

    public boolean getPlanosaude() {
        return planosaude;
    }

    public void setPlanosaude(boolean planosaude) {
        this.planosaude = planosaude;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexame != null ? idexame.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exame)) {
            return false;
        }
        Exame other = (Exame) object;
        if ((this.idexame == null && other.idexame != null) || (this.idexame != null && !this.idexame.equals(other.idexame))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "classes.Exame[ idexame=" + idexame + " ]";
    }

    public int getCodplano() {
        return codplano;
    }

    public void setCodplano(int codplano) {
        this.codplano = codplano;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Planosaude getPlanosaude1() {
        return planosaude1;
    }

    public void setPlanosaude1(Planosaude planosaude1) {
        this.planosaude1 = planosaude1;
    }
    
}
