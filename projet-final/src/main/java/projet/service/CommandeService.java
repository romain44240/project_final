package projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.IDAOCommande;
import projet.model.Commande;

@Service
public class CommandeService {

	@Autowired
	IDAOCommande daoCommande;
	
	public boolean existById(Integer id) {
		return daoCommande.existsById(id);
	}

	public Commande getById(Integer id)
	{
		Optional<Commande> opt = daoCommande.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public List<Commande> getAll()
	{
		return daoCommande.findAll();
	}

	public Commande create(Commande commande) 
	{
		commande=daoCommande.save(commande);
		return commande;
	}

	public Commande update(Commande commande) 
	{
		commande=daoCommande.save(commande);
		return commande;
	}

	public boolean deleteById(Integer id) 
	{
		daoCommande.deleteById(id);
		return true;
	}

	public boolean delete(Commande commande) throws Exception 
	{
		return deleteById(commande.getId());
	}
}
