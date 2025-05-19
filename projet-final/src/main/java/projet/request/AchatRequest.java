package projet.request;

import org.springframework.beans.BeanUtils;

import projet.model.Achat;
import projet.model.Consommable;
import projet.model.Jeu;
import projet.model.Produit;
import projet.model.Reservation;

public class AchatRequest {

	private int quantite;
	private Integer idProduit;
	private Integer idReservation;
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

	public Integer getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
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
		
		Produit produit = null;
		if (achatRequestDTO.getProduitType() == ProduitType.CONSOMMABLE) {
			produit = new Consommable();
		} else if (achatRequestDTO.getProduitType() == ProduitType.JEU) {
			produit = new Jeu();
		}else{
			throw new IllegalArgumentException("ni un jeu ni un consommable");
		}
		
		produit.setId(achatRequestDTO.getIdProduit());
		achat.setProduit(produit);

		Reservation reservation = new Reservation();
		reservation.setId(achatRequestDTO.getIdReservation());
		achat.setReservation(reservation);
		
		return achat;
	}
	
	public enum ProduitType {
		JEU, CONSOMMABLE;
	}
}
