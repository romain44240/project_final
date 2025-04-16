package projet.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import quest.dao.IDAOUtilisateur;
import quest.model.Stagiaire;
import quest.model.Utilisateur;
import quest.rest.request.InscriptionStagiaireRequest;
import quest.rest.request.UtilisateurRequest;
import quest.rest.response.UtilisateurResponse;

@RestController
@RequestMapping("/utilisateur")
public class ReservationRestController {

	private IDAOUtilisateur daoUtilisateur;

	public ReservationRestController(IDAOUtilisateur daoUtilisateur) {
		super();
		this.daoUtilisateur = daoUtilisateur;
	}

	@GetMapping("")
	public List<UtilisateurResponse> getAll() {
		List<Utilisateur> utilisateurs = this.daoUtilisateur.findAll();

//		return utilisateurs.stream().map(u -> UtilisateurResponse.convert(u)).toList();
		return utilisateurs.stream().map(UtilisateurResponse::convert).toList();
	}

	@GetMapping("/{id}")
	public UtilisateurResponse getById(@PathVariable Integer id) {
		return this.daoUtilisateur.findById(id).map(UtilisateurResponse::convert)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("")
	public Utilisateur create(@RequestBody UtilisateurRequest utilisateurRequest) {
		Utilisateur utilisateur = UtilisateurRequest.convert(utilisateurRequest);

		return daoUtilisateur.save(utilisateur);
	}

	@PutMapping("/{id}")
	public Utilisateur update(@RequestBody UtilisateurRequest utilisateurRequest, @PathVariable Integer id) {
		if (id != utilisateurRequest.getId() || !this.daoUtilisateur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incohérence de l'appel");
		}

		Utilisateur utilisateur = UtilisateurRequest.convert(utilisateurRequest);

		return daoUtilisateur.save(utilisateur);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!this.daoUtilisateur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non trouvé");
		}

		this.daoUtilisateur.deleteById(id);
	}
	
	@PostMapping("/inscriptionStagiaire")
	public Stagiaire create(@RequestBody @Valid InscriptionStagiaireRequest inscriptionStagiaireRequest, BindingResult result) {
		if(result.hasErrors()) {
			String errors = result.getAllErrors().toString(); // Récupération propre des messages d'erreurs à effectuer
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors);
		}
		
		Stagiaire stagiaire = InscriptionStagiaireRequest.convert(inscriptionStagiaireRequest);

		return daoUtilisateur.save(stagiaire);
	}
}
