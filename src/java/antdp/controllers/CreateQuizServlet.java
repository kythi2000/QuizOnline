/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.QuestionDAO;
import antdp.daos.SubjectDAO;
import antdp.dtos.QuestionDTO;
import antdp.dtos.SubjectDTO;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author HP 840 G2
 */
@WebServlet(name = "CreateQuizServlet", urlPatterns = {"/CreateQuizServlet"})
public class CreateQuizServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CreateQuestionServlet.class);
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
        
        
        String subjectID = request.getParameter("txtSubjectID");
        try {
            int index = 0;
            HttpSession session = request.getSession();
            QuestionDAO questionDAO = new QuestionDAO();
            SubjectDAO subjectDAO = new SubjectDAO();
            SubjectDTO subjectDTO = subjectDAO.getSubjectByID(subjectID);
            int time = subjectDTO.getTime();
            int numOfQuestion = subjectDTO.getNumberOfQuestions();
            List<String> listQuestionID = questionDAO.getQuestionIDBySubjectID(subjectDTO.getSubjectID(), numOfQuestion);
            
            Map<String, QuestionDTO> quiz = new HashMap();
            Map<String, String> answer = new HashMap();
            for (String questionID : listQuestionID) {
                QuestionDTO questionDTO = questionDAO.getQuestionByID(questionID);
                quiz.put(questionID, questionDTO);
            }
            
            session.setAttribute("ListQuestionID", listQuestionID);
            session.setAttribute("QUIZ", quiz);
            session.setAttribute("INDEX", index);
            session.setAttribute("ANSWER", answer);
            session.setAttribute("numOfQuestions", numOfQuestion);
       
            // get end time of quiz     
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.SECOND, time * 60);            
            session.setAttribute("EndTime", calendar.getTime());
            session.setAttribute("TimeRemaining", time * 60);
            
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }finally{
            response.sendRedirect("quiz");
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
