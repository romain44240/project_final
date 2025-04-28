package projet.response;

import java.time.LocalDate;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import projet.model.Employe;
import projet.model.Views;

public class EmployeResponse {

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
	private String poste;
	@JsonView(Views.ViewCompte.class)
	private double sal;

	public EmployeResponse() {}

	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(LocalDate dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public static EmployeResponse convert(Employe employe) {
		EmployeResponse employeResponseDTO = new EmployeResponse();
		
		BeanUtils.copyProperties(employe, employeResponseDTO);
		
		return employeResponseDTO; 
	}
}
