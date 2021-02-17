package com.example.bankFinal.Controllers;

import com.example.bankFinal.dao.CompteDao;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SupprimerServlet", value = "/supprimer")
public class SupprimerServlet extends HttpServlet {
    CompteDao compteDao;
    public SupprimerServlet() {
        compteDao = new CompteDao();
    }
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            compteDao.supprimer(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect(request.getContextPath());
    }
}
