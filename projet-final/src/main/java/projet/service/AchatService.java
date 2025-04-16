package projet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.IDAOAchat;
import projet.dao.IDAOProduit;
import projet.dao.IDAOCommande;
import projet.model.Achat;
import projet.model.Produit;
import projet.model.Commande;
import projet.request.AchatRequestDTO;
import projet.response.AchatResponseDTO;

@Service
public class AchatService {

	@Autowired
	private IDAOAchat daoAchat;

	@Autowired
	private IDAOProduit daoProduit;

	@Autowired
	private IDAOCommande daoCommande;
	
	public List<AchatResponseDTO> findAll() {
		return daoAchat.findAll()
			.stream()
			.map(AchatResponseDTO::convert)
			.collect(Collectors.toList());
	}

	public AchatResponseDTO create(AchatRequestDTO dto) {
		Produit produit = daoProduit.findById(dto.getIdProduit())
				.orElseThrow(() -> new RuntimeException("Produit non trouvé avec id : " + dto.getIdProduit()));

		Commande commande = daoCommande.findById(dto.getIdCommande())
				.orElseThrow(() -> new RuntimeException("Commande non trouvée avec id : " + dto.getIdCommande()));

		Achat achat = AchatRequestDTO.convert(dto);

		achat.setProduit(produit);
		achat.setCommande(commande);

		Achat saved = daoAchat.save(achat);

		return AchatResponseDTO.convert(saved);
	}

	public AchatResponseDTO findById(Integer id) {
		Achat achat = daoAchat.findById(id)
				.orElseThrow(() -> new RuntimeException("Achat non trouvé avec id : " + id));
		return AchatResponseDTO.convert(achat);
	}

	public void delete(Integer id) {
		if (!daoAchat.existsById(id)) {
			throw new RuntimeException("Achat non trouvé avec id : " + id);
		}
		daoAchat.deleteById(id);
	}

	public AchatResponseDTO update(Integer id, AchatRequestDTO dto) {
		Achat achatExistant = daoAchat.findById(id)
				.orElseThrow(() -> new RuntimeException("Achat non trouvé avec id : " + id));

		Produit produit = daoProduit.findById(dto.getIdProduit())
				.orElseThrow(() -> new RuntimeException("Produit non trouvé avec id : " + dto.getIdProduit()));

		Commande commande = daoCommande.findById(dto.getIdCommande())
				.orElseThrow(() -> new RuntimeException("Commande non trouvée avec id : " + dto.getIdCommande()));

		achatExistant.setQuantite(dto.getQuantite());
		achatExistant.setProduit(produit);
		achatExistant.setCommande(commande);

		Achat updated = daoAchat.save(achatExistant);

		return AchatResponseDTO.convert(updated);
	}
}
