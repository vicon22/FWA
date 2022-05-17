<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>
    <a href="signIn">signIn</a>
    <%= " or " %>
    <a href="signUp">signUp</a>
</h1>
<%--<br/>--%>
<%--<a href="signI">signIn</a>--%>
<%--<br>--%>
<%--<a href="signUp">signUp</a>--%>
</body>

<form action="action_page.php">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <hr>
        <button type="submit" class="registerbtn">Register</button>
    </div>

    <div class="container signin">
        <p>Already have an account? <a href="SignIn">Sign in</a>.</p>
    </div>
</form>

</html>