/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.SQLException;
import javax.persistence.PersistenceException;
import entidade.ForumEntidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.EnviarEmail;

/**
 *
 * @author joaosantos
 */
public class ForumDAO {

    public static void criarForum(ForumEntidade f) throws SQLException, PersistenceException {

        String Query = "INSERT INTO forum(assunto,mensagem,id_usuario_remetente, id_usuario_destinatario, id_negociacao, ativo) values(?,?,?,?,?,?)";
        Connection con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setString(1, f.getAssunto());
        pst.setString(2, f.getMensagem());
        pst.setInt(3, f.getIdUsuarioRemetente());
        pst.setInt(4, f.getIdUsuarioDestinatario());
        pst.setInt(5, f.getIdNegociacao());
        pst.setShort(6, f.getAtivo());
        pst.executeUpdate();

        ResultSet Rs = pst.getGeneratedKeys();
        if (Rs.next()) {
            f.setIdForum(Rs.getInt(1));
        }

    }

    public static ArrayList<ForumEntidade> listarMeusForuns(String id_usuario) throws SQLException {
        ArrayList<ForumEntidade> lst = new ArrayList<ForumEntidade>();

        String Query = "SELECT * FROM forum where id_usuario_destinatario = " + id_usuario + " or id_usuario_remetente = " + id_usuario + ";";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            ForumEntidade ms = new ForumEntidade();
            ms.setIdForum(rs.getInt("id_forum"));
            ms.setAssunto(rs.getString("assunto"));
            ms.setMensagem(rs.getString("mensagem"));
            ms.setDataMensagem(rs.getTime("data_mensagem"));
            ms.setIdUsuarioRemetente(rs.getInt("id_usuario_remetente"));
            ms.setIdUsuarioDestinatario(rs.getInt("id_usuario_destinatario"));


            lst.add(ms);
        }
        return lst;
    }

    public static ForumEntidade getForum(String id) throws SQLException {
        ForumEntidade ms = new ForumEntidade();
        String Query = "SELECT * FROM forum where id_forum = " + id + ";";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            ms.setIdForum(rs.getInt("id_forum"));
            ms.setAssunto(rs.getString("assunto"));
            ms.setMensagem(rs.getString("mensagem"));
            ms.setDataMensagem(rs.getTime("data_mensagem"));
            ms.setIdUsuarioRemetente(rs.getInt("id_usuario_remetente"));
            ms.setIdUsuarioDestinatario(rs.getInt("id_usuario_destinatario"));
            ms.setIdNegociacao(rs.getInt("id_negociacao"));

        }
        return ms;
    }

    public static int getQuantidadeTotalForum(int id) throws SQLException {
        int total = 0;

        String Query = "SELECT COUNT(*) FROM forum where ativo in (select ativo from forum where id_usuario_destinatario =" 
                + id + " or id_usuario_remetente=" + id + ") and ativo =1;"; 
        
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            total = rs.getInt(1);
            

        }

        return total;
    }

    public static ArrayList<ForumEntidade> getForumPaginados(int PaginaAtual, int QuantidadeRegistros, String id_usuario) throws SQLException {

        ArrayList<ForumEntidade> lst = new ArrayList<ForumEntidade>();
        String Query = "SELECT * FROM forum where ativo =1 AND (id_usuario_destinatario = " + id_usuario + " or id_usuario_remetente=" + id_usuario + ")";
        Query += " ORDER BY id_forum DESC LIMIT " + (PaginaAtual * QuantidadeRegistros) + ", " + QuantidadeRegistros +";" ;


                
        
        ResultSet rs = BaseDados.getInstancia().executeQuery(Query);
        while (rs.next()) {
            ForumEntidade ms = new ForumEntidade();
            ms.setIdForum(rs.getInt("id_forum"));
            ms.setAssunto(rs.getString("assunto"));
            ms.setMensagem(rs.getString("mensagem"));
            ms.setDataMensagem(rs.getTime("data_mensagem"));
            ms.setIdUsuarioRemetente(rs.getInt("id_usuario_remetente"));
            ms.setIdUsuarioDestinatario(rs.getInt("id_usuario_destinatario"));
            ms.setIdNegociacao(rs.getInt("id_negociacao"));
            ms.setAtivo(rs.getShort("ativo"));

            lst.add(ms);
        }

        return lst;
    }

    public static void desativarForumPeloIdNegociacao(Integer id) throws SQLException {

        String Query = "UPDATE forum SET ativo = 0 where id_negociacao =?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, id);
        pst.executeUpdate();

    }
    
    public static Boolean verificarSeForumEstaAtivo(String idForum) throws SQLException{
        Boolean resp = null;
        String Query = "SELECT ativo FROM forum where id_forum = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idForum);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            resp = rs.getBoolean("ativo");
        }
        return resp;
    }

    public static int pegarIdForumPeloIdNegociacao(String idNegociacao) throws SQLException{
        int idForum =0;
        String Query = "SELECT id_forum FROM forum where id_negociacao=?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idNegociacao);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            idForum = rs.getInt("id_forum");
        }
        return idForum;
    }

    public static Boolean verificarSeForumEstaAtivoPeloIdNegociacao(String idForum) throws SQLException{
        Boolean resp = false;
        String Query = "SELECT ativo FROM forum where id_negociacao = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idForum);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            resp = rs.getBoolean("ativo");
        }
        return resp;
    }

    public static String pegarTituloForum(Integer idForum) throws SQLException{
        String titulo="";
        String Query = "SELECT assunto FROM forum WHERE id_forum =?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, idForum);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            titulo = rs.getString("assunto");
        }

        return titulo;
    }
    
    public static Boolean verificarSeForumJaExiste(String idPatrimonio) throws SQLException{
        Boolean existe = false;
        String Query = "SELECT * FROM forum WHERE id_negociacao = ?; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idPatrimonio);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            existe = true;
        }
        return existe;
    }


}
