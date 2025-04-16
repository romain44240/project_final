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

import projet.dao.IDAOSurface;
import projet.model.Surface;
import projet.model.Views;

@RestController
@RequestMapping("/Surface")
public class SurfaceRestController {

	private IDAOSurface daoSurface;

	public SurfaceRestController(IDAOSurface daoSurface) {
		super();
		this.daoSurface = daoSurface;
	}

	@GetMapping("")
	@JsonView(Views.ViewSurface.class)
	public List<Surface> getAll() {
		return this.daoSurface.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewSurfaceDetail.class)
	public Surface getById(@PathVariable Integer id) {
		return this.daoSurface.findById(id).get();
	}
	
	@PostMapping("")
	@JsonView(Views.ViewSurface.class)
	public Surface create(@RequestBody Surface Surface) {
		return this.daoSurface.save(Surface);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewSurface.class)
	public Surface update(@RequestBody Surface Surface, @PathVariable Integer id) {
		if (id != Surface.getId() || !this.daoSurface.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		return this.daoSurface.save(Surface);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.daoSurface.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.daoSurface.deleteById(id);
	}
}
