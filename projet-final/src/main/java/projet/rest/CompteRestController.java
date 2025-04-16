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

import quest.dao.IDAOModule;
import quest.model.Module;
import quest.model.Views;

@RestController
@RequestMapping("/module")
public class CompteRestController {

	private IDAOModule daoModule;

	public CompteRestController(IDAOModule daoModule) {
		super();
		this.daoModule = daoModule;
	}

	@GetMapping("")
	@JsonView(Views.ViewModule.class)
	public List<Module> getAll() {
		return this.daoModule.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewModule.class)
	public Module getById(@PathVariable Integer id) {
		return this.daoModule.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	@JsonView(Views.ViewModule.class)
	public Module create(@RequestBody Module module) {
		return this.daoModule.save(module);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewModule.class)
	public Module update(@RequestBody Module module, @PathVariable Integer id) {
		if (id != module.getId() || !this.daoModule.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		return this.daoModule.save(module);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.daoModule.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.daoModule.deleteById(id);
	}
}
