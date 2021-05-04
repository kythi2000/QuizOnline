<%-- 
    Document   : admin.jsp
    Created on : Feb 7, 2021, 2:47:41 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>

        <font color = "red">
        Welcome, ${sessionScope.REGISTRATION.fullname}  
        <a href="logout">Logout</a><br>
        </font>

        <a href="createPage">Create Question</a>
        <form action="search" method="POST">
            Title: <input type="text" name="txtSearchValue" value="" required/>
            <input type="submit" value="Search" />
        </form>
        <c:forEach items="${requestScope.LIST}" var="subjectDTO">
            <h2>${subjectDTO.name}</h2><br>
            <form action="updateSubject" method="POST">
                Time: <input type="number" name="txtTime" value="${subjectDTO.time}" min="1" required />(minutes) <br> 
                Number of question: <input type="number" name="txtNumberOfQuestion" value="${subjectDTO.numberOfQuestions}" min="1" required />  <br>
                <input type="hidden" name="txtSubjectID" value="${subjectDTO.subjectID}" />
                <input type="submit" value="Update" />
            </form>       
            <c:if test="${not empty subjectDTO.listQuestion}" >
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Title</th>
                            <th>Option 1</th>
                            <th>Option 2</th>
                            <th>Option 3</th>
                            <th>Answer</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${subjectDTO.listQuestion}" var = "questionDTO" varStatus = "counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${questionDTO.title}</td>
                                <td>${questionDTO.option1}</td>
                                <td>${questionDTO.option2}</td>
                                <td>${questionDTO.option3}</td>
                                <td>${questionDTO.answer}</td>
                                <td>
                                    <c:url var = "urlRewriting" value = "delete">
                                        <c:param name="txtQuestionID" value="${questionDTO.questionID}"/>
                                        <c:param name="index" value="${param.index}"/>                                                                   
                                    </c:url>
                                    <a href="${urlRewriting}" onclick="return ConfirmDelete()">Delete</a>
                                </td>
                                <td>
                                    <form action="updatePage" method="POST">
                                        <input type="submit" value="Update"/>
                                        <input type="hidden" name="txtQuestionID" value="${questionDTO.questionID}" />
                                        <input type="hidden" name="txtTitle" value="${questionDTO.title}" />
                                        <input type="hidden" name="txtOp1" value="${questionDTO.option1}" />
                                        <input type="hidden" name="txtOp2" value="${questionDTO.option2}" />
                                        <input type="hidden" name="txtOp3" value="${questionDTO.option3}" />
                                        <input type="hidden" name="txtAnswer" value="${questionDTO.answer}" />
                                        <input type="hidden" name="index" value="${param.index}" />  
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:forEach>
        <c:forEach begin="1" end="${requestScope.endPage}" var="i">
            <a id="${i}" href="getAll?index=${i}">${i}</a>
        </c:forEach>
        <script>
            document.getElementById('${param.index}').style.color = "red";
        </script>

    </body>
</html>
