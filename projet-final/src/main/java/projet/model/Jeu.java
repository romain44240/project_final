package projet.model;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("jeu")
public class Jeu extends Produit {
	
	private int nbMin; // Nombres minimum de joueurs
	
	private int nbMax; // Nombres maximum de joueurs
	
	private int duree; // En minutes
	
	private String editeur;
	
	private String regle; // Lien internet vers les règles
	
	List<Categorie> categories;
	
	@OneToOne(mappedBy = "jeu")
	private Reservation reservation;
	
	public Jeu() {super();}
	
	public Jeu(Integer id, String nom, double prix, int stock, int nbMin, int nbMax, int duree, String editeur,
			String regle, List<Categorie> categories) {
		super(id, nom, prix, stock);
		this.nbMin = nbMin;
		this.nbMax = nbMax;
		this.duree = duree;
		this.editeur = editeur;
		this.regle = regle;
		this.categories = categories;
	}
	
	public Jeu(String nom, double prix, int stock, int nbMin, int nbMax, int duree, String editeur,
			String regle, List<Categorie> categories) {
		super(nom, prix, stock);
		this.nbMin = nbMin;
		this.nbMax = nbMax;
		this.duree = duree;
		this.editeur = editeur;
		this.regle = regle;
		this.categories = categories;
	}

	public int getNbMin() {
		return nbMin;
	}

	public int getNbMax() {
		return nbMax;
	}

	public int getDuree() {
		return duree;
	}
	
	public String getEditeur() {
		return editeur;
	}

	public String getRegle() {
		return regle;
	}

	public void setNbMin(int nbMin) {
		this.nbMin = nbMin;
	}

	public void setNbMax(int nbMax) {
		this.nbMax = nbMax;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public void setRegle(String regle) {
		this.regle = regle;
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Jeu [nbMin=" + nbMin + ", nbMax=" + nbMax + ", duree=" + duree + ", editeur=" + editeur + ", regle="
				+ regle + ", id=" + id + ", nom=" + nom + ", prix=" + prix + ", stock=" + stock + "]";
	}
}
