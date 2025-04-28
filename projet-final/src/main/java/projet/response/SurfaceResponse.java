package projet.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import projet.model.Surface;
import projet.model.Views;

public class SurfaceResponse {

	@JsonView(Views.ViewSurfaceDetail.class)
	private Integer id;

	@JsonView(Views.ViewSurface.class)
	private int capacite;

	@JsonView(Views.ViewSurface.class)
	private String couleur;

	public SurfaceResponse() {
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

	// Fabrique un DTO depuis une entité Surface
	public static SurfaceResponse convert(Surface surface) {
		SurfaceResponse response = new SurfaceResponse();
		BeanUtils.copyProperties(surface, response);
		return response;
	}

}
