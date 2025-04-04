package projet.model;

import java.time.LocalDate;

public class Client extends Compte {

	private String email, telephone;
	
	
	public Client(int id, String login, String password, String nom, String prenom, LocalDate dateArrivee, String email,
			String telephone) {
		super(id, login, password, nom, prenom, dateArrivee);
		this.email = email;
		this.telephone = telephone;
	}


	public String getEmail() {
		return email;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	@Override
	public String toString() {
		return "Client [email=" + email + ", telephone=" + telephone + ", id=" + id + ", login=" + login + ", password="
				+ password + ", nom=" + nom + ", prenom=" + prenom + ", dateArrivee=" + dateArrivee + "]";
	}


	
	
	
	
	
}
