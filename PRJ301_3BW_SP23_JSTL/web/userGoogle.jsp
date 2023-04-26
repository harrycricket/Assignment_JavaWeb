<%-- 
    Document   : userGoogle
    Created on : Apr 22, 2023, 9:49:04 PM
    Author     : Huynh Van Phuot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Information</title>
    </head>
    <body>
        <h1>Welcome ${requestScope.name}</h1>
       
        ID: ${requestScope.id} </br>
        Name: ${requestScope.name}</br>
        Email: ${requestScope.email}
        <form action="MainController">
            <input type="submit" value="Logout" name="action" style="margin-top: 5px"/>
        </form>
    </body>
</html>
