<%-- 
    Document   : create
    Created on : Dec 20, 2020, 8:15:07 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <title>Create Question</title>
    </head>
    <body style="margin: 15px">
        <h1 style="text-align: center">Create Question</h1>
        <form action="create" method="POST">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Title*</span>
                </div>
                <input type="text" name="txtTitle" value="${param.txtTitle}" 
                       class="form-control" aria-label="Default" 
                       aria-describedby="inputGroup-sizing-default"
                       minlength="5" maxlength="500" required>(5-500 chars)
            </div>


            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Option 1*</span>
                </div>
                <input type="text" name="txtOp1" value="${param.txtOp1}" 
                       class="form-control" aria-label="Default" 
                       aria-describedby="inputGroup-sizing-default"
                       minlength="1" maxlength="200" required >(1-200 chars)
            </div>



            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Option 2*</span>
                </div>
                <input type="text" name="txtOp2" value="${param.txtOp2}" 
                       class="form-control" aria-label="Default" 
                       aria-describedby="inputGroup-sizing-default"
                       minlength="1" maxlength="200" required>(1-200 chars)
            </div>


            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Option 3*</span>
                </div>
                <input type="text" name="txtOp3" value="${param.txtOp3}" 
                       class="form-control" aria-label="Default" 
                       aria-describedby="inputGroup-sizing-default"
                       minlength="1" maxlength="200" required>(1-200 chars)
            </div>


            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Answer *</span>
                </div>
                <input type="text" name="txtAnswer" value="${param.txtAnswer}" class="form-control" 
                       aria-label="Default" aria-describedby="inputGroup-sizing-default">
            </div>
            <c:if test = "${not empty CreateQuestionError.answerErr}">
                <font color="red">
                ${CreateQuestionError.answerErr}
                </font>  <br>
            </c:if>
            Subject*: <select name="txtSubjectID">
                <c:forEach items="${sessionScope.ListSubjectID}" var="subjectID">
                    <option>${subjectID}</option>
                </c:forEach>
            </select>
            <br>
            <br>
            <button type="submit" class="btn btn-outline-primary">Create</button>
            <button type="reset" class="btn btn-dark">Reset</button>
        </form>
    </body>
</html>
