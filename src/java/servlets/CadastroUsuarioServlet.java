/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controle.ControladorUsuario;
import entidade.UsuarioEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.PersistenciaException;
import persistencia.UsuarioDAO;

/**
 *
 * @author joaomarcelo
 */
@WebServlet(name = "CadastroUsuarioServlet", urlPatterns = {"/CadastroUsuarioServlet"})
public class CadastroUsuarioServlet extends HttpServlet {

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

            UsuarioEntidade Novo = new UsuarioEntidade();
            String email = request.getParameter("txt_email");
            String emailConfirma = request.getParameter("txt_email_confirma");
            String liTexto = request.getParameter("li_concordo");
            Novo.setNome(request.getParameter("txt_nome"));
            Novo.setEmail(request.getParameter("txt_email"));
            Novo.setSenha(request.getParameter("txt_senha"));

            if (!Novo.getNome().equals("") && !Novo.getSenha().equals("") && !Novo.getEmail().equals("")) {//valida se todos os campos foram preenchidos

                if (email.equals(emailConfirma) && !email.isEmpty()) {//valida se digitou email correto duas vezes
                    if (liTexto == null) {
                        response.sendRedirect("cadastre_se.jsp?msg=7");
                    } else {
                        ControladorUsuario.inserir(Novo);
                        
                        response.sendRedirect("cadastre_se.jsp?msg=1");
                    }
                } else {
                    //Login ou senha Incorretos
                    RequestDispatcher Rd = request.getRequestDispatcher("cadastre_se.jsp?msg=6");
                    Rd.include(request, response);
                }

            } else {
                //Login ou senha Incorretos
                RequestDispatcher Rd = request.getRequestDispatcher("cadastre_se.jsp?msg=5");
                Rd.include(request, response);
            }

        } catch (PersistenciaException e) {
            response.sendRedirect("cadastre_se.jsp?msg=2&excep=" + e.getMessage());

        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect("cadastre_se.jsp?msg=3");
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("cadastre_se.jsp?msg=4");
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
