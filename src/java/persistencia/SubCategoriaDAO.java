/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.PatrimonioEntidade;
import entidade.CategoriaEntidade;
import entidade.SubcategoriaEntidade;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author joaomarcelo
 */
public class SubCategoriaDAO {

    public static ArrayList<SubcategoriaEntidade> getSubCategoriasMulheres() throws SQLException {
        ArrayList<SubcategoriaEntidade> lst = new ArrayList<SubcategoriaEntidade>();
        SubcategoriaEntidade Sub = null;

        String Query = "SELECT titulo_subcategoria FROM eutenho.subcategoria where id_categoria = 1 order by id_subcategoria ASC;";
        ResultSet Rs = BaseDados.getInstancia().executeQuery(Query);
        while (Rs.next()) {
            Sub = new SubcategoriaEntidade();
            Sub.setTituloSubcategoria(Rs.getString("titulo_subcategoria"));
            lst.add(Sub);
        }

        return lst;
    }

    public static ArrayList<SubcategoriaEntidade> getSubCategoriasHomens() throws SQLException {

        ArrayList<SubcategoriaEntidade> lst = new ArrayList<SubcategoriaEntidade>();
        SubcategoriaEntidade Sub = null;

        String Query = "SELECT titulo_subcategoria FROM eutenho.subcategoria where id_categoria = 2 order by id_subcategoria ASC;";
        ResultSet Rs = BaseDados.getInstancia().executeQuery(Query);
        while (Rs.next()) {
            Sub = new SubcategoriaEntidade();
            Sub.setTituloSubcategoria(Rs.getString("titulo_subcategoria"));
            lst.add(Sub);
        }

        return lst;
    }

    public static ArrayList<SubcategoriaEntidade> getSubCategoriasCriancas() throws SQLException {

        ArrayList<SubcategoriaEntidade> lst = new ArrayList<SubcategoriaEntidade>();
        SubcategoriaEntidade Sub = null;

        String Query = "SELECT * FROM eutenho.subcategoria where id_categoria = 3 order by id_subcategoria ASC;";
        ResultSet Rs = BaseDados.getInstancia().executeQuery(Query);
        while (Rs.next()) {
            Sub = new SubcategoriaEntidade();
            Sub.setIdCategoria(Rs.getInt("id_categoria"));
            Sub.setIdSubcategoria(Rs.getInt("id_subcategoria"));
            Sub.setTituloSubcategoria(Rs.getString("titulo_subcategoria"));
            lst.add(Sub);
        }

        return lst;
    }

    public static ArrayList<SubcategoriaEntidade> getSubCategoriasCasa() throws SQLException {

        ArrayList<SubcategoriaEntidade> lst = new ArrayList<SubcategoriaEntidade>();
        SubcategoriaEntidade Sub = null;

        String Query = "SELECT titulo_subcategoria FROM eutenho.subcategoria where id_categoria = 4 order by id_subcategoria ASC;";
        ResultSet Rs = BaseDados.getInstancia().executeQuery(Query);
        while (Rs.next()) {
            Sub = new SubcategoriaEntidade();
            Sub.setTituloSubcategoria(Rs.getString("titulo_subcategoria"));
            lst.add(Sub);
        }

        return lst;
    }

    public static ArrayList<SubcategoriaEntidade> getSubCategoriasInfoEletro() throws SQLException {

        ArrayList<SubcategoriaEntidade> lst = new ArrayList<SubcategoriaEntidade>();
        SubcategoriaEntidade Sub = null;

        String Query = "SELECT titulo_subcategoria FROM eutenho.subcategoria where id_categoria = 5 order by id_subcategoria ASC;";
        ResultSet Rs = BaseDados.getInstancia().executeQuery(Query);
        while (Rs.next()) {
            Sub = new SubcategoriaEntidade();
            Sub.setTituloSubcategoria(Rs.getString("titulo_subcategoria"));
            lst.add(Sub);
        }

        return lst;
    }

    public static ArrayList<SubcategoriaEntidade> getSubCategoriasOutros() throws SQLException {

        ArrayList<SubcategoriaEntidade> lst = new ArrayList<SubcategoriaEntidade>();
        SubcategoriaEntidade Sub = null;

        String Query = "SELECT titulo_subcategoria FROM eutenho.subcategoria where id_categoria = 6 order by id_subcategoria ASC;";
        ResultSet Rs = BaseDados.getInstancia().executeQuery(Query);
        while (Rs.next()) {
            Sub = new SubcategoriaEntidade();
            Sub.setTituloSubcategoria(Rs.getString("titulo_subcategoria"));
            lst.add(Sub);
        }

        return lst;
    }

    public static ArrayList<SubcategoriaEntidade> getSubCategorias() throws SQLException {

        ArrayList<SubcategoriaEntidade> lst = new ArrayList<SubcategoriaEntidade>();
        SubcategoriaEntidade C = null;

        String Query = "SELECT * FROM subcategoria order by id_subcategoria ASC;";
        ResultSet Rs = BaseDados.getInstancia().executeQuery(Query);
        while (Rs.next()) {
            C = new SubcategoriaEntidade();
            C.setTituloSubcategoria(Rs.getString("titulo_subcategoria"));
            C.setIdSubcategoria(Rs.getInt("id_subcategoria"));
            lst.add(C);
        }

        return lst;
    }

    public static ArrayList<SubcategoriaEntidade> pegarSubCategoriasPeloId(String idCat) throws SQLException {
        ArrayList<SubcategoriaEntidade> lst = new ArrayList<SubcategoriaEntidade>();
        SubcategoriaEntidade su = null;
        String Query = "SELECT * FROM subcategoria where id_categoria = ? order by id_subcategoria;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idCat);
        ResultSet rs = pst.executeQuery();
        su = new SubcategoriaEntidade();
        su.setIdCategoria(0);
        su.setIdSubcategoria(0);
        su.setTituloSubcategoria("subcategoria");
        lst.add(su);
        while (rs.next()) {
            su = new SubcategoriaEntidade();
            su.setIdCategoria(rs.getInt("id_categoria"));
            su.setIdSubcategoria(rs.getInt("id_subcategoria"));
            su.setTituloSubcategoria(rs.getString("titulo_subcategoria"));
            lst.add(su);
        }

        return lst;
    }

}
