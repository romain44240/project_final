package projet.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_compte", columnDefinition = "ENUM('employe','client')")
@Table(name="compte")
public abstract class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(length = 25, nullable=false, unique = true)
	protected String login;
	@Column(length = 100, nullable=false)
	protected String password;
	@Column(length = 50, nullable=false)
	protected String nom;
	@Column(length = 50, nullable=false)
	protected String prenom;
	
	@Column(name="date_arrivee", nullable=false)
	protected LocalDate dateArrivee;
	
	public Compte() {}
	
	public Compte(Integer id, String login, String password, String nom, String prenom, LocalDate dateArrivee) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.dateArrivee = dateArrivee;
	}



	public Integer getId() {
		return id;
	}



	public String getLogin() {
		return login;
	}



	public String getPassword() {
		return password;
	}



	public String getNom() {
		return nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public LocalDate getDateArrivee() {
		return dateArrivee;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public void setDateArrivee(LocalDate dateArrivee) {
		this.dateArrivee = dateArrivee;
	}



	@Override
	public String toString() {
		return "Compte [id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom="
				+ prenom + ", dateArrivee=" + dateArrivee + "]";
	}
	
}
