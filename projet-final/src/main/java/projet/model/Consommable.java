package projet.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("conso")
public class Consommable extends Produit {

	public Consommable(Integer id, String nom, double prix, int stock) {
		super(id, nom, prix, stock);
	}

	
	@Override
	public String toString() {
		return "Consommable [id=" + id + ", nom=" + nom + ", prix=" + prix + ", stock=" + stock + "]";
	}

	
	
	
	

}
