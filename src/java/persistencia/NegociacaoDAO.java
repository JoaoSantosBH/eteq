/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.NegociacaoEntidade;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaosantos
 */
public class NegociacaoDAO {

    public static int criarNegociacao(NegociacaoEntidade n) throws SQLException {
        int idNegociacao = 0;
        String Query = "INSERT INTO negociacao(id_usuario_tem,id_usuario_quer,id_produto_tem,id_produto_quer,status, data_inicio)\n"
                + "values(?,?,?,?,?,?);";
        Connection con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, n.getIdUsuarioTem());
        pst.setInt(2, n.getIdUsuarioQuer());
        pst.setInt(3, n.getIdProdutoTem());
        pst.setInt(4, n.getIdProdutoQuer());
        pst.setShort(5, n.getStatus());
        pst.setString(6, n.getDataInicio());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            n.setIdNegociacao(rs.getInt(1));
        }

        idNegociacao = n.getIdNegociacao();
        return idNegociacao;

    }

    public static NegociacaoEntidade selecionarNegociacaoPorId(String idNegociacao) throws SQLException {
        NegociacaoEntidade n = new NegociacaoEntidade();
        String Query = "SELECT * FROM negociacao where id_negociacao = " + idNegociacao + ";";
        ResultSet rs = BaseDados.getInstancia().executeQuery(Query);
        if (rs.next()) {
            n.setIdNegociacao(rs.getInt("id_negociacao"));
            n.setIdUsuarioTem(rs.getInt("id_usuario_tem"));
            n.setIdUsuarioQuer(rs.getInt("id_usuario_quer"));
            n.setIdProdutoTem(rs.getInt("id_produto_tem"));
            n.setIdProdutoQuer(rs.getInt("id_produto_quer"));
            n.setDataInicio(rs.getString("data_inicio"));

        }

        return n;
    }

    public static void excluirNegociacao(String id) throws SQLException {

        String Query = "DELETE FROM negociacao WHERE id_negociacao= ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        pst.executeUpdate();

    }

    public static void desativarNegociacao(String id) throws SQLException {

        String Query = "UPDATE negociacao SET status = 0 where id_negociacao =?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        pst.executeUpdate();

    }

    public static Boolean verificarNegociacaoComPatrimonioEspecifico(String idPatrm) throws SQLException {
        Boolean tem = false;
        String Query = "SELECT * FROM negociacao where status =1 AND (id_produto_tem  = ? or id_produto_quer = ? )";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idPatrm);
        pst.setString(2, idPatrm);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            tem =true;
        }
        
        return tem;
    }

    public static int selecionarIdNegociacaoPeloIdPatrimonioEnvolvido(String idPatrimonio) throws SQLException {
        int id =0;
        String Query = "SELECT id_negociacao FROM negociacao where id_produto_tem = ? or id_produto_quer = ? and status =1";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idPatrimonio);
        pst.setString(2, idPatrimonio);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            id = rs.getInt("id_negociacao");
        }
        
        
        return id;
    }

    public static List<NegociacaoEntidade> pegarListaDeNegociacoesAtivas() throws SQLException{
        List<NegociacaoEntidade> lst = new ArrayList<NegociacaoEntidade>();
        String Query = "SELECT * FROM negociacao where status = 1 order by id_negociacao;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        
        while(rs.next()){
            NegociacaoEntidade n = new NegociacaoEntidade();
            n.setIdNegociacao(rs.getInt("id_negociacao"));
            n.setIdUsuarioTem(rs.getInt("id_usuario_tem"));
            n.setIdUsuarioQuer(rs.getInt("id_usuario_quer"));
            n.setIdProdutoTem(rs.getInt("id_produto_tem"));
            n.setIdProdutoQuer(rs.getInt("id_produto_quer"));
            n.setStatus(rs.getShort("status"));
            n.setDataInicio(rs.getString("data_inicio"));
            
            lst.add(n);
        }
        
        return lst;
    }

    public static int pegarAvisoDeNegociacaoInativa(Integer idNegocio) throws SQLException{
        int aviso =0;
        String Query = "SELECT avisado FROM negociacao WHERE id_negociacao = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, idNegocio);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            aviso = rs.getInt(1);
        }
        return aviso;
    }

    public static void alterarAvisoInatividade(int numeroAviso, String idNeg) throws SQLException{
        
        String Query = "UPDATE negociacao SET avisado = ? WHERE id_negociacao = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, numeroAviso);
        pst.setString(2, idNeg);
        pst.executeUpdate();
    }
    
    
        public static Boolean verificarSeEuNaoSouProponente(String idNegociacao, int idUsuario) throws SQLException{
        Boolean resp = false;
        String Query = "SELECT id_usuario_tem FROM negociacao WHERE id_negociacao = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idNegociacao);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            int idUsuTem = rs.getInt(1);
            if(idUsuTem == idUsuario){
                resp = true;
            }
        }
        
        return resp;
    }
    
}
