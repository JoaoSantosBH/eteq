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
public class EditarPerfiFotoUploadlServlet extends HttpServlet {

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
            int idUsuario = 0;
            String finalidade = "fotoPerfil";

            ArrayList<ParametrosPagina> lst = new ArrayList<ParametrosPagina>();
            HttpSession session = request.getSession();
            UsuarioEntidade Logado = (UsuarioEntidade) session.getAttribute("UsuarioLogado");
            ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());

            PrintWriter writer = response.getWriter();

            StringBuilder json = new StringBuilder();

            List<FileItem> items = uploadHandler.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) //pegando os atributosd da pagina pelo MULTIPART form data
                {
                    ParametrosPagina p = new ParametrosPagina();
                    p.setName(item.getFieldName());
                    p.setValor(item.getString());
                    lst.add(p);
                }
            }
            
            json.setLength(1);
            for (FileItem item : items) {

                if (!item.isFormField()) {

                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("name", item.getName());
                    jsonObj.put("size", item.getSize());
                    jsonObj.put("url", DESTINATION_DIR_PATH + "/" + item.getName());
                    jsonObj.put("deleteUrl", DESTINATION_DIR_PATH + "/" + item.getName());
                    jsonObj.put("deleteType", "DELETE");
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(jsonObj);

                    //CRIANDO MD5   PELO Id DO Usuario
                    MD5 md5 = new MD5();
                    String MD5 = md5.criarMD5ParaEnviarLink(Logado.getIdUsuario().toString());
                    String nome = MD5 + "_" + item.getName();

                    //LEMBRETE: limitar numero de imagensa para 5
//                    String path = request.getServletContext().getRealPath("/") + "fotos_usuarios/";

                    String path = getServletContext().getInitParameter("uploadDirectory");
                    String foto = "/fotos_usuarios/" + nome;
                    File file = new File(path, nome);

                    System.out.println(path);
                    item.write(file);

                    jsonArray.write(writer);

                    //redefiniNDO tamanho DA imagem
                    BufferedImage originalImage = ImageIO.read(new File(path + nome));
                    int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                    BufferedImage resizeImageHintJpg = RedefinirTamanhoImagem.resizeImageWithHint(originalImage, type, finalidade);
                    ImageIO.write(resizeImageHintJpg, "jpg", new File(path + nome));

                    BufferedImage resizeImageHintPng = RedefinirTamanhoImagem.resizeImageWithHint(originalImage, type, finalidade);
                    ImageIO.write(resizeImageHintPng, "png", new File(path + nome));
                    ////////////////
                     for (ParametrosPagina p : lst) {
                       
                        if (p.getName().equals("id_usuario")) {
                            idUsuario = Integer.parseInt(p.getValor());
                        }
                    }

                    

                    if (foto == null) {
                        foto = "/img/foto_default.png";
                    }
                    String nomeAdmin = AdminDAO.pegarNomeAdmin();
                    String emailAdmin = AdminDAO.pegarEmailAdmin();
                    ControladorUsuario.cadastrarFotoUsuario(idUsuario, foto, nomeAdmin, emailAdmin);


                    
                    

                    response.sendRedirect("editar_perfil.jsp?msg=5");

                }
            }
        } catch (PersistenciaException e) {
            response.sendRedirect("editar_perfil.jsp?msg=2&excep=" + e.getMessage());

        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect("editar_perfil.jsp?msg=3");
        } catch (Exception e) {
            System.out.println(e);
            response.sendRedirect("editar_perfil.jsp?msg=4"+ e);
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
            Logger.getLogger(EditarPerfiFotoUploadlServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditarPerfiFotoUploadlServlet.class.getName()).log(Level.SEVERE, null, ex);
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
