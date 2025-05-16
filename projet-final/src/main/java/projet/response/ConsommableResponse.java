package projet.response;

import org.springframework.beans.BeanUtils;

import projet.model.Consommable;

public class ConsommableResponse extends ProduitResponse {
	
	protected Integer id;
	protected String nom;
	protected double prix;
	protected int stock;

	public ConsommableResponse() {}

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

	public static ConsommableResponse convert(Consommable consommable) {
		ConsommableResponse consommableResponse = new ConsommableResponse();
		
		BeanUtils.copyProperties(consommable, consommableResponse);
		
		return consommableResponse;
	}
}
