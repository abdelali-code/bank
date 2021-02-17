package com.example.bankFinal.Controllers;

import com.example.bankFinal.dao.PersonneDao;
import com.example.bankFinal.helper.RandomStr;
import com.example.bankFinal.models.Personne;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ModifierServlet", value = "/modifier_personne")
public class ModifierPersonneServlet extends HttpServlet {
    PersonneDao personneDao;

    public ModifierPersonneServlet() {
        personneDao = new PersonneDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Personne personne = personneDao.getById(id);
            request.setAttribute("personne", personne);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/modifierPersonne.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        double solde = Double.parseDouble(request.getParameter("solde"));
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("the id is " + id);

        //Personne(int id, double solde, String nom, String prenom)
        Personne personne = new Personne(id, solde, nom, prenom);
        System.out.println("personne id " + personne.getId());
        try {
            personneDao.modifier(personne);
            System.out.println("personne updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath());
    }
}
