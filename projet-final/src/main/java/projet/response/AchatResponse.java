package projet.response;

import org.springframework.beans.BeanUtils;

import projet.model.Achat;
import projet.model.Produit;
import projet.model.Reservation;

public class AchatResponse {

	private Integer id;
	private int quantite;
	private Produit produit;
	private Reservation reservation;

	public AchatResponse() {}

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

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	// Fabrique un DTO depuis une entité Achat
	public static AchatResponse convert(Achat achat) {
		AchatResponse achatResponseDTO = new AchatResponse();
		
		BeanUtils.copyProperties(achat, achatResponseDTO);
		
		return achatResponseDTO; 
	}
}
