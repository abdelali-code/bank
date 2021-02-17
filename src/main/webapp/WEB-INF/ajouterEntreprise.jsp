<%--
  Created by IntelliJ IDEA.
  User: elouadi
  Date: 16‏/2‏/2021
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="includes/_head.jsp"%>
    <title>Title</title>
</head>
<body>
<header>
    <%@include file="includes/_nav.jsp"%>
</header>
<main>
    <div class="container">
        <% String message = (String) request.getAttribute("message");
        %>
        <%if (message != null && !message.isEmpty()) {%>
        <div class="alert alert-success"><%= message%></div>
        <%}%>
        <form method="post" action="ajouter_entreprise">
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="nom" placeholder="entrer a name">
            </div>
            <div class="mb-3">
                <label for="solde" class="form-label">Solde</label>
                <input type="number" class="form-control" id="solde"  name="solde" placeholder="entrer a solde">
            </div>
            <button type="submit" class="btn btn-primary">ajouter entreprise</button>
        </form>
    </div>
</main>
</body>
</html>
