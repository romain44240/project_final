package projet.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("jeu")
public class Jeu extends Produit {
	
	private int nbMin; // Nombres minimum de joueurs
	
	private int nbMax; // Nombres maximum de joueurs
	
	private int duree; // En minutes
	
	private String editeur;
	
	private String urlRegle; // Lien internet vers les règles

	private String urlImage; // Lien internet vers l'image du jeu
	
	// @Convert
	private List<Categorie> categories;
	
	@OneToMany(mappedBy = "jeu", fetch = FetchType.LAZY)
	private List<Reservation> reservations;
	
	public Jeu() {super();}
	
	public Jeu(Integer id, String nom, double prix, int stock, int nbMin, int nbMax, int duree, String editeur,
			String urlRegle, List<Categorie> categories) {
		super(id, nom, prix, stock);
		this.nbMin = nbMin;
		this.nbMax = nbMax;
		this.duree = duree;
		this.editeur = editeur;
		this.urlRegle = urlRegle;
		this.categories = categories;
	}
	
	public Jeu(String nom, double prix, int stock, int nbMin, int nbMax, int duree, String editeur,
			String urlRegle, List<Categorie> categories) {
		super(nom, prix, stock);
		this.nbMin = nbMin;
		this.nbMax = nbMax;
		this.duree = duree;
		this.editeur = editeur;
		this.urlRegle = urlRegle;
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

	public String getUrlRegle() {
		return urlRegle;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public List<Categorie> getCategories() {
		return categories;
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

	public void setUrlRegle(String urlRegle) {
		this.urlRegle = urlRegle;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Jeu [nbMin=" + nbMin + ", nbMax=" + nbMax + ", duree=" + duree + ", editeur=" + editeur + ", urlRegle="
				+ urlRegle + ", urlImage=" + urlImage + ", id=" + id + ", nom=" + nom + ", prix=" + prix + ", stock=" + stock + "]";
	}
}
