package com.example.bankFinal.Controllers;

import com.example.bankFinal.dao.EntrepriseDao;
import com.example.bankFinal.dao.PersonneDao;
import com.example.bankFinal.models.Entreprise;
import com.example.bankFinal.models.Personne;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ModifierENtrepriseServlet", value = "/modifier_entreprise")
public class ModifierENtrepriseServlet extends HttpServlet {
    EntrepriseDao entrepriseDao;

    public ModifierENtrepriseServlet() {
        entrepriseDao = new EntrepriseDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Entreprise entreprise = entrepriseDao.getById(id);
            request.setAttribute("entreprise", entreprise);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/modifierEntreprise.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("inside modifier entreprise servelet");

        String nom = request.getParameter("nom");
        double solde = Double.parseDouble(request.getParameter("solde"));
        int id = Integer.parseInt(request.getParameter("id"));

        Entreprise entreprise = new Entreprise(id, nom, solde);
        try {
            entrepriseDao.modifier(entreprise);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath());
    }
}
