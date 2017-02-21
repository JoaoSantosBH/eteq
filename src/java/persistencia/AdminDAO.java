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
public class AdminDAO {

    public static String pegarEmailAdmin() throws SQLException {
        String email = "";
        String Query = "SELECT email from admin where id = 1";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        if (rs.next()){
            email = rs.getString("email");
        }
        return email;
    }

    public static String pegarNomeAdmin() throws SQLException {
        String nome = "";
        String Query = "SELECT nome FROM admin WHERE id =1";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    nome = rs.getString("nome");
                }
        
        
        return nome;
    }

    
        public static String pegarEmailFaleConosco() throws SQLException {
        String email = "";
        String Query = "SELECT email from admin where id = 2";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        if (rs.next()){
            email = rs.getString("email");
        }
        return email;
    }

    public static String pegarNomeFaleConosco() throws SQLException {
        String nome = "";
        String Query = "SELECT nome FROM admin WHERE id =2";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    nome = rs.getString("nome");
                }
        
        
        return nome;
    }
}
