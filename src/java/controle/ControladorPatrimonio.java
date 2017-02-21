/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.CadastroPatrimonioEntidade;
import entidade.HistoricoEntidade;
import entidade.PatrimonioEntidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import persistencia.AdminDAO;
import persistencia.BaseDados;
import persistencia.CadastroPatrimonioDAO;
import persistencia.HistoricoDAO;
import persistencia.ParametrosDAO;
import persistencia.PatrimonioDAO;
import persistencia.PersistenciaException;
import persistencia.UsuarioDAO;
import util.ControladorDeDatas;
import util.EnviarEmail;
import util.OperacaoComDataParaVerSeAnuncioVenceu;

/**
 *
 * @author joaosantos
 */
public class ControladorPatrimonio {

    //CSU 0 4 -   M A N T E R    A N U N  C  I  O 
    public static void cadastrarPatrimonio(PatrimonioEntidade pat) throws SQLException, PersistenciaException {

        //cadastra o patrimônio para aprovação do administrador
        PatrimonioDAO.inserir(pat);

        //cria registro do cadastro para controle de tempo de anuncio
        String data = ControladorDeDatas.pegarData();
        CadastroPatrimonioEntidade cad = new CadastroPatrimonioEntidade();
        cad.setIdUsuario(pat.getIdUsuario());
        cad.setIdPatrimonio(pat.getIdPatrimonio());
        cad.setDataCadastro(data);
        CadastroPatrimonioDAO.criarCadastroPatrimonio(cad);

        //envia e-mail notificação para Admin       
        //buscar dados admin para compor notificacao
        String emailAdmin = AdminDAO.pegarEmailAdmin();
        String nomeAdmin = AdminDAO.pegarNomeAdmin();
        String idPatrimonio = pat.getIdPatrimonio().toString();
        EnviarEmail env = new EnviarEmail();

        env.enviarEmailAdminAprovarPatrimonio(nomeAdmin, emailAdmin, idPatrimonio);

    }

    public static void excluirPatrimonio(String idUsuario, String idPatrm) throws SQLException {

        PatrimonioDAO.alterarStatusPatrimonioParaExcluido(idPatrm);

        HistoricoEntidade h = new HistoricoEntidade();
        h.setIdUsuarioTem(Integer.parseInt(idUsuario));
        h.setIdPatrimonioTem(Integer.parseInt(idPatrm));
        h.setIdUsuarioQuis(0);
        h.setIdPatrimonioQuis(0);
        h.setStatus("Excluido pelo usuario");
        h.setConcluido(Short.parseShort("0"));
        HistoricoDAO.criarRegistroHistorico(h);

    }

    //CSU27 - D E S A T I V A R   C A D A S T R O    D E    P A T R I M O N I O 
    public static void desativarCadastroDePatrimonioPrazoVencido(String idPatrimonio) throws SQLException {
        //Verifica se existe negociacao ativa com o item
        Boolean temNegocioAtivo = controle.ControladorNegociacao.verificarNegociacaoAtivaComPatrimonio(idPatrimonio);

        if (temNegocioAtivo) {
            System.out.println("Este patrimonio esta em uma negociacao atualmente");
        } else {

            //Sistema: altera o status do patrimônio para excluido
            PatrimonioDAO.alterarStatusPatrimonioParaExcluido(idPatrimonio);
            //Sistema: envia mensagens ao proprietário do patrimônio desativado informando que o mesmo expirou
            String idUsuario = PatrimonioDAO.pegarIdUsuarioPorIdPatrimonio(idPatrimonio);
            String email = UsuarioDAO.pegarAtributoEmailUsuarioPeloId(idUsuario);
            String nome = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(idUsuario);
            String produto = PatrimonioDAO.pegarTituloDoPatrimonioPeloIdPatrimonio(idPatrimonio);
            EnviarEmail env = new EnviarEmail();

            env.enviarEmailPatrimonioExpirado(nome, email, produto);

            //Sistema: cria registro tabela “historico”
            HistoricoEntidade h = new HistoricoEntidade();
            h.setIdUsuarioTem(Integer.parseInt(idUsuario));
            h.setIdPatrimonioTem(Integer.parseInt(idPatrimonio));
            h.setIdUsuarioQuis(0);
            h.setIdPatrimonioQuis(0);
            h.setStatus("Item expirado pelo sistema");
            h.setConcluido(Short.parseShort("0"));
            HistoricoDAO.criarRegistroHistorico(h);

            CadastroPatrimonioDAO.excluirCadastro(idPatrimonio, idUsuario);

        }

    }

    //CSU19 - R E N O V A R   C A D A S T R O    D E    P A T R I M O N I O 
    public static void renovarCadastroDePatrimonio(String idPatrimonio) throws SQLException {

        //ALTERAR STATUS para NAO EXPIRADO
        PatrimonioDAO.alterarStatusPatrimonioParaNaoExpirado(idPatrimonio);

        //ALTERAR STATUS para NAO AVISADO
        PatrimonioDAO.alterarStatusDoPatrimonioParaNaoAvisado(idPatrimonio);

        //RESETA DATA CADASTRO   PATRIMONIO
        CadastroPatrimonioEntidade cad = CadastroPatrimonioDAO.pegarCadastroPorIdPatrimonio(idPatrimonio);
        String data = ControladorDeDatas.pegarData();
        cad.setDataCadastro(data);
        CadastroPatrimonioDAO.renovarCadastroPatrimonio(cad);

    }

    public static CadastroPatrimonioEntidade pegarCadastroPatrimonio(String idPatr) throws SQLException {
        CadastroPatrimonioEntidade c = new CadastroPatrimonioEntidade();
        c = CadastroPatrimonioDAO.pegarCadastroPorIdPatrimonio(idPatr);

        return c;
    }

    public static List<PatrimonioEntidade> procurarPatrimonio(String palavraChave) throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        lst = PatrimonioDAO.procurarPatrimonios(palavraChave);
        return lst;
    }

    public static List<PatrimonioEntidade> listarMeusIntens(String idUsuario) throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        lst = PatrimonioDAO.listarMeusPatrimoniosCadastrados(idUsuario);
        return lst;

    }

    public static PatrimonioEntidade pegarPatrimonioPeloId(String idPatr) throws SQLException {
        PatrimonioEntidade p = null;
        p = PatrimonioDAO.pegarPatrimonioPeloId(idPatr);
        return p;
    }

    public static List<PatrimonioEntidade> pegarItensPelaSubcategoria(String idCat, String idSubCat) throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        lst = PatrimonioDAO.pegarItensPelaSubCategoria(idCat, idSubCat);

        return lst;
    }

    public static List<PatrimonioEntidade> pegarItensDaSubCategoria(String idCat, String idItensSubCat) throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        lst = PatrimonioDAO.pegarItensDaSubCategoria(idCat, idItensSubCat);
        return lst;
    }

    public static List<PatrimonioEntidade> listarUltimosPatrimoniosCadastrados() throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        lst = PatrimonioDAO.listarUltimosPatrimoniosCadastrados();
        return lst;
    }

    //CSU25 - VERIFICAR SE PATRIMONIO EXPIROU                          
    public static void verificarSePatrimonioEstaExpirado() throws SQLException {

        ArrayList<CadastroPatrimonioEntidade> lst = pegarListaDeTodosCadastros(); // RECEBE LISTA DE TODOS ANUNCIOS CADASTRADOS

        int maxDiasAnuncio = ParametrosDAO.pegarValorParametro("1"); // PEGA PARAMETRO DE PRAZO DE ANUNCIO

        for (CadastroPatrimonioEntidade c : lst) {
            Integer idUsuario = c.getIdUsuario();
            String nomeProduto = PatrimonioDAO.pegarTituloDoPatrimonioPeloIdPatrimonio(c.getIdPatrimonio().toString());
            String nomePropietario = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(idUsuario.toString());
            String data = c.getDataCadastro();

            Boolean vencido = OperacaoComDataParaVerSeAnuncioVenceu.verificarSeDataVenceAgora(data, maxDiasAnuncio); // VERIFICA SE ESTA VENCIDO

            if (vencido) {
                    //Altera Atributo do patrimoinio para EXPIRADO
                    alterarAtributoExpiradoParaExpirado(c.getIdPatrimonio());
                //CONTA NUMERO DA NOTIFICACAO
                //pegar parametro de contagem de notificacoes
                int numeroMaxAviso = ParametrosDAO.pegarValorParametro("2");
                int numeroAviso = pegarAvisoDeExpirado(c.getIdPatrimonio());    //recebe numero de aviso 0 da tabela

                if (numeroAviso <= numeroMaxAviso) {

                    //NOTIFICA PROPRIETARIO
                    ControladorUsuario.notificarUsuarioPatrimonioVencido(idUsuario, nomeProduto, nomePropietario);
                    numeroAviso++;//incrementa o aviso
                    alterarAtributoAvisoExpirado(numeroAviso, c.getIdPatrimonio()); //persiste alteracao

                } else {
                    //CSU27 DESATIVAR CADASTRO DE PATRIMONIO 
                    desativarCadastroDePatrimonioPrazoVencido(c.getIdPatrimonio().toString());

                }

            }
        }
        //NOTIFICAR ADMIN ROTINA RODOU
        EnviarEmail env = new EnviarEmail();
        String admin = AdminDAO.pegarEmailAdmin();
        env.enviarEmailNotificandoRotinaDeVerificacaoPatrimoniosVencidos(admin);
    }

    public static ArrayList<CadastroPatrimonioEntidade> pegarListaDeTodosCadastros() throws SQLException {

        ArrayList<CadastroPatrimonioEntidade> lst = new ArrayList<CadastroPatrimonioEntidade>();
        lst = CadastroPatrimonioDAO.pegarListaDeTodosCadastros();

        return lst;
    }

    private static int pegarAvisoDeExpirado(Integer idPatrimonio) throws SQLException {
        int numeroAviso = 0;
        numeroAviso = PatrimonioDAO.pegarAtributoAvisado(idPatrimonio);
        return numeroAviso;
    }

    private static void alterarAtributoAvisoExpirado(int numeroAviso, int idPat) throws SQLException {
        PatrimonioDAO.incrementarAviso(numeroAviso, idPat);
    }

    public static Boolean verificarSeProponmenteDonoPatrimonio(String idPatrimonioQuer, String usuarioQuer) throws SQLException {
        Boolean ehMeu = false;
        String idDono = PatrimonioDAO.pegarIdUsuarioPorIdPatrimonio(idPatrimonioQuer);

        if (idDono.equals(usuarioQuer)) {
            ehMeu = true;
        }
        return ehMeu;
    }

    public static List<PatrimonioEntidade> buscarPatrimoniosParaAprovacao() throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        lst = PatrimonioDAO.pegarListaPatrimoniosParaAprovacao();
        return lst;
    }

    public static void aprovarPatrimonio(String idProd) throws SQLException {

        PatrimonioDAO.aprovarPatrimonio(idProd);
        String idUsuario = ControladorUsuario.pegarIdUsuarioPeloIdProduto(idProd);
        CadastroPatrimonioEntidade ca = new CadastroPatrimonioEntidade();

        ca.setIdPatrimonio(Integer.parseInt(idProd));
        ca.setIdUsuario(Integer.parseInt(idUsuario));
        Calendar c = Calendar.getInstance();
        String data = ControladorDeDatas.pegarData();

        ca.setDataCadastro(data);
        CadastroPatrimonioDAO.criarCadastroPatrimonio(ca);

        //notificar usuario 
        String nome = ControladorUsuario.pegarNomeUsuarioPeloIdProduto(idProd);
        String email = ControladorUsuario.pegarEmailUsuarioPeloIdProduto(idProd);
        String produto = PatrimonioDAO.pegarTituloDoPatrimonioPeloIdPatrimonio(idProd);

        EnviarEmail env = new EnviarEmail();
        env.enviarEmailNotificandoAprovacaoPatrimonio(nome, email, produto);

    }

    public static void naoAprovarPatrimonio(String idProd) throws SQLException {

        //Enviar email notificando
        String nome = ControladorUsuario.pegarNomeUsuarioPeloIdProduto(idProd);
        String email = ControladorUsuario.pegarEmailUsuarioPeloIdProduto(idProd);
        PatrimonioEntidade produto = ControladorPatrimonio.pegarPatrimonioPeloId(idProd);
        PatrimonioDAO.excluirPatrimonio(idProd);

        EnviarEmail env = new EnviarEmail();
        env.enviarEmailNotificandoPatrimonioNaoAprovado(nome, email, produto.getTitulo());
    }

    public static int pegarAtributoAvisado(String idPatrimonio) throws SQLException {
        int valor = 0;
        valor = PatrimonioDAO.pegarAtributoAvisado(Integer.parseInt(idPatrimonio));
        return valor;
    }

    public static void editarPatrimonio(PatrimonioEntidade p) throws SQLException {

        PatrimonioEntidade antes = PatrimonioDAO.pegarPatrimonioPeloId(p.getIdPatrimonio().toString());

//                p.setIdCategoria(Integer.MIN_VALUE);
//                p.setIdSubcategoria(Integer.SIZE);
//                p.setIdItensSubcat(Integer.MIN_VALUE);
//                p.set
//                p.setReservado(antes.getReservado());
//                p.setAprovado(antes.getAprovado());
//                p.setExpirado(antes.getExpirado());
//                p.setAvisado(antes.getAvisado());
        PatrimonioDAO.editarPatrimonio(p);

    }

    private static void alterarAtributoExpiradoParaExpirado(Integer idPatrimonio) throws SQLException {
        PatrimonioDAO.alterarStatusPatrimonioParaExpirado(idPatrimonio.toString());

    }

    public static Boolean verificarSePatrimonioPossuiReserva(String idPatrimonio) throws SQLException {
        Boolean resp = false;
        resp = PatrimonioDAO.verificarSePatrimonioPossuiReserva(idPatrimonio);
        return resp;
    }

    public static List<PatrimonioEntidade> listarDoacoes() throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        lst = PatrimonioDAO.listarDoacoes();
        return lst;
    }

    public static List<PatrimonioEntidade> listarTrocas() throws SQLException {
        List<PatrimonioEntidade> lst = new ArrayList<PatrimonioEntidade>();
        lst = PatrimonioDAO.listarTrocas();
        return lst;
    }
}
