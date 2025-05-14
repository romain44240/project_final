package projet.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false)
	private LocalDateTime debut;
	@Column(nullable=false)
	private LocalDateTime fin;
	@Column(nullable=false)
	private int nbPersonne;
	
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToOne
	private Employe employe;
	
	@OneToOne
	private Surface surface;
	
	@OneToOne
	private Jeu jeu;

	@OneToMany(mappedBy = "reservation")
	private List<Achat> achats;
	
	public Reservation() {}

	public Reservation(Integer id, LocalDateTime debut, LocalDateTime fin, int nbPersonne, Client client,
			Employe employe, Surface surface, Jeu jeu, List<Achat> achats) {
		this.id = id;
		this.debut = debut;
		this.fin = fin;
		this.nbPersonne = nbPersonne;
		this.client = client;
		this.employe = employe;
		this.surface = surface;
		this.jeu = jeu;
		this.achats = achats;
	}

	public Reservation(LocalDateTime debut, LocalDateTime fin, int nbPersonne, Client client,
			Employe employe, Surface surface, Jeu jeu, List<Achat> achats) {
		this.debut = debut;
		this.fin = fin;
		this.nbPersonne = nbPersonne;
		this.client = client;
		this.employe = employe;
		this.surface = surface;
		this.jeu = jeu;
		this.achats = achats;
	}

	public Integer getId() {
		return id;
	}

	public LocalDateTime getDebut() {
		return debut;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public int getNbPersonne() {
		return nbPersonne;
	}

	public Client getClient() {
		return client;
	}

	public Employe getEmploye() {
		return employe;
	}

	public Surface getSurface() {
		return surface;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDebut(LocalDateTime debut) {
		this.debut = debut;
	}

	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}

	public void setNbPersonne(int nbPersonne) {
		this.nbPersonne = nbPersonne;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", debut=" + debut + ", fin=" + fin + ", nbPersonne="
				+ nbPersonne + ", client=" + client + ", employe=" + employe + ", surface=" + surface + ", jeu=" + jeu
				+ ", achats=" + achats + "]";
	}
}
