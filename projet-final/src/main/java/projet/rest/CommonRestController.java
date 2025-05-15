package projet.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projet.request.ClientRequest;
import projet.request.ConnexionRequest;
import projet.response.ClientResponse;
import projet.response.ConnexionResponse;
import projet.service.CompteService;
import projet.config.jwt.JwtUtil;
import projet.model.Client;
import projet.model.Compte;
import projet.model.Employe;

@RestController
@RequestMapping("/api")
public class CommonRestController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CompteService compteService;

	public CommonRestController() {
		super();
	}

	@PostMapping("/connexion")
	public ConnexionResponse create(@RequestBody ConnexionRequest connexionRequest) {
		// On va demander à SPRING SECURITY de vérifier le username / password
		// On a besoin d'un AuthenticationManager
		// On utilisera la méthode authenticate, qui attend un Authentication
		// Et on utilisera le type UsernamePasswordAuthenticationToken pour donner le
		// username & le password
		Authentication authentication = new UsernamePasswordAuthenticationToken(connexionRequest.getLogin(),connexionRequest.getPassword());

		// On demande à SPRING SECURITY de vérifier ces informations de connexion
		this.authenticationManager.authenticate(authentication);
		
		// Si on arrive ici, c'est que la connexion a fonctionné
		ConnexionResponse connexionResponse = new ConnexionResponse();

		// On génère un jeton pour l'utilisateur connecté
		String token = JwtUtil.generate(authentication);
		
		connexionResponse.setSuccess(true);
		connexionResponse.setToken(token);

		Compte compte = compteService.getByLoginAndPassword(connexionRequest.getLogin(), connexionRequest.getPassword());
		if (compte instanceof Employe) {
			connexionResponse.setRole("EMPLOYE");
		} else if (compte instanceof Client) {
			connexionResponse.setRole("CLIENT");
		}

		return connexionResponse;
	}
	
	@PostMapping("/inscription")
	public ClientResponse inscription(@RequestBody ClientRequest clientRequest) {
		if(this.compteService.getByEmail(clientRequest.getEmail()) != null) {
			throw new IllegalArgumentException("email déjà utilisé");
		}
		
		if (this.compteService.getByLogin(clientRequest.getLogin()) != null) {
			throw new IllegalArgumentException("login déjà utilisé");
		}
		
	    return compteService.createClient(clientRequest);
	}
}
