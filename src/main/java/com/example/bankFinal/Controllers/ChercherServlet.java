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
import java.util.List;

@WebServlet(name = "ChercherServlet", value = "/chercher")
public class ChercherServlet extends HttpServlet {
    EntrepriseDao entrepriseDao;
    PersonneDao personneDao;
    public ChercherServlet() {
        entrepriseDao = new EntrepriseDao();
        personneDao = new PersonneDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pattern = request.getParameter("q");
        try {
            List<Personne> personnes = personneDao.chercher(pattern);
            List<Entreprise> entreprises = entrepriseDao.chercher(pattern);
            System.out.println("personne size " + personnes.size() + "entreprise size " + entreprises.size());
            request.setAttribute("personnes", personnes);
            request.setAttribute("entreprises", entreprises);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home.jsp");
        dispatcher.forward(request, response);
    }

}
