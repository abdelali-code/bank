package com.example.bankFinal.Controllers;

import com.example.bankFinal.dao.PersonneDao;
import com.example.bankFinal.helper.RandomStr;
import com.example.bankFinal.models.Personne;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AjouterPersonneServlet", value = "/ajouter_personne")
public class AjouterPersonneServlet extends HttpServlet {
    PersonneDao personneDao;
    public AjouterPersonneServlet() {
        personneDao = new PersonneDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/ajouterPersonne.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String numero = RandomStr.randomString();
        double solde = Double.parseDouble(request.getParameter("solde"));
        System.out.println("nom " + nom + " prenom "+ prenom + " numero " + numero);
        Personne personne = new Personne(solde, numero, nom, prenom);

        try {
            personneDao.ajouter(personne);
            request.setAttribute("message", "personne added successfuly");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/ajouterPersonne.jsp").forward(request, response);
    }
}
