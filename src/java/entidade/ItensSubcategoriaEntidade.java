/*
 * To change this template, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joaomarcelo
 */
@Entity
@Table(name = "itens_subcategoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItensSubcategoriaEntidade.findAll", query = "SELECT i FROM ItensSubcategoriaEntidade i"),
    @NamedQuery(name = "ItensSubcategoriaEntidade.findByIdItensSubcategoria", query = "SELECT i FROM ItensSubcategoriaEntidade i WHERE i.idItensSubcategoria = :idItensSubcategoria"),
    @NamedQuery(name = "ItensSubcategoriaEntidade.findByIdCategoria", query = "SELECT i FROM ItensSubcategoriaEntidade i WHERE i.idCategoria = :idCategoria"),
    @NamedQuery(name = "ItensSubcategoriaEntidade.findByIdSubCategoria", query = "SELECT i FROM ItensSubcategoriaEntidade i WHERE i.idSubCategoria = :idSubCategoria"),
    @NamedQuery(name = "ItensSubcategoriaEntidade.findByNomeItens", query = "SELECT i FROM ItensSubcategoriaEntidade i WHERE i.nomeItens = :nomeItens")})
public class ItensSubcategoriaEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_itens_subcategoria")
    private Integer idItensSubcategoria;
    @Column(name = "id_categoria")
    private Integer idCategoria;
    @Column(name = "id_sub_categoria")
    private Integer idSubCategoria;
    @Column(name = "nome_itens")
    private String nomeItens;

    public ItensSubcategoriaEntidade() {
    }

    public ItensSubcategoriaEntidade(Integer idItensSubcategoria) {
        this.idItensSubcategoria = idItensSubcategoria;
    }

    public Integer getIdItensSubcategoria() {
        return idItensSubcategoria;
    }

    public void setIdItensSubcategoria(Integer idItensSubcategoria) {
        this.idItensSubcategoria = idItensSubcategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdSubCategoria() {
        return idSubCategoria;
    }

    public void setIdSubCategoria(Integer idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }

    public String getNomeItens() {
        return nomeItens;
    }

    public void setNomeItens(String nomeItens) {
        this.nomeItens = nomeItens;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItensSubcategoria != null ? idItensSubcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensSubcategoriaEntidade)) {
            return false;
        }
        ItensSubcategoriaEntidade other = (ItensSubcategoriaEntidade) object;
        if ((this.idItensSubcategoria == null && other.idItensSubcategoria != null) || (this.idItensSubcategoria != null && !this.idItensSubcategoria.equals(other.idItensSubcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ItensSubcategoriaEntidade[ idItensSubcategoria=" + idItensSubcategoria + " ]";
    }
    
}
