/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controle.ControladorPatrimonio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joaosantos
 */
public class SolicitarTrocaServlet extends HttpServlet {

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

            String idPatTem = request.getParameter("id_produto");
            String idUsuQuer = request.getParameter("id_usuario");
            try {

                //Sistema verifica se o produto não é do proponente
                Boolean ehMeu = ControladorPatrimonio.verificarSeProponmenteDonoPatrimonio(idPatTem, idUsuQuer);
                
                if (!ehMeu) {
                    
                    Boolean rola = controle.ControladorNegociacao.tenhoPatrimonios(idUsuQuer);
                    if (rola) {
                        
                        response.sendRedirect("seleciona_itens_troca.jsp?idPatTem=" + idPatTem);
                        //exibe lista de patrimônios cadastrados pelo usuário 

                    }else {
                        response.sendRedirect("produto.jsp?msg=5");
                    }
                } else {
                    
                    response.sendRedirect("produto.jsp?msg=4");
                }
            } catch (SQLException ex) {
                Logger.getLogger(SolicitarTrocaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

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