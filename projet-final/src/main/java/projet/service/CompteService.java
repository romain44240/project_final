package projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projet.dao.IDAOCompte;
import projet.model.Compte;

@Service
public class CompteService implements UserDetailsService{

	@Autowired
	IDAOCompte daoCompte;
	
	public boolean existById(Integer id) {
		return daoCompte.existsById(id);
	}

	public Compte getByLogin(String login)
	{
		Optional<Compte> opt = daoCompte.findByLogin(login);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
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
		compte.setPassword(this.encode(compte.getPassword()));
		compte=daoCompte.save(compte);
		return compte;
	}

	public Compte update(Compte compte) 
	{
		compte.setPassword(this.encode(compte.getPassword()));
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

	private String encode(String password){
		return password = new BCryptPasswordEncoder().encode(password);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Compte compte = getByLogin(username);

		if (compte == null) {
			throw new UsernameNotFoundException("Aucun compte avec le login : " + username);
		}
		String role;
		if (compte instanceof projet.model.Employe) {
			role = "ROLE_EMPLOYE";
		} else if (compte instanceof projet.model.Client) {
			role = "ROLE_CLIENT";
		} else {
			role = "ROLE_USER"; 
		}

		return org.springframework.security.core.userdetails.User
			.withUsername(compte.getLogin())
			.password(compte.getPassword())
			.roles(role.replace("ROLE_", ""))
			.build();
	}

}
