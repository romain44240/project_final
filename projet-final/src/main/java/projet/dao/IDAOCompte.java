package projet.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import projet.model.Compte;


public interface IDAOCompte extends JpaRepository<Compte,Integer>{
	
	public Optional<Compte> findByLogin(String login);
	
	public Optional<Compte> findByLoginAndPassword(String login, String password);

}
	