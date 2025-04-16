package projet.response;

import projet.model.Achat;
import projet.model.Surface;

public class SurfaceResponse {

	private Integer id;
	private int capacite;
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
	public static SurfaceResponse fromEntity(Surface surface) {
		SurfaceResponse dto = new SurfaceResponse();
		dto.setId(surface.getId());
		dto.setCapacite(surface.getCapacite());
		dto.setCouleur(surface.getCouleur());
		return dto;
	}

}
