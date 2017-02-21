/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidade;

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

/**
 *
 * @author joaosantos
 */
@Entity
@Table(name = "negociacao")
@NamedQueries({
    @NamedQuery(name = "NegociacaoEntidade.findAll", query = "SELECT n FROM NegociacaoEntidade n")})
public class NegociacaoEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_negociacao")
    private Integer idNegociacao;
    @Column(name = "id_usuario_tem")
    private Integer idUsuarioTem;
    @Column(name = "id_usuario_quer")
    private Integer idUsuarioQuer;
    @Column(name = "id_produto_tem")
    private Integer idProdutoTem;
    @Column(name = "id_produto_quer")
    private Integer idProdutoQuer;
    @Column(name = "status")
    private Short status;
    @Column(name = "data_inicio")
    private String dataInicio;
    @Column(name = "avisado")
    private Integer avisado;

    public NegociacaoEntidade() {
    }

    public NegociacaoEntidade(Integer idNegociacao) {
        this.idNegociacao = idNegociacao;
    }

    public Integer getIdNegociacao() {
        return idNegociacao;
    }

    public void setIdNegociacao(Integer idNegociacao) {
        this.idNegociacao = idNegociacao;
    }

    public Integer getIdUsuarioTem() {
        return idUsuarioTem;
    }

    public void setIdUsuarioTem(Integer idUsuarioTem) {
        this.idUsuarioTem = idUsuarioTem;
    }

    public Integer getIdUsuarioQuer() {
        return idUsuarioQuer;
    }

    public void setIdUsuarioQuer(Integer idUsuarioQuer) {
        this.idUsuarioQuer = idUsuarioQuer;
    }

    public Integer getIdProdutoTem() {
        return idProdutoTem;
    }

    public void setIdProdutoTem(Integer idProdutoTem) {
        this.idProdutoTem = idProdutoTem;
    }

    public Integer getIdProdutoQuer() {
        return idProdutoQuer;
    }

    public void setIdProdutoQuer(Integer idProdutoQuer) {
        this.idProdutoQuer = idProdutoQuer;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getAvisado() {
        return avisado;
    }

    public void setAvisado(Integer avisado) {
        this.avisado = avisado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNegociacao != null ? idNegociacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NegociacaoEntidade)) {
            return false;
        }
        NegociacaoEntidade other = (NegociacaoEntidade) object;
        if ((this.idNegociacao == null && other.idNegociacao != null) || (this.idNegociacao != null && !this.idNegociacao.equals(other.idNegociacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.NegociacaoEntidade[ idNegociacao=" + idNegociacao + " ]";
    }
    
}
