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
@Table(name = "subcategoria")
@NamedQueries({
    @NamedQuery(name = "SubcategoriaEntidade.findAll", query = "SELECT s FROM SubcategoriaEntidade s")})
public class SubcategoriaEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_subcategoria")
    private Integer idSubcategoria;
    @Column(name = "id_categoria")
    private Integer idCategoria;
    @Column(name = "titulo_subcategoria")
    private String tituloSubcategoria;

    public SubcategoriaEntidade() {
    }

    public SubcategoriaEntidade(Integer idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }

    public Integer getIdSubcategoria() {
        return idSubcategoria;
    }

    public void setIdSubcategoria(Integer idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTituloSubcategoria() {
        return tituloSubcategoria;
    }

    public void setTituloSubcategoria(String tituloSubcategoria) {
        this.tituloSubcategoria = tituloSubcategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubcategoria != null ? idSubcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubcategoriaEntidade)) {
            return false;
        }
        SubcategoriaEntidade other = (SubcategoriaEntidade) object;
        if ((this.idSubcategoria == null && other.idSubcategoria != null) || (this.idSubcategoria != null && !this.idSubcategoria.equals(other.idSubcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.SubcategoriaEntidade[ idSubcategoria=" + idSubcategoria + " ]";
    }
    
}
