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

import quest.model.Matiere;
import quest.model.Views;
import quest.service.MatiereService;

@RestController
@RequestMapping("/matiere")
public class CommandeRestController {

	private MatiereService matiereService;

	public CommandeRestController(MatiereService matiereService) {
		super();
		this.matiereService = matiereService;
	}

	@GetMapping("")
	@JsonView(Views.ViewMatiere.class)
	public List<Matiere> getAll() {
		return this.matiereService.getAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.ViewMatiere.class)
	public Matiere getById(@PathVariable Integer id) {
		return this.matiereService.getById(id);
	}

	@PostMapping("")
	@JsonView(Views.ViewMatiere.class)
	public Matiere create(@RequestBody Matiere matiere) {
		return this.matiereService.create(matiere);
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ViewMatiere.class)
	public Matiere update(@RequestBody Matiere matiere, @PathVariable Integer id) {
		if(id != matiere.getId() || !this.matiereService.existById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}
		
		return this.matiereService.update(matiere);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if(!this.matiereService.existById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}
		
		this.matiereService.deleteById(id);
	}
}
