package projet.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_produit", columnDefinition = "ENUM('Jeu', 'Consommable')")
@Table(name = "produit")
public abstract class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	protected String nom;

	protected double prix; // En euros
	
	@Column(nullable=false, columnDefinition="INT(4) DEFAULT 0")
	protected int stock;
	
	public Produit() {}
	
	public Produit(Integer id, String nom, double prix, int stock) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.stock = stock;
	}
	
	public Produit(String nom, double prix, int stock) {
		this.nom = nom;
		this.prix = prix;
		this.stock = stock;
	}

	public Integer getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public double getPrix() {
		return prix;
	}

	public int getStock() {
		return stock;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", stock=" + stock + "]";
	}
}
