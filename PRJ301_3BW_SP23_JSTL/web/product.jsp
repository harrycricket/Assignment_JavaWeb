<%-- 
    Document   : product
    Created on : Apr 20, 2023, 2:33:01 AM
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
        <title>Product Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'AD'}" >
            <c:redirect url ="login.html"></c:redirect>
        </c:if>

        <h1>List Products</h1>
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
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" varStatus="counter" items="${requestScope.LIST_PRODUCT}">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${product.productID}</td>
                                <td>${product.name}</td>
                                <td>${product.price}$</td>
                                <td>${product.quantity}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </tbody>
            </table>
        </c:if>
    </c:if>
    <h2 style="color: orange">${requestScope.SUCCESS_MSG}</h2>
    <a href="admin.jsp">Click here to return admin page</a><br>
</body>
</html>
