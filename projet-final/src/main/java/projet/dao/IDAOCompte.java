package projet.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projet.model.Compte;
import projet.model.Employe;


public interface IDAOCompte extends JpaRepository<Compte,Integer>{
	
	public Optional<Compte> findByLogin(String login);
	
	public Optional<Compte> findByEmail(String email);
	
	public Optional<Compte> findByLoginAndPassword(String login, String password);

	@Query("SELECT e FROM Employe e")
	public List<Employe> findAllEmployes();
}
	