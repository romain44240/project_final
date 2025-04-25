package projet.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
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
	public List<ReservationResponse> getAll() {
		return this.reservationService.getAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewReservation.class)
	public ReservationResponse getById(@PathVariable Integer id) {
		return this.reservationService.getById(id);
	}

	@PostMapping("")
	@JsonView(Views.ViewReservation.class)
	public ReservationResponse create(@RequestBody ReservationRequest dto) {
		return this.reservationService.create(dto);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewReservation.class)
	public ReservationResponse update(@RequestBody ReservationRequest dto, @PathVariable Integer id) {
		return this.reservationService.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		this.reservationService.delete(id);
	}
}
