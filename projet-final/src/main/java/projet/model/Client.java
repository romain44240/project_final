package projet.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("client")
public class Client extends Compte {

	@Column(length = 100, nullable=false)
	private String email;
	@Column(length = 100, nullable=false)
	private String telephone;
	
	@OneToMany
	private List<Reservation> reservation;

	
	public Client() {super();}
	
	public Client(int id, String login, String password, String nom, String prenom, LocalDate dateArrivee, String email,
			String telephone) {
		super(id, login, password, nom, prenom, dateArrivee);
		this.email = email;
		this.telephone = telephone;
	}


	public String getEmail() {
		return email;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	

	public List<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Client [email=" + email + ", telephone=" + telephone + ", id=" + id + ", login=" + login + ", password="
				+ password + ", nom=" + nom + ", prenom=" + prenom + ", dateArrivee=" + dateArrivee + "]";
	}

	
	
}
