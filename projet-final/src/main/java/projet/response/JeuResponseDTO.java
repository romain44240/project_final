package projet.response;

import java.time.LocalDate;

import projet.model.Achat;
import projet.model.Commande;
import projet.model.Employe;
import projet.model.Jeu;
import projet.model.Produit;

public class JeuResponseDTO {

	private Integer id;
	private String nom;
	private double prix;
	private int stock;
	
	private int nbMin;
	private int nbMax;
	private int duree;
	private String editeur;
	private String regle;

	public JeuResponseDTO() {}

	
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

	public static JeuResponseDTO fromEntity(Jeu jeu) {
		JeuResponseDTO dto = new JeuResponseDTO();
		
		dto.setId(jeu.getId());
		dto.setNom(jeu.getNom());
		dto.setPrix(jeu.getPrix());
		dto.setStock(jeu.getStock());
		dto.setNbMin(jeu.getNbMin());
		dto.setNbMax(jeu.getNbMax());
		dto.setDuree(jeu.getDuree());
		dto.setEditeur(jeu.getEditeur());
		dto.setRegle(jeu.getRegle());
		return dto;
	}
}
