/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import entidade.HistoricoEntidade;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaosantos
 */
public class HistoricoDAO {
    
    public static void  criarRegistroHistorico(HistoricoEntidade h) throws SQLException{
        
        String Query = "INSERT INTO historico (id_usuario_tem, id_usuario_quis, id_patrimonio_tem, id_patrimonio_quis, status, concluido) values (?,?,?,?,?,?)";
        Connection con = BaseDados.getInstancia().getConnection();
        PreparedStatement pst = con.prepareStatement(Query , PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, h.getIdUsuarioTem());
        pst.setInt(2, h.getIdUsuarioQuis());
        pst.setInt(3, h.getIdPatrimonioTem());
        pst.setInt(4, h.getIdPatrimonioQuis());
        pst.setString(5, h.getStatus());
        pst.setShort(6, h.getConcluido());
        
        pst.executeUpdate();
        
        ResultSet rs = pst.getGeneratedKeys();
        if(rs.next()){
            h.setIdHistorico(rs.getInt(1));
        }
        
    }

    public static ArrayList<HistoricoEntidade> visualizarHistoricoDeAnuncios(String idUsuario) throws SQLException {
        
        ArrayList<HistoricoEntidade> lst = new ArrayList<HistoricoEntidade>();
        String Query = "SELECT * FROM historico where id_usuario_tem = ? ORDER BY id_historico DESC";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {            
            
            HistoricoEntidade h = new HistoricoEntidade();
            h.setIdUsuarioTem(rs.getInt("id_usuario_tem"));
            h.setIdUsuarioQuis(rs.getInt("id_usuario_quis"));
            h.setIdPatrimonioTem(rs.getInt("id_patrimonio_tem"));
            h.setIdPatrimonioQuis(rs.getInt("id_patrimonio_quis"));
            h.setDataDesativacao(rs.getDate("data_desativacao"));
            h.setStatus(rs.getString("status"));
            h.setConcluido(rs.getShort("concluido"));
            
            
            lst.add(h);
        }
        
        
        return lst;
        
    }

    public static int pegarQuantidadeDePatrimoniosNegociados() throws SQLException{
        int total=0;
        String Query ="SELECT COUNT(*) FROM historico;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            total = rs.getInt(1);
        }
        
        return total;
    }

    public static int pegarQuantidadeDePatrimoniosConcluidos() throws SQLException {
        int total =0;
        String Query = "SELECT COUNT(*) FROM historico WHERE concluido = 1;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        if (rs.next()){
            total = rs.getInt(1);
        }
        
        return total;
    }

    public static int pegarQuantidadeDePatrimoniosExcluidos() throws SQLException{
        int total=0;
        String Query = "SELECT count(*) FROM historico where status = 'Excluido pelo usuario'; ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            total = rs.getInt(1);
        }
        return total;
    }

    public static int pegarQuantidadeDePatrimoniosExpirados() throws SQLException{
        int total =0;
        String Query = "SELECT COUNT(*) FROM historico WHERE status like '%Item expirado%';";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            total = rs.getInt(1);
        }
        
        return total;
    }

    public static int pegarQuantidadeDePatrimoniosCancelados() throws SQLException{
        int total = 0;
        String Query = "SELECT COUNT(*) FROM historico WHERE status like '%Cancelada%';";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            total = rs.getInt(1);
        }
        
        return total;
        
    }

    public static int pegarQuantidadeDeDoacoesCancelados() throws SQLException {
        int total=0;
        String Query = "SELECT COUNT(*) FROM historico WHERE status like '%Doacao Cancelada%';";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            total = rs.getInt(1);
        }
        
        return total;
    }

    public static int pegarQuantidadeDeTrocasCancelados() throws SQLException{
        
        int total=0;
        String Query = "SELECT COUNT(*) FROM historico WHERE status like '%Troca Cancelada%';";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            total = rs.getInt(1);
        }
        
        return total;
    }

    public static List<HistoricoEntidade> pegarTodosHistorico() throws SQLException{
        List<HistoricoEntidade> lst = new ArrayList<HistoricoEntidade>();
        HistoricoEntidade h = null;
        String Query = "SELECT * FROM historico";  
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            h = new HistoricoEntidade();
            h.setIdHistorico(rs.getInt("id_historico"));
            h.setConcluido(rs.getShort("concluido"));
            h.setStatus(rs.getString("status"));
            lst.add(h);
        }
        return lst;
    }
    
}
