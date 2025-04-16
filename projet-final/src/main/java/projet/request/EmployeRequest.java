package projet.request;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import projet.model.Employe;


public class EmployeRequest {
	
	private Integer id;
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private LocalDate dateArrivee;
	
	private String poste;
	private double sal;

	public EmployeRequest() {}

	
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
	
	public Employe convert(EmployeRequest employeRequestDTO) {
		Employe employe = new Employe();
		
		BeanUtils.copyProperties(employeRequestDTO, employe);

		employeRequestDTO.getId();
		employeRequestDTO.getLogin();
		employeRequestDTO.getPassword();
		employeRequestDTO.getNom();
		employeRequestDTO.getPrenom();
		employeRequestDTO.getDateArrivee();
		return employe;
	}
}
