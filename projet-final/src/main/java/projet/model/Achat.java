package projet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "achat")
public class Achat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private int quantite;
	
	@OneToOne
	private Produit produit;

	@OneToOne
	@JsonIgnore
	private Reservation reservation;
	
	public Achat() {}
	
	public Achat(Integer id, int quantite, Produit produit, Reservation reservation) {
		this.id = id;
		this.quantite = quantite;
		this.produit = produit;
		this.reservation = reservation;
	}
	
	public Achat(int quantite, Produit produit, Reservation reservation) {
		this.quantite = quantite;
		this.produit = produit;
		this.reservation = reservation;
	}

	public Integer getId() {
		return id;
	}

	public int getQuantite() {
		return quantite;
	}

	public Produit getProduit() {
		return produit;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Achat [id=" + id + ", quantite=" + quantite + ", produit=" + produit + "]";
	}
}
