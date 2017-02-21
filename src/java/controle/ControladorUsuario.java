/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.EnderecoEntidade;
import entidade.PatrimonioEntidade;
import entidade.TelefoneEntidade;
import entidade.UsuarioEntidade;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.EnderecoDAO;
import persistencia.PatrimonioDAO;
import persistencia.PersistenciaException;
import persistencia.TelefoneDAO;
import persistencia.UsuarioDAO;
import util.EnviarEmail;
import util.MD5;

/**
 *
 * @author joaosantos
 */
public class ControladorUsuario {

    public static void inserir(UsuarioEntidade Novo) throws SQLException, PersistenciaException, NoSuchAlgorithmException {

        UsuarioDAO.inserir(Novo);

        TelefoneEntidade tel = new TelefoneEntidade();
        EnderecoEntidade end = new EnderecoEntidade();

        tel.setDdd(0);
        tel.setFixo(null);
        tel.setCelular(null);
        tel.setIdUsuario(Novo.getIdUsuario());

        end.setBairro(null);
        end.setCep(null);
        end.setCidade(0);
        end.setComplemento(null);
        end.setLogradouro(null);
        end.setNumero(0);
        end.setUf(0);
        end.setIdUsuario(Novo.getIdUsuario());

        TelefoneDAO.inserirTelefone(tel);
        EnderecoDAO.inserirEndereco(end);
        EnviarEmail env = new EnviarEmail();
        env.enviarLinkValidacaoDeCadastro(Novo.getEmail());
    }

    public static void cadastrarFotoUsuario(int idUsuario, String foto, String nomeAdmin, String emailAdmin) throws SQLException {
        UsuarioDAO.cadastrarFotoUsuario(idUsuario, foto, nomeAdmin, emailAdmin);
        UsuarioDAO.alterarStatusParaFotoNaoAprovada(idUsuario);
        EnviarEmail env = new EnviarEmail();
        env.enviarEmailAdminAprovarFotoPerfil(nomeAdmin, emailAdmin, idUsuario);
    }

    public static String pegarNomeProponenteTroca(String idProp) throws SQLException {
        String nome = "";
        nome = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(idProp);
        return nome;
    }

    public static void criarLinkParaRedefinirSenhaUsuario(String email) throws SQLException, NoSuchAlgorithmException {

        Integer idUsuario = UsuarioDAO.pegarIdPorEmail(email);
        MD5 md = new MD5();
        String hashId = md.criarMD5ParaEnviarLink(idUsuario.toString());
        String link = "?hash=" + hashId + "&email=" + email;
        EnviarEmail env = new EnviarEmail();
        env.enviarLinkParaRedefinirSenhaUsuario(email, link);

    }

    public static void redefinirSenhaUsuario(String email, String senha) throws SQLException {

        UsuarioDAO.redefinirSenhaUsuario(email, senha);

    }

    public static int pegarIdPorEmail(String email) throws SQLException {
        int id = UsuarioDAO.pegarIdPorEmail(email);
        return id;
    }

    public static String pegarFotoUsuario(String idUsuario) throws SQLException {
        String path = "";

        path = UsuarioDAO.pegarAtributoFotoDeUsuario(idUsuario);

        return path;
    }

    public static void notificarUsuarioPatrimonioVencido(Integer idUsuario, String nomePatrimonio, String proprietario) throws SQLException {
        String email = UsuarioDAO.pegarAtributoEmailUsuarioPeloId(idUsuario.toString());
        EnviarEmail env = new EnviarEmail();

        env.enviarEmailNotificandoPatrimonioVencido(email, nomePatrimonio, proprietario);

    }

    public static void notificarInatividadeNegociacaoParaEnvolvidos(String idQuer, String idTem, String titulo) throws SQLException {

        UsuarioEntidade tem = new UsuarioEntidade();
        UsuarioEntidade quer = new UsuarioEntidade();
        quer = pegarUsuario(idQuer);
        tem = pegarUsuario(idTem);

        EnviarEmail env = new EnviarEmail();

        env.enviarEmailNotificandoInatividadeNegociacao(quer.getEmail(), tem.getEmail(), titulo);
    }

    static String pegarIdUsuarioPeloIdProduto(String idProd) throws SQLException {
        String idUsuario = "";
        idUsuario = PatrimonioDAO.pegarIdUsuarioPorIdPatrimonio(idProd);
        return idUsuario;
    }

    static String pegarNomeUsuarioPeloIdProduto(String idProd) throws SQLException {
        String nome = "";
        String id = "";
        id = PatrimonioDAO.pegarIdUsuarioPorIdPatrimonio(idProd);
        nome = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(id);

        return nome;
    }

    static String pegarEmailUsuarioPeloIdProduto(String idProd) throws SQLException {
        String email = "";
        String id = PatrimonioDAO.pegarIdUsuarioPorIdPatrimonio(idProd);
        email = UsuarioDAO.pegarAtributoEmailUsuarioPeloId(id);
        return email;
    }

    public static List<UsuarioEntidade> pegarFotosParaAprovacao() throws SQLException {
        List<UsuarioEntidade> lst = new ArrayList<UsuarioEntidade>();
        lst = UsuarioDAO.pegarFotosASeremAprovadas();
        return lst;
    }

    public static UsuarioEntidade pegarUsuario(String idUsuario) throws SQLException {
        UsuarioEntidade u = new UsuarioEntidade();
        u = UsuarioDAO.pegarUsuarioPorId(idUsuario);
        return u;
    }

    public static void aprovarFotoPerfil(String idUsuario) throws SQLException {

        UsuarioDAO.aprovarFotoUsuario(idUsuario);

    }

    public static void naoAprovarFotoPerfil(String idUsuario) throws SQLException {

        String foto = UsuarioDAO.pegarAtributoFotoDeUsuario(idUsuario);
        String fotoPre[] = foto.split("_");
        String result = fotoPre[2];

        //apagarFotoHD
        String fotoPreRem[] = foto.split("/");
        String resultRem = fotoPreRem[2];

//        String path = "/Users/joaosantos/NetBeansProjects/EuTenhoEuQuero/web/fotos_usuarios/";
        String path = "/usr/local/tomcat8/webapps/ROOT/fotos_usuarios/";          //OLD 
//        String path = "/home/eteq/fotos_usuarios/";

        java.io.File f = new java.io.File(path, resultRem);

        f.delete();

        String fotoDefault = "/img/foto_default.png";
        UsuarioDAO.naoAprovarFotoPerfil(idUsuario, fotoDefault);
        UsuarioDAO.aprovarFotoUsuario(idUsuario);

        //enviar e-mail
        String nomeUsuario = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(idUsuario);
        String email = UsuarioDAO.pegarAtributoEmailUsuarioPeloId(idUsuario);

        EnviarEmail env = new EnviarEmail();
        env.enviarEmailNotificandoFotoNaoAprovada(nomeUsuario, email, result);

    }

    public static Boolean verificarSeFotoFoiAprovada(String idUsuario) throws SQLException {
        Boolean aprovada = false;
        aprovada = UsuarioDAO.verificaSeFotoFoiAprovada(idUsuario);
        return aprovada;
    }

    public static Boolean verificarSeUsuarioPossuiReserva(String idUsuQuer) throws SQLException {
        Boolean temReserva = UsuarioDAO.verificaSePossuiReserva(idUsuQuer);
        return temReserva;
    }

    public static String pegarPatrimoniosPeloIdUsuario(int idUsuario) throws SQLException {
        String titulo = "";
        titulo = UsuarioDAO.pegarPatrimoniosPeloIdUsuario(idUsuario);
        return titulo;
    }

    public static Boolean excluirPerfilUsuario(String idUsu, String senha, String verificaSenha) throws SQLException {

        //VERIFICA RESERVA
        Boolean possuiReserva = verificarSeUsuarioPossuiReserva(idUsu);

        if (!possuiReserva) {
            //DESATIVAR USUARIO
            UsuarioDAO.desativarPerfilUsuario(idUsu);
            
            //ENVIAR MSG DESPEDIDA
            EnviarEmail env = new EnviarEmail();
            String email = UsuarioDAO.pegarAtributoEmailUsuarioPeloId(idUsu);
            String nome = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(idUsu);
            env.enviarEmailAgradecendoUsuarioPorTerInscrito(nome, email);
            
        }

        return possuiReserva;
    }

}
