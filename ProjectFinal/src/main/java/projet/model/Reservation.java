package projet.model;

import java.time.LocalDateTime;

public class Reservation {
	
	private Integer id;
	private LocalDateTime dateReservation;
	private int duree, nbPersonne;
	private Client client;
	private Employe employe;
	private Surface surface;
	private Jeu jeu;
	
	
	
	public Reservation(Integer id, LocalDateTime dateReservation, int duree, int nbPersonne, Client client,
			Employe employe, Surface surface, Jeu jeu) {
		this.id = id;
		this.dateReservation = dateReservation;
		this.duree = duree;
		this.nbPersonne = nbPersonne;
		this.client = client;
		this.employe = employe;
		this.surface = surface;
		this.jeu = jeu;
	}



	public Integer getId() {
		return id;
	}



	public LocalDateTime getDateReservation() {
		return dateReservation;
	}



	public int getDuree() {
		return duree;
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



	public void setId(Integer id) {
		this.id = id;
	}



	public void setDateReservation(LocalDateTime dateReservation) {
		this.dateReservation = dateReservation;
	}



	public void setDuree(int duree) {
		this.duree = duree;
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



	@Override
	public String toString() {
		return "Reservation [id=" + id + ", dateReservation=" + dateReservation + ", duree=" + duree + ", nbPersonne="
				+ nbPersonne + ", client=" + client + ", employe=" + employe + ", surface=" + surface + ", jeu=" + jeu
				+ "]";
	}



	
	
	
	
	
	
	

	
	

}
