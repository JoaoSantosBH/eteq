/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controle.ControladorPatrimonio;
import entidade.PatrimonioEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.NegociacaoDAO;

/**
 *
 * @author joaosantos
 */
public class EditarDadosPatrimonioServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            String idUser = request.getParameter("id_usuario");
            String idPat = request.getParameter("id_patrimonio");
            String titulo = request.getParameter("txt_titulo");
            String descricao = request.getParameter("txt_descricao");

            String estado = request.getParameter("txt_estado");
            String palavra = request.getParameter("txt_palavrachave");

            Boolean temNegocio = NegociacaoDAO.verificarNegociacaoComPatrimonioEspecifico(idPat);

            if (!temNegocio) {

                PatrimonioEntidade p = ControladorPatrimonio.pegarPatrimonioPeloId(idPat);
  
                p.setTitulo(titulo);
                p.setDescricao(descricao);
                p.setEstadoConservacao(estado);
                p.setPalavraChave(palavra);

                ControladorPatrimonio.editarPatrimonio(p);
                response.sendRedirect("editar_patrimonio.jsp?msg=1&idProd=" + idPat);
            } else {
                response.sendRedirect("editar_patrimonio.jsp?msg=3&idProd=" + idPat);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditarDadosPatrimonioServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("editar_patrimonio.jsp?msg=2");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
