package projet.rest;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import projet.request.AchatRequest;
import projet.response.AchatResponse;
import projet.service.AchatService;

@RestController
@RequestMapping("/api/achat")
public class AchatRestController {

	private final AchatService achatService;

	public AchatRestController(AchatService achatService) {
		this.achatService = achatService;
	}

	@GetMapping("")
	@PreAuthorize("hasRole('EMPLOYE')")
	public List<AchatResponse> getAll() {
		return achatService.getAll(); 
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public AchatResponse getById(@PathVariable Integer id) {
		return achatService.getById(id);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('EMPLOYE')")
	public AchatResponse create(@RequestBody AchatRequest dto) {
		return achatService.create(dto);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public AchatResponse update(@PathVariable Integer id, @RequestBody AchatRequest dto) {
		return achatService.update(id, dto);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public void delete(@PathVariable Integer id) {
		achatService.delete(id);
	}
}
