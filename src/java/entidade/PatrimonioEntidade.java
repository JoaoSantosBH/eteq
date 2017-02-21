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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author joaosantos
 */
@Entity
@Table(name = "patrimonio")
@NamedQueries({
    @NamedQuery(name = "PatrimonioEntidade.findAll", query = "SELECT p FROM PatrimonioEntidade p")})
public class PatrimonioEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_patrimonio")
    private Integer idPatrimonio;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "foto")
    private String foto;
    @Column(name = "titulo")
    private String titulo;
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "estado_conservacao")
    private String estadoConservacao;
    @Column(name = "reservado")
    private Short reservado;
    @Column(name = "id_categoria")
    private Integer idCategoria;
    @Column(name = "id_subcategoria")
    private Integer idSubcategoria;
    @Column(name = "id_itens_subcat")
    private Integer idItensSubcat;
    @Column(name = "palavra_chave")
    private String palavraChave;
    @Column(name = "aprovado")
    private Short aprovado;
    @Column(name = "avisado")
    private Short avisado;
    @Column(name = "expirado")
    private Short expirado;
    @Column(name = "excluido")
    private Short excluido;
    @Column(name = "tipo")
    private Short tipo;

    public PatrimonioEntidade() {
    }

    public PatrimonioEntidade(Integer idPatrimonio) {
        this.idPatrimonio = idPatrimonio;
    }

    public Integer getIdPatrimonio() {
        return idPatrimonio;
    }

    public void setIdPatrimonio(Integer idPatrimonio) {
        this.idPatrimonio = idPatrimonio;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(String estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public Short getReservado() {
        return reservado;
    }

    public void setReservado(Short reservado) {
        this.reservado = reservado;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdSubcategoria() {
        return idSubcategoria;
    }

    public void setIdSubcategoria(Integer idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }

    public Integer getIdItensSubcat() {
        return idItensSubcat;
    }

    public void setIdItensSubcat(Integer idItensSubcat) {
        this.idItensSubcat = idItensSubcat;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public Short getAprovado() {
        return aprovado;
    }

    public void setAprovado(Short aprovado) {
        this.aprovado = aprovado;
    }

    public Short getAvisado() {
        return avisado;
    }

    public void setAvisado(Short avisado) {
        this.avisado = avisado;
    }

    public Short getExpirado() {
        return expirado;
    }

    public void setExpirado(Short expirado) {
        this.expirado = expirado;
    }

    public Short getExcluido() {
        return excluido;
    }

    public void setExcluido(Short excluido) {
        this.excluido = excluido;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPatrimonio != null ? idPatrimonio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatrimonioEntidade)) {
            return false;
        }
        PatrimonioEntidade other = (PatrimonioEntidade) object;
        if ((this.idPatrimonio == null && other.idPatrimonio != null) || (this.idPatrimonio != null && !this.idPatrimonio.equals(other.idPatrimonio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.PatrimonioEntidade[ idPatrimonio=" + idPatrimonio + " ]";
    }
    
}
