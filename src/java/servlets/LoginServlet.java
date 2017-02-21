package servlets;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidade.UsuarioEntidade;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import persistencia.UsuarioDAO;

/**
 *
 * @author joaomarcelo
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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
            String login = request.getParameter("txt_email");
            String senha = request.getParameter("txt_senha");

            UsuarioEntidade Autenticado;
            Autenticado = UsuarioDAO.logar(login, senha);

            if (Autenticado != null && Autenticado.getValidado() !=0) {
                
                HttpSession Sessao = request.getSession();
                Sessao.setAttribute("UsuarioLogado", Autenticado);
                response.sendRedirect("index.jsp");
                

            } else {
                //Login ou senha Incorretos
                RequestDispatcher Rd = request.getRequestDispatcher("identifique_se.jsp?e=1");
                Rd.include(request, response);

                //out.println("<h2> login ou senha Incorretos</h2>");
            }
        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect("identifique_se.jsp?e=2");

        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("erro.jsp");

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
