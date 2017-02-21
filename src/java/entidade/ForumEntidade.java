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
import javax.persistence.Lob;
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
@Table(name = "forum")
@NamedQueries({
    @NamedQuery(name = "ForumEntidade.findAll", query = "SELECT f FROM ForumEntidade f")})
public class ForumEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_forum")
    private Integer idForum;
    @Column(name = "assunto")
    private String assunto;
    @Lob
    @Column(name = "mensagem")
    private String mensagem;
    @Column(name = "data_mensagem")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMensagem;
    @Column(name = "id_usuario_remetente")
    private Integer idUsuarioRemetente;
    @Column(name = "id_usuario_destinatario")
    private Integer idUsuarioDestinatario;
    @Column(name = "id_negociacao")
    private Integer idNegociacao;
    @Column(name = "ativo")
    private Short ativo;

    public ForumEntidade() {
    }

    public ForumEntidade(Integer idForum) {
        this.idForum = idForum;
    }

    public Integer getIdForum() {
        return idForum;
    }

    public void setIdForum(Integer idForum) {
        this.idForum = idForum;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataMensagem() {
        return dataMensagem;
    }

    public void setDataMensagem(Date dataMensagem) {
        this.dataMensagem = dataMensagem;
    }

    public Integer getIdUsuarioRemetente() {
        return idUsuarioRemetente;
    }

    public void setIdUsuarioRemetente(Integer idUsuarioRemetente) {
        this.idUsuarioRemetente = idUsuarioRemetente;
    }

    public Integer getIdUsuarioDestinatario() {
        return idUsuarioDestinatario;
    }

    public void setIdUsuarioDestinatario(Integer idUsuarioDestinatario) {
        this.idUsuarioDestinatario = idUsuarioDestinatario;
    }

    public Integer getIdNegociacao() {
        return idNegociacao;
    }

    public void setIdNegociacao(Integer idNegociacao) {
        this.idNegociacao = idNegociacao;
    }

    public Short getAtivo() {
        return ativo;
    }

    public void setAtivo(Short ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idForum != null ? idForum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ForumEntidade)) {
            return false;
        }
        ForumEntidade other = (ForumEntidade) object;
        if ((this.idForum == null && other.idForum != null) || (this.idForum != null && !this.idForum.equals(other.idForum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ForumEntidade[ idForum=" + idForum + " ]";
    }
    
}
