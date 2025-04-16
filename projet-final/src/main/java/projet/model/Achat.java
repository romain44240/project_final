package projet.model;

import jakarta.persistence.Entity;

@Entity
public class Achat {

	private Integer id;
	private double prix;
	private int quantite;
	private Reservation reservation;
	private Produit produit;
	
	
	
	public Achat(Integer id, double prix, int quantite, Reservation reservation, Produit produit) {
		this.id = id;
		this.prix = prix;
		this.quantite = quantite;
		this.reservation = reservation;
		this.produit = produit;
	}



	public Integer getId() {
		return id;
	}



	public double getPrix() {
		return prix;
	}



	public int getQuantite() {
		return quantite;
	}



	public Reservation getReservation() {
		return reservation;
	}



	public Produit getProduit() {
		return produit;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public void setPrix(double prix) {
		this.prix = prix;
	}



	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}



	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}



	public void setProduit(Produit produit) {
		this.produit = produit;
	}



	@Override
	public String toString() {
		return "Achat [id=" + id + ", prix=" + prix + ", quantite=" + quantite + ", reservation=" + reservation
				+ ", produit=" + produit + "]";
	}
	
	
	
	
}
