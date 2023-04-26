<%-- 
    Document   : createUser
    Created on : Apr 15, 2023, 2:46:48 PM
    Author     : Huynh Van Phuot
--%>

<%@page import="sp.bw.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
    </head>
    <body>
        <h1>Create a new user</h1>

        <form action="MainController" method="POST">
            User ID     <input type="text" name="userID" required=""/>
            ${requestScope.USER_ERROR.userIDError}<br>
            Full Name     <input type="text" name="fullName" required=""/>
            ${requestScope.USER_ERROR.fullNameError}<br>
            Role ID     <input type="text" name="roleID" value="US" readonly=""/><br>
            Password     <input type="password" name="password" required=""/><br>
            Confirm     <input type="password" name="confirm" required=""/>
            ${requestScope.USER_ERROR.confirmError}<br>
            <input type="submit" name="action" value="Create" />
            <input type="reset" value="Reset" /><br>
             ${requestScope.userError.error}
        </form>
    </body>
</html>
