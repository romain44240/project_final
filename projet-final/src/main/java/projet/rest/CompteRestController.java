package projet.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projet.request.ClientRequest;
import projet.request.EmployeRequest;
import projet.response.ClientResponse;
import projet.response.CompteInfoResponse;
import projet.response.EmployeResponse;
import projet.service.CompteService;

@RestController
@RequestMapping("/api/compte")
public class CompteRestController {

	private CompteService compteService;

	public CompteRestController(CompteService compteService) {
		super();
		this.compteService = compteService;
	}

	// CLIENT
	@GetMapping("/clients")
	@PreAuthorize("hasRole('EMPLOYE')")
	public List<ClientResponse> getAllClients() {
		return this.compteService.getAllClients();
	}

	@GetMapping("/client/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public ClientResponse getClientById(@PathVariable Integer id) {
		return this.compteService.getClientById(id);
	}

	@GetMapping("/client/{id}/info")
	// TODO PreAuthorize soit employe, soit id client en question
	public CompteInfoResponse getCompteInfo(@PathVariable Integer id) {
		return this.compteService.getCompteInfo(id);
	}

	@PostMapping("/client")
	@PreAuthorize("hasRole('EMPLOYE')")
	public ClientResponse createClient(@RequestBody ClientRequest dto) {
		return this.compteService.createClient(dto);
	}

	@PutMapping("/client/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public ClientResponse updateClient(@RequestBody ClientRequest dto, @PathVariable Integer id) {
		return this.compteService.updateClient(id,dto);
	}

	@DeleteMapping("/client/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public void deleteClient(@PathVariable Integer id) {
		this.compteService.deleteClient(id);
	}

	// EMPLOYE	
	@GetMapping("/employes")
	@PreAuthorize("hasRole('EMPLOYE')")
	public List<EmployeResponse> getAllEmployes() {
		return this.compteService.getAllEmployes();
	}

	@GetMapping("/employe/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public EmployeResponse getEmployeById(@PathVariable Integer id) {
		return this.compteService.getEmployeById(id);
	}

	@GetMapping("employe/disponibles")
	public List<EmployeResponse> getEmployesDisponibles(
		@RequestParam("debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime debut,
	    @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
		
		return this.compteService.getEmployesDisponibles(debut, fin);
	}

	@PostMapping("/employe")
	@PreAuthorize("hasRole('EMPLOYE')")
	public EmployeResponse createEmploye(@RequestBody EmployeRequest dto) {
		return this.compteService.createEmploye(dto);
	}

	@PutMapping("/employe/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public EmployeResponse update(@RequestBody EmployeRequest dto, @PathVariable Integer id) {
		return this.compteService.updateEmploye(id,dto);
	}

	@DeleteMapping("/employe/{id}")
	@PreAuthorize("hasRole('EMPLOYE')")
	public void deleteEmploye(@PathVariable Integer id) {
		this.compteService.deleteEmploye(id);
	}
}
