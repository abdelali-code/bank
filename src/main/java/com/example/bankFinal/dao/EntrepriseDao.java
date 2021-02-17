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

}
