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
import java.util.List;
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
@WebServlet(name = "GetAllServlet", urlPatterns = {"/GetAllServlet"})
public class GetAllServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(GetAllServlet.class);
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
        
        String url = "admin.jsp";
        String indexString  = request.getParameter("index");
        try {
            int index = 0;
            if (indexString == null || indexString.equals("")) {
                index = 1;
            }else{
                index = Integer.parseInt(indexString);
            }
            
            SubjectDAO subjectDAO = new SubjectDAO();
            QuestionDAO questionDAO = new QuestionDAO();
            int count = subjectDAO.countAllSubject();
            List<SubjectDTO> listSubject = subjectDAO.getAllSubject(index);
            for (SubjectDTO subjectDTO : listSubject) {
                List<QuestionDTO> listQuestion = questionDAO.getAllQuestionBySubjectID(subjectDTO.getSubjectID());
                subjectDTO.setListQuestion(listQuestion);
            }
            
            int pageSize = 2;
            int endPage = 0;
            endPage = count / pageSize;
            if(count % pageSize != 0){
                endPage++;
            }
            
            request.setAttribute("endPage", endPage);
            request.setAttribute("LIST", listSubject);
            
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }finally{
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
