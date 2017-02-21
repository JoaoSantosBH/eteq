/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import controle.ControladorHistorico;
import entidade.CategoriaEntidade;
import entidade.ItensSubcategoriaEntidade;
import entidade.SubcategoriaEntidade;
import entidade.TbCidadesEntidade;
import entidade.TbEstadosEntidade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.CategoriaDAO;
import persistencia.ItensSubcategoriaDAO;
import persistencia.SubCategoriaDAO;
import persistencia.TbCidadesDAO;
import persistencia.TbEstadosDAO;

/**
 *
 * @author joaosantos
 */
public class MetodosParaODWR {

    public List<TbEstadosEntidade> pegarTodosEstadosDWR() {
        List<TbEstadosEntidade> lst = new ArrayList<TbEstadosEntidade>();
        try {
            lst = TbEstadosDAO.pegarEstados();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosParaODWR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    public List<TbCidadesEntidade> pegarCidadesDWR(String idEstado) {

        List<TbCidadesEntidade> lst = new ArrayList<TbCidadesEntidade>();
        try {
            lst = TbCidadesDAO.pegarCidadesPelaUfDWR(idEstado);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosParaODWR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    public List<CategoriaEntidade> pegarTodasCategoriasDWR() {
        List<CategoriaEntidade> lst = new ArrayList<CategoriaEntidade>();
        try {
            lst = CategoriaDAO.getCategorias();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosParaODWR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    public List<SubcategoriaEntidade> pegarSubCategoriasPeloId(String id_sub) {
        List<SubcategoriaEntidade> lst = new ArrayList<SubcategoriaEntidade>();
        try {
            lst = SubCategoriaDAO.pegarSubCategoriasPeloId(id_sub);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosParaODWR.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lst;
    }

    public List<ItensSubcategoriaEntidade> pegarItensSubcategoriaPeloId(String idCat, String idSub) {
        List<ItensSubcategoriaEntidade> lst = new ArrayList<ItensSubcategoriaEntidade>();
        try {
            lst = ItensSubcategoriaDAO.getIntensSubCategoriasComposto(idCat, idSub);
        } catch (SQLException ex) {
            Logger.getLogger(MetodosParaODWR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }


}
