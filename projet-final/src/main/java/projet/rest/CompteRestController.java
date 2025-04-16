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

import projet.dao.IDAOCompte;
import projet.model.Compte;
import projet.model.Views;


@RestController
@RequestMapping("/Compte")
public class CompteRestController {

	private IDAOCompte daoCompte;

	public CompteRestController(IDAOCompte daoCompte) {
		super();
		this.daoCompte = daoCompte;
	}

	@GetMapping("")
	@JsonView(Views.ViewCompte.class)
	public List<Compte> getAll() {
		return this.daoCompte.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewCompte.class)
	public Compte getById(@PathVariable Integer id) {
		return this.daoCompte.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	@JsonView(Views.ViewCompte.class)
	public Compte create(@RequestBody Compte Compte) {
		return this.daoCompte.save(Compte);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewCompte.class)
	public Compte update(@RequestBody Compte Compte, @PathVariable Integer id) {
		if (id != Compte.getId() || !this.daoCompte.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		return this.daoCompte.save(Compte);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.daoCompte.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.daoCompte.deleteById(id);
	}
}
