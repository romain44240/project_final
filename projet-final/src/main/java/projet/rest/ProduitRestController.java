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

import projet.dao.IDAOProduit;
import projet.model.Produit;
import projet.model.Views;


@RestController
@RequestMapping("/ordinateur")
public class ProduitRestController {

	private IDAOProduit daoProduit;

	public ProduitRestController(IDAOProduit daoProduit) {
		super();
		this.daoProduit = daoProduit;
	}

	@GetMapping("")
	@JsonView(Views.ViewProduit.class)
	public List<Produit> getAll() {
		return this.daoProduit.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewProduit.class)
	public Produit getById(@PathVariable Integer id) {
		return this.daoProduit.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/{id}/detail")
	@JsonView(Views.ViewProduitDetail.class)
	public Produit getDetailById(@PathVariable Integer id) {
		return this.daoProduit.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	@JsonView(Views.ViewProduitDetail.class)
	public Produit create(@RequestBody Produit produit) {
		return this.daoProduit.save(produit);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewProduit.class)
	public Produit update(@RequestBody Produit produit, @PathVariable Integer id) {
		if (id != produit.getId() || !this.daoProduit.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		return this.daoProduit.save(produit);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.daoProduit.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.daoProduit.deleteById(id);
	}
}
