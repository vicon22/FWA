<%--
  Created by IntelliJ IDEA.
  User: eveiled
  Date: 5/23/22
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>

<h1>Profile</h1>

<c:forEach var="user" item="${requestScope.user}">
    <ul>
        <li>ToString: <c:out value="user.toString()"/></li>
        <li>Email: <c:out value="user.email"/></li>
        <li>FirstName: <c:out value="user.firstName"/></li>
        <li>LastName: <c:out value="user.lastName"/></li>
        <li>PhoneNumber: <c:out value="user.phone"/></li>
        <li>Password: <c:out value="user.password"/></li>
    </ul>
    <hr/>

<%--</c:forEach>--%>

</body>
</html>
