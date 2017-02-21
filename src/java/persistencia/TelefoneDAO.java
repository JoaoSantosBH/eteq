/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import entidade.TelefoneEntidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joaosantos
 */
public class TelefoneDAO {
    
    public static void inserirTelefone(TelefoneEntidade t) throws SQLException{
        String Query = "INSERT INTO telefone(id_usuario, ddd, fixo, celular) VALUES(?,?,?,?);";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, t.getIdUsuario());
        pst.setInt(2, t.getDdd());
        pst.setString(3, t.getFixo());
        pst.setString(4, t.getCelular());
        pst.executeUpdate();
        
        
    }

    public static void editarTelefones(TelefoneEntidade tel) throws SQLException{
        String Query = "UPDATE telefone SET  ddd=?, fixo=?, celular=? where id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        
        pst.setInt(1, tel.getDdd());
        pst.setString(2, tel.getFixo());
        pst.setString(3, tel.getCelular());
        pst.setInt(4, tel.getIdUsuario());
        pst.executeUpdate();
        
    }
    
}
