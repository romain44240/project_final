package projet.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("jeu")
public class Jeu extends Produit {
	
	private int nbMin;
	
	private int nbMax;
	
	private int duree;
	
	private String editeur;
	
	private String regle;
	
	public Jeu() {super();}
	public Jeu(Integer id, String nom, double prix, int stock, int nbMin, int nbMax, int duree, String editeur,
			String regle) {
		super(id, nom, prix, stock);
		this.nbMin = nbMin;
		this.nbMax = nbMax;
		this.duree = duree;
		this.editeur = editeur;
		this.regle = regle;
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

	@Override
	public String toString() {
		return "Jeu [nbMin=" + nbMin + ", nbMax=" + nbMax + ", duree=" + duree + ", editeur=" + editeur + ", regle="
				+ regle + ", id=" + id + ", nom=" + nom + ", prix=" + prix + ", stock=" + stock + "]";
	}
}
