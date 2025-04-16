package projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.IDAOCommande;
import projet.dao.IDAOCompte;
import projet.model.Commande;
import projet.model.Compte;

@Service
public class CompteService {

	@Autowired
	IDAOCompte daoCompte;
	
	public boolean existById(Integer id) {
		return daoCompte.existsById(id);
	}

	public Compte getById(Integer id)
	{
		Optional<Compte> opt = daoCompte.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public List<Compte> getAll()
	{
		return daoCompte.findAll();
	}

	public Compte create(Compte compte) 
	{
		compte=daoCompte.save(compte);
		return compte;
	}

	public Compte update(Compte compte) 
	{
		compte=daoCompte.save(compte);
		return compte;
	}

	public boolean deleteById(Integer id) 
	{
		daoCompte.deleteById(id);
		return true;
	}

	public boolean delete(Compte compte) throws Exception 
	{
		return deleteById(compte.getId());
	}
}
