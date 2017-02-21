/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.EnderecoEntidade;
import entidade.TelefoneEntidade;
import java.io.IOException;
import java.sql.SQLException;
import persistencia.EnderecoDAO;
import persistencia.TelefoneDAO;
import util.GoogleMapsApi;

/**
 *
 * @author joaosantos
 */
public class ControladorEndereco {

    public static void editarEndereco(EnderecoEntidade e) throws SQLException, IOException {
        
        //pegar cidade
        String cidade = EnderecoDAO.pegarNomeCidadePeloId(e.getCidade());
        String estado = EnderecoDAO.pegarNomeEstadoPeloId(e.getUf());
        //monta query
        String queryPesquisa = GoogleMapsApi.montaQueryParaConsultaGoogleMapApi(e.getLogradouro(), e.getNumero().toString(), cidade, estado);
        //pega retorno json com google
        String JsonResult = GoogleMapsApi.pegarJSONdaQueryDigitadaGoogleWS(queryPesquisa);
        //filtra a corrdenada para persistir
        String coordenadas = GoogleMapsApi.filtrarLtnLtgDoJSONResult(JsonResult);
        e.setCoordenada(coordenadas);
        EnderecoDAO.editarEndereco(e);

    }

    public static String pegarCoordenadaUsuarioAtual(String idUser) throws SQLException {
        String result = "";
        
        result = EnderecoDAO.pegarCoordenadaUsuario(idUser);
        //remove a virgula fina
        return result.substring(0,result.length()-1);

    }

}
