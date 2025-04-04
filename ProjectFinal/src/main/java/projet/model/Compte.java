package projet.model;

import java.time.LocalDate;

public abstract class Compte {

	protected Integer id;
	protected String login, password, nom, prenom;
	protected LocalDate dateArrivee;
	
	
	
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
