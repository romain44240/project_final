package projet.response;

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
	public static AchatResponseDTO fromEntity(Achat achat) {
		AchatResponseDTO dto = new AchatResponseDTO();
		dto.setId(achat.getId());
		dto.setQuantite(achat.getQuantite());
		dto.setProduit(achat.getProduit());
		dto.setCommande(achat.getCommande());
		return dto;
	}
}
