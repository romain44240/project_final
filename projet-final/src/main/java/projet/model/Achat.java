package projet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "achat")
public class Achat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private int quantite;
	
	@OneToOne
	@NotBlank
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name = "commande_id")
	private Commande commande;
	
	public Achat() {}
	
	public Achat(Integer id, int quantite, Produit produit) {
		this.id = id;
		this.quantite = quantite;
		this.produit = produit;
	}
	
	public Achat(int quantite, Produit produit) {
		this.quantite = quantite;
		this.produit = produit;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
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

	@Override
	public String toString() {
		return "Achat [id=" + id + ", quantite=" + quantite + ", produit=" + produit + "]";
	}
	
	
	
	
}
