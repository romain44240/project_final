package projet.request;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import projet.model.Client;
import projet.model.Commande;
import projet.model.Produit;

public class ClientRequest {

	private Integer id;
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private LocalDate dateArrivee;
	
	private String email;
	private String telephone;
	

	public ClientRequest() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(LocalDate dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public static Client convert(ClientRequest clientRequestDTO) {
		
		Client client = new Client();
		
		BeanUtils.copyProperties(clientRequestDTO, client);
		
		return client;
	}
}
