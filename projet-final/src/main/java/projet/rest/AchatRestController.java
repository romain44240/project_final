package projet.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import projet.request.AchatRequest;
import projet.response.AchatResponse;
import projet.service.AchatService;

@RestController
@RequestMapping("/achat")
public class AchatRestController {

	private final AchatService achatService;

	public AchatRestController(AchatService achatService) {
		this.achatService = achatService;
	}

	@GetMapping("")
	public List<AchatResponse> getAll() {
		return achatService.findAll(); 
	}

	@GetMapping("/{id}")
	public AchatResponse getById(@PathVariable Integer id) {
		return achatService.findById(id);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public AchatResponse create(@RequestBody AchatRequest dto) {
		return achatService.create(dto);
	}

	@PutMapping("/{id}")
	public AchatResponse update(@PathVariable Integer id, @RequestBody AchatRequest dto) {
		return achatService.update(id, dto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		achatService.delete(id);
	}
}
