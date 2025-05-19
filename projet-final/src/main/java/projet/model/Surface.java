package projet.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="surface")
public class Surface {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private int capacite;

	private String couleur;

	@OneToMany(mappedBy = "surface", fetch = FetchType.LAZY)
	private List<Reservation> reservations;
	
	
	public Surface() {}

	public Surface(Integer id, int capacite) {
		this.id = id;
		this.capacite = capacite;
	}

	public Surface(int capacite) {
		this.capacite = capacite;
	}

	public Integer getId() {
		return id;
	}

	public int getCapacite() {
		return capacite;
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


	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public List<Reservation> getReservation() {
		return reservations;
	}

	public void setReservation(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "Surface [id=" + id + ", capacite=" + capacite + ", couleur=" + couleur + "]";
	}

}
