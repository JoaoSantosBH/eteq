/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import entidade.TbEstadosEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joaosantos
 */
public class TbEstadosDAO {
    
    
    public static ArrayList<TbEstadosEntidade> pegarEstados() throws SQLException {
        ArrayList<TbEstadosEntidade> lst = new ArrayList<TbEstadosEntidade>();
        String Query = "SELECT * FROM tb_estados order by id asc;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            TbEstadosEntidade t = new TbEstadosEntidade();
            t.setId(rs.getInt("id"));
            t.setNome(rs.getString("nome"));
            t.setUf(rs.getString("uf"));
            lst.add(t);        
        }
        return lst;
    }
    
    
}
