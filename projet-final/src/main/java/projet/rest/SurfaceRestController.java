package projet.rest;

import java.util.List;
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
import projet.request.SurfaceRequest;
import projet.response.SurfaceResponse;
import projet.service.SurfaceService;

@RestController
@RequestMapping("/api/surface")
public class SurfaceRestController {

	private SurfaceService surfaceService;

	public SurfaceRestController(SurfaceService surfaceService) {
		super();
		this.surfaceService = surfaceService;
	}

	@GetMapping("")
	@JsonView(Views.ViewSurface.class)
	public List<SurfaceResponse> getAll() {
		return this.surfaceService.getAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewSurfaceDetail.class)
	public SurfaceResponse getById(@PathVariable Integer id) {
		return this.surfaceService.getById(id);
	}
	
	@PostMapping("")
	@JsonView(Views.ViewSurface.class)
	public SurfaceResponse create(@RequestBody SurfaceRequest dto) {
		return this.surfaceService.create(dto);
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewSurface.class)
	public SurfaceResponse update(@RequestBody SurfaceRequest dto, @PathVariable Integer id) {
		return this.surfaceService.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		this.surfaceService.delete(id);
	}
}
