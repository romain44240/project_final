package projet.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import projet.model.Client;
import projet.model.Employe;
import projet.model.Jeu;
import projet.model.Reservation;
import projet.model.Surface;

public class test {

	public static void main(String[] args) {
		
		Client c1 = new Client(1, "toto", "titi", "tata", "tutu", LocalDate.now(), "test", "num");
		Employe e1 = new Employe(1, "test", "test", "test", "test", LocalDate.now(), "serveur", 1500);
		Surface s1 = new Surface(1, 4, 4, 4, 4, 4, "marron");
		Jeu j1 = new Jeu(1, "jeu de l'oie", 5, 7, 2, 4, 1, "edit", "regle");
		Reservation r1 = new Reservation(1, LocalDateTime.now(), 1, 4, c1, e1, s1, j1);
		
		System.out.println(r1);
		
	}

}
