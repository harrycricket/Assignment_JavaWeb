<%-- 
    Document   : viewCart
    Created on : Apr 17, 2023, 4:45:36 PM
    Author     : Huynh Van Phuot
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sp.bw.user.UserDTO"%>
<%@page import="sp.bw.user.Product"%>
<%@page import="sp.bw.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <%-- 
        <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'US'}" >
            <c:redirect url ="login.html"></c:redirect>
        </c:if>
        --%>
        <c:if test="${sessionScope.CART == null}">
            <h1 style="color: red"> Your cart is empty!</h1>
        </c:if>
        <c:if test="${sessionScope.CART != null}">
            <c:if test="${not empty sessionScope.CART}">
                <h1>Your Cart</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Product ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th>Edit</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" varStatus="counter" items="${sessionScope.CART.getCart().values()}">
                            <c:set var="total" value="${total + (product.quantity * product.price)}"/>
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    <input type="text" name="productID" value="${product.productID}" readonly="" />
                                </td>
                                <td>${product.name}</td>
                                <td>${product.price}$</td>
                                <td>
                                    <input type="number" name="quantity" value="${product.quantity}" min="1" required="">
                                </td>
                                <td>${product.quantity * product.price}$</td>
                                <td>
                                    <input type="submit" name="action" value="Edit" />
                                </td>
                                <td>
                                    <input type="submit" name="action" value="Remove" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            <h1>TOTAL: ${total}$</h1></br>
            <form action="MainController" method="POST">
                <input type="submit" name="action" value="Check Out" />
            </form>
        </c:if>
    </c:if>
    <h2><a style="color: orange" href="MainController?action=Get all" >Add more</a></h2><br>

</body>
</html>
