package com.example.bankFinal.Controllers;

import com.example.bankFinal.dao.EntrepriseDao;
import com.example.bankFinal.dao.PersonneDao;
import com.example.bankFinal.models.Compte;
import com.example.bankFinal.models.Entreprise;
import com.example.bankFinal.models.Personne;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/")
public class HomeServlet extends HttpServlet {
    EntrepriseDao entrepriseDao = new EntrepriseDao();
    PersonneDao personneDao = new PersonneDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String nom, String numero, double solde

        try {
            List<Entreprise> entreprises = entrepriseDao.getAll();
            List<Personne> personnes = personneDao.getAll();
            request.setAttribute("entreprises", entreprises);
            request.setAttribute("personnes", personnes);
            System.out.println("entreprise size " + entreprises.size() + " personne size " + personnes.size());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
