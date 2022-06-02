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
    <title>Профиль пользователя</title>
</head>
<body>
<style>
    table {
        width: 75%; /* Ширина таблицы */
        background: white; /* Цвет фона таблицы */
        color: white; /* Цвет текста */
        border-spacing: 1px; /* Расстояние между ячейками */
    }
    td, th {
        background: saddlebrown; /* Цвет фона ячеек */
        padding: 5px; /* Поля вокруг текста */
    }
</style>
<h1>Profile</h1>

<% User user = (User)request.getSession().getAttribute("user");%>

<div class="row">
    <div class="col-sm-4">
        <% if (request.getAttribute("image") != null) {%>
            <div style="text-align: center;">
                <img src="data:image/png;base64,<%=request.getAttribute("image")%>" style="height: 400px; width: 50%;">
            </div>
        <%} else {%>
            <div style="text-align: center;">
                <img src="${pageContext.request.contextPath}/images/Anonymity.jpeg" style="height: 400px; width: 50%;">
            </div>
        <%}%>
        <form action="${pageContext.request.contextPath}/profile" enctype="multipart/form-data" method="post">
            <input type="file" id="image" name="image" accept="image/*">
            <button type="submit" style="width: 50%">Загрузить фото</button>
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

    <table>
        <tr><th style="color: black">Sessions</th><th style="color: black">Images</th></tr>
        <tr>
            <td>
                <table style="color: black">
                    <tr><th>Date</th><th>Time</th><th>Ip</th></tr>
                    <% for (Session auth :user.getSessionList()) {%>
                    <tr>
                        <td><%=auth.getDate()%></td>
                        <td><%=auth.getTime()%></td>
                        <td><%=auth.getIp()%></td>
                    </tr>
                    <%}%>
                </table>
            </td>

            <td>
                <table style="color: black">
                    <tr><th>Date</th><th>Time</th><th>Ip</th></tr>
                    <% for (Session auth :user.getSessionList()) {%>
                    <tr>
                        <td><%=auth.getDate()%></td>
                        <td><%=auth.getTime()%></td>
                        <td><%=auth.getIp()%></td>
                    </tr>
                    <%}%>
                </table>
            </td>
        </tr>
    </table>


<%--<table>--%>
<%--    <tr><th>Date</th><th>Time</th><th>Ip</th></tr>--%>
<%--    <% for (Session auth :user.getSessionList()) {%>--%>
<%--    <tr>--%>
<%--        <td><%=auth.getDate()%></td>--%>
<%--        <td><%=auth.getTime()%></td>--%>
<%--        <td><%=auth.getIp()%></td>--%>
<%--    </tr>--%>
<%--    <%}%>--%>
<%--</table>--%>

<%--    <form action="profile" method="post">--%>
<%--        <div class="col">--%>
<%--            <label for="firstname">Имя</label>--%>
<%--            <br>--%>
<%--            <input type="text" id="firstname" name="firstname" placeholder="Имя" style="width: 100%">--%>
<%--        </div>--%>
<%--        <div class="col">--%>
<%--            <label for="lastname">Фамилия</label>--%>
<%--            <br>--%>
<%--            <input type="text" id="lastname" name="lastname" placeholder="Фамилия" style="width: 100%">--%>
<%--        </div>--%>
<%--        <div class="col">--%>
<%--            <label for="phone">Телефон</label>--%>
<%--            <br>--%>
<%--            <input type="text" id="phone" name="phone" placeholder="Телефон" style="width: 100%">--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <button type="submit" style="width: 100%">Обновить информацию</button>--%>
<%--        </div>--%>
<%--    </form>--%>

</body>
</html>
