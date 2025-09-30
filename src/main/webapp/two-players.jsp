<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 27.09.2025
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Введите имена игроков</h2>
<form action="${pageContext.request.contextPath}/two-players" method="post">
    <label for="playerOne">Игрок 1:</label>
    <input type="text" id="playerOne" name="playerOne" required>
    <br><br>

    <label for="playerTwo">Игрок 2:</label>
    <input type="text" id="playerTwo" name="playerTwo" required>
    <br><br>

    <button type="submit">Создать матч</button>
</form>
<h2>Список игроков</h2>
<ul>
    <c:forEach var="player" items="${players}">
        <li>${player.name}</li>
    </c:forEach>
</ul>
<h2>Список матчей</h2>
<ul>
    <c:forEach var="match" items="${matches}">
        <li>${match.id}</li>
    </c:forEach>
</ul>
</body>
</html>
