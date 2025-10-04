<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>

        <div class="input-container">
            <input class="input-filter"
                   id="filterInput"
                   name="filter_by_name"
                   placeholder="Filter by name"
                   type="text" />

            <div>
                <a href="${pageContext.request.contextPath}/matches">
                    <button class="btn-filter" type="button">Reset Filter</button>
                </a>
            </div>
        </div>

        <script>
            document.getElementById("filterInput").addEventListener("keypress", function(event) {
                if (event.key === "Enter") {
                    event.preventDefault(); // чтобы не было лишних действий
                    const value = this.value.trim();
                    if (value) {
                        window.location.href = "${pageContext.request.contextPath}/matches?filter_by_name=" + encodeURIComponent(value);
                    } else {
                        window.location.href = "${pageContext.request.contextPath}/matches";
                    }
                }
            });
        </script>


        <table class="table-matches">

            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:forEach var="match" items="${finishedMatches}">
                <tr>
                    <td>${match.player1.name}</td>
                    <td>${match.player2.name}</td>
                    <td><span class="winner-name-td">${match.winner.name}</span></td>
                </tr>
            </c:forEach>
        </table>

        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a class="prev" href="matches?page=${currentPage - 1}&filter_by_name=${filterByName}"> < </a>
            </c:if>

            <c:forEach var="i" begin="1" end="${totalPages}">
                <a class="num-page ${i == currentPage ? 'current' : ''}"
                   href="matches?page=${i}&filter_by_name=${filterByName}">${i}</a>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <a class="next" href="matches?page=${currentPage + 1}&filter_by_name=${filterByName}"> > </a>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
