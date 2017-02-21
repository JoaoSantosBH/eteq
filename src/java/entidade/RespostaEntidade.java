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
@Table(name = "resposta")
@NamedQueries({
    @NamedQuery(name = "RespostaEntidade.findAll", query = "SELECT r FROM RespostaEntidade r")})
public class RespostaEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resposta")
    private Integer idResposta;
    @Column(name = "id_forum")
    private Integer idForum;
    @Lob
    @Column(name = "texto_resposta")
    private String textoResposta;
    @Basic(optional = false)
    @Column(name = "data_resposta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataResposta;
    @Column(name = "id_usuario_remetente")
    private Integer idUsuarioRemetente;
    @Column(name = "id_usuario_destinatario")
    private Integer idUsuarioDestinatario;
    @Column(name = "lida")
    private Short lida;

    public RespostaEntidade() {
    }

    public RespostaEntidade(Integer idResposta) {
        this.idResposta = idResposta;
    }

    public RespostaEntidade(Integer idResposta, Date dataResposta) {
        this.idResposta = idResposta;
        this.dataResposta = dataResposta;
    }

    public Integer getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(Integer idResposta) {
        this.idResposta = idResposta;
    }

    public Integer getIdForum() {
        return idForum;
    }

    public void setIdForum(Integer idForum) {
        this.idForum = idForum;
    }

    public String getTextoResposta() {
        return textoResposta;
    }

    public void setTextoResposta(String textoResposta) {
        this.textoResposta = textoResposta;
    }

    public Date getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(Date dataResposta) {
        this.dataResposta = dataResposta;
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

    public Short getLida() {
        return lida;
    }

    public void setLida(Short lida) {
        this.lida = lida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResposta != null ? idResposta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespostaEntidade)) {
            return false;
        }
        RespostaEntidade other = (RespostaEntidade) object;
        if ((this.idResposta == null && other.idResposta != null) || (this.idResposta != null && !this.idResposta.equals(other.idResposta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.RespostaEntidade[ idResposta=" + idResposta + " ]";
    }
    
}
