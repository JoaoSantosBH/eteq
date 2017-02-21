/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.ParametrosDAO;

/**
 *
 * @author joaosantos
 */
public class ParametrosFotoPerfilServlet extends HttpServlet {

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
            String largura = request.getParameter("txt_largura");
            String altura = request.getParameter("txt_altura");
            String idAlt = request.getParameter("id_altfoto");
            String idLarg = request.getParameter("id_largfoto");

            if (!largura.equals("") && !altura.equals("")) {//valida se todos os campos foram preenchidos

                ParametrosDAO.alterarParametros(idAlt, altura);
                ParametrosDAO.alterarParametros(idLarg, largura);

                response.sendRedirect("manter_parametros.jsp?msg=1");

            } else {
                RequestDispatcher Rd = request.getRequestDispatcher("manter_parametros.jsp?msg=2");
                Rd.include(request, response);
            }

        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect("manter_parametros.jsp?msg=3");
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("manter_parametros.jsp?msg=3");
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
