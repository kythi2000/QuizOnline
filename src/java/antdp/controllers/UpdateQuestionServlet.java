/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.QuestionDAO;
import antdp.dtos.QuestionCreateError;
import antdp.dtos.QuestionDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "UpdateQuestionServlet", urlPatterns = {"/UpdateQuestionServlet"})
public class UpdateQuestionServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UpdateQuestionServlet.class);
    
    private final String ERROR_PAGE = "update.jsp";
    private final String ADMIN_PAGE = "GetAllServlet";

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
        
        String url = ERROR_PAGE;
        String questionID = request.getParameter("txtQuestionID");
        String title = request.getParameter("txtTitle");
        String op1 = request.getParameter("txtOp1");
        String op2 = request.getParameter("txtOp2");
        String op3 = request.getParameter("txtOp3");
        String answer = request.getParameter("txtAnswer");
        String subjectID = request.getParameter("txtSubjectID");
        QuestionCreateError errors = new QuestionCreateError();

        try {
            //1. check 4 faults of user
            boolean foundError = false;

            
            if (!answer.trim().equals(op1) && !answer.trim().equals(op2) && !answer.trim().equals(op3)) {
                foundError = true;
                errors.setAnswerErr("Answer is required like as one of options");
            }
            if (foundError) {
                //2. 
                request.setAttribute("UpdateQuestionError", errors);
            } else {
                QuestionDAO dao = new QuestionDAO();
                QuestionDTO questionDTO = new QuestionDTO(questionID, title, op1, op2, op3, answer, subjectID);
                boolean result = dao.update(questionDTO);
                if (result) {
                    url = ADMIN_PAGE;
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
