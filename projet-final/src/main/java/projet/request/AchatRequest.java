package projet.request;

import org.springframework.beans.BeanUtils;

import projet.model.Achat;
import projet.model.Commande;
import projet.model.Consommable;
import projet.model.Jeu;
import projet.model.Produit;

public class AchatRequest {

	private int quantite;
	private Integer idProduit;
	private Integer idCommande;
	private ProduitType produitType;

	public AchatRequest() {}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Integer getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Integer idProduit) {
		this.idProduit = idProduit;
	}

	public Integer getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(Integer idCommande) {
		this.idCommande = idCommande;
	}

	public ProduitType getProduitType() {
		return produitType;
	}

	public void setProduitType(ProduitType produitType) {
		this.produitType = produitType;
	}

	// Méthode pour transformer le DTO en entité Achat
	public static Achat convert(AchatRequest achatRequestDTO) {
		
		Achat achat = new Achat();
		
		BeanUtils.copyProperties(achatRequestDTO, achat);
		
		Commande commande = new Commande();
		commande.setId(achatRequestDTO.getIdCommande());
		achat.setCommande(commande);
		
		Produit produit = null;
		if (achatRequestDTO.getProduitType() == ProduitType.CONSOMMABLE) {
			produit = new Consommable();
		} else if (achatRequestDTO.getProduitType() == ProduitType.JEU) {
			produit = new Jeu();
		}
		
		produit.setId(achatRequestDTO.getIdProduit());
		achat.setProduit(produit);
		
		return achat;
	}
	
	public enum ProduitType {
		JEU, CONSOMMABLE;
	}
}
