package projet.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("employe")
public class Employe extends Compte {

	@OneToMany(mappedBy = "employe", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Reservation> reservations;
	
	private String poste; // null = serveur --> gameMaster => c'est qu'il peut plus servir ?
	
	private double salaire;
	
	public Employe() {super();}
	public Employe(int id, String login, String password, String nom, String prenom, String email, LocalDate dateArrivee,
			String poste, double salaire) {
		super(id, login, password, nom, prenom, email, dateArrivee);
		this.poste = poste;
		this.salaire = salaire;
	}

	public String getPoste() {
		return poste;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "Employe [poste=" + poste + ", salaire=" + salaire + ", id=" + id + ", login=" + login + ", password=" + password
				+ ", nom=" + nom + ", prenom=" + prenom + ", dateArrivee=" + dateArrivee + "]";
	}
}
