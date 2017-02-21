/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import entidade.CadastroPatrimonioEntidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joaosantos
 */
public class CadastroPatrimonioDAO {

    public static CadastroPatrimonioEntidade pegarCadastroPorIdPatrimonio(String idPatrimonio) throws SQLException {
        CadastroPatrimonioEntidade c = new CadastroPatrimonioEntidade();
        String Query = "SELECT * FROM cadastro_patrimonio where id_patrimonio = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idPatrimonio);
        ResultSet rs = pst.executeQuery();
        
        if(rs.next()){
            c.setIdCadastro(rs.getInt("id_cadastro"));
            c.setIdUsuario(rs.getInt("id_usuario"));
            c.setIdPatrimonio(rs.getInt("id_patrimonio"));
            c.setDataCadastro(rs.getString("data_cadastro"));
        }
        
        return c;
    }

    public static void renovarCadastroPatrimonio(CadastroPatrimonioEntidade cad) throws SQLException {
        String Query = "UPDATE cadastro_patrimonio SET data_cadastro = ? where id_cadastro=?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, cad.getDataCadastro());
        pst.setInt(2, cad.getIdCadastro());
        pst.executeUpdate();
        
    }

    public static void criarCadastroPatrimonio(CadastroPatrimonioEntidade cad) throws SQLException{
        String Query = "INSERT INTO cadastro_patrimonio (id_usuario, id_patrimonio, data_cadastro) VALUES (?,?,?);";
        Connection con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, cad.getIdUsuario());
        pst.setInt(2, cad.getIdPatrimonio());
        pst.setString(3, cad.getDataCadastro());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if(rs.next()){
            cad.setIdCadastro(rs.getInt(1));
        }
        
    }

    public static ArrayList<CadastroPatrimonioEntidade> pegarListaDeTodosCadastros() throws SQLException{

        CadastroPatrimonioEntidade cad = null;
        ArrayList<CadastroPatrimonioEntidade> lst = new ArrayList<CadastroPatrimonioEntidade>();
        String Query = "SELECT * FROM cadastro_patrimonio";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            cad = new CadastroPatrimonioEntidade();
            cad.setIdCadastro(rs.getInt("id_cadastro"));
            cad.setIdUsuario(rs.getInt("id_usuario"));
            cad.setIdPatrimonio(rs.getInt("id_patrimonio"));
            cad.setDataCadastro(rs.getString("data_cadastro"));
            lst.add(cad);
        }
        return lst;
    }

    public static void excluirCadastro(String idPatrimonio, String idUsuario) throws SQLException{
        String Query = "DELETE from cadastro_patrimonio where id_usuario = ? and id_patrimonio = ? ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuario);
        pst.setString(2, idPatrimonio);
        pst.executeUpdate();
  
      }
    
}
