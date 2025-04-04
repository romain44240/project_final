package projet.model;

public abstract class Produit {
	
	protected Integer id;
	protected String nom;
	protected double prix;
	protected int stock;
	
	
	
	public Produit(Integer id, String nom, double prix, int stock) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.stock = stock;
	}



	public Integer getId() {
		return id;
	}



	public String getNom() {
		return nom;
	}



	public double getPrix() {
		return prix;
	}



	public int getStock() {
		return stock;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public void setPrix(double prix) {
		this.prix = prix;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}



	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", stock=" + stock + "]";
	}
	
	

}
