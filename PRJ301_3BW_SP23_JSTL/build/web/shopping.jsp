<%-- 
    Document   : shopping.jsp
    Created on : Apr 17, 2023, 4:10:01 PM
    Author     : Huynh Van Phuot
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sp.bw.user.Product"%>
<%@page import="java.util.List"%>
<%@page import="sp.bw.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>

        <form action="MainController">
            <input type="submit" name="action" value="Get all"/>
            <input type="submit" name="action" value="View cart"/>
        </form>

        <c:if test="${requestScope.LIST_PRODUCT != null}">
            <c:if test="${not empty requestScope.LIST_PRODUCT}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Product ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Add</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" varStatus="counter" items="${requestScope.LIST_PRODUCT}">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${product.productID}</td>
                                <td>${product.name}</td>
                                <td>${product.price}$</td>
                                <td>
                                    <input type="number" name="quantity" value="1" min="1" required=""/>
                                </td>
                                <td>
                                    <input type="submit" name="action" value="Add"/>
                                    <input type="hidden" name="productID" value="${product.productID}"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
    <h3 style="color: orange">${requestScope.MESSAGE}</h3>

</body>
</html>
