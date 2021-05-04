<%-- 
    Document   : point
    Created on : Feb 20, 2021, 11:47:37 AM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Point Page</title>
    </head>
    <body>
        <font color = "red" style="font-size: 50px">
        Welcome, ${sessionScope.REGISTRATION.fullname} <a href="LogoutServlet">Logout</a><br>
        Correct Answer: ${requestScope.NUMBER_CORRECT}/${sessionScope.numOfQuestions} <br>
        Point: ${requestScope.POINT}
        </font><br>
        <a href="studentPage">Home</a>
        
    </body>
</html>
