package projet.request;

import org.springframework.beans.BeanUtils;

import projet.model.Consommable;
import projet.model.Jeu;
import projet.model.Produit;

public abstract class ProduitRequest {
	
	private Integer id;
	private String nom;
	private double prix;
	private int stock;
	private ProduitType produitType;
	
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
	
	public ProduitType getProduitType() {
		return produitType;
	}

	public void setProduitType(ProduitType produitType) {
		this.produitType = produitType;
	}

	public static Produit convert(ProduitRequest produitRequest) {
		Produit produit = null;
		
		if (produitRequest.getProduitType() == ProduitType.JEU) {
			produit = new Jeu();
		} else if (produitRequest.getProduitType() == ProduitType.CONSOMMABLE) {
			produit = new Consommable();
		}
		
		BeanUtils.copyProperties(produitRequest, produit);
		
		return produit;
	}
	
	public enum ProduitType {
		JEU, CONSOMMABLE;
	}
}
