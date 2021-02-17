package com.example.bankFinal.dao;

import com.example.bankFinal.DbConnection.Dbconn;
import com.example.bankFinal.models.Compte;
import com.example.bankFinal.models.Personne;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneDao {
    Connection connection = null;
    ResultSet resultSet = null;
    CompteDao compteDao = new CompteDao();
    public List<Personne> getAll() throws SQLException, ClassNotFoundException {
        List<Personne> personnes = new ArrayList<>();
        String query = "SELECT * FROM personne, compte WHERE compte.id = personne.compteid";
        try (Connection connection = Dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                //Personne(int id, double solde, String numero, String nom, String prenom)
                personnes.add(new Personne(
                        resultSet.getInt("id"),
                        resultSet.getDouble("solde"),
                        resultSet.getString("numero"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom")
                ));
            }
        }
        return personnes;
    }

    public Personne ajouter(Personne personne) throws ClassNotFoundException, SQLException {
        connection = Dbconn.getConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement addInEntreprise = connection.prepareStatement("INSERT INTO personne(nom, prenom, compteid) VALUES(?, ?, ?)"))
        {
            Compte insertedCompte = compteDao.ajouter(new Compte(personne.getSolde(), personne.getNumero()), connection);
            personne.setId(insertedCompte.getId());
            addInEntreprise.setString(1, personne.getNom());
            addInEntreprise.setString(2, personne.getPrenom());
            addInEntreprise.setInt(3, personne.getId());

            addInEntreprise.executeUpdate();
            connection.commit();
        }
        return personne;
    }

    /** get personne by id*/
    public Personne getById(int id) throws ClassNotFoundException, SQLException {
        Personne personne = null;
        String query = "SELECT * FROM personne, compte WHERE compte.id = personne.compteid and personne.compteid =" + id;
        try (Connection connection = Dbconn.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                //Personne(int id, double solde, String numero, String nom, String prenom)
                personne = new Personne(
                        resultSet.getInt("id"),
                        resultSet.getDouble("solde"),
                        resultSet.getString("numero"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom")
                );
            }
        }
        return personne;
    }

    public void modifier(Personne personne) throws SQLException, ClassNotFoundException {
        connection = Dbconn.getConnection();
        connection.setAutoCommit(false);
        //System.out.println("personne from comtpe " + personne.getId());
        try (PreparedStatement updateEntreprise = connection.prepareStatement("UPDATE personne SET nom=?, prenom=? WHERE compteid=?"))
        {
            Compte updateCompte = new Compte(personne.getSolde(), personne.getNumero());
            updateCompte.setId(personne.getId());
            Compte test = compteDao.modifier(updateCompte, connection);
            updateEntreprise.setString(1, personne.getNom());
            updateEntreprise.setString(2, personne.getPrenom());
            updateEntreprise.setInt(3, personne.getId());
            updateEntreprise.executeUpdate();
            connection.commit();
        }
    }
}
