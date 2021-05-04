/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.SubjectDAO;
import antdp.dtos.SubjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author HP 840 G2
 */
@WebServlet(name = "UpdateSubjectServlet", urlPatterns = {"/UpdateSubjectServlet"})
public class UpdateSubjectServlet extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(UpdateQuestionServlet.class);

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
        
        String time_String = request.getParameter("txtTime");
        String numOfQuestion_String = request.getParameter("txtNumberOfQuestion");
        String subjecID = request.getParameter("txtSubjectID");
        int time = Integer.parseInt(time_String);
        int numOfQues = Integer.parseInt(numOfQuestion_String);
        try {
            SubjectDAO dAO = new SubjectDAO();
            SubjectDTO subjectDTO = dAO.getSubjectByID(subjecID);
            subjectDTO.setTime(time);
            subjectDTO.setNumberOfQuestions(numOfQues);
            dAO.update(subjectDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }finally{
            request.getRequestDispatcher("GetAllServlet").forward(request, response);
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
