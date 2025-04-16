package projet.request;

import org.springframework.beans.BeanUtils;

import projet.model.Achat;
import projet.model.Commande;
import projet.model.Reservation;
import projet.model.Surface;

public class SurfaceRequest {

	private Integer id;
	private int capacite;
	private String couleur;

	public SurfaceRequest() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	// Méthode pour transformer le DTO en entité Surface
	public Surface convert() {
		Surface surface = new Surface();
		BeanUtils.copyProperties(this, surface);
		return surface;
	}
}
