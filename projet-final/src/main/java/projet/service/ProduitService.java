package projet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.IDAOProduit;
import projet.model.Produit;
import projet.request.ProduitRequest;
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
	
	public ProduitResponse getById(Integer id) {
		Produit produit = daoProduit.findById(id)
				.orElseThrow(() -> new RuntimeException("Produit non trouvé avec id : " + id));
		return ProduitResponse.convert(produit);
	}
	
	public ProduitResponse create(ProduitRequest produitRequest) {
		Produit produit = ProduitRequest.convert(produitRequest);
		
		produit = daoProduit.save(produit);
		
		return ProduitResponse.convert(produit);
	}
	
	public ProduitResponse update(Integer id, ProduitRequest produitRequest) {
		Produit produit = daoProduit.findById(id)
				.orElseThrow(() -> new RuntimeException("Produit non trouvé avec id : " + id));
		
		BeanUtils.copyProperties(produitRequest, produit);
		produit = daoProduit.save(produit);
		
		return ProduitResponse.convert(produit);
	}
	
	public void delete(Integer id) {
		if (!daoProduit.existsById(id)) {
			throw new RuntimeException("Produit non trouvé avec id : " + id);
		}
		daoProduit.deleteById(id);
	}
}
