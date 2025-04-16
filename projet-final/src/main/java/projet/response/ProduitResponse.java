package projet.response;

import projet.model.Consommable;
import projet.model.Jeu;
import projet.model.Produit;

public abstract class ProduitResponse {
	
	private Integer id;
	private String nom;
	private double prix;
	private int stock;
	
	public ProduitResponse() {}

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
	
	public static ProduitResponse convert(Produit produit) {
		if (produit instanceof Consommable) {
			return ConsommableResponse.convert(produit);
		} else if (produit instanceof Jeu) {
			return JeuResponse.convert(produit);
		} else {
			throw new IllegalArgumentException("Type de produit inconnu");
		}
	}
}
