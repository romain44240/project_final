package projet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import projet.dao.IDAOProduit;
import projet.model.Consommable;
import projet.model.Jeu;
import projet.model.Produit;
import projet.request.ConsommableRequest;
import projet.request.JeuRequest;
import projet.request.ProduitRequest;
import projet.response.ConsommableResponse;
import projet.response.JeuResponse;
import projet.response.ProduitResponse;

@Service
public class ProduitService {
	
	@Autowired
	private IDAOProduit daoProduit;
	
	public List<ProduitResponse> getAll() {
		return daoProduit.findAll()
						 .stream()
						 .map(ProduitResponse::convert)
						 .collect(Collectors.toList());
	}
	
	public List<ProduitResponse> getAllJeux(){
		return daoProduit.findAll()
				         .stream()
				         .filter(p -> p instanceof Jeu)
				         .map(ProduitResponse::convert)
				         .collect(Collectors.toList());
	}
	
	public List<ProduitResponse> getAllConsommables(){
		return daoProduit.findAll()
		         .stream()
		         .filter(p -> p instanceof Consommable)
		         .map(ProduitResponse::convert)
		         .collect(Collectors.toList());
	}
	
	public ProduitResponse getById(Integer id) {
		Produit produit = daoProduit.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé avec id : " + id));
		return ProduitResponse.convert(produit);
	}
	
	public ProduitResponse create(ProduitRequest produitRequest) {
		Produit produit = ProduitRequest.convert(produitRequest);
		
		produit = daoProduit.save(produit);
		
		return ProduitResponse.convert(produit);
	}
	
	public JeuResponse createJeu(JeuRequest jeuRequest) {
		Jeu jeu = new Jeu();
		jeu.setNom(jeuRequest.getNom());
		jeu.setPrix(jeuRequest.getPrix());
		jeu.setStock(jeuRequest.getStock());
		jeu.setNbMin(jeuRequest.getNbMin());
		jeu.setNbMax(jeuRequest.getNbMax());
		jeu.setDuree(jeuRequest.getDuree());
		jeu.setEditeur(jeuRequest.getEditeur());
		jeu.setUrlRegle(jeuRequest.getUrlRegle());
		jeu.setUrlImage(jeuRequest.getUrlImage());
		
		Jeu saved = daoProduit.save(jeu);
	    return JeuResponse.convert(saved);
	}
	
	public ConsommableResponse createConsommable(ConsommableRequest consommableRequest) {
		Consommable consommable = new Consommable();
		consommable.setNom(consommableRequest.getNom());
		consommable.setPrix(consommableRequest.getPrix());
		consommable.setStock(consommableRequest.getStock());
		
		Consommable saved = daoProduit.save(consommable);
		return ConsommableResponse.convert(saved);
		
	}

	public JeuResponse updateJeu(Integer id, JeuRequest jeuRequest) {
		Jeu jeu = (Jeu) daoProduit.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé avec id : " + id));
		
		BeanUtils.copyProperties(jeuRequest, jeu);
		jeu.setId(id);
		jeu = daoProduit.save(jeu);
		
		return JeuResponse.convert(jeu);
	}

	public ConsommableResponse updateConsommable(Integer id, ConsommableRequest consommableRequest) {
		Consommable consommable = (Consommable) daoProduit.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé avec id : " + id));
		
		BeanUtils.copyProperties(consommableRequest, consommable);
		consommable.setId(id);

		consommable = daoProduit.save(consommable);
		
		return ConsommableResponse.convert(consommable);
	}
	
	public void delete(Integer id) {
		if (!daoProduit.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit non trouvé avec id : " + id);
		}
		daoProduit.deleteById(id);
	}
}
