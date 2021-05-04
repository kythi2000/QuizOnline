<%-- 
    Document   : history
    Created on : Feb 20, 2021, 8:54:42 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <h1>History</h1>
        Search:<br>
        <form action="searchHistory" method="POST">
            Subject: <input type="text" name="txtSubjectID" value="" required />
            <input type="submit" value="Search"/>
        </form>
        <c:set var="result" value = "${sessionScope.HISTORY}"/>
        <c:if test = "${not empty result}">


            <table border="1" style="width: 400; align-content: center" >
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Subject</th>
                        <th>Point</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var = "dto" items="${result}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.subjectID}
                            </td>
                            <td>
                                ${dto.point}
                            </td>
                            <td>                                    
                                ${dto.dateOfCreate}
                            </td>
                        </tr>
                    </c:forEach>  
                </tbody>
            </table>
            <font style="color: green">--------------------------------------------------------------------</font><br>
        </c:if>
    </body>
</html>
