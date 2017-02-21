/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.HistoricoEntidade;
import entidade.PatrimonioEntidade;
import java.sql.SQLException;
import java.util.ArrayList;
import persistencia.HistoricoDAO;
import persistencia.PatrimonioDAO;

/**
 *
 * @author joaosantos
 */
public class ControladorHistorico {

    public static ArrayList<HistoricoEntidade> visualizarMeuHistoricodeAnuncios(String idUsuario) throws SQLException {

        ArrayList<HistoricoEntidade> lstH = new ArrayList<HistoricoEntidade>();
        lstH = HistoricoDAO.visualizarHistoricoDeAnuncios(idUsuario);

        return lstH;
    }

    public static int pegarQuantidadeDePatrimoniosNegociados() throws SQLException {
        int total = 0;
        total = HistoricoDAO.pegarQuantidadeDePatrimoniosNegociados();

        return total;
    }

    public static int pegarQuantidadeDePatrimoniosConcluidos() throws SQLException {
        int total = 0;
        total = HistoricoDAO.pegarQuantidadeDePatrimoniosConcluidos();
        return total;
    }

    public static int pegarQuantidadeDePatrimoniosExcluidos() throws SQLException {
        int total = 0;
        total = HistoricoDAO.pegarQuantidadeDePatrimoniosExcluidos();
        return total;
    }

    public static int pegarQuantidadeDePatrimoniosExpirados() throws SQLException {
        int total = 0;
        total = HistoricoDAO.pegarQuantidadeDePatrimoniosExpirados();
        return total;
    }

    public static int pegarQuantidadeDePatrimoniosCancelados() throws SQLException {
        int total = 0;
        total = HistoricoDAO.pegarQuantidadeDePatrimoniosCancelados();
        return total;
    }

    public static int pegarQuantidadeDeDoacoesCancelados() throws SQLException {
        int total = 0;
        total = HistoricoDAO.pegarQuantidadeDeDoacoesCancelados();
        return total;
    }

    public static int pegarQuantidadeDeTrocasaCancelados() throws SQLException {
        int total = 0;
        total = HistoricoDAO.pegarQuantidadeDeTrocasCancelados();
        return total;
    }

    public static String emitirRelatorioAnunciosConcluidos() throws SQLException {

        int total = pegarQuantidadeDePatrimoniosNegociados();
        int totalConcluido = pegarQuantidadeDePatrimoniosConcluidos();
        int totalExpirado = pegarQuantidadeDePatrimoniosExpirados();
        int totalDoacaoCancelada = pegarQuantidadeDeDoacoesCancelados();
        int totalTrocaCancelada = pegarQuantidadeDeTrocasaCancelados();
        int totalExcluidos = pegarQuantidadeDePatrimoniosExcluidos();
        
        //formatando string para formato JS
        String relatorio = "[['Relatorio', 'Negociacoes'],";
        relatorio += "['Negociacoes Efetivadas', " + totalConcluido + "],";
        relatorio += "['Negociacoes Expiradas', " + totalExpirado + "],";
        relatorio += "['Doacoes Canceladas'," + totalDoacaoCancelada + "],";
        relatorio += "['Trocas Canceladas', " + totalTrocaCancelada + "],";
        relatorio += "['Negociacoes Excluidas', " + totalExcluidos + "]]";

        return relatorio;
    }
}
