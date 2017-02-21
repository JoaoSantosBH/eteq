/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.CategoriaEntidade;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author joaomarcelo
 */
public class CategoriaDAO {

    public static ArrayList<CategoriaEntidade> getCategorias() throws SQLException {
        ArrayList<CategoriaEntidade> lst = new ArrayList<CategoriaEntidade>();
        CategoriaEntidade C = null;

        String Query = "SELECT * FROM categoria order by id_categoria ASC;";
        ResultSet Rs = BaseDados.getInstancia().executeQuery(Query);
        while (Rs.next()) {
            C = new CategoriaEntidade();
            C.setTitulo(Rs.getString("titulo"));
            C.setIdCategoria(Rs.getInt("id_categoria"));
            lst.add(C);
        }

        return lst;
    }
}
