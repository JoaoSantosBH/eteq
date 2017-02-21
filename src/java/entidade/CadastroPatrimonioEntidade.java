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
@Table(name = "cadastro_patrimonio")
@NamedQueries({
    @NamedQuery(name = "CadastroPatrimonioEntidade.findAll", query = "SELECT c FROM CadastroPatrimonioEntidade c")})
public class CadastroPatrimonioEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cadastro")
    private Integer idCadastro;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "id_patrimonio")
    private Integer idPatrimonio;
    @Column(name = "data_cadastro")
    private String dataCadastro;

    public CadastroPatrimonioEntidade() {
    }

    public CadastroPatrimonioEntidade(Integer idCadastro) {
        this.idCadastro = idCadastro;
    }

    public Integer getIdCadastro() {
        return idCadastro;
    }

    public void setIdCadastro(Integer idCadastro) {
        this.idCadastro = idCadastro;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPatrimonio() {
        return idPatrimonio;
    }

    public void setIdPatrimonio(Integer idPatrimonio) {
        this.idPatrimonio = idPatrimonio;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCadastro != null ? idCadastro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadastroPatrimonioEntidade)) {
            return false;
        }
        CadastroPatrimonioEntidade other = (CadastroPatrimonioEntidade) object;
        if ((this.idCadastro == null && other.idCadastro != null) || (this.idCadastro != null && !this.idCadastro.equals(other.idCadastro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.CadastroPatrimonioEntidade[ idCadastro=" + idCadastro + " ]";
    }
    
}
