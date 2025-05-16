package projet.response;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import projet.model.Client;

public class ClientResponse {

	private Integer id;
	private String login;
	private String nom;
	private String prenom;
	private String email;
	private LocalDate dateArrivee;
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
