package projet.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import projet.dao.IDAOReservation;
import projet.model.Reservation;
import projet.model.Views;

@RestController
@RequestMapping("/reservation")
public class ReservationRestController {

	private IDAOReservation daoReservation;

	public ReservationRestController(IDAOReservation daoReservation) {
		super();
		this.daoReservation = daoReservation;
	}

	@GetMapping("")
	@JsonView(Views.ViewReservation.class)
	public List<Reservation> getAll() {
		return this.daoReservation.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewReservation.class)
	public Reservation getById(@PathVariable Integer id) {
		return this.daoReservation.findById(id).get();
	}

	@PostMapping("")
	@JsonView(Views.ViewReservation.class)
	public Reservation create(@RequestBody Reservation reservation) {
		return this.daoReservation.save(reservation);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewReservation.class)
	public Reservation update(@RequestBody Reservation reservation, @PathVariable Integer id) {
		if (id != reservation.getId() || !this.daoReservation.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		return this.daoReservation.save(reservation);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.daoReservation.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.daoReservation.deleteById(id);
	}
}
