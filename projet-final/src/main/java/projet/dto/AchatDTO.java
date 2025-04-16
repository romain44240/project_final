package projet.dto;

import projet.model.Achat;
import projet.model.Commande;
import projet.model.Produit;


public class AchatDTO {
	
	private Integer id;
	private int quantite;
	private Produit produit;
	private Commande commande;
	
	public AchatDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	public static Achat toRequest() {
		return null;
	}
	
	public static Achat toResponse() {
		return null;
	}
	
	

}
