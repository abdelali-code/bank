<%@ page import="java.util.List" %>
<%@ page import="com.example.bankFinal.models.Entreprise" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.bankFinal.models.Compte" %>
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
            <% ArrayList<Entreprise> entreprises = (ArrayList<Entreprise>) request.getAttribute("entreprises");
                ArrayList<Personne> personnes = (ArrayList<Personne>) request.getAttribute("personnes");
            %>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">name</th>
                    <th scope="col">prenom</th>
                    <th scope="col">solde</th>
                    <th scope="col">numero</th>
                    <th scope="col"></th>
                    <th scope="col"></th>

                </tr>
                </thead>
                <tbody>
                <% for (Entreprise entreprise: entreprises) {%>
                    <tr>
                            <td>
                                <%= entreprise.getId()%>
                            </td>
                            <td>
                                <%= entreprise.getNom()%>
                            </td>
                            <td>

                            </td>
                            <td>
                                <%= entreprise.getSolde()%>
                            </td>
                            <td>
                                <%= entreprise.getNumero()%>
                            </td>
                            <td>
                                <form method="get" action="modifier_entreprise">
                                    <input hidden name="id" value="<%= entreprise.getId()%>">
                                    <button class="btn btn-warning">modifier</button>
                                </form>
                            </td>
                            <td>
                                <form method="post" action="supprimer">
                                    <input hidden name="id" value="<%= entreprise.getId()%>">
                                    <button class="btn btn-danger">supprimer</button>
                                </form>
                            </td>
                    </tr>
                <% } %>
                    <% for (Personne personne: personnes) {%>
                    <tr>
                        <td>
                            <%= personne.getId()%>
                        </td>
                        <td>
                            <%= personne.getNom()%>
                        </td>
                        <td>
                            <%= personne.getPrenom()%>
                        </td>
                        <td>
                            <%= personne.getSolde()%>
                        </td>
                        <td>
                            <%= personne.getNumero()%>
                        </td>
                        <td>
                            <form method="get" action="modifier_personne">
                                <input hidden name="id" value="<%= personne.getId()%>">
                                <button class="btn btn-warning">modifier</button>
                            </form>
                        </td>
                        <td>
                            <form method="post" action="supprimer">
                                <input hidden name="id" value="<%= personne.getId()%>">
                                <button class="btn btn-danger">supprimer</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>
