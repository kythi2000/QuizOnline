/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.RegistrationDAO;
import antdp.daos.SubjectDAO;
import antdp.dtos.RegistrationDTO;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import org.apache.log4j.Logger;

/**
 *
 * @author HP 840 G2
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

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

        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");

        String url = "";
        try {
            HttpSession session = request.getSession();
            RegistrationDAO dao = new RegistrationDAO();

            String hashPassword = "";
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] digestBytes = messageDigest.digest();
            hashPassword = DatatypeConverter.printHexBinary(digestBytes).toLowerCase();

            String role = dao.checkLogin(email, hashPassword);
            if (role.equals("failed")) {
                request.setAttribute("ERROR", "Email or password is incorrect");
            } else {
                RegistrationDTO dto = dao.getRegistrationByKey(email);
                session.setAttribute("REGISTRATION", dto);

                SubjectDAO subjectDAO = new SubjectDAO();
                List<String> listSubjectID = subjectDAO.getAllSubjectID();
                session.setAttribute("ListSubjectID", listSubjectID);
                
                if (role.equals("admin")) {
                    url = "GetAllServlet";
                } else if (role.equals("student")) {
                    url = "student.jsp";
                } else {
                    request.setAttribute("ERROR", "Your role is invalid");
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
