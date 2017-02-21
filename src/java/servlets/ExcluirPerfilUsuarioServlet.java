/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controle.ControladorUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.UsuarioDAO;

/**
 *
 * @author joaosantos
 */
public class ExcluirPerfilUsuarioServlet extends HttpServlet {

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

            String idUsuario = request.getParameter("id_usuario");
            String senha = request.getParameter("txt_senha");
            String verificaSenha = UsuarioDAO.pegarAtributoSenhaUsuario(idUsuario);

            Boolean temReserva = false;
            if (senha.equals(verificaSenha)) {
                temReserva = ControladorUsuario.excluirPerfilUsuario(idUsuario, senha, verificaSenha);
                if (temReserva) {
                    response.sendRedirect("excluir_perfil.jsp?msg=1");//NAO PODE
                } else {
                    HttpSession s = request.getSession();
                    s.invalidate();
                    response.sendRedirect("index.jsp");

                }

            } else {
                response.sendRedirect("excluir_perfil.jsp?msg=2");//SENHA NAO CONFERE
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExcluirPerfilUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("excluir_perfil.jsp?msg=3" + ex);
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
