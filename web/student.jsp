<%-- 
    Document   : student.jsp
    Created on : Feb 7, 2021, 2:47:53 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Page</title>
    </head>
    <body>
        <h1>Student</h1>
        <font color = "red">
        Welcome, ${sessionScope.REGISTRATION.fullname} <a href="logout">Logout</a><br>
        </font>
        <form action="createQuiz">
            Choose quiz: <select name="txtSubjectID">
                <c:forEach items="${sessionScope.ListSubjectID}" var="subjectID">
                    <option>${subjectID}</option>
                </c:forEach>
            </select>
            <input type="submit" value="OK!!!" />
        </form>
        <a href="history">History</a>
    </body>
</html>
