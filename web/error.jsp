<%-- 
    Document   : error.jsp
    Created on : Feb 7, 2021, 4:40:49 PM
    Author     : HP 840 G2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <font color = "red">
        ${requestScope.ERROR}
        </font>
    </body>
</html>
