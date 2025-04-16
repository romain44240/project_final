package projet.request;


import org.springframework.beans.BeanUtils;

import projet.model.Jeu;


public class JeuRequest {
	
	private Integer id;
	private String nom;
	private double prix;
	private int stock;
	
	private int nbMin;
	private int nbMax;
	private int duree;
	private String editeur;
	private String regle;
	

	public JeuRequest() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getNbMin() {
		return nbMin;
	}

	public void setNbMin(int nbMin) {
		this.nbMin = nbMin;
	}

	public int getNbMax() {
		return nbMax;
	}

	public void setNbMax(int nbMax) {
		this.nbMax = nbMax;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public String getRegle() {
		return regle;
	}

	public void setRegle(String regle) {
		this.regle = regle;
	}

	public Jeu convert(JeuRequest jeuRequestDTO) {
		Jeu jeu = new Jeu();
		
		BeanUtils.copyProperties(jeuRequestDTO, jeu);
		
		return jeu;
	}
}
