package projet.response;

import java.time.LocalDate;
import org.springframework.beans.BeanUtils;

import projet.model.Employe;

public class EmployeResponse {

	private Integer id;
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private String email;
	private LocalDate dateArrivee;

	private String poste;
	private double salaire;

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

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public static EmployeResponse convert(Employe employe) {
		EmployeResponse employeResponseDTO = new EmployeResponse();
		
		BeanUtils.copyProperties(employe, employeResponseDTO);
		
		return employeResponseDTO; 
	}
}
