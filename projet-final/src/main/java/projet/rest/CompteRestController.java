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

import projet.model.Compte;
import projet.model.Views;
import projet.service.CompteService;

@RestController
@RequestMapping("/api/compte")
public class CompteRestController {

	private CompteService compteService;

	public CompteRestController(CompteService compteService) {
		super();
		this.compteService = compteService;
	}

	@GetMapping("")
	@JsonView(Views.ViewCompte.class)
	public List<Compte> getAll() {
		return this.compteService.getAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewCompte.class)
	public Compte getById(@PathVariable Integer id) {
		return this.compteService.getById(id);
	}

	@PostMapping("")
	@JsonView(Views.ViewCompte.class)
	public Compte create(@RequestBody Compte Compte) {
		return this.compteService.create(Compte);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewCompte.class)
	public Compte update(@RequestBody Compte Compte, @PathVariable Integer id) {
		if (id != Compte.getId() || !this.compteService.existById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		return this.compteService.update(Compte);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.compteService.existById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.compteService.deleteById(id);
	}
}
