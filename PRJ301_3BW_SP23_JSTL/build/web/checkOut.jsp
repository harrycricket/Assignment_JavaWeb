<%-- 
    Document   : checkOut
    Created on : Apr 19, 2023, 9:38:42 PM
    Author     : Huynh Van Phuot
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Check Out</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <c:set var="success" value="${false}" />
        <h2 style="color: yellowgreen">${requestScope.NOTIFICATION}</h2>
        <c:if test="${requestScope.NOTIFICATION.contains("Congratulations")}">
            <c:set var="success" value="${true}" />
        </c:if>
        <c:if test="${requestScope.NOTIFICATION.contains("out of stock")}">
            <c:set var="success" value="${false}" />
        </c:if>

        <c:choose>
            <c:when test="${success}">
                <h2><a style="color: orange; text-decoration: none" href="MainController?action=Get all">Click here to return the shopping page</a></h2><br>
            </c:when>
            <c:otherwise>
                <h2><a style="color: orange; text-decoration: none" href="viewCart.jsp">Click here to return your cart</a></h2><br>
            </c:otherwise>
        </c:choose>
    </body>
</html>
