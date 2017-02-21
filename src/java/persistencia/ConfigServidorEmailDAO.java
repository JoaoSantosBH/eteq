/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.ConfigServidorEmailEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joaosantos
 */
public class ConfigServidorEmailDAO {

    public static ConfigServidorEmailEntidade obterConfiguracaoServidorEmail(String id) throws SQLException {

        ConfigServidorEmailEntidade conf = new ConfigServidorEmailEntidade();
        String Query = "SELECT * FROM config_servidor_email where id_config =" + id + ";";
        
        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
        
        ResultSet Rs = Pst.executeQuery();
        
        if (Rs.next()){
            conf.setHost(Rs.getString("host"));
            conf.setPort(Rs.getString("port"));
            conf.setAuth(Rs.getString("auth"));
            conf.setStarttls(Rs.getString("starttls"));
            conf.setUserEmail(Rs.getString("user_email"));
            conf.setFromNameEmail(Rs.getString("from_name_email"));
            conf.setPassword(Rs.getString("password"));
            conf.setCharset(Rs.getString("charset"));
            conf.setSubject(Rs.getString("subject"));
                   
            
        }
        return conf;
    }

}
