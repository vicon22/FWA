<%@ page import="edu.school21.cinema.models.User" %>
<%@ page import="edu.school21.cinema.models.Session" %><%--
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

<% User user = (User)request.getSession().getAttribute("user");%>

<% for (Session auth :user.getSessionList()) {%>
<tr>
    <li>Date: <td><%=auth.getDate()%></td></li>
    <li>Ip: <td><%=auth.getIp()%></td></li>
</tr>
<%}%>

    <ul>
        <li>Email: <%=user.getEmail()%></li>
        <li>FirstName: <%=user.getFirstName()%></li>
        <li>LastName: <%=user.getLastName()%></li>
        <li>PhoneNumber: <%=user.getPhone()%></li>
        <li>Password: <%=user.getPassword()%></li>
    </ul>
    <hr/>

<%--</c:forEach>--%>

</body>
</html>
