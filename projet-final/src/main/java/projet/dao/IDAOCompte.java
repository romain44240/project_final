package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.model.Compte;


public interface IDAOCompte extends JpaRepository<Compte,Integer>{

}
	