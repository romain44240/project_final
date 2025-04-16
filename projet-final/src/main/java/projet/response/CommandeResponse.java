package projet.response;

import java.util.List;

import org.springframework.beans.BeanUtils;

import projet.model.Achat;
import projet.model.Commande;
import projet.model.Reservation;

public class CommandeResponse {
	
	private Integer id;
	private List<Achat> achats;
	private Reservation reservation;
	
	public CommandeResponse() {}

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
	
	public static CommandeResponse convert(Commande commande) {
		CommandeResponse commandeResponse = new CommandeResponse();
		
		BeanUtils.copyProperties(commande, commandeResponse);
		
		return commandeResponse;
	}
}
