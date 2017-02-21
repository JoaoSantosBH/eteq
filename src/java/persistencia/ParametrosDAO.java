/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joaosantos
 */
public class ParametrosDAO {
    
    public static void alterarParametros(String idParametro, String valor) throws SQLException{
        
        String Query = "UPDATE parametros set valor = ? where id_parametro = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, valor);
        pst.setString(2, idParametro);
        pst.executeUpdate();
       
    }
    
    public static int pegarValorParametro (String idParametro) throws SQLException{
        int valor = 0;
        String Query = "SELECT * FROM parametros where id_parametro = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idParametro);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            valor= rs.getInt("valor");
        }
        return valor;
    }
    
}
