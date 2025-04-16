package projet.response;

import org.springframework.beans.BeanUtils;

import projet.model.Achat;
import projet.model.Commande;
import projet.model.Produit;

public class AchatResponseDTO {

	private Integer id;
	private int quantite;
	private Produit produit;
	private Commande commande;

	public AchatResponseDTO() {}

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

	// Fabrique un DTO depuis une entité Achat
	public static AchatResponseDTO convert(Achat achat) {
		AchatResponseDTO achatResponseDTO = new AchatResponseDTO();
		
		BeanUtils.copyProperties(achat, achatResponseDTO);
		
		return achatResponseDTO; 
	}
}
