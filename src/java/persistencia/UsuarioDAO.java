/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.restfb.types.User;
import entidade.PatrimonioEntidade;
import entidade.UsuarioEntidade;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.EnviarEmail;

/**
 *
 * @author joaomarcelo.ms@gmail.com
 */
public class UsuarioDAO {

    public static void inserir(UsuarioEntidade Novo) throws SQLException, PersistenciaException {

        if (verificaEmailCadastro(Novo.getEmail())) {
            throw new PersistenciaException("email já cadastrado!");
        }
        if (verificaNomeCadastro(Novo.getNome())) {
            throw new PersistenciaException("nome já cadastrado!");
        }

        String Query = "INSERT INTO usuario(nome,email,senha,reserva, autentico, foto_aprovada) values(?,?,?,0,0,1);";
        Connection Con = BaseDados.getInstancia().getConnection();
        PreparedStatement Pst = Con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        Pst.setString(1, Novo.getNome());
        Pst.setString(2, Novo.getEmail());
        Pst.setString(3, Novo.getSenha());
        Pst.executeUpdate();

        ResultSet Rs = Pst.getGeneratedKeys();
        if (Rs.next()) {
            Novo.setIdUsuario(Rs.getInt(1));
        }

    }
    public static int inserirFB(UsuarioEntidade Novo) throws SQLException, PersistenciaException {

        int id =0;
        String Query = "INSERT INTO usuario(nome, email, senha, reserva, foto, validado, autentico, foto_aprovada) values(?,?,?,0,?,1,1,1);";
        Connection Con = BaseDados.getInstancia().getConnection();
        PreparedStatement Pst = Con.prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
        Pst.setString(1, Novo.getNome());
        Pst.setString(2, Novo.getEmail());
        Pst.setString(3, Novo.getSenha());
        Pst.setString(4, Novo.getFoto());
        Pst.executeUpdate();

        ResultSet Rs = Pst.getGeneratedKeys();
        if (Rs.next()) {
            Novo.setIdUsuario(Rs.getInt(1));
            id = Rs.getInt(1);
        }
        return id;
    }
    public static boolean verificaEmailCadastro(String Email) throws SQLException {
        String Query = "SELECT id_usuario FROM usuario WHERE email = ? ";
        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
        Pst.setString(1, Email);
        ResultSet Rs = Pst.executeQuery();
        return Rs.next();

    }

    public static boolean verificaNomeCadastro(String Nome) throws SQLException {
        String Query = "SELECT id_usuario FROM usuario WHERE nome = ? ";
        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
        Pst.setString(1, Nome);
        ResultSet Rs = Pst.executeQuery();
        return Rs.next();
    }

    public static boolean verificaSePossuiReserva(String id) throws SQLException {
        String Query = "SELECT * FROM usuario WHERE id_usuario = ? and reserva = 1";
        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
        Pst.setString(1, id);
        ResultSet Rs = Pst.executeQuery();
        return Rs.next();
    }

    public static UsuarioEntidade logar(String login, String senha) throws SQLException {

        UsuarioEntidade U = null;
        String Query = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
        Pst.setString(1, login);
        Pst.setString(2, senha);
        ResultSet Rs = Pst.executeQuery();

        if (Rs.next()) {
            U = new UsuarioEntidade();
            U.setIdUsuario(Rs.getInt("id_usuario"));
            U.setNome(Rs.getString("nome"));
            U.setEmail(Rs.getString("email"));
            U.setSenha(""); //Não guardar a senha por questão de segurança.
            U.setFoto(Rs.getString("foto"));
            U.setReserva(Rs.getShort("reserva"));
            U.setUltimoAcesso(Rs.getDate("ultimo_acesso"));
            U.setValidado(Rs.getShort("validado"));
        }
        return U;
    }

    public static UsuarioEntidade verificarSeJaExisteUsuarioCadastrado(User facebookUser) throws SQLException, PersistenciaException {
        UsuarioEntidade user = null;

        if (verificaEmailCadastro(facebookUser.getEmail())) {

            String senha = pegarSenhaUsuarioPorEmail(facebookUser.getEmail());
            user = new UsuarioEntidade();
            user.setEmail(facebookUser.getEmail());
            user.setNome(facebookUser.getName());
            user.setFoto(facebookUser.getLink());
            user.setFotoAprovada(Short.parseShort("1"));
            user.setValidado(Short.parseShort("1"));
            user.setSenha(senha);
        }

        return user;
    }

    public static void validarUsuarioPorLinkEmail(String login) throws SQLException {

        String Query = "UPDATE usuario SET validado = 1 where email = ?;";
        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
        Pst.setString(1, login);

        Pst.executeUpdate();

    }

    public static int pegarAtributoValidadoDeUsuario(String Email) throws SQLException {
        String Query = "SELECT validado FROM usuario WHERE email = ? ";
        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
        Pst.setString(1, Email);
        ResultSet Rs = Pst.executeQuery();
        int saida = 0;
        if (Rs.next()) {
            saida = Rs.getInt("validado");
        }
        return saida;

    }

    public static String pegarAtributoNomeDeUsuario(String Email) throws SQLException {
        String Query = "SELECT nome FROM usuario WHERE email = ? ";
        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
        Pst.setString(1, Email);
        ResultSet Rs = Pst.executeQuery();
        String saida = "";
        if (Rs.next()) {
            saida = Rs.getString("nome");
        }
        return saida;

    }

    public static String pegarAtributoNomeDeUsuarioPeloId(String id) throws SQLException {
        String Query = "SELECT nome FROM usuario WHERE id_usuario = ? ";
        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
        Pst.setString(1, id);
        ResultSet Rs = Pst.executeQuery();
        String saida = "";
        if (Rs.next()) {
            saida = Rs.getString("nome");
        }
        return saida;

    }

    public static String pegarAtributoFotoDeUsuario(String id) throws SQLException {
        String Query = "SELECT foto FROM usuario WHERE id_usuario = ? ";
        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
        Pst.setString(1, id);
        ResultSet Rs = Pst.executeQuery();
        String saida = "";
        if (Rs.next()) {
            saida = Rs.getString("foto");
        }
        return saida;

    }

    public static int pegarIdPorEmail(String Email) throws SQLException {
        String Query = "SELECT id_usuario FROM usuario WHERE email = ? ";
        PreparedStatement Pst = BaseDados.getInstancia().prepareStatement(Query);
        Pst.setString(1, Email);
        ResultSet Rs = Pst.executeQuery();
        int saida = 0;
        if (Rs.next()) {
            saida = Rs.getInt("id_usuario");
        }
        return saida;

    }

    public static UsuarioEntidade pegarUsuarioPorId(String id) throws SQLException {
        UsuarioEntidade u = new UsuarioEntidade();
        String Query = "SELECT * FROM usuario WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            u.setIdUsuario(rs.getInt("id_usuario"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setReserva(rs.getShort("reserva"));
            u.setUltimoAcesso(rs.getDate("ultimo_acesso"));
            u.setFoto(rs.getString("foto"));
            u.setValidado(rs.getShort("validado"));
            u.setAutentico(rs.getShort("autentico"));
        }

        return u;
    }

    public static void marcarReservaParaUsuario(String id) throws SQLException {

        String Query = "UPDATE usuario SET reserva = 1 where id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        pst.executeUpdate();

    }

    public static void desmarcarReservaParaUsuario(String id) throws SQLException {
        String Query = "UPDATE usuario SET reserva = 0 where id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, id);
        pst.executeUpdate();

    }

    public static String pegarAtributoEmailUsuarioPeloId(String idUsuarioTem) throws SQLException {
        String email = "";
        String Query = "SELECT email FROM usuario where id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuarioTem);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            email = rs.getString("email");
        }
        return email;

    }

    public static void cadastrarFotoUsuario(int idUsuario, String foto, String nomeAdmin, String emailAdmin) throws SQLException {
        String Query = "UPDATE usuario set foto = ? where id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, foto);
        pst.setInt(2, idUsuario);
        pst.executeUpdate();

    }

    public static void redefinirSenhaUsuario(String email, String senha) throws SQLException {
        String Query = "UPDATE usuario set senha=? where email = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, senha);
        pst.setString(2, email);
        pst.executeUpdate();

    }

    public static List<UsuarioEntidade> pegarFotosASeremAprovadas() throws SQLException {
        UsuarioEntidade u = null;
        ArrayList<UsuarioEntidade> lst = new ArrayList<UsuarioEntidade>();
        String Query = "SELECT * FROM usuario WHERE foto_aprovada = 0;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            u = new UsuarioEntidade();
            u.setIdUsuario(rs.getInt("id_usuario"));
            u.setNome(rs.getString("nome"));
            u.setFoto(rs.getString("foto"));
            lst.add(u);

        }

        return lst;
    }

    public static void aprovarFotoUsuario(String idUsuario) throws SQLException {
        String Query = "UPDATE usuario set foto_aprovada = 1 WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuario);
        pst.executeUpdate();

    }

    public static void alterarStatusParaFotoNaoAprovada(int idUsuario) throws SQLException {
        String Query = "UPDATE usuario SET foto_aprovada = 0 WHERE id_usuario = ?";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, idUsuario);
        pst.executeUpdate();

    }

    public static void naoAprovarFotoPerfil(String idUsuario, String fotoDefault) throws SQLException {
        String Query = "UPDATE usuario set foto = ? where id_usuario= ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, fotoDefault);
        pst.setString(2, idUsuario);
        pst.executeUpdate();

    }

    public static Boolean verificaSeFotoFoiAprovada(String idUsuario) throws SQLException {
        Boolean foi = false;
        Short result = 10;
        String Query = "SELECT foto_aprovada FROM usuario WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            result = rs.getShort("foto_aprovada");
        }
        if (result == 1) {
            foi = true;
        }
        return foi;
    }

    public static String pegarPatrimoniosPeloIdUsuario(int idUsuario) throws SQLException {

        String titulo = "";
        String Query = "SELECT titulo FROM patrimonio WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setInt(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            titulo = rs.getString("titulo");
        }
        return titulo;
    }

    private static String pegarSenhaUsuarioPorEmail(String facebookEmail) throws SQLException {
        String senha = "";
        String Query = "SELECT senha FROM usuario WHERE email = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, facebookEmail);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            senha = rs.getString("senha");
        }

        return senha;
    }

    public static void desativarPerfilUsuario(String idUsu) throws SQLException{
        String Query = "UPDATE usuario SET validado =0 WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsu);
        pst.executeUpdate();
        
    }

    public static String pegarAtributoSenhaUsuario(String idUsuario) throws SQLException{
        String retorno = "";
        String Query = "SELECT senha FROM usuario WHERE id_usuario = ?;";
        PreparedStatement pst = BaseDados.getInstancia().prepareStatement(Query);
        pst.setString(1, idUsuario);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            retorno = rs.getString(1);
        }
        
        return retorno;
    }

}
