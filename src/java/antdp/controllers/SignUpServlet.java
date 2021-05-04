/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.RegistrationDAO;
import antdp.dtos.AccountCreationError;
import antdp.dtos.RegistrationDTO;
import java.io.IOException;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import org.apache.log4j.Logger;

/**
 *
 * @author HP 840 G2
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SignUpServlet.class);

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

        String url = "error.jsp";
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        String role = request.getParameter("ckRole");
        AccountCreationError errors = new AccountCreationError();
        try {
            boolean foundError = false;
            if (!password.trim().equals(confirm.trim())) {
                foundError = true;
                errors.setConfirmErr("Confirm doesn't match password");
            }
            if (foundError) {
                request.setAttribute("CREATEERROR", errors);
                url = "signUp.jsp";
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                String hashPassword = "";
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(password.getBytes());
                byte[] digestBytes = messageDigest.digest();
                hashPassword = DatatypeConverter.printHexBinary(digestBytes).toLowerCase();
                
                RegistrationDTO dto = new RegistrationDTO(email, hashPassword, fullname, role);
                boolean result = dao.create(dto);
                if (result) {
                    url = "login.jsp";
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                errors.setUsernameIsExistErr(email + " is existed");
                request.setAttribute("CREATEERROR", errors);
                url = "signUp.jsp";
            }
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
