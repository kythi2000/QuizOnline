<%-- 
    Document   : quiz.jsp
    Created on : Feb 19, 2021, 2:21:09 PM
    Author     : HP 840 G2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz</title>
    </head>
    <body style="margin: 5px; padding: 200px">
        <h1></h1>


        Ahihi!!!<br>

        <input type="hidden" id="TimeRemaining" value="${sessionScope.TimeRemaining}" />

        Time:
        <span style="color: red" id="timer">

        </span>


        <script>
            var timeRemaining = document.getElementById("TimeRemaining").value;
            var show = document.getElementById("timer");
            var time = parseInt(timeRemaining);
            setInterval(function () {
                time--;
                show.innerHTML = convert(time);
            }, 1000);
            function convert(t) {
                var m = Math.floor(t / 60);
                s = t - m * 60;
                return  (m < 10 ? '0' + m : m) + ':' + (s < 10 ? '0' + s : s);
            }
            setTimeout(function () {
                document.getElementById("submit").click();
            }, time * 1000);
        </script>

        <c:set var="question" value="${sessionScope.QUESTION}"/>
        <c:if test="${not empty question}">
            <div style="font-size: 15px">
                <form action="quiz">
                    <c:set var="questionIndex" value="${sessionScope.INDEX+1}"/>
                    <font style="font-size: 20px; font-weight: bold ">${questionIndex}. ${question.title} </font><br>

                    <c:if test="${sessionScope.lastChoosen eq question.option1}"> 
                        <input type="radio" name="choosen" value="${question.option1}"  checked="checked" />
                        ${question.option1} <br>
                    </c:if>  
                    <c:if test="${sessionScope.lastChoosen ne question.option1}"> 
                        <input type="radio" name="choosen" value="${question.option1}"/>
                        ${question.option1} <br>
                    </c:if> 

                    <c:if test="${sessionScope.lastChoosen eq question.option2}"> 
                        <input type="radio" name="choosen" value="${question.option2}"  checked="checked" />
                        ${question.option2} <br>
                    </c:if>  
                    <c:if test="${sessionScope.lastChoosen ne question.option2}"> 
                        <input type="radio" name="choosen" value="${question.option2}"/>
                        ${question.option2} <br>
                    </c:if>  

                    <c:if test="${sessionScope.lastChoosen eq question.option3}"> 
                        <input type="radio" name="choosen" value="${question.option3}"  checked="checked" />
                        ${question.option3} <br>
                    </c:if>  
                    <c:if test="${sessionScope.lastChoosen ne question.option3}"> 
                        <input type="radio" name="choosen" value="${question.option3}"/>
                        ${question.option3} <br>
                    </c:if>  


                    <c:if test="${questionIndex > 1}">
                        <input type="submit" name="btnAction" value="Previous" />
                        <input type="hidden" name="index" value="${sessionScope.INDEX}" /> 
                    </c:if>
                    <c:if test="${questionIndex < sessionScope.ListQuestionID.size()}">
                        <input type="submit" name="btnAction" value="Next" />
                        <input type="hidden" name="index" value="${sessionScope.INDEX}" /> 
                    </c:if>
                        
                    <br>
                    <input id="submit" name="btnAction" type="submit" value="Submit"/>
                    <input type="hidden" name="questionID" value="${question.questionID}" /> 
                </form>
            </div>
        </c:if>
    </body>
</html>
