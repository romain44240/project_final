package projet.rest;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
import projet.request.ProduitRequest;
import projet.response.ProduitResponse;
import projet.service.ProduitService;


@RestController
@RequestMapping("/api/produit")
public class ProduitRestController {

	private ProduitService produitService;

	public ProduitRestController(ProduitService produitService) {
		super();
		this.produitService = produitService;
	}

	@GetMapping("")
	public List<ProduitResponse> getAll() {
		return this.produitService.getAll();
	}

	@GetMapping("/jeux")
	public List<ProduitResponse> getAllJeux() {
		return this.produitService.getAllJeux();
	}

	@GetMapping("/consos")
	public List<ProduitResponse> getAllConsommables() {
		return this.produitService.getAllConsommables();
	}

	@GetMapping("/{id}")
	public ProduitResponse getById(@PathVariable Integer id) {
		return this.produitService.getById(id);
	}
	
	@GetMapping("/{id}/detail")
	@PreAuthorize("hasRole('EMPLOYE')")
	public ProduitResponse getDetailById(@PathVariable Integer id) {
		// id + stock
		return this.produitService.getById(id);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('EMPLOYE')")
	public ProduitResponse create(@RequestBody ProduitRequest dto) {
		return this.produitService.create(dto);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public ProduitResponse update(@PathVariable Integer id, @RequestBody ProduitRequest dto) {
		return this.produitService.update(id, dto);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public void delete(@PathVariable Integer id) {
		this.produitService.delete(id);
	}
}
