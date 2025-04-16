package projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.model.Produit;


public interface IDAOProduit extends JpaRepository<Produit,Integer>{

}
	