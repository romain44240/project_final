package projet.model;

public class Surface {
	
	private Integer id;
	private int capacite, x, y, longueur, largeur;
	private String couleur;
	
	
	
	public Surface(Integer id, int capacite, int x, int y, int longueur, int largeur, String couleur) {
		this.id = id;
		this.capacite = capacite;
		this.x = x;
		this.y = y;
		this.longueur = longueur;
		this.largeur = largeur;
		this.couleur = couleur;
	}



	public Integer getId() {
		return id;
	}



	public int getCapacite() {
		return capacite;
	}



	public int getX() {
		return x;
	}



	public int getY() {
		return y;
	}



	public int getLongueur() {
		return longueur;
	}



	public int getLargeur() {
		return largeur;
	}



	public String getCouleur() {
		return couleur;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}



	public void setX(int x) {
		this.x = x;
	}



	public void setY(int y) {
		this.y = y;
	}



	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}



	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}



	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}



	@Override
	public String toString() {
		return "Surface [id=" + id + ", capacite=" + capacite + ", x=" + x + ", y=" + y + ", longueur=" + longueur
				+ ", largeur=" + largeur + ", couleur=" + couleur + "]";
	}
	
	
	

}
