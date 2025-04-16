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

import quest.dao.IDAOOrdinateur;
import quest.model.Ordinateur;
import quest.model.Views;

@RestController
@RequestMapping("/ordinateur")
public class ProduitRestController {

	private IDAOOrdinateur daoOrdinateur;

	public ProduitRestController(IDAOOrdinateur daoOrdinateur) {
		super();
		this.daoOrdinateur = daoOrdinateur;
	}

	@GetMapping("")
	@JsonView(Views.ViewOrdinateur.class)
	public List<Ordinateur> getAll() {
		return this.daoOrdinateur.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewOrdinateur.class)
	public Ordinateur getById(@PathVariable Integer id) {
		return this.daoOrdinateur.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/{id}/detail")
	@JsonView(Views.ViewOrdinateurDetail.class)
	public Ordinateur getDetailById(@PathVariable Integer id) {
		return this.daoOrdinateur.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	@JsonView(Views.ViewOrdinateurDetail.class)
	public Ordinateur create(@RequestBody Ordinateur ordinateur) {
		return this.daoOrdinateur.save(ordinateur);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewOrdinateur.class)
	public Ordinateur update(@RequestBody Ordinateur ordinateur, @PathVariable Integer id) {
		if (id != ordinateur.getNumero() || !this.daoOrdinateur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		return this.daoOrdinateur.save(ordinateur);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.daoOrdinateur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.daoOrdinateur.deleteById(id);
	}
}
