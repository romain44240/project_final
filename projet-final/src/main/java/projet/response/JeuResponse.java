package projet.response;

import java.util.List;

import org.springframework.beans.BeanUtils;

import projet.model.Categorie;
import projet.model.Jeu;

public class JeuResponse extends ProduitResponse {
	
	private int nbMin;
	private int nbMax;
	private int duree;
	private String editeur;
	private String regle;
	private List<Categorie> categories;

	public JeuResponse() {}

	public int getNbMin() {
		return nbMin;
	}

	public void setNbMin(int nbMin) {
		this.nbMin = nbMin;
	}

	public int getNbMax() {
		return nbMax;
	}

	public void setNbMax(int nbMax) {
		this.nbMax = nbMax;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public String getRegle() {
		return regle;
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

	public static JeuResponse convert(Jeu jeu) {
		JeuResponse jeuResponse = new JeuResponse();
		
		BeanUtils.copyProperties(jeu, jeuResponse);
		
		return jeuResponse;
	}
}
