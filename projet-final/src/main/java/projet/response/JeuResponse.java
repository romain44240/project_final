package projet.response;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import projet.model.Categorie;
import projet.model.Jeu;
import projet.model.Views;

public class JeuResponse extends ProduitResponse {
	
	@JsonView(Views.ViewProduit.class)
	private int nbMin;

	@JsonView(Views.ViewProduit.class)
	private int nbMax;

	@JsonView(Views.ViewProduit.class)
	private int duree;

	@JsonView(Views.ViewProduit.class)
	private String editeur;

	@JsonView(Views.ViewProduit.class)
	private String regle;

	@JsonView(Views.ViewProduit.class)
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
