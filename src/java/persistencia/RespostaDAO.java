/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import entidade.RespostaEntidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.EnviarEmail;

/**
 *
 * @author joaosantos
 */
public class RespostaDAO {

    public static void alterarRespostaParaLida(String idResposta) throws SQLException {
        
        String Query = "UPDATE resposta set lida = 1 where id_resposta = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idResposta);
        pst.executeUpdate();
        
    }
    
    public static boolean existeRespostaNaoLida(String idUsuarioDestinatario) throws SQLException{
        boolean resp = false;
        String Query = "SELECT * FROM resposta where id_usuario_destinatario = ? and lida = 0";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuarioDestinatario);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            resp = true;
        }
        
        return resp;
    }

    public static Boolean criarResposta(RespostaEntidade r) throws SQLException {
        Boolean resp = false;
        String Query = "INSERT INTO resposta (id_forum,texto_resposta,id_usuario_remetente,id_usuario_destinatario, lida) VALUES (?,?,?,?,?)";
        Connection con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, r.getIdForum());
        pst.setString(2, r.getTextoResposta());
        pst.setInt(3, r.getIdUsuarioRemetente());
        pst.setInt(4, r.getIdUsuarioDestinatario());
        pst.setShort(5, r.getLida());
        pst.executeUpdate();
        
        ResultSet rs = pst.getGeneratedKeys();
        if(rs.next()){
            resp = true;
            r.setIdResposta(rs.getInt(1));
        }
        
        return resp;
    }

    public static List<RespostaEntidade> pegarRespostasDoForum(String idForum)throws SQLException {
        RespostaEntidade r = null;
        List<RespostaEntidade> lst = new ArrayList<RespostaEntidade>();
        String Query = "SELECT * FROM resposta where id_forum = ? order by id_resposta desc; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idForum);
        
        ResultSet rs = pst.executeQuery();
        while(rs.next()){   //pega somente a ultima resposta
            r= new RespostaEntidade();
            r.setIdResposta(rs.getInt("id_resposta"));
            r.setIdForum(rs.getInt("id_forum"));
            r.setTextoResposta(rs.getString("texto_resposta"));
            r.setDataResposta(rs.getDate("data_resposta"));
            r.setIdUsuarioRemetente(rs.getInt("id_usuario_remetente"));
            r.setIdUsuarioDestinatario(rs.getInt("id_usuario_destinatario"));
            r.setLida(rs.getShort("lida"));
            lst.add(r);
        }
        
        return lst;
    }
    
        public static List<RespostaEntidade> pegarUltimaRespostaDoForumParaPegarData(String idForum)throws SQLException {
        RespostaEntidade r = null;
        List<RespostaEntidade> lst = new ArrayList<RespostaEntidade>();
        String Query = "SELECT * FROM resposta where id_forum = ? order by id_resposta desc; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idForum);
        
        ResultSet rs = pst.executeQuery();
        if(rs.next()){   //pega somente a ultima resposta
            r= new RespostaEntidade();
            r.setIdResposta(rs.getInt("id_resposta"));
            r.setIdForum(rs.getInt("id_forum"));
            r.setTextoResposta(rs.getString("texto_resposta"));
            r.setDataResposta(rs.getDate("data_resposta"));
            r.setIdUsuarioRemetente(rs.getInt("id_usuario_remetente"));
            r.setIdUsuarioDestinatario(rs.getInt("id_usuario_destinatario"));
            r.setLida(rs.getShort("lida"));
            lst.add(r);
        }
        
        return lst;
    }

    public static void apagarRespostasReferentesoForumInativo(int idForum) throws SQLException{
        
        String Query = "DELETE FROM resposta where id_forum = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, idForum);
        pst.executeUpdate();
        
    }
    
}
