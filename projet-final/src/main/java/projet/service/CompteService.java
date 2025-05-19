package projet.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import projet.dao.IDAOAchat;
import projet.dao.IDAOCompte;
import projet.dao.IDAOReservation;
import projet.model.Achat;
import projet.model.Client;
import projet.model.Compte;
import projet.model.Consommable;
import projet.model.Employe;
import projet.model.Jeu;
import projet.model.Reservation;
import projet.request.ClientRequest;
import projet.request.EmployeRequest;
import projet.response.ClientResponse;
import projet.response.CompteInfoResponse;
import projet.response.EmployeResponse;
import projet.response.ReservationResponse;

@Service
public class CompteService implements UserDetailsService {

    @Autowired
    private IDAOCompte daoCompte;

    @Autowired
    private IDAOReservation daoReservation;

    @Autowired
    private IDAOAchat daoAchat;

    // COMPTE
    public Compte getByLogin(String login)
	{
		Optional<Compte> opt = daoCompte.findByLogin(login);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}
    
    public Compte getByEmail(String email) {
    	Optional<Compte> opt = daoCompte.findByEmail(email);
    	if(opt.isEmpty()) {return null;}
    	else {
    		return opt.get();
    	}
    }
    
    public Compte getByLoginAndPassword(String login, String password) {
    	Optional<Compte> opt = daoCompte.findByLoginAndPassword(login, password);
    	if(opt.isEmpty()) {
    		return null;
    	}
    	return opt.get();
    }

    //CLIENT
    public ClientResponse createClient(ClientRequest clientRequest) {
        Client client = ClientRequest.convert(clientRequest);
        client.setPassword(encode(clientRequest.getPassword()));
        client = daoCompte.save(client);
        return ClientResponse.convert(client);
    }

    public ClientResponse updateClient(Integer id, ClientRequest clientRequest) {
        Client client = (Client) daoCompte.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé avec id : " + id));

        if(clientRequest.getPassword() == null){
            clientRequest.setPassword(client.getPassword());
        }else{
            clientRequest.setPassword(encode(clientRequest.getPassword()));
        }
        BeanUtils.copyProperties(clientRequest, client);
        client.setId(id);

        client = daoCompte.save(client);
        return ClientResponse.convert(client);
    }

    public List<ClientResponse> getAllClients() {
        return daoCompte.findAll().stream()
                .filter(client -> client instanceof Client) 
                .map(compte -> ClientResponse.convert((Client) compte))
                .collect(Collectors.toList());
    }

    public ClientResponse getClientById(Integer id) {
        Client client = (Client) daoCompte.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé avec id : " + id));
        return ClientResponse.convert(client);
    }

    public boolean deleteClient(Integer id) {
        daoCompte.deleteById(id);
        return true;
    }
    
    public List<ReservationResponse> getReservationsByClientId(Integer id){
    	Compte compte = (Client) daoCompte.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Réservation d'un client non trouvé avec id : " + id));
    	
    	if(!(compte instanceof Client)) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le compte avec l'id " + id + "n'est pas un client.");
    	}
    	
    	Client client = (Client) compte;
    
    	return client.getReservations().stream()
                .map(ReservationResponse::convert)
                .collect(Collectors.toList());
    	
    }

    public CompteInfoResponse getCompteInfo(Integer id) {
        CompteInfoResponse compteInfoResponse = new CompteInfoResponse();

        compteInfoResponse.setReservations(daoReservation.getReservationsByClientId(id));

        for (Reservation r : compteInfoResponse.getReservations()) {
            r.getClient().setReservations(null);
            r.getSurface().setReservation(null);
            if(r.getEmploye()!=null){
                r.getEmploye().setReservations(null);
            }
            
        }
        
        List<Achat> achats = daoAchat.getAchatsByClientId(id);
        HashMap<String, Integer> consommables = new HashMap<String, Integer>();
        List<Jeu> jeux = new ArrayList<Jeu>();

        for (Achat a : achats) {
            if (a.getProduit() instanceof Consommable consommable) {
                consommables.put(consommable.getNom(), a.getQuantite());
            } else if (a.getProduit() instanceof Jeu jeu) {
                jeux.add(jeu);
            }
        }

        compteInfoResponse.setConsommables(consommables);
        compteInfoResponse.setJeux(jeux);

        return compteInfoResponse;
    }

    //EMPLOYE
    public EmployeResponse createEmploye(EmployeRequest employeRequest) {
        Employe employe = EmployeRequest.convert(employeRequest);
        employe.setPassword(encode(employeRequest.getPassword()));
        employe = daoCompte.save(employe);
        return EmployeResponse.convert(employe);
    }

    public EmployeResponse updateEmploye(Integer id, EmployeRequest employeRequest) {
        Employe employe = (Employe) daoCompte.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employé non trouvé avec id : " + id));
        BeanUtils.copyProperties(employeRequest, employe);
        employe.setId(id);

        employe = daoCompte.save(employe);
        return EmployeResponse.convert(employe);
    }

    public List<EmployeResponse> getAllEmployes() {
        return daoCompte.findAll().stream()
                .filter(employe -> employe instanceof Employe) 
                .map(compte -> EmployeResponse.convert((Employe) compte))
                .collect(Collectors.toList());
    }

    public EmployeResponse getEmployeById(Integer id) {
        Employe employe = (Employe) daoCompte.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employé non trouvé avec id : " + id));
        return EmployeResponse.convert(employe);
    }

    public List<EmployeResponse> getEmployesDisponibles(LocalDateTime debut, LocalDateTime fin) {
        List<Reservation> incluses = daoReservation.findByDebutLessThanAndFinGreaterThan(fin, debut);

        List<Employe> employesDisponibles = daoCompte.findAllEmployes();

        for (Reservation r : incluses) {
            if (r.getEmploye() != null) {employesDisponibles.remove(r.getEmploye());}
        }

        return employesDisponibles.stream().map(EmployeResponse::convert).collect(Collectors.toList());
    }

    public boolean deleteEmploye(Integer id) {
        daoCompte.deleteById(id);
        return true;
    }

    public void delete(Compte compte)
    {
        daoCompte.deleteById(compte.getId());
    }

    private String encode(String password) {
        return password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Compte compte = getByLogin(login);

        if (compte == null) {
            throw new UsernameNotFoundException("Aucun compte avec le login : " + login);
        }

        String role;
        if (compte instanceof Employe) {
            role = "ROLE_EMPLOYE";
        } else if (compte instanceof Client) {
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