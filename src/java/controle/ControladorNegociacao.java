/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.ForumEntidade;
import entidade.HistoricoEntidade;
import entidade.NegociacaoEntidade;
import persistencia.NegociacaoDAO;
import entidade.PatrimonioEntidade;
import entidade.RespostaEntidade;
import entidade.UsuarioEntidade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencia.AdminDAO;
import persistencia.ForumDAO;
import persistencia.HistoricoDAO;
import persistencia.NegociacaoDAO;
import persistencia.ParametrosDAO;
import persistencia.PatrimonioDAO;
import persistencia.UsuarioDAO;
import util.EnviarEmail;
import util.OperacaoComDataParaVerSeAnuncioVenceu;

/**
 *
 * @author joaosantos
 */
public class ControladorNegociacao {

    //C S U 05 S O L I C I T A R   D O A C A O
    public static void solicitarDoacaoDePatrimonio(String idUsuarioQuer, String idProdutoTem) throws SQLException {

        //Sistema: verifica que o usuário solicitante não possui reserva 
        Boolean possuiReserva = persistencia.UsuarioDAO.verificaSePossuiReserva(idUsuarioQuer);

        if (possuiReserva) {
            System.out.println("USUARIO POSSUI RESERVA, não pode efetuar pedido");
        } else {

            //Sistema: registra a ocorrência de uma reserva para o usuário 
            UsuarioDAO.marcarReservaParaUsuario(idUsuarioQuer);

            //Sistema: altera o status do patrimônio para reservado
            PatrimonioDAO.alterarStatusReservaPatrimonioReservado(idProdutoTem);

            //Sistema: cria uma negociação contendo os usuários envolvidos, o item e marca o status da negociação como ativo 
            NegociacaoEntidade n = new NegociacaoEntidade();

            String idUsuarioTem = PatrimonioDAO.pegarIdUsuarioPorIdPatrimonio(idProdutoTem);
            String data = util.ControladorDeDatas.pegarData();

            n.setIdUsuarioTem(Integer.parseInt(idUsuarioTem));
            n.setIdUsuarioQuer(Integer.parseInt(idUsuarioQuer));
            n.setIdProdutoTem(Integer.parseInt(idProdutoTem));
            n.setIdProdutoQuer(0);
            n.setStatus(Short.parseShort("1"));
            n.setDataInicio(data);
            n.setAvisado(0);

            int idNegociacao = NegociacaoDAO.criarNegociacao(n);

            //Sistema: envia e-mail de notificação para o dono do patrimônio, com link de acesso para a negociação 
            String nomeTem = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(idUsuarioTem);
            String nomeQuer = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(idUsuarioQuer);
            String email = UsuarioDAO.pegarAtributoEmailUsuarioPeloId(idUsuarioTem);
            EnviarEmail env = new EnviarEmail();
            env.enviarEmailNegociacaoSolicitarDoacao(nomeTem, email, nomeQuer, idNegociacao);

            //Sistema: exibe alerta com mensagem
            System.out.println("UMA NOTIFICACAO FOI ENVIADA PARA O USUARIO, AGUARDE CONTATO PARA NEGOCIACAO");

        }
    }

    //C S U 06 I N I C I A R   D O A C A O
    public static NegociacaoEntidade iniciarDoacao(String idNegociacao) throws SQLException {

        NegociacaoEntidade n = NegociacaoDAO.selecionarNegociacaoPorId(idNegociacao);

        //Sistema: envia notificação para usuário solicitante contendo link
        String nomeQuer = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(n.getIdUsuarioQuer().toString());
        String nomeTem = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(n.getIdUsuarioTem().toString());
        PatrimonioEntidade pTem = PatrimonioDAO.pegarPatrimonioPeloId(n.getIdProdutoTem().toString());

        String assunto = "Doacao de " + pTem.getTitulo() + " para " + nomeQuer;
        String mensagem = "Ola "
                + nomeQuer + " , "
                + nomeTem + "  aceitou doar  o(a) "
                + pTem.getTitulo() + "  dele(a),  para voce!  "
                + " Inicie a negociacao enviando uma mensagem para ele(a)";

        ForumEntidade forum = new ForumEntidade();
        forum.setAssunto(assunto);
        forum.setMensagem(mensagem);
        forum.setIdNegociacao(Integer.parseInt(idNegociacao));
        forum.setIdUsuarioDestinatario(n.getIdUsuarioTem());
        forum.setIdUsuarioRemetente(n.getIdUsuarioQuer());
        forum.setAtivo(Short.parseShort("1"));
        //CRIAR FORUM
        ForumDAO.criarForum(forum);

        String email = UsuarioDAO.pegarAtributoEmailUsuarioPeloId(n.getIdUsuarioQuer().toString());

        EnviarEmail env = new EnviarEmail();
        env.enviarEmailNegociacaoIniciarDoacao(nomeTem, email, nomeQuer, idNegociacao);

        return n;
    }

    //C S U 07 F I N A L I Z A R   D O A C A O
    public static void finalizarDoacao(String idNegociacao) throws SQLException {

        NegociacaoEntidade n = NegociacaoDAO.selecionarNegociacaoPorId(idNegociacao);

        //Sistema: pegaIdForum
        int idForum = ForumDAO.pegarIdForumPeloIdNegociacao(idNegociacao);

        ForumDAO.desativarForumPeloIdNegociacao(Integer.parseInt(idNegociacao));

        //Sistema: desativa as respostas referentes à negociação
        ControladorResposta.apagarRespostasReferentesAoForumInativo(idForum);

        //Sistema: marca o status da negociação como inativo
        NegociacaoDAO.desativarNegociacao(idNegociacao);

        //Sistema: desativa o anuncio do patrimônio (Marcas como expirado)
        PatrimonioDAO.alterarStatusPatrimonioParaExpirado(n.getIdProdutoTem().toString());
        PatrimonioDAO.alterarStatusPatrimonioParaExpirado(n.getIdProdutoQuer().toString());

        //Sistema: retira reserva do usuário solicitante
        UsuarioDAO.desmarcarReservaParaUsuario(n.getIdUsuarioQuer().toString());
        //Sistema: grava na tabela “histórico” as informações dos usuários e do item envolvidos na negociação
        HistoricoEntidade h = new HistoricoEntidade();
        h.setIdUsuarioTem(n.getIdUsuarioTem());
        h.setIdUsuarioQuis(n.getIdUsuarioQuer());
        h.setIdPatrimonioTem(n.getIdProdutoTem());
        h.setIdPatrimonioQuis(n.getIdProdutoQuer());
        h.setStatus("Doacao Finalizada");
        h.setConcluido(Short.parseShort("1"));
        HistoricoDAO.criarRegistroHistorico(h);

    }

    //C S U 07 FLUXO ALTERNATIVO [ALT01] -- C A N C E L A R   D O A C A O
    public static void cancelarDoacao(String idNegociacao) throws SQLException {

        NegociacaoEntidade n = NegociacaoDAO.selecionarNegociacaoPorId(idNegociacao);
        //Sistema: pegaIdForum
        int idForum = ForumDAO.pegarIdForumPeloIdNegociacao(idNegociacao);
        //Sistema: desativa o fórum referente à negociação
        ForumDAO.desativarForumPeloIdNegociacao(Integer.parseInt(idNegociacao));

        //Sistema: desativa as respostas referentes à negociação
        ControladorResposta.apagarRespostasReferentesAoForumInativo(idForum);

        //Sistema: marca o status da negociação como inativo
        NegociacaoDAO.desativarNegociacao(idNegociacao);

        //Sistema: retira reserva do patrimônio\
        PatrimonioDAO.alterarStatusReservaPatrimonioNaoReservado(n.getIdProdutoTem().toString());

        //Sistema: retira reserva do usuário solicitante
        UsuarioDAO.desmarcarReservaParaUsuario(n.getIdUsuarioQuer().toString());

        //Sistema: grava na tabela “histórico” as informações dos usuários e do item envolvidos na negociação e também o status final da negociação
        HistoricoEntidade h = new HistoricoEntidade();
        h.setIdUsuarioTem(n.getIdUsuarioTem());
        h.setIdUsuarioQuis(n.getIdUsuarioQuer());
        h.setIdPatrimonioTem(n.getIdProdutoTem());
        h.setIdPatrimonioQuis(n.getIdProdutoQuer());
        h.setStatus("Doacao Cancelada");
        h.setConcluido(Short.parseShort("0"));
        HistoricoDAO.criarRegistroHistorico(h);

    }

    //C S U 08 S O L I C I T A R    T R O C A 
    public static void solicitarTrocaDePatrimonios(String idUsuarioQuer, String idPatrimonioUsuarioTem, String idPatrimonioQuer) throws SQLException {

        //sistema pega id do usuario TEM pelo item do patrimonio dele
        String idUsuarioTem = PatrimonioDAO.pegarIdUsuarioPorIdPatrimonio(idPatrimonioUsuarioTem);
        String data = util.ControladorDeDatas.pegarData();

        //sistema pega usuario TEM
        UsuarioEntidade uTem = new UsuarioEntidade();
        uTem = UsuarioDAO.pegarUsuarioPorId(idUsuarioTem);
        //sistema pega usuario QUER
        UsuarioEntidade uQuer = new UsuarioEntidade();
        uQuer = UsuarioDAO.pegarUsuarioPorId(idUsuarioQuer);

        //sisterma cria negociacao
        NegociacaoEntidade n = new NegociacaoEntidade();

        n.setIdUsuarioTem(uTem.getIdUsuario());
        n.setIdUsuarioQuer(uQuer.getIdUsuario());
        n.setIdProdutoTem(Integer.parseInt(idPatrimonioUsuarioTem));
        n.setIdProdutoQuer(Integer.parseInt(idPatrimonioQuer));
        n.setStatus(Short.parseShort("1"));
        n.setDataInicio(data);
        n.setAvisado(0);

        Integer idNegociacao = NegociacaoDAO.criarNegociacao(n);// recebe o id gerado da negociacao

        //Notificar usuario 
        String email = uTem.getEmail();
        String nome = uTem.getNome();
        EnviarEmail env = new EnviarEmail();
        env.enviarEmailNegociacaoSolicitarTroca(idNegociacao.toString(), email, nome);

    }

    public static ArrayList<PatrimonioEntidade> perguntarPorQualItemDesejaRealizarTrocaComUsuarioTem(String idUsuarioQuer) throws SQLException {
        //sistema perguntar pro usuari QUER qual item deseja trocar com usuasrio TEM
        ArrayList<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        lst = PatrimonioDAO.pegarTodosPatrimoniosPorIdUsuarioNaoExpirados(idUsuarioQuer);
        return lst;
    }

    public static Boolean tenhoPatrimonios(String idUsuarioQuer) throws SQLException {//sistema so permite a troca se houver itens cadastrados usuario quer 
        Boolean tenho = false;
        tenho = PatrimonioDAO.usuarioTemPatrimonioCadastrado(idUsuarioQuer);

        return tenho;
    }

    // CSU 09 A C E I T A R T R O CA 
    public static void aceitarTroca(String idNegociacao) throws SQLException {

        NegociacaoEntidade n = NegociacaoDAO.selecionarNegociacaoPorId(idNegociacao);

        //PEGAR USUARIOS
        UsuarioEntidade uTem = persistencia.UsuarioDAO.pegarUsuarioPorId(n.getIdUsuarioTem().toString());
        UsuarioEntidade uQuer = persistencia.UsuarioDAO.pegarUsuarioPorId(n.getIdUsuarioQuer().toString());

        //[PEGAR PRODUTOS        
        PatrimonioEntidade pTem = PatrimonioDAO.pegarPatrimonioPorId(n.getIdProdutoTem().toString());
        PatrimonioEntidade pQuer = PatrimonioDAO.pegarPatrimonioPorId(n.getIdProdutoQuer().toString());

        //ALTERA STATUS DOS PRODUTOS PARA RESERVADOS
        PatrimonioDAO.alterarStatusReservaPatrimonioReservado(pTem.getIdPatrimonio().toString());
        PatrimonioDAO.alterarStatusReservaPatrimonioReservado(pQuer.getIdPatrimonio().toString());

        //CRIA UMA RESERVA PARA AMBOS USUARIOS
        UsuarioDAO.marcarReservaParaUsuario(uTem.getIdUsuario().toString());
        UsuarioDAO.marcarReservaParaUsuario(uQuer.getIdUsuario().toString());

        String assunto = "Trocar " + pTem.getTitulo() + " por " + pQuer.getTitulo() + "  do(a) " + uQuer.getNome();
        String mensagem = "Ola "
                + uQuer.getNome() + " , "
                + uTem.getNome() + "  aceitou TROCAR o(a) "
                + pTem.getTitulo() + "  dele(a),  PELO(a) SEU(ua)  "
                + pQuer.getTitulo() + ". Inicie a negociacao enviando uma mensagem para "
                + uTem.getNome();

        ForumEntidade forum = new ForumEntidade();
        forum.setAssunto(assunto);
        forum.setMensagem(mensagem);
        forum.setIdNegociacao(Integer.parseInt(idNegociacao));
        forum.setIdUsuarioDestinatario(uQuer.getIdUsuario());
        forum.setIdUsuarioRemetente(uTem.getIdUsuario());
        forum.setAtivo(Short.parseShort("1"));
        //CRIAR FORUM
        ForumDAO.criarForum(forum);

        //NOTIFICAR PROPONENTE
        EnviarEmail env = new EnviarEmail();
        env.enviarEmailNegociacaoAceitarTroca(idNegociacao, uQuer.getEmail(), uQuer.getNome());

    }

    // FLUXO ALTERNATIVO C S U 09 -- [ALT01] N A O   A C E I T A R   T R O C A 
    public static void naoAceitarTroca(String idNegociacao) throws SQLException {

        //pegar negociacao
        NegociacaoEntidade n = NegociacaoDAO.selecionarNegociacaoPorId(idNegociacao);

        //sistema altera atributo reservado dos itens enviolvidos para NAO RESERVAADO
        PatrimonioDAO.alterarStatusPatrimonioParaExpirado(n.getIdProdutoTem().toString());
        PatrimonioDAO.alterarStatusPatrimonioParaExpirado(n.getIdProdutoQuer().toString());

        //sistema notifica usuario proponente que nao foi aceita a troca
        UsuarioEntidade uQuer = UsuarioDAO.pegarUsuarioPorId(n.getIdUsuarioQuer().toString());
        UsuarioEntidade uTem = UsuarioDAO.pegarUsuarioPorId(n.getIdUsuarioTem().toString());
        EnviarEmail env = new EnviarEmail();
        env.enviarEmailNegociacaoNaoAceitarTroca(uTem.getNome(), uQuer.getEmail(), uQuer.getNome());

        //sistema exclui negociacao
        NegociacaoDAO.excluirNegociacao(idNegociacao);

    }

    // CSU 10 F I N A L I Z A R    T R O C A
    public static void finalizarTroca(String idNegociacao) throws SQLException {

        NegociacaoEntidade n = NegociacaoDAO.selecionarNegociacaoPorId(idNegociacao);

        //Sistema: grava na tabela “histórico” as informações dos usuários e dos itens envolvidos na negociação
        HistoricoEntidade h = new HistoricoEntidade();
        h.setIdUsuarioTem(n.getIdUsuarioTem());
        h.setIdUsuarioQuis(n.getIdUsuarioQuer());
        h.setIdPatrimonioTem(n.getIdProdutoTem());
        h.setIdPatrimonioQuis(n.getIdProdutoQuer());
        h.setStatus("Troca Finalizada");
        h.setConcluido(Short.parseShort("1"));

        HistoricoDAO.criarRegistroHistorico(h);

        //Sistema: pegaIdForum
        int idForum = ForumDAO.pegarIdForumPeloIdNegociacao(idNegociacao);
        //Sistema: desativa o fórum  
//        ForumEntidade f = ForumDAO.getForum(idNegociacao);      
        ForumDAO.desativarForumPeloIdNegociacao(Integer.parseInt(idNegociacao));

        //Sistema apaga respostas referentes à negociação
//        ControladorResposta.apagarRespostasReferentesAoForumInativo(idForum);

        //Sistema: marca o status da negociação como inativo
        NegociacaoDAO.desativarNegociacao(idNegociacao);

        //Sistema: retira reserva dos usuários envolvidos
        UsuarioDAO.desmarcarReservaParaUsuario(n.getIdUsuarioTem().toString());
        UsuarioDAO.desmarcarReservaParaUsuario(n.getIdUsuarioQuer().toString());

        //***** 88 88 88  Sistema altera patrimonio para excluido
        PatrimonioDAO.alterarStatusPatrimonioParaExcluido(n.getIdProdutoTem().toString());
        PatrimonioDAO.alterarStatusPatrimonioParaExcluido(n.getIdProdutoQuer().toString());
        
        
        //Sistema: desativa os patrimônios envolvidos na negociação COLOCANDO COMO EXPIRADOS
        PatrimonioDAO.alterarStatusPatrimonioParaExpirado(n.getIdProdutoTem().toString());
        PatrimonioDAO.alterarStatusPatrimonioParaExpirado(n.getIdProdutoQuer().toString());

    }

    // FLUXO ALTERNATIVO CSU 10 [ALT01] C A N C E L A R   T R O C A
    public static void cancelarTroca(String idNegociacao) throws SQLException {

        NegociacaoEntidade n = NegociacaoDAO.selecionarNegociacaoPorId(idNegociacao);
        //Sistema: pegaIdForum
        int idForum = ForumDAO.pegarIdForumPeloIdNegociacao(idNegociacao);
        //Sistema: desativa o fórum referente à negociação
        ForumDAO.desativarForumPeloIdNegociacao(Integer.parseInt(idNegociacao));

        //Sistema: apaga as respostas referentes à negociação
        ControladorResposta.apagarRespostasReferentesAoForumInativo(idForum);
        //Sistema: marca o status da negociação como inativo
        NegociacaoDAO.desativarNegociacao(idNegociacao);

        //Sistema: retira reservas dos patrimônios envolvidos
        PatrimonioDAO.alterarStatusReservaPatrimonioNaoReservado(n.getIdProdutoTem().toString());
        PatrimonioDAO.alterarStatusReservaPatrimonioNaoReservado(n.getIdProdutoQuer().toString());

        //Sistema: retira reserva dos usuários envolvidos
        UsuarioDAO.desmarcarReservaParaUsuario(n.getIdUsuarioTem().toString());
        UsuarioDAO.desmarcarReservaParaUsuario(n.getIdUsuarioQuer().toString());

        //Sistema: grava na tabela “histórico” as informações dos usuários e do item envolvidos na negociação e também o status final da negociação
        HistoricoEntidade h = new HistoricoEntidade();
        h.setIdUsuarioTem(n.getIdUsuarioTem());
        h.setIdUsuarioQuis(n.getIdUsuarioQuer());
        h.setIdPatrimonioTem(n.getIdProdutoTem());
        h.setIdPatrimonioQuis(n.getIdProdutoQuer());
        h.setStatus("Troca Cancelada");
        h.setConcluido(Short.parseShort("0"));
        HistoricoDAO.criarRegistroHistorico(h);

    }

    public static Boolean verificarNegociacaoAtivaComPatrimonio(String idPatrm) throws SQLException {

        Boolean tem = NegociacaoDAO.verificarNegociacaoComPatrimonioEspecifico(idPatrm);

        return tem;
    }

    public static NegociacaoEntidade pegarNegociacaoPeloId(String idNeg) throws SQLException {
        NegociacaoEntidade n = new NegociacaoEntidade();
        n = NegociacaoDAO.selecionarNegociacaoPorId(idNeg);
        return n;
    }

    public static List<NegociacaoEntidade> pegarListaDeNegociacoesAtivas() throws SQLException {
        List<NegociacaoEntidade> lst = new ArrayList<NegociacaoEntidade>();
        lst = NegociacaoDAO.pegarListaDeNegociacoesAtivas();
        return lst;
    }

    public static int pegarAvisoDeExpirado(Integer idNegocio) throws SQLException {
        int avisado = 0;
        avisado = NegociacaoDAO.pegarAvisoDeNegociacaoInativa(idNegocio);
        return avisado;
    }

    public static void alterarAtributoAvisoNegociacao(int numeroAviso, String idNeg) throws SQLException {

        NegociacaoDAO.alterarAvisoInatividade(numeroAviso, idNeg);
    }

    public static void verificarNegociosPendentesPorRespostasInativas() throws SQLException {

        //pega listagem negociacao
        List<NegociacaoEntidade> lstN = new ArrayList<NegociacaoEntidade>();
        lstN = pegarListaDeNegociacoesAtivas();

        for (NegociacaoEntidade n : lstN) {

            //Verifica Negociacao pegando data Inicio
            String dataInicial = n.getDataInicio();

            //verifica se o forum esta ativo
            Boolean forumAtivo = ForumDAO.verificarSeForumEstaAtivoPeloIdNegociacao(n.getIdNegociacao().toString());

            if (forumAtivo) {

                //Verifica respostas com id Forum
                Integer idForum = ForumDAO.pegarIdForumPeloIdNegociacao(n.getIdNegociacao().toString());
                List<RespostaEntidade> lstR = new ArrayList<RespostaEntidade>();
                lstR = ControladorResposta.pegarUltimaRespostaDoForumParaPegarData(idForum.toString());
                for (RespostaEntidade r : lstR) {
                    //pega data ultima resposta

                    String dataUltimaResp = r.getDataResposta().toString();

                    //pega parametros
                    int maxIdleAnuncio = ParametrosDAO.pegarValorParametro("7");
                    int contadorAnuncio = ParametrosDAO.pegarValorParametro("8");
                    
                    // VERIFICA SE ESTA VENCIDO
                    Boolean vencido = OperacaoComDataParaVerSeAnuncioVenceu.verificarSeDataVenceAgora(dataUltimaResp, maxIdleAnuncio); 
                    if (vencido) {

                        //CONTA NUMERO DA NOTIFICACAO
                        int numeroAviso = ControladorNegociacao.pegarAvisoDeExpirado(n.getIdNegociacao());    //recebe numero de avisos ja ocorridos da tabela
                        //trata numero avisos
                        if (numeroAviso <= contadorAnuncio) {

                            //NOTIFICA ENVOLVIDOS//envia msg avisando inatividade forum
                            String idQuer = n.getIdUsuarioQuer().toString();
                            String idTem = n.getIdUsuarioTem().toString();

                            //Pegar titulo Forum
                            String titulo = ForumDAO.pegarTituloForum(idForum);

                            ControladorUsuario.notificarInatividadeNegociacaoParaEnvolvidos(idQuer, idTem, titulo);
                            numeroAviso++;//incrementa o aviso  
                            ControladorNegociacao.alterarAtributoAvisoNegociacao(numeroAviso, n.getIdNegociacao().toString()); //persiste alteracao

                        } else {
                            //cancelar Anuncio
                            int tipo = n.getIdProdutoQuer(); //verifica se eh troca ou doacao
                            if (tipo == 0) {
                                ControladorNegociacao.cancelarDoacao(n.getIdNegociacao().toString());
                            }
                            ControladorNegociacao.cancelarTroca(n.getIdNegociacao().toString());
                        }
                    }
                }
            } else {
                System.out.println("INATTIVO " + n.getIdNegociacao());
            }
            System.out.println("+");
        }
        //NOTIFICAR ADMIN ROTINA EXECUTOU
        EnviarEmail env = new EnviarEmail();
        String admin = AdminDAO.pegarEmailAdmin();
        env.enviarEmailNotificandoRotinaDeVerificacaoDeNegociosPendentes(admin);

    }
    
    
}
