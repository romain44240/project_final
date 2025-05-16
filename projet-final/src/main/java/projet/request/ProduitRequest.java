package projet.request;

import java.util.List;

import org.springframework.beans.BeanUtils;

import projet.model.Categorie;
import projet.model.Produit;

public class ProduitRequest {
	
	// JEU + CONSO
	private Integer id;
	private String nom;
	private double prix;
	private int stock;

	// JEU
	private int nbMin;
	private int nbMax;
	private int duree;
	private String editeur;
	private String urlRegle;
	private String urlImage;
	private List<Categorie> categories;
	
	public ProduitRequest() {}

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

	public String getUrlRegle() {
		return urlRegle;
	}

	public void setUrlRegle(String urlRegle) {
		this.urlRegle = urlRegle;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public static Produit convert(ProduitRequest produitRequest) {
		Produit produit = null;
		
		BeanUtils.copyProperties(produitRequest, produit);
		
		return produit;
	}
}
