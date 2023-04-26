<%-- 
    Document   : login
    Created on : Apr 13, 2023, 3:50:10 PM
    Author     : Huynh Van Phuot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="MainController" method="POST" id="form">
            UserID <input type="text" name="userID" required="" />${requestScope.ERROR}<br>
            Password <input type="password" name="password" required="" /><br>
            <div class="g-recaptcha" data-sitekey="6LdNyaolAAAAAFiAJQumIplsTfKO2UBk_JUVBCoZ"></div>
            <div id="error"></div>
<!--             <div>
                    <c:choose>
                        <c:when test="${rememberMe}">
                            <input type="hidden" value="y" name="rememberMe" id="remember" checked>
                        </c:when>
                        <c:when test="${!rememberMe}">
                            <input type="checkbox" value="y" name="rememberMe" id="remember">
                        </c:when>
                    </c:choose>
                    <label for="remember">
                        Remember me
                    </label>
                </div>-->
            <input value="Login" name="action" hidden=""/>
            <input type="submit" name="action" value="Login" />
            <input type="reset" value="Reset" />
        </form>

        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            window.onload = function () {
                let isValid = false;
                const form = document.getElementById("form");
                const error = document.getElementById("error");

                form.addEventListener("submit", function (event) {
                    event.preventDefault();
                    const response = grecaptcha.getResponse();
                    if (response) {
                        form.submit();
                    } else {
                        error.innerHTML = "Please confirm you're not a robot";
                    }
                });
            }
        </script>
        
        <button style="margin-top:  10px;"><a style="text-decoration: none" href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8084/PRJ301_3BW_SP23_JSTL/LoginWithGoogle&response_type=code&client_id=255256168369-1om0qpgh1dig4i78csb6c8485tqo9c1j.apps.googleusercontent.com&approval_prompt=force">Login with Google</a></button><br>
        <a href="createUser.html">Create a new user</a>
    </body>
</html>