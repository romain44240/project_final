package projet.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import projet.request.ClientRequest;
import projet.request.ConnexionRequest;
import projet.response.ClientResponse;
import projet.response.ConnexionResponse;
import projet.service.CompteService;
import projet.service.JwtService;

@RestController
@RequestMapping("/api")
public class CommonRestController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

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
		authentication = this.authenticationManager.authenticate(authentication);
		
		// Si on arrive ici, c'est que la connexion a fonctionné
		ConnexionResponse connexionResponse = new ConnexionResponse();

		// On génère un jeton pour l'utilisateur connecté
		String token = this.jwtService.generate(authentication);
		
		connexionResponse.setSuccess(true);
		connexionResponse.setToken(token);

		Integer id = this.compteService.getByLogin(connexionRequest.getLogin()).getId();
		connexionResponse.setId(id);

		return connexionResponse;
	}
	
	@PostMapping("/inscription")
	public ClientResponse inscription(@RequestBody ClientRequest clientRequest) {
		if(this.compteService.getByEmail(clientRequest.getEmail()) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email déjà utilisé");
		}
		
		if (this.compteService.getByLogin(clientRequest.getLogin()) != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login déjà utilisé");
		}
		
	    return compteService.createClient(clientRequest);
	}
}
