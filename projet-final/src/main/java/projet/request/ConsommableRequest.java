package projet.request;

import org.springframework.beans.BeanUtils;

import projet.model.Consommable;

public class ConsommableRequest {

	private Integer id;
	private String nom;
	private double prix;
	private int stock;
	
	public ConsommableRequest() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public static Consommable convert(ConsommableRequest consommableRequest) {
		Consommable consommable = new Consommable();
		
		BeanUtils.copyProperties(consommableRequest, consommable);
		
		return consommable;
	}
}
