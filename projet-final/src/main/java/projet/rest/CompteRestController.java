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
import projet.request.ClientRequest;
import projet.request.EmployeRequest;
import projet.response.ClientResponse;
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
	@JsonView(Views.ViewCompte.class)
	public List<ClientResponse> getAllClients() {
		return this.compteService.getAllClients();
	}

	@GetMapping("/client/{id}")
	@JsonView(Views.ViewCompte.class)
	public ClientResponse getClientById(@PathVariable Integer id) {
		return this.compteService.getClientById(id);
	}

	@PostMapping("/client")
	@JsonView(Views.ViewCompte.class)
	public ClientResponse createClient(@RequestBody ClientRequest dto) {
		return this.compteService.createClient(dto);
	}

	@PutMapping("/client/{id}")
	@JsonView(Views.ViewCompte.class)
	public ClientResponse updateClient(@RequestBody ClientRequest dto, @PathVariable Integer id) {
		return this.compteService.updateClient(id,dto);
	}

	@DeleteMapping("/client/{id}")
	public void deleteClient(@PathVariable Integer id) {
		this.compteService.deleteClient(id);
	}

	// EMPLOYE	
	@GetMapping("/employes")
	@JsonView(Views.ViewCompte.class)
	public List<EmployeResponse> getAllEmployes() {
		return this.compteService.getAllEmployes();
	}

	@GetMapping("/employe/{id}")
	@JsonView(Views.ViewCompte.class)
	public EmployeResponse getEmployeById(@PathVariable Integer id) {
		return this.compteService.getEmployeById(id);
	}

	@PostMapping("/employe")
	@JsonView(Views.ViewCompte.class)
	public EmployeResponse createEmploye(@RequestBody EmployeRequest dto) {
		return this.compteService.createEmploye(dto);
	}

	@PutMapping("/employe/{id}")
	@JsonView(Views.ViewCompte.class)
	public EmployeResponse update(@RequestBody EmployeRequest dto, @PathVariable Integer id) {
		return this.compteService.updateEmploye(id,dto);
	}

	@DeleteMapping("/employe/{id}")
	public void deleteEmploye(@PathVariable Integer id) {
		this.compteService.deleteEmploye(id);
	}

}
