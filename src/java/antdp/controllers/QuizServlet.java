/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.QuizDAO;
import antdp.dtos.QuestionDTO;
import antdp.dtos.QuizDTO;
import antdp.dtos.RegistrationDTO;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
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
@WebServlet(name = "QuizServlet", urlPatterns = {"/QuizServlet"})
public class QuizServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(QuizServlet.class);

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

        String indexString = request.getParameter("index");
        String choosen = request.getParameter("choosen");
        String questionID = request.getParameter("questionID");
        String btnAction = request.getParameter("btnAction");
        String url = "error.jsp";
        try {
            HttpSession session = request.getSession();

            HashMap quiz = (HashMap) session.getAttribute("QUIZ");
            HashMap answer = (HashMap) session.getAttribute("ANSWER");
            List<String> listQuestionID = (List<String>) session.getAttribute("ListQuestionID");

            if (questionID != null) {
                answer.put(questionID, choosen);
            }

            if (btnAction == null || btnAction.equals("Next")) {
                int indexFirst = (int) session.getAttribute("INDEX");
                int index;
                if (indexString == null) {
                    index = indexFirst;
                } else {
                    index = Integer.parseInt(indexString) + 1;
                }
                String lastChoosen = (String) answer.get(listQuestionID.get(index));

                QuestionDTO questionDTO = (QuestionDTO) quiz.get(listQuestionID.get(index));

                session.setAttribute("lastChoosen", lastChoosen);
                session.setAttribute("QUESTION", questionDTO);
                session.setAttribute("INDEX", index);
                session.setAttribute("ANSWER", answer);

                url = "quiz.jsp";

                Date endTime = (Date) session.getAttribute("EndTime");
                Date now = new Date();
                long different = endTime.getTime() - now.getTime();
                session.setAttribute("TimeRemaining", different / 1000);

            } else if (btnAction.equals("Previous")) {
                int index;
                index = Integer.parseInt(indexString) - 1;
                QuestionDTO questionDTO = (QuestionDTO) quiz.get(listQuestionID.get(index));

                String lastChoosen = (String) answer.get(listQuestionID.get(index));
                
                
                session.setAttribute("lastChoosen", lastChoosen);
                session.setAttribute("QUESTION", questionDTO);
                session.setAttribute("INDEX", index);
                session.setAttribute("ANSWER", answer);
                url = "quiz.jsp";

                Date endTime = (Date) session.getAttribute("EndTime");
                Date now = new Date();
                long different = endTime.getTime() - now.getTime();
                session.setAttribute("TimeRemaining", different / 1000);

            } else if (btnAction.equals("Submit")) {
                String subjectID = "";
                Set<String> keys = answer.keySet();
                int correct = 0;
                if (keys != null) {
                    for (String key : keys) {
                        QuestionDTO questionDTO = (QuestionDTO) quiz.get(key);
                        String correctAnswer = questionDTO.getAnswer();
                        if (correctAnswer.equals(answer.get(key))) {
                            correct++;
                        }
                        subjectID = questionDTO.getSubjectID();
                    }
                }
                
                float point = (float) 10 / listQuestionID.size() * correct;
                request.setAttribute("POINT", point);
                request.setAttribute("NUMBER_CORRECT", correct);
                url = "point.jsp";

                QuizDAO quizDAO = new QuizDAO();
                String quizID = "";
                String lassID = quizDAO.getLastQuestionID();
                if (lassID == null) {
                    quizID = "Q-" + "1";
                } else {
                    String[] tmp = lassID.split("-");
                    quizID = "Q-" + (Integer.parseInt(tmp[1]) + 1);
                }
                RegistrationDTO registrationDTO = (RegistrationDTO) session.getAttribute("REGISTRATION");
                String email = registrationDTO.getEmail();
                QuizDTO quizDTO = new QuizDTO(quizID, email, subjectID, point);
                quizDAO.create(quizDTO);
                session.removeAttribute("QUESTION");
                session.removeAttribute("INDEX");
                session.removeAttribute("ANSWER");
                session.removeAttribute("ListQuestionID");
                session.removeAttribute("EndTime");
                session.removeAttribute("QUIZ");
                session.removeAttribute("lastChoosen");
                
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
