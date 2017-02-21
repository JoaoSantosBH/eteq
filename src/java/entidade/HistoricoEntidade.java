/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author joaosantos
 */
@Entity
@Table(name = "historico")
@NamedQueries({
    @NamedQuery(name = "HistoricoEntidade.findAll", query = "SELECT h FROM HistoricoEntidade h")})
public class HistoricoEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historico")
    private Integer idHistorico;
    @Column(name = "id_usuario_tem")
    private Integer idUsuarioTem;
    @Column(name = "id_usuario_quis")
    private Integer idUsuarioQuis;
    @Column(name = "id_patrimonio_tem")
    private Integer idPatrimonioTem;
    @Column(name = "id_patrimonio_quis")
    private Integer idPatrimonioQuis;
    @Column(name = "data_desativacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDesativacao;
    @Column(name = "status")
    private String status;
    @Column(name = "concluido")
    private Short concluido;

    public HistoricoEntidade() {
    }

    public HistoricoEntidade(Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Integer getIdUsuarioTem() {
        return idUsuarioTem;
    }

    public void setIdUsuarioTem(Integer idUsuarioTem) {
        this.idUsuarioTem = idUsuarioTem;
    }

    public Integer getIdUsuarioQuis() {
        return idUsuarioQuis;
    }

    public void setIdUsuarioQuis(Integer idUsuarioQuis) {
        this.idUsuarioQuis = idUsuarioQuis;
    }

    public Integer getIdPatrimonioTem() {
        return idPatrimonioTem;
    }

    public void setIdPatrimonioTem(Integer idPatrimonioTem) {
        this.idPatrimonioTem = idPatrimonioTem;
    }

    public Integer getIdPatrimonioQuis() {
        return idPatrimonioQuis;
    }

    public void setIdPatrimonioQuis(Integer idPatrimonioQuis) {
        this.idPatrimonioQuis = idPatrimonioQuis;
    }

    public Date getDataDesativacao() {
        return dataDesativacao;
    }

    public void setDataDesativacao(Date dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Short getConcluido() {
        return concluido;
    }

    public void setConcluido(Short concluido) {
        this.concluido = concluido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorico != null ? idHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoEntidade)) {
            return false;
        }
        HistoricoEntidade other = (HistoricoEntidade) object;
        if ((this.idHistorico == null && other.idHistorico != null) || (this.idHistorico != null && !this.idHistorico.equals(other.idHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.HistoricoEntidade[ idHistorico=" + idHistorico + " ]";
    }
    
}
