package projet.request;

import java.util.List;

import org.springframework.beans.BeanUtils;

import projet.model.Achat;
import projet.model.Commande;
import projet.model.Reservation;

public class CommandeRequest {
	
	private Integer id;
	private List<Achat> achats;
	private Reservation reservation;
	
	public CommandeRequest() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	public static Commande convert(CommandeRequest commandeRequest) {
		Commande commande = new Commande();
		
		BeanUtils.copyProperties(commandeRequest, commande);
		
		return commande;
	}
}
