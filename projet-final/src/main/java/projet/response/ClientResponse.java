package projet.response;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import projet.model.Client;
import projet.model.Views;

public class ClientResponse {

	@JsonView(Views.ViewCompte.class)
	private Integer id;
	@JsonView(Views.ViewCompte.class)
	private String login;
	@JsonView(Views.ViewCompte.class)
	private String password;
	@JsonView(Views.ViewCompte.class)
	private String nom;
	@JsonView(Views.ViewCompte.class)
	private String prenom;
	@JsonView(Views.ViewCompte.class)
	private String email;
	@JsonView(Views.ViewCompte.class)
	private LocalDate dateArrivee;
	@JsonView(Views.ViewCompte.class)
	private String telephone;
	
	public ClientResponse() {}

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

	public static ClientResponse convert(Client client) {
		ClientResponse clientResponseDTO = new ClientResponse();
		
		BeanUtils.copyProperties(client, clientResponseDTO);
		
		return clientResponseDTO; 
	}
}
