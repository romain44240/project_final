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

import projet.model.Client;
import projet.model.Compte;
import projet.model.Employe;
import projet.request.ReservationRequest;
import projet.response.ReservationResponse;
import projet.service.CompteService;
import projet.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationRestController {

	private ReservationService reservationService;
	private CompteService compteService;

	public ReservationRestController(ReservationService reservationService, CompteService compteService) {
		super();
		this.reservationService = reservationService;
		this.compteService = compteService;
	}

	@GetMapping("")
	@PreAuthorize("hasRole('EMPLOYE')")
	public List<ReservationResponse> getAll() {
		return this.reservationService.getAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public ReservationResponse getById(@PathVariable Integer id) {
		return this.reservationService.getById(id);
	}

	@PostMapping("")
	//@PreAuthorize("hasAnyRole('employe', 'client')") //dépendant si on réserve en ligne ou sur place
	@PreAuthorize("isAuthenticated()")
	public ReservationResponse create(@RequestBody ReservationRequest dto) {
		return this.reservationService.create(dto);
	}

	@PutMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ReservationResponse update(@RequestBody ReservationRequest dto, @PathVariable Integer id) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// authentication.getPrincipal() renvoie le login (String) car CompteService.loadUserByUsername() retourne un User
		Compte compte = compteService.getByLogin((String) authentication.getPrincipal()); // login unique
		
		if(compte instanceof Employe || (compte instanceof Client && compte.getId() == dto.getIdClient())) {
			return this.reservationService.update(id, dto);
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vous n'avez pas les droits pour faire cela");
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public void delete(@PathVariable Integer id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Compte compte = compteService.getByLogin((String) authentication.getPrincipal());
		
		if(compte instanceof Employe || (compte instanceof Client && compte.getId() == reservationService.getById(id).getClient().getId())) {
			this.reservationService.delete(id);
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vous n'avez pas les droits pour faire cela");
		}
	}
}
