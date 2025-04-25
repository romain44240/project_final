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

import projet.model.Commande;
import projet.model.Views;
import projet.service.CommandeService;

@RestController
@RequestMapping("/api/commande")
public class CommandeRestController {

	private CommandeService CommandeService;

	public CommandeRestController(CommandeService CommandeService) {
		super();
		this.CommandeService = CommandeService;
	}

	@GetMapping("")
	@JsonView(Views.ViewCommande.class)
	public List<Commande> getAll() {
		return this.CommandeService.getAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.ViewCommande.class)
	public Commande getById(@PathVariable Integer id) {
		return this.CommandeService.getById(id);
	}

	@PostMapping("")
	@JsonView(Views.ViewCommande.class)
	public Commande create(@RequestBody Commande commande) {
		return this.CommandeService.create(commande);
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ViewCommande.class)
	public Commande update(@RequestBody Commande commande, @PathVariable Integer id) {
		if(id != commande.getId() || !this.CommandeService.existById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}
		
		return this.CommandeService.update(commande);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if(!this.CommandeService.existById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}
		
		this.CommandeService.deleteById(id);
	}
}
