package projet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.IDAOCommande;
import projet.model.Commande;
import projet.request.CommandeRequest;
import projet.response.CommandeResponse;

@Service
public class CommandeService {

	@Autowired
	IDAOCommande daoCommande;

	public List<CommandeResponse> getAll()
	{
		return daoCommande.findAll()
						  .stream()
						  .map(CommandeResponse::convert)
						  .collect(Collectors.toList());
	}

	public CommandeResponse getById(Integer id)
	{
		Commande commande = daoCommande.findById(id)
				 .orElseThrow(() -> new RuntimeException("Commande non trouvée avec id : " + id));
		return CommandeResponse.convert(commande);
	}

	public boolean existsById(Integer id) {
		return daoCommande.existsById(id);
	}

	public CommandeResponse create(CommandeRequest commandeRequest) 
	{
		Commande commande = CommandeRequest.convert(commandeRequest);
		commande = daoCommande.save(commande);
		return CommandeResponse.convert(commande);
	}

	public CommandeResponse update(Integer id, CommandeRequest commandeRequest) 
	{
		Commande commande = daoCommande.findById(id)
				 .orElseThrow(() -> new RuntimeException("Commande non trouvée avec id : " + id));
		
		BeanUtils.copyProperties(commandeRequest, commande);
		commande=daoCommande.save(commande);
		
		return CommandeResponse.convert(commande);
	}

	public void delete(Integer id)
	{
		if (!daoCommande.existsById(id)) {
			throw new RuntimeException("Commande non trouvée avec id : " + id);
		}
		daoCommande.deleteById(id);
	}
}
