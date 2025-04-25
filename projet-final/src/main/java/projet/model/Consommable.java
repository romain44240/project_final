package projet.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("consommable")
public class Consommable extends Produit {
	
	public Consommable() {super();}
	
	public Consommable(Integer id, String nom, double prix, int stock) {
		super(id, nom, prix, stock);
	}

	public Consommable(String nom, double prix, int stock) {
		super(nom, prix, stock);
	}
	
	@Override
	public String toString() {
		return "Consommable [id=" + id + ", nom=" + nom + ", prix=" + prix + ", stock=" + stock + "]";
	}

	
	
	
	

}
