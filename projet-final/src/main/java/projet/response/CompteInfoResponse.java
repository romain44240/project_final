package projet.response;

import java.util.HashMap;
import java.util.List;

import projet.model.Jeu;
import projet.model.Reservation;

public class CompteInfoResponse {
    
    List<Reservation> reservations;
    HashMap<String, Integer> consommables;
    List<Jeu> jeux;

    public CompteInfoResponse() {}

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public HashMap<String, Integer> getConsommables() {
        return consommables;
    }

    public void setConsommables(HashMap<String, Integer> consommables) {
        this.consommables = consommables;
    }

    public List<Jeu> getJeux() {
        return jeux;
    }

    public void setJeux(List<Jeu> jeux) {
        this.jeux = jeux;
    }
}
