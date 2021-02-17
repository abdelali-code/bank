package com.example.bankFinal.Controllers;

import com.example.bankFinal.dao.EntrepriseDao;
import com.example.bankFinal.dao.PersonneDao;
import com.example.bankFinal.helper.RandomStr;
import com.example.bankFinal.models.Entreprise;
import com.example.bankFinal.models.Personne;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AjouterEntrepriseServlet", value = "/ajouter_entreprise")
public class AjouterEntrepriseServlet extends HttpServlet {
    EntrepriseDao entrepriseDao;
    public AjouterEntrepriseServlet() {
        entrepriseDao = new EntrepriseDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/ajouterEntreprise.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String numero = RandomStr.randomString();
        double solde = Double.parseDouble(request.getParameter("solde"));
        System.out.println("nom " + nom + " prenom "+ " numero " + numero);
        Entreprise entreprise = new Entreprise(nom, numero, solde);

        try {
            entrepriseDao.ajouter(entreprise);
            request.setAttribute("message", "entreprise added successfuly");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/ajouterEntreprise.jsp").forward(request, response);
    }
}
