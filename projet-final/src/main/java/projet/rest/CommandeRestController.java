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

import projet.model.Views;
import projet.request.CommandeRequest;
import projet.response.CommandeResponse;
import projet.service.CommandeService;

@RestController
@RequestMapping("/api/commande")
public class CommandeRestController {

	private CommandeService commandeService;

	public CommandeRestController(CommandeService commandeService) {
		super();
		this.commandeService = commandeService;
	}

	@GetMapping("")
	@JsonView(Views.ViewCommande.class)
	public List<CommandeResponse> getAll() {
		return commandeService.getAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.ViewCommande.class)
	public CommandeResponse getById(@PathVariable Integer id) {
		return this.commandeService.getById(id);
	}

	@PostMapping("")
	@JsonView(Views.ViewCommande.class)
	public CommandeResponse create(@RequestBody CommandeRequest commande) {
		return this.commandeService.create(commande);
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ViewCommande.class)
	public CommandeResponse update(@RequestBody CommandeRequest commande, @PathVariable Integer id) {
		if(id != commande.getId() || !this.commandeService.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}
		
		return this.commandeService.update(id, commande);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if(!this.commandeService.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}
		
		this.commandeService.delete(id);
	}
}
