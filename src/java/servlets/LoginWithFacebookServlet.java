/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.restfb.Parameter;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import entidade.EnderecoEntidade;
import entidade.TelefoneEntidade;
import entidade.UsuarioEntidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.EnderecoDAO;
import persistencia.PersistenciaException;
import persistencia.TelefoneDAO;
import persistencia.UsuarioDAO;

/**
 *
 * @author joaosantos
 */
public class LoginWithFacebookServlet extends HttpServlet {

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
            FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("token"));
            User facebookUser = facebookClient.fetchObject("me", User.class);

            try {

                UsuarioEntidade user = UsuarioDAO.verificarSeJaExisteUsuarioCadastrado(facebookUser);

                if (user != null) {

                    user = UsuarioDAO.logar(user.getEmail(), user.getSenha());
                    HttpSession Sessao = request.getSession();
                    Sessao.setAttribute("UsuarioLogado", user);
                    response.sendRedirect("index.jsp");

                } else {
                    String urlFoto = "http://graph.facebook.com/" + facebookUser.getId() + "/picture?width=200&height=200";
                    String senha = "abc";
                    user = new UsuarioEntidade();
                    user.setEmail(facebookUser.getEmail());
                    user.setNome(facebookUser.getName());
                    user.setValidado(Short.parseShort("1"));
                    user.setSenha(senha);
                    user.setFoto(urlFoto);
                    user.setFotoAprovada(Short.parseShort("1"));
                    int id = UsuarioDAO.inserirFB(user);

                    EnderecoEntidade e = new EnderecoEntidade();
                    e.setIdUsuario(id);
                    e.setLogradouro("");
                    e.setNumero(0);
                    e.setComplemento("");
                    e.setBairro("");
                    e.setCep("00000-00");
                    e.setUf(0);
                    e.setCidade(0);
                    e.setCoordenada("000000");
                    EnderecoDAO.inserirEndereco(e);

                    TelefoneEntidade tel = new TelefoneEntidade();
                    tel.setIdUsuario(id);
                    tel.setDdd(0);
                    tel.setFixo("0000-0000");
                    tel.setCelular("0000-0000");
                    TelefoneDAO.inserirTelefone(tel);

                    user = UsuarioDAO.logar(user.getEmail(), user.getSenha());
                    HttpSession Sessao = request.getSession();
                    Sessao.setAttribute("UsuarioLogado", user);
                    response.sendRedirect("index.jsp");

                    //out.println("<h2> login ou senha Incorretos</h2>");
                }

            } catch (SQLException ex) {
                Logger.getLogger(LoginWithFacebookServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PersistenciaException ex) {
                Logger.getLogger(LoginWithFacebookServlet.class.getName()).log(Level.SEVERE, null, ex);
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
