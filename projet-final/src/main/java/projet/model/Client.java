package projet.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("client")
public class Client extends Compte {

	@Column(length = 100)
	private String telephone;
	
	@OneToMany(mappedBy = "client")
	@JsonIgnore
	private List<Reservation> reservations;
	
	public Client() {super();}
	
	public Client(int id, String login, String password, String nom, String prenom, LocalDate dateArrivee, String email,
			String telephone) {
		super(id, login, password, nom, prenom, email, dateArrivee);
		this.telephone = telephone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservation(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "Client [telephone=" + telephone + ", id=" + id + ", login=" + login + ", password="
				+ password + ", nom=" + nom + ", prenom=" + prenom + ", dateArrivee=" + dateArrivee + "]";
	}
}
