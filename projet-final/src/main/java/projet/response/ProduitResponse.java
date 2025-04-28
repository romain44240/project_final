package projet.response;

import com.fasterxml.jackson.annotation.JsonView;

import projet.model.Consommable;
import projet.model.Jeu;
import projet.model.Produit;
import projet.model.Views;

public abstract class ProduitResponse {
	
	@JsonView(Views.ViewProduitDetail.class)
	protected Integer id;

	@JsonView(Views.ViewProduit.class)
	protected String nom;

	@JsonView(Views.ViewProduit.class)
	protected double prix;

	@JsonView(Views.ViewProduitDetail.class)
	protected int stock;
	
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
		if (produit instanceof Consommable consommable) {
			return ConsommableResponse.convert(consommable);
		} else if (produit instanceof Jeu jeu) {
			return JeuResponse.convert(jeu);
		} else {
			throw new IllegalArgumentException("Type de produit inconnu");
		}
	}
}
