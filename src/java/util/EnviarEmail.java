package util;

import entidade.ConfigServidorEmailEntidade;
import entidade.PatrimonioEntidade;
import entidade.RespostaEntidade;
import entidade.UsuarioEntidade;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import persistencia.AdminDAO;
import persistencia.ConfigServidorEmailDAO;
import persistencia.UsuarioDAO;

/**
 *
 * @author joaosantos
 */
public class EnviarEmail {

    public void enviarLinkValidacaoDeCadastro(String email) throws SQLException, NoSuchAlgorithmException {
        String id_config = "1";
        Integer ID = UsuarioDAO.pegarIdPorEmail(email);

        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        MD5 md5 = new MD5();
        String hash = md5.criarMD5ParaEnviarLink(ID.toString());//cria hash para id  do usuário 

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<p>Prezado usuário, por favor clique no link ao lado, para validar o seu cadastro em nosso sistema : "
                + "<a href='http://www.eutenhoeuquero.com.br/validar_cadastro.jsp?hash=" + hash + "&e-mail=" + email + "'>Validar meu Cadastro</a>"
                + "</p>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailNegociacaoSolicitarTroca(String idNegociacao, String email, String nome) throws SQLException {

        Integer ID = UsuarioDAO.pegarIdPorEmail(idNegociacao);
        String id_config = "2";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + nome + ", você possui uma nova proposta de troca"
                + "<a href='http://www.eutenhoeuquero.com.br/proposta_troca.jsp?id_negocio=" + idNegociacao + "'>Ver proposta</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailNegociacaoAceitarTroca(String idNegociacao, String email, String nome) throws SQLException {

        String id_config = "2";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + nome + ", sua proposta foi aceita. Acesse o link para iniciar a negociacao"
                + "<a href='http://www.eutenhoeuquero.com.br/minhas_mensagens.jsp'>Pagina de negociacao</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailNegociacaoNaoAceitarTroca(String nomeTem, String email, String nome) throws SQLException {

        String id_config = "3";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + nome + ", sua proposta de troca com " + nomeTem + "  nao foi aceita. "
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailNegociacaoSolicitarDoacao(String nomeTem, String email, String nomeQuer, int idNegociacao) throws SQLException {

        String id_config = "4";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + nomeTem + ", " + nomeQuer + "  deseja receber uma doação sua. "
                + "<a href='http://www.eutenhoeuquero.com.br/doacao.jsp?idNegocio=" + idNegociacao + "'>Pagina de negociacao</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailNegociacaoIniciarDoacao(String nomeTem, String email, String nomeQuer, String idNegociacao) throws SQLException {
        String id_config = "5";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + nomeQuer + ", " + nomeTem + "  iniciou o processo de doacao com voce, por favor acesse o link para negociar. "
                + "<a href='http://www.eutenhoeuquero.com.br/minhas_mensagens.jsp'>Pagina de negociacao</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailPatrimonioExpirado(String nome, String email, String produto) throws SQLException {

        String id_config = "6";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + nome + ", seu item " + produto + " foi desativado em nosso sistema por ter seu prazo expirado!"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailAdminAprovarPatrimonio(String nomeAdmin, String emailAdmin, String idPatrimonio) throws SQLException {

        String id_config = "7";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + nomeAdmin + ", existe um novo patrimonio a ser aprovado!"
                + "<a href='http://www.eutenhoeuquero.com.br/aprovar_item.jsp'>Pagina de aprovação</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(emailAdmin, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarNotificacaoNovaResposta(String email, String nomeDest, String assunto) throws SQLException {

        String id_config = "8";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + nomeDest + ", existe uma nova mensagem sobre sua negociacao : - " + assunto + "!"
                + "<a href='http://www.eutenhoeuquero.com.br'/></br>  Acesse o sistema para ver suas mensagens</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailAdminAprovarFotoPerfil(String nomeAdmin, String emailAdmin, int idUsuario) throws SQLException {

        String id_config = "9";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + nomeAdmin + ", existe uma nova foto a ser aprovado!"
                + "<a href='http://www.eutenhoeuquero.com.br/aprovar_foto.jsp'>Pagina de aprovação</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(emailAdmin, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarLinkParaRedefinirSenhaUsuario(String email, String link) throws SQLException {
        String id_config = "10";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "<a href='http://www.eutenhoeuquero.com.br/redefinir_senha.jsp" + link + "'>Redefinir sua senha</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailNotificandoPatrimonioVencido(String email, String nomePatrimonio, String nomeProprietario) throws SQLException {

        String id_config = "11";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Ola " + nomeProprietario + " seu produto [" + nomePatrimonio + "] expirou, por favor acesse o sistema para renovar o seu cadastro casa haja interesse, ou para remover o anuncio ! Informamos que apos o 3º aviso a negociacao sera cancelada!"
                + "<a href='http://www.eutenhoeuquero.com.br/'>E u T e n h o E u Q u e r o</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailNotificandoPatrimonioNaoAprovado(String nome, String email, String titulo) throws SQLException {

        String id_config = "12";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Ola " + nome + " seu produto [ " + titulo + " ] nao foi aprovado em nosso sitema por nao estar em conformidade com a politica do site!!"
                + "<a href='http://www.eutenhoeuquero.com.br/'>E u T e n h o E u Q u e r o</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailNotificandoFotoNaoAprovada(String nomeUsuario, String email, String foto) throws SQLException {
        String id_config = "13";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Ola " + nomeUsuario + " sua foto [ " + foto + " ] nao foi aprovada em nosso sitema por nao estar em conformidade com a politica do site!!"
                + "<a href='http://www.eutenhoeuquero.com.br/'>E u T e n h o E u Q u e r o</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailNotificandoInatividadeNegociacao(String emailQuer, String emailTem, String titulo) throws SQLException {

        String id_config = "14";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Caro usuário, sua negociacao [[ " + titulo + " ]] se encontra pendente em nosso sistema, por favor acesse e conclua!!"
                + "<a href='http://www.eutenhoeuquero.com.br/'>E u T e n h o E u Q u e r o</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(emailQuer, "email gmail");
        map.put(emailTem, "email gmail");
        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailNotificandoAprovacaoPatrimonio(String nome, String email, String produto) throws SQLException {

        String id_config = "15";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Ola " + nome + " seu produto [ " + produto + " ]  foi aprovado em nosso sitema !!"
                + "<a href='http://www.eutenhoeuquero.com.br/'>E u T e n h o E u Q u e r o</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailNotificandoRotinaDeVerificacaoPatrimoniosVencidos(String admin) throws SQLException {

        String id_config = "16";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Bom dia " + admin + "o sistema realizou a rotina de verificacao de cadastros vencidos !!"
                + "</br><a href='http://www.eutenhoeuquero.com.br/'>E u T e n h o E u Q u e r o</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(admin, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailNotificandoRotinaDeVerificacaoDeNegociosPendentes(String admin) throws SQLException {

        String id_config = "17";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Bom dia " + admin + "o sistema realizou a rotina de verificacao de negociacoes inativas !!"
                + "</br><a href='http://www.eutenhoeuquero.com.br/'>E u T e n h o E u Q u e r o</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(admin, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailAgradecendoUsuarioPorTerInscrito(String nome, String email) throws SQLException {

        String id_config = "18";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Obrigado " + nome + "o sistema ETEQ agradece sua particiapção e espera que retorne um dia !!"
                + "</br><a href='http://www.eutenhoeuquero.com.br/'>E u T e n h o E u Q u e r o</a>"
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    public void enviarEmailFaleConosco(UsuarioEntidade u, String assunto, String msg) throws SQLException {

        String id_config = "19";
        ConfigServidorEmailEntidade c = ConfigServidorEmailDAO.obterConfiguracaoServidorEmail(id_config);
        String nomeResponsavel = AdminDAO.pegarNomeFaleConosco();
        String email = AdminDAO.pegarEmailFaleConosco();
        MailJava mj = new MailJava();

        //configuracoes de envio
        mj.setSmtpHostMail(c.getHost());
        mj.setSmtpPortMail(c.getPort());
        mj.setSmtpAuth(c.getAuth());
        mj.setSmtpStarttls(c.getStarttls());
        mj.setUserMail(c.getUserEmail());
        mj.setFromNameMail(c.getFromNameEmail());
        mj.setPassMail(c.getPassword());
        mj.setCharsetMail(c.getCharset());
        mj.setSubjectMail(c.getSubject());
        mj.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        mj.setBodyMail("<html>"
                + "<head>"
                + "<title></title>"
                + "</head>"
                + "<body>"
                + "<div style='background-color:#d8d7d2; color:black; width:100%; height:100px;'>"
                + "Olá " + nomeResponsavel + " ! "
                + "<br>"
                + "Você possui uma nova notificação do seguinte usuario: "
                + "<br>"
                + "<b>"
                + u.getNome()
                + "</b>"
                + "<br>  "
                + "<b>"
                + "ID-Usuário : "
                + "</b>"
                + u.getIdUsuario()
                + "<br>  "
                + "E-mail para resposta : " + u.getEmail()
                + "<br> "
                + "<br> "
                + "<b>"
                + "ASSUNTO: "
                + "</b>"
                + "<br>"
                + assunto
                + "<br>"
                + "<br>"
                + "<b>"
                + "MENSAGEM: "
                + "</b>"
                + "<br>"
                + msg
                + "</div>"
                + "</body>"
                + "</html>");

        //sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(email, "email gmail");

        mj.setToMailsUsers(map);

        try {
            new MailJavaSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
