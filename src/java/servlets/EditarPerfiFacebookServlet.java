/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controle.ControladorEndereco;
import controle.ControladorUsuario;
import entidade.EnderecoEntidade;
import entidade.TelefoneEntidade;
import entidade.UsuarioEntidade;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import persistencia.AdminDAO;
import persistencia.EnderecoDAO;
import persistencia.PersistenciaException;
import persistencia.TelefoneDAO;
import upload.JSONArray;
import upload.JSONObject;
import upload.RedefinirTamanhoImagem;
import util.MD5;
import util.ParametrosPagina;

/**
 *
 * @author joaosantos
 */
public class EditarPerfiFacebookServlet extends HttpServlet {

    private static final String DESTINATION_DIR_PATH = "http://localhost:8084";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */

            String logradouro = request.getParameter("txt_logradouro");
            String numero = request.getParameter("txt_numero");
            String complemento = request.getParameter("txt_complemento");
            String bairro = request.getParameter("txt_bairro");
            String cep = request.getParameter("txt_cep");
            String uf = request.getParameter("estados");
            String cidade = request.getParameter("cidades");
            String idUsuario = request.getParameter("id_usuario");

            String ddd = request.getParameter("txt_ddd");
            String fixo = request.getParameter("txt_fixo");
            String celular = request.getParameter("txt_cel");

            HttpSession session = request.getSession();
            UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");


            EnderecoEntidade end = new EnderecoEntidade();
            TelefoneEntidade tel = new TelefoneEntidade();
            end.setIdUsuario(Integer.parseInt(idUsuario));
            end.setLogradouro(logradouro);
            end.setNumero(Integer.parseInt(numero));
            end.setComplemento(complemento);
            end.setBairro(bairro);
            end.setCep(cep);
            end.setUf(Integer.parseInt(uf));
            end.setCidade(Integer.parseInt(cidade));
            

            tel.setIdUsuario(Integer.parseInt(idUsuario));
            tel.setDdd(Integer.parseInt(ddd));
            tel.setFixo(fixo);
            tel.setCelular(celular);

            ControladorEndereco.editarEndereco(end);
            TelefoneDAO.editarTelefones(tel);

            response.sendRedirect("editar_perfil.jsp?msg=1");

        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect("editar_perfil.jsp?msg=3");
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("editar_perfil.jsp?msg=4");
        } finally {

            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(EditarPerfiFacebookServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(EditarPerfiFacebookServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
