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
    <title>Profile</title>
</head>
<body>
<style>
    TD {
        /*background: #fc0; !* Цвет фона *!*/
        border: 1px solid; /* Граница вокруг ячеек */
        padding: 5px; /* Поля в ячейках */
    }
</style>

<% User user = (User)request.getSession().getAttribute("user");%>

<h1 align="center"><%=user.getFirstName()%>'s profile</h1>
<div class="row">
    <table style="color: black">
        <tr>
            <th>
                <div class="col-sm-4">
                        <% if (request.getAttribute("image") != null) {%>
                    <div style="text-align: center;">
                        <img src="data:image/png;base64,<%=request.getAttribute("image")%>" style="height: 400px; width: 50%;" class="img-circle img-responsive">
                    </div>
                        <%} else {%>
                    <div style="text-align: center;">
                        <img src="${pageContext.request.contextPath}/images/Anonymity.jpeg" style="height: 400px; width: 50%;">
                    </div>
                        <%}%>
            </th>
            <th>
                <table style="color: black; border-color: black">
                    <tr><th>Date</th><th>Time</th><th>Ip</th></tr>
                    <% for (Session auth :user.getSessionList()) {%>
                    <tr>
                        <td valign="top"><%=auth.getDate()%></td>
                        <td><%=auth.getTime()%></td>
                        <td><%=auth.getIp()%></td>
                    </tr>
                    <%}%>
                </table>
            </th>
        </tr>
    </table>
    <form action="${pageContext.request.contextPath}/profile" enctype="multipart/form-data" method="post">
        <input type="file" id="image" name="image" accept="image/*">
        <button type="submit" style="width: 60%">Upload</button>
    </form>

    </form>
    <h3>
        <li>Email: <%=user.getEmail()%></li>
        <li>Name: <%=user.getFirstName()%></li>
        <li>LastName: <%=user.getLastName()%></li>
        <li>Phone: <%=user.getPhone()%></li>
    </h3>
    <br>
    <br>
</div>

</body>
</html>
