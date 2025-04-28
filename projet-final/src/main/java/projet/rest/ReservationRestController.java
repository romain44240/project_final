package projet.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import projet.model.Client;
import projet.model.Compte;
import projet.model.Employe;
import projet.model.Views;
import projet.request.ReservationRequest;
import projet.response.ReservationResponse;
import projet.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationRestController {

	private ReservationService reservationService;

	public ReservationRestController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}

	@GetMapping("")
	@JsonView(Views.ViewReservation.class)
	@PreAuthorize("hasRole('employe')")
	public List<ReservationResponse> getAll() {
		return this.reservationService.getAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewReservation.class)
	@PreAuthorize("hasRole('employe')")
	public ReservationResponse getById(@PathVariable Integer id) {
		return this.reservationService.getById(id);
	}

	@PostMapping("")
	@JsonView(Views.ViewReservation.class)
	//@PreAuthorize("hasAnyRole('employe', 'client')") //dépendant si on réserve en ligne ou sur place
	@PreAuthorize("isAuthenticated()")
	public ReservationResponse create(@RequestBody ReservationRequest dto) {
		return this.reservationService.create(dto);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewReservation.class)
	@PreAuthorize("isAuthenticated()")
	public ReservationResponse update(@RequestBody ReservationRequest dto, @PathVariable Integer id) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Compte compte = (Compte) authentication.getPrincipal();
		if(compte instanceof Employe) {
			return this.reservationService.update(id, dto);
		} else if(compte instanceof Client) {
			if(compte.getId() == dto.getIdClient()) {
				return this.reservationService.update(id, dto);
			} 
			else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Accès interdit !");
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur 404" );
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Compte compte = (Compte) authentication.getPrincipal();
		
		this.reservationService.delete(id);
	}
}
