/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.PatrimonioEntidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author joaosantos
 */
public class PatrimonioDAO {

    public static void inserir(PatrimonioEntidade Novo) throws SQLException, PersistenciaException {
//        Short atributoInicial = 0;

        String Query = "INSERT INTO patrimonio(foto,titulo,descricao,estado_conservacao,id_categoria,id_subcategoria,id_itens_subcat, palavra_chave,id_usuario,tipo)\n"
                + "values(?,?,?,?,?,?,?,?,?,?);";
        Connection Con = BaseDados.getInstancia().getConnection();
        PreparedStatement Pst = Con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);

        Pst.setString(1, Novo.getFoto());
        Pst.setString(2, Novo.getTitulo());
        Pst.setString(3, Novo.getDescricao());
        Pst.setString(4, Novo.getEstadoConservacao());
        Pst.setInt(5, Novo.getIdCategoria());
        Pst.setInt(6, Novo.getIdSubcategoria());
        Pst.setInt(7, Novo.getIdItensSubcat());
        Pst.setString(8, Novo.getPalavraChave());
        Pst.setInt(9, Novo.getIdUsuario());
        Pst.setShort(10, Novo.getTipo());

        Pst.executeUpdate();

        ResultSet Rs = Pst.getGeneratedKeys();
        if (Rs.next()) {
            Novo.setIdPatrimonio(Rs.getInt(1));
        }
    }

    public static void alterarStatusReservaPatrimonioReservado(String id) throws SQLException {

        String Query = "UPDATE patrimonio SET reservado = 1 WHERE id_patrimonio = ?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        pst.executeUpdate();

    }

    public static void alterarStatusPatrimonioParaExpirado(String id) throws SQLException {

        String Query = "UPDATE patrimonio SET expirado = 1 WHERE id_patrimonio = ?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        pst.executeUpdate();

    }
    public static void alterarStatusPatrimonioParaExcluido(String id) throws SQLException {

        String Query = "UPDATE patrimonio SET excluido = 1 WHERE id_patrimonio = ?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        pst.executeUpdate();

    }
    public static void alterarStatusPatrimonioParaNaoExpirado(String idPatrimonio) throws SQLException {

        String Query = "UPDATE patrimonio SET expirado = 0 where id_patrimonio = ?";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idPatrimonio);
        pst.executeUpdate();

    }

    public static void alterarStatusReservaPatrimonioNaoReservado(String id) throws SQLException {

        String Query = "UPDATE patrimonio SET reservado = 0 WHERE id_patrimonio = ?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        pst.executeUpdate();

    }

    public static void alterarStatusDoPartimonioParaAvisado(String idPatr, int count) throws SQLException {

        String Query = "UPDATE patrimonio SET avisado =? where id_patrimonio = ?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, count);
        pst.setString(2, idPatr);
        pst.executeUpdate();

    }

    public static void alterarStatusDoPatrimonioParaNaoAvisado(String idPatr) throws SQLException {
        String Query = "UPDATE patrimonio SET avisado =? where id_patrimonio = ?;";

        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setShort(1, Short.parseShort("0"));
        pst.setString(2, idPatr);
        pst.executeUpdate();

    }

    public static ArrayList<PatrimonioEntidade> procurarPatrimonios(String palavraChave) throws SQLException, PersistenceException {

        ArrayList<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        String Query = "SELECT * FROM eutenho.patrimonio where reservado =0 and expirado =0 and (palavra_chave like '%" + palavraChave + "%' or titulo like '%" + palavraChave + "%' or descricao like '%" + palavraChave + "%');";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            PatrimonioEntidade p = new PatrimonioEntidade();
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));

            lst.add(p);

        }

        return lst;
    }

    public static String pegarIdUsuarioPorIdPatrimonio(String idPatrimonioUsuarioTem) throws SQLException {
        PatrimonioEntidade p = new PatrimonioEntidade();
        String id = "";
        String Query = "SELECT id_usuario FROM patrimonio where id_patrimonio =' " + idPatrimonioUsuarioTem + "';";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            p.setIdUsuario(rs.getInt("id_usuario"));
            id = p.getIdUsuario().toString();
        }

        return id;
    }

    public static ArrayList<PatrimonioEntidade> pegarTodosPatrimoniosPorIdUsuario(String idUsuarioQuer) throws SQLException, PersistenceException {//provavelmente nao utilizado
        ArrayList<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();;

        String Query = "SELECT * FROM patrimonio where id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuarioQuer);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            PatrimonioEntidade p = new PatrimonioEntidade();
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));

            lst.add(p);
        }

        return lst;
    }

    public static String pegarPatrimonioPorIdUsuario(String idUsuarioQuer) throws SQLException, PersistenceException {//provavelmente nao utilizado
        String produto = "";

        String Query = "SELECT titulo FROM patrimonio where id_usuario = ? and reservado =0 and aprovado =1 and expirado =0 ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuarioQuer);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            produto = rs.getString("titulo");
        }

        return produto;
    }

    public static ArrayList<PatrimonioEntidade> pegarTodosPatrimoniosPorIdUsuarioNaoExpirados(String idUsuarioQuer) throws SQLException, PersistenceException {
        ArrayList<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();;

        String Query = "SELECT * FROM patrimonio where id_usuario = ? and aprovado =1 and expirado=0 and excluido =0;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuarioQuer);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            PatrimonioEntidade p = new PatrimonioEntidade();
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));

            lst.add(p);
        }

        return lst;
    }

    public static ArrayList<PatrimonioEntidade> listarMeusPatrimoniosCadastrados(String idUsuario) throws SQLException {
        ArrayList<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        String Query = "SELECT * FROM patrimonio where id_usuario = ? AND excluido =0";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            PatrimonioEntidade p = new PatrimonioEntidade();
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));

            lst.add(p);
        }

        return lst;
    }

    public static Boolean usuarioTemPatrimonioCadastrado(String id) throws SQLException {
        Boolean tenho = false;
        String Query = "SELECT * FROM patrimonio where id_usuario = ? and reservado =0 and aprovado =1 and expirado =0 and tipo =1 and excluido =0";//reserva diferente 0
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            tenho = true;
        }

        return tenho;

    }

    public static PatrimonioEntidade pegarPatrimonioPorId(String id) throws SQLException {

        PatrimonioEntidade p = new PatrimonioEntidade();
        String Query = "SELECT * FROM patrimonio where id_patrimonio = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));

        }
        return p;

    }

    public static String pegarTituloDoPatrimonioPeloIdPatrimonio(String idPatrimonio) throws SQLException {
        String titulo = "";
        String Query = "SELECT titulo FROM patrimonio where id_patrimonio = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idPatrimonio);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            titulo = rs.getString("titulo");
        }
        return titulo;
    }

    public static PatrimonioEntidade pegarPatrimonioPeloId(String idPatr) throws SQLException {
        PatrimonioEntidade p = new PatrimonioEntidade();
        String Query = "SELECT * from patrimonio where id_patrimonio = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idPatr);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setIdItensSubcat(rs.getInt("id_itens_subcat"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));
            p.setExcluido(rs.getShort("excluido"));

        }
        return p;
    }

    public static List<PatrimonioEntidade> pegarItensPelaSubCategoria(String idCat, String idSubCat) throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        PatrimonioEntidade p = null;
        String Query = "SELECT * FROM eutenho.patrimonio where id_categoria = ? and id_subcategoria = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idCat);
        pst.setString(2, idSubCat);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            p = new PatrimonioEntidade();
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));
            lst.add(p);
        }
        return lst;
    }

    public static List<PatrimonioEntidade> pegarItensDaSubCategoria(String idCat, String idItensSubCat) throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();

        PatrimonioEntidade p = null;
        String Query = "SELECT * FROM eutenho.patrimonio where id_categoria = ? and id_itens_subcat = ? and reservado =0 and expirado =0;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idCat);
        pst.setString(2, idItensSubCat);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            p = new PatrimonioEntidade();
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));
            lst.add(p);

        }

        return lst;
    }

    public static List<PatrimonioEntidade> listarUltimosPatrimoniosCadastrados() throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        PatrimonioEntidade p = null;
        String Query = "SELECT * FROM patrimonio where excluido =0 order by id_patrimonio DESC";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p = new PatrimonioEntidade();
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));
            lst.add(p);
        }

        return lst;
    }

    public static int pegarAtributoAvisado(Integer idPatrimonio) throws SQLException {

        int numeroAviso = 0;
        String Query = "SELECT avisado FROM patrimonio WHERE id_patrimonio = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, idPatrimonio);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            numeroAviso = rs.getInt("avisado");
        }

        return numeroAviso;
    }

    public static void incrementarAviso(int numeroAviso, int idPat) throws SQLException {
        String Query = "UPDATE patrimonio SET avisado = ? where id_patrimonio = ? ";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, numeroAviso);
        pst.setInt(2, idPat);
        pst.executeUpdate();
    }

    public static List<PatrimonioEntidade> pegarListaPatrimoniosParaAprovacao() throws SQLException {
        PatrimonioEntidade p = null;
        ArrayList<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        String Query = "SELECT * FROM patrimonio where aprovado = 0 and excluido =0";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p = new PatrimonioEntidade();
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));
            lst.add(p);

        }

        return lst;
    }

    public static void aprovarPatrimonio(String idProd) throws SQLException {
        String Query = "UPDATE patrimonio SET aprovado = 1 where id_patrimonio = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idProd);
        pst.executeUpdate();

    }

    public static void excluirPatrimonio(String idProd) throws SQLException {

        String Query = " DELETE FROM patrimonio WHERE id_patrimonio = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idProd);
        pst.executeUpdate();

    }

    public static void editarPatrimonio(PatrimonioEntidade p) throws SQLException {

        String Query = "Update patrimonio set titulo=?, descricao=?, estado_conservacao=?, id_categoria=?, id_subcategoria=?, id_itens_subcat=?, palavra_chave=?, tipo=? where id_patrimonio =?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, p.getTitulo());
        pst.setString(2, p.getDescricao());
        pst.setString(3, p.getEstadoConservacao());
        pst.setString(4, p.getIdCategoria().toString());
        pst.setString(5, p.getIdSubcategoria().toString());
        pst.setString(6, p.getIdItensSubcat().toString());
        pst.setString(7, p.getPalavraChave());
        pst.setString(8, p.getTipo().toString());
        pst.setString(9, p.getIdPatrimonio().toString());
        pst.executeUpdate();

    }

    public static Boolean verificarSePatrimonioPossuiReserva(String idPatrimonio) throws SQLException {
        Boolean resp = false;
        String Query = "SELECT reservado FROM patrimonio WHERE id_patrimonio = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idPatrimonio);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            short valor = rs.getShort(1);
            if (valor == 1) {
                resp = true;
            }
        }

        return resp;
    }

    public static String pegarIdPatrimonioPeloNomePatrimonio(String produto) throws SQLException {
        Integer id;
        String idProd = "";
        String Query = "SELECT id_patrimonio FROM patrimonio WHERE titulo = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, produto);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id_patrimonio");
            idProd = id.toString();
        }
        return idProd;
    }

    public static String pegarFotoPatrimonioPeloIdPatrimonio(String idProduto) throws SQLException {
        String foto = "";
        String Query = "SELECT foto FROM patrimonio WHERE id_patrimonio = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idProduto);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            foto = rs.getString("foto");
        }
        return foto;
    }

    public static List<PatrimonioEntidade> listarDoacoes() throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        PatrimonioEntidade p = null;
        String Query = "SELECT * FROM patrimonio where tipo=0 AND expirado =0 AND aprovado =1;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p = new PatrimonioEntidade();
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));
            lst.add(p);
        }

        return lst;
    }
    
        public static List<PatrimonioEntidade> listarTrocas() throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        PatrimonioEntidade p = null;
        String Query = "SELECT * FROM patrimonio where tipo=1 AND expirado =0 AND aprovado =1 AND excluido =0 ;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            p = new PatrimonioEntidade();
            p.setIdPatrimonio(rs.getInt("id_patrimonio"));
            p.setIdUsuario(rs.getInt("id_usuario"));
            p.setFoto(rs.getString("foto"));
            p.setTitulo(rs.getString("titulo"));
            p.setDescricao(rs.getString("descricao"));
            p.setEstadoConservacao(rs.getString("estado_conservacao"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdSubcategoria(rs.getInt("id_subcategoria"));
            p.setPalavraChave(rs.getString("palavra_chave"));
            p.setAprovado(rs.getShort("aprovado"));
            p.setAvisado(rs.getShort("avisado"));
            p.setExpirado(rs.getShort("expirado"));
            p.setReservado(rs.getShort("reservado"));
            p.setTipo(rs.getShort("tipo"));
            lst.add(p);
        }

        return lst;
    }
}
