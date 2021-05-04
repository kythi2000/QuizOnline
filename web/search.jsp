<%-- 
    Document   : search
    Created on : Feb 14, 2021, 9:42:12 AM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <font color = "red">
        Welcome, ${sessionScope.REGISTRATION.fullname} <a href="logout">Logout</a><br>
        </font>
        <a href="createPage">Create Question</a>
        <form action="search" method="POST">
            Title: <input type="text" name="txtSearchValue" value="" required/>
            <input type="submit" value="Search" />
        </form>
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
                <c:forEach items="${requestScope.LISTSEARCH}" var="questionDTO" varStatus="counter">
                <form action="AddController" method="POST">
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
                            <a href="${urlRewriting}">Delete</a>
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
                </form>
            </c:forEach>
        </tbody>
    </table>
    <c:forEach begin="1" end="${requestScope.endPage}" var="i">
        <a id="${i}" href="search?index=${i}&txtSearchValue=${param.txtSearchValue}">${i}</a>
    </c:forEach>
    <script>
        document.getElementById('${param.index}').style.color = "red";
    </script>
</body>
</html>
