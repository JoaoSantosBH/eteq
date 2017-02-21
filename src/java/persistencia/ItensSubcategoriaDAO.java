/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.ItensSubcategoriaEntidade;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author joaomarcelo
 */
public class ItensSubcategoriaDAO {

    public static ArrayList<ItensSubcategoriaEntidade> getIntensSubCategorias() throws SQLException {
        ArrayList<ItensSubcategoriaEntidade> lst = new ArrayList<ItensSubcategoriaEntidade>();
        ItensSubcategoriaEntidade Sub = null;

        String Query = "SELECT * FROM eutenho.itens_subcategoria ;";
        ResultSet Rs = BaseDados.getInstancia().executeQuery(Query);
        while (Rs.next()) {
            Sub = new ItensSubcategoriaEntidade();
            Sub.setNomeItens(Rs.getString("nome_itens"));
            Sub.setIdCategoria(Rs.getInt("id_categoria"));
            Sub.setIdSubCategoria(Rs.getInt("id_sub_categoria"));

            lst.add(Sub);
        }

        return lst;
    }

    public static ArrayList<ItensSubcategoriaEntidade> getIntensSubCategoriasPorId(int x, int y) throws SQLException {
        ArrayList<ItensSubcategoriaEntidade> lst = new ArrayList<ItensSubcategoriaEntidade>();
        ItensSubcategoriaEntidade Sub = null;

        String Query = "SELECT * FROM eutenho.itens_subcategoria where id_categoria = " + x + " and id_sub_categoria = " + y + " order by id_itens_subcategoria ASC;";
        ResultSet Rs = BaseDados.getInstancia().executeQuery(Query);
        while (Rs.next()) {
            Sub = new ItensSubcategoriaEntidade();
            Sub.setNomeItens(Rs.getString("nome_itens"));
            Sub.setIdCategoria(Rs.getInt("id_categoria"));
            Sub.setIdSubCategoria(Rs.getInt("id_sub_categoria"));
            Sub.setIdItensSubcategoria(Rs.getInt("id_itens_subcategoria"));

            lst.add(Sub);
        }

        return lst;
    }

    public static ArrayList<ItensSubcategoriaEntidade> getIntensSubCategoriasComposto(String cat, String sub) throws SQLException {
        ArrayList<ItensSubcategoriaEntidade> lst = new ArrayList<ItensSubcategoriaEntidade>();
        ItensSubcategoriaEntidade Sub = null;

        String Query = "SELECT * FROM itens_subcategoria where id_categoria = ? and id_sub_categoria = ? order by id_itens_subcategoria ASC;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, cat);
        pst.setString(2, sub);
        ResultSet Rs = pst.executeQuery();
        Sub = new ItensSubcategoriaEntidade();
            Sub.setNomeItens("item");
            Sub.setIdCategoria(0);
            Sub.setIdSubCategoria(0);
            Sub.setIdItensSubcategoria(0);

            lst.add(Sub);
        while (Rs.next()) {
            Sub = new ItensSubcategoriaEntidade();
            Sub.setNomeItens(Rs.getString("nome_itens"));
            Sub.setIdCategoria(Rs.getInt("id_categoria"));
            Sub.setIdSubCategoria(Rs.getInt("id_sub_categoria"));
            Sub.setIdItensSubcategoria(Rs.getInt("id_itens_subcategoria"));

            lst.add(Sub);
        }

        return lst;
    }
    


}
