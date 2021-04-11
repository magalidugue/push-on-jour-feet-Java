package org.example.modeles;

//J'initialise la classe Evenements//

import java.time.LocalDateTime;

public class Evenements {
    int id;
    String dateEvenement;
    double latitude;
    double longitude;
    String nomEvenement;
    int organisateurId;
    String commentairesEvenement;
    int participants;

    public Evenements(int id, String dateEvenement, double latitude, double longitude, String nomEvenement, int organisateurId, String commentairesEvenement, int participants) {
        this.id = id;
        this.dateEvenement = dateEvenement;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nomEvenement = nomEvenement;
        this.organisateurId = organisateurId;
        this.commentairesEvenement = commentairesEvenement;
        this.participants = participants;
    }

    @Override
    public String toString(){
        return "Date " + dateEvenement + "\nIntitulé "+ nomEvenement +"\n Commentaires " + commentairesEvenement;
    }
}
