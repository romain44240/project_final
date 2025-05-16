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

import projet.request.ConsommableRequest;
import projet.request.JeuRequest;
import projet.response.ConsommableResponse;
import projet.response.JeuResponse;
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

	@PostMapping("/jeu")
	@PreAuthorize("hasRole('EMPLOYE')")
	public JeuResponse createJeu(@RequestBody JeuRequest jeu) {
		return this.produitService.createJeu(jeu);
	}

	@PostMapping("/consommable")
	@PreAuthorize("hasRole('EMPLOYE')")
	public ConsommableResponse createConsommable(@RequestBody ConsommableRequest consommable) {
		return this.produitService.createConsommable(consommable);
	}

	@PutMapping("/jeu/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public JeuResponse updateJeu(@PathVariable Integer id, @RequestBody JeuRequest dto) {
		return this.produitService.updateJeu(id, dto);
	}

	@PutMapping("/consommable/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public ConsommableResponse updateConsommable(@PathVariable Integer id, @RequestBody ConsommableRequest dto) {
		return this.produitService.updateConsommable(id, dto);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public void delete(@PathVariable Integer id) {
		this.produitService.delete(id);
	}
}
