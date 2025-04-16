package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.model.Commande;


public interface IDAOCommande extends JpaRepository<Commande,Integer>{

}
	