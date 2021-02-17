package com.example.bankFinal.dao;

import com.example.bankFinal.DbConnection.Dbconn;
import com.example.bankFinal.models.Compte;
import com.example.bankFinal.models.Entreprise;
import com.example.bankFinal.models.Personne;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntrepriseDao {
    Connection connection = null;
    ResultSet resultSet = null;
    CompteDao compteDao;

    public EntrepriseDao() {
        compteDao = new CompteDao();
    }

    //(int compteId, String nom, String numero, double solde)
    public List<Entreprise> getAll() throws SQLException, ClassNotFoundException {
        List<Entreprise> entreprises = new ArrayList<>();
        String query = "SELECT * FROM entreprise, compte WHERE compte.id = entreprise.compteid";
        try (Connection connection = Dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                //Personne(int id, double solde, String numero, String nom, String prenom)
                entreprises.add(new Entreprise(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("numero"),
                        resultSet.getDouble("solde")
                ));
            }
        }
        return entreprises;
    }


    public Entreprise ajouter(Entreprise entreprise) throws ClassNotFoundException, SQLException {
        connection = Dbconn.getConnection();

        try (PreparedStatement addInEntreprise = connection.prepareStatement("INSERT  INTO entreprise(nom, compteid) VALUES(?, ?)"))
        {
            connection.setAutoCommit(false);
            Compte insertedCompte = compteDao.ajouter(new Compte(entreprise.getSolde(), entreprise.getNumero()), connection);
            entreprise.setId(insertedCompte.getId());
            addInEntreprise.setString(1, entreprise.getNom());
            addInEntreprise.setInt(2, entreprise.getId());
            addInEntreprise.executeUpdate();
            connection.commit();
        }
        return entreprise;
    }

    public Entreprise getById(int id) throws SQLException, ClassNotFoundException {
        Entreprise entreprise = null;
        String query = "SELECT * FROM entreprise, compte WHERE compte.id = entreprise.compteid and entreprise.compteid =" + id;
        try (Connection connection = Dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                //Entreprise(int compteId, String nom, String numero, double solde)
                entreprise = new Entreprise(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("numero"),
                        resultSet.getDouble("solde")
                );
            }
        }
        return entreprise;
    }

    public void modifier(Entreprise entreprise) throws SQLException, ClassNotFoundException {
        connection = Dbconn.getConnection();
        connection.setAutoCommit(false);
        //System.out.println("personne from comtpe " + personne.getId());
        try (PreparedStatement updateEntreprise = connection.prepareStatement("UPDATE entreprise SET nom=? WHERE compteid=?"))
        {
            Compte updateCompte = new Compte(entreprise.getSolde(), entreprise.getNumero());
            updateCompte.setId(entreprise.getId());
            Compte test = compteDao.modifier(updateCompte, connection);
            updateEntreprise.setString(1, entreprise.getNom());
            updateEntreprise.setInt(2, entreprise.getId());
            updateEntreprise.executeUpdate();
            connection.commit();
        }
    }


    /** search for a comptes */
    public List<Entreprise> chercher(String pattern) throws SQLException, ClassNotFoundException {
        List<Entreprise> entreprises = new ArrayList<>();
        String query = "SELECT * FROM entreprise, compte WHERE compte.id = entreprise.compteid and  nom LIKE ?";

        try (Connection connection = Dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, "%"+pattern+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //Personne(int id, double solde, String numero, String nom, String prenom)
                entreprises.add(new Entreprise(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("numero"),
                        resultSet.getDouble("solde")
                ));
            }
        }
        return entreprises;
    }
}
