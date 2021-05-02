package org.example.daos;

import org.example.core.Database;
import org.example.modeles.Evenement;
import org.example.modeles.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EvenementDao {


    public Evenement getEvenementById (int id){
        Evenement evenement = new Evenement();
        Database db =Database.get();
        Connection connection = db.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM evenements WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            evenement=mapEvenement(resultSet);



            System.out.println(resultSet.getString(2));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return evenement;
    }

    public User getOrganisateur (int id){
        User organisateur = new User();

        Database db =Database.get();
        Connection connection = db.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilisateurs INNER JOIN evenements ON utilisateurs.id = evenements.organisateur_id WHERE evenements.id =?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            organisateur=mapUser(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return organisateur;
    }


    public List<User> getParticipants (int id){
        List<User> participants = new ArrayList<User>();
        Database db =Database.get();
       Connection connection = db.getConnection();
       try {
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilisateurs  INNER JOIN evenements_utilisateurs ON utilisateurs.id = evenements_utilisateurs.utilisateurs_id WHERE evenements_utilisateurs.evenements_id=?");
          statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()== true) {
                User p = mapUser(resultSet);
                participants.add(p);

           }

       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
         return participants;
    }


//    public User getParticipants (int id){
//        User participants = new User();
//
//        Database db =Database.get();
//        Connection connection = db.getConnection();
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilisateurs  INNER JOIN evenements_utilisateurs ON utilisateurs.id = evenements_utilisateurs.utilisateurs_id WHERE evenements_utilisateurs.evenements_id=?");
//            statement.setInt(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()== true){
//                participants = mapUser(resultSet);
//            }
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return participants;
//    }


    public List<Evenement> getAllEvenements(){
        List<Evenement> evenements = new ArrayList<>();

        Database db =Database.get();
        Connection connection = db.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM evenements");
//           Donne un curseur
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next() == true){
                Evenement e = mapEvenement(resultSet);
                evenements.add(e);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return evenements;

    }

    private Evenement mapEvenement(ResultSet resultSet) throws SQLException {
        Evenement e = new Evenement();
        e.setId(resultSet.getInt(1));
        e.setDateEvenement(resultSet.getString(2));
        e.setNomEvenement(resultSet.getString(5));
        e.setOrganisateurId(resultSet.getInt(6));
        e.setCommentairesEvenement(resultSet.getString(7));
       return e;
    }

    private User mapUser (ResultSet resultSet) throws SQLException{
        User u = new User();
        u.setId(resultSet.getInt(1));
        u.setPseudo(resultSet.getString(2));
        return u;
    }


}
