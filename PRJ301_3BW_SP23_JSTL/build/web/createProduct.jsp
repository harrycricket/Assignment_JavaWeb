<%-- 
    Document   : createProduct
    Created on : Apr 20, 2023, 1:56:04 AM
    Author     : Huynh Van Phuot
--%>

<%@page import="sp.bw.user.ProductError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Product</title>
    </head>
    <body>
        <h1>Create a new product</h1>

        <form action="MainController" method="POST">
            Product ID     <input type="text" name="productID" required=""/>
            ${requestScope.PRODUCT_ERROR.productIDError}<br>
            Name     <input type="text" name="name" required=""/>
            ${requestScope.PRODUCT_ERROR.nameError}<br>
            Price     <input type="number" name="price" value="1" min="1" required=""/><br>
            Quantity     <input type="number" name="quantity" value="1" min="1" required=""/><br>
            <input type="submit" name="action" value="Create Product" />
            <input type="reset" value="Reset" />
        </form>
        ${requestScope.PRODUCT_ERROR.error}<br>
    </body>
</html>
