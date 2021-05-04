/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.filters;

import antdp.dtos.RegistrationDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AuthenticatedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //kiểm tra session
        //lay session
        String url = "notfound.jsp";
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session = req.getSession(false);
        // lấy attribute
        //dòng này khi hết session thì ra lỗi cần fix
        RegistrationDTO account = (RegistrationDTO) session.getAttribute("REGISTRATION");
        //admin
        //lay duong dan request
        String uri = req.getRequestURI();
        //lay vi tri dung
        int lastIndexOf = uri.lastIndexOf("/");
        //lay ten resource
        String resource = uri.substring(lastIndexOf + 1);

        //----------------LIST ACTIVITIES ADMIN ROLE----------------------------------------
        List<String> resource_allow_Admin = new ArrayList<>();
        //
        resource_allow_Admin.add("search");
        resource_allow_Admin.add("searchPage");
        resource_allow_Admin.add("create");
        resource_allow_Admin.add("createPage");
        resource_allow_Admin.add("update");
        resource_allow_Admin.add("updatePage");
        resource_allow_Admin.add("getAll");
        resource_allow_Admin.add("getSubjectID");
        resource_allow_Admin.add("updateSubject");
        

        //----------------LIST ACTIVITIES STUDENT ROLE--------------------------------------
        List<String> resource_allow_Student = new ArrayList<>();
        //
        resource_allow_Student.add("quizPage");
        resource_allow_Student.add("createQuiz");
        resource_allow_Student.add("quiz");
        resource_allow_Student.add("historyPage");
        resource_allow_Student.add("searchHistory");
        resource_allow_Student.add("history");
        

        //----------------ADMIN ROLE----------------------------------------
        if (account != null && account.getRole().equals("admin")) {
            if (resource_allow_Admin.contains(resource)) {
                //request
                chain.doFilter(request, response);
                //response
            } //neu sai role thi ra login
            else {
                req.getRequestDispatcher(url).forward(request, response);
            }
        }

        //----------------STUDENT ROLE--------------------------------------
        if (account != null && account.getRole().equals("student")) {
            if (resource_allow_Student.contains(resource)) {
                //request
                chain.doFilter(request, response);
                //response
            } //neu sai role thi ra login
            else {
                req.getRequestDispatcher(url).forward(request, response);
            }
        }

        if (account == null) {
            res.sendRedirect("login.jsp");
        }

    }

    @Override
    public void destroy() {

    }

}
