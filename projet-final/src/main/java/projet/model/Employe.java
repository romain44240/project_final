package projet.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("employe")
public class Employe extends Compte {

	
	@OneToOne
	private Reservation reservation;
	
	private String poste; // null = serveur --> gameMaster => c'est qu'il peut plus servir ?
	@Column(nullable = false)
	private double sal;
	
	
	public Employe() {super();}
	public Employe(int id, String login, String password, String nom, String prenom, LocalDate dateArrivee,
			String poste, double sal) {
		super(id, login, password, nom, prenom, dateArrivee);
		this.poste = poste;
		this.sal = sal;
	}



	public String getPoste() {
		return poste;
	}



	public double getSal() {
		return sal;
	}



	public void setPoste(String poste) {
		this.poste = poste;
	}



	public void setSal(double sal) {
		this.sal = sal;
	}



	@Override
	public String toString() {
		return "Employe [poste=" + poste + ", sal=" + sal + ", id=" + id + ", login=" + login + ", password=" + password
				+ ", nom=" + nom + ", prenom=" + prenom + ", dateArrivee=" + dateArrivee + "]";
	}



	
	
	
	
	
	

}
