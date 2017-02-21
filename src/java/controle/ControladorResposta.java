/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.ForumEntidade;
import entidade.NegociacaoEntidade;
import entidade.RespostaEntidade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.ForumDAO;
import persistencia.NegociacaoDAO;
import persistencia.RespostaDAO;
import persistencia.UsuarioDAO;
import util.EnviarEmail;

/**
 *
 * @author joaosantos
 */
public class ControladorResposta {

    public static void alterarRespostaParaLida(String idResposta) throws SQLException {

        RespostaDAO.alterarRespostaParaLida(idResposta);

    }

    public static boolean verificarSeExisteRespostaNaoLida(String idDestinatario) throws SQLException {
        boolean resp = RespostaDAO.existeRespostaNaoLida(idDestinatario);
        return resp;

    }

    public static void criarResposta(String idForum, String idUsuario, String resposta) throws SQLException {

        ForumEntidade f = ForumDAO.getForum(idForum);
        NegociacaoEntidade n = NegociacaoDAO.selecionarNegociacaoPorId(f.getIdNegociacao().toString());
        RespostaEntidade r = new RespostaEntidade();
        r.setIdForum(Integer.parseInt(idForum));
        r.setTextoResposta(resposta);

        if (idUsuario.equals(n.getIdUsuarioTem().toString())) {
            r.setIdUsuarioRemetente(Integer.parseInt(idUsuario));
            r.setIdUsuarioDestinatario(n.getIdUsuarioQuer());
            
        } 
        if(idUsuario.equals(n.getIdUsuarioQuer().toString())){
            r.setIdUsuarioRemetente(Integer.parseInt(idUsuario));
            r.setIdUsuarioDestinatario(n.getIdUsuarioTem());
        }
        

        r.setLida(Short.parseShort("0"));

        Boolean resp = RespostaDAO.criarResposta(r);
        if (resp) {
            EnviarEmail env = new EnviarEmail();
            String email = UsuarioDAO.pegarAtributoEmailUsuarioPeloId(r.getIdUsuarioDestinatario().toString());
            String nomeDest = UsuarioDAO.pegarAtributoNomeDeUsuarioPeloId(r.getIdUsuarioDestinatario().toString());

            String assunto = f.getAssunto();
            env.enviarNotificacaoNovaResposta(email, nomeDest, assunto);
        }

    }

    public static List<RespostaEntidade> pegarRespostas(String idForum) throws SQLException {
        List<RespostaEntidade> lst = new ArrayList<RespostaEntidade>();
        lst = RespostaDAO.pegarRespostasDoForum(idForum);
        return lst;
    }

    static void apagarRespostasReferentesAoForumInativo(int idForum) throws SQLException{
        
        RespostaDAO.apagarRespostasReferentesoForumInativo(idForum);
        
        
    }

    public static List<RespostaEntidade> pegarUltimaRespostaDoForumParaPegarData(String idForum) throws SQLException{
        List<RespostaEntidade> lst = new ArrayList<RespostaEntidade>();
        lst = RespostaDAO.pegarUltimaRespostaDoForumParaPegarData(idForum);
        return lst;
    }

}
