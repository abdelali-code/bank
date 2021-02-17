package com.example.bankFinal.dao;

import com.example.bankFinal.DbConnection.Dbconn;
import com.example.bankFinal.models.Compte;
import java.sql.*;

public class CompteDao {
    /** ajouter compte */
    public Compte ajouter(Compte compte, Connection connection) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO compte(solde, numero) values(?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, compte.getSolde());
            preparedStatement.setString(2, compte.getNumero());

            int res = preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    //return resultSet.getInt(1);
                    compte.setId(resultSet.getInt(1));
                    return compte;
                }
            }
            throw new SQLException("can't add compte");
        }

    }

    /** delete compte */
    public boolean supprimer(int compteId) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM compte WHERE id=?";
        Connection connection = Dbconn.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, compteId);
        int res = preparedStatement.executeUpdate();
        if (res > 0) return true;
        throw new SQLException("can't delete compte");
    }



    /** modifier compte */
    public Compte modifier(Compte target, Connection connection) throws SQLException, ClassNotFoundException {
        String query = "UPDATE compte SET solde=? WHERE id=?";
        System.out.println("the id from compte " + target.getId());
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDouble(1, target.getSolde());
        preparedStatement.setInt(2, target.getId());

        System.out.println("solde " + target.getSolde() + " id " + target.getId());

        int res = preparedStatement.executeUpdate();
        if (res > 0) {
            return target;
        }
        throw new SQLException("cant'modifier compte");
    }

    /** modifier compte */
//    public void supprimer(int id) throws SQLException, ClassNotFoundException {
//        String query = "DELETE FROM compte WHERE id=?";
//        try (   Connection connection = Dbconn.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(query)){
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        }
//    }
}
