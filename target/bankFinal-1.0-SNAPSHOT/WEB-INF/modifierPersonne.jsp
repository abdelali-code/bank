<%@ page import="com.example.bankFinal.models.Personne" %><%--
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
        <%Personne personne = (Personne) request.getAttribute("personne");%>
        <h2>Modifier personne</h2>
        <form method="post" action="modifier_personne">
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="nom" placeholder="entrer a name" value="<%=personne.getNom()%>">
            </div>
            <div class="mb-3">
                <label for="prenom" class="form-label">Prenom</label>
                <input type="text" class="form-control" id="prenom" name="prenom" placeholder="entrer a prenom" value="<%=personne.getPrenom()%>">
            </div>
            <div class="mb-3">
                <label for="solde" class="form-label">Solde</label>
                <input type="number" class="form-control" id="solde"  name="solde" placeholder="entrer a solde" value="<%=personne.getSolde()%>">
                <input hidden name="id" value="<%= personne.getId()%>">
            </div>
            <button type="submit" class="btn btn-primary">ajouter personne</button>
        </form>
    </div>
</main>
</body>
</html>
