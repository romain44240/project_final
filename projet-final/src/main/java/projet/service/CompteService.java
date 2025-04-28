package projet.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.IDAOCompte;
import projet.model.Client;
import projet.model.Compte;
import projet.model.Employe;
import projet.request.ClientRequest;
import projet.request.EmployeRequest;
import projet.response.ClientResponse;
import projet.response.EmployeResponse;
import projet.response.ReservationResponse;

@Service
public class CompteService {

    @Autowired
    private IDAOCompte daoCompte;

    // COMPTE
    public Compte getByLogin(String login)
	{
		Optional<Compte> opt = daoCompte.findByLogin(login);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}
    
    public Compte getByEmailAndPassword(String email, String login) {
    	Optional<Compte> opt = daoCompte.findByEmailAndPassword(email, login);
    	if(opt.isEmpty()) {
    		return null;
    	}
    	return opt.get();
    }

    //CLIENT
    public ClientResponse createClient(ClientRequest clientRequest) {
        Client client = ClientRequest.convert(clientRequest);
        client = daoCompte.save(client); 
        return ClientResponse.convert(client);
    }

    public ClientResponse updateClient(Integer id, ClientRequest clientRequest) {
        Client client = (Client) daoCompte.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec id : " + id));
        BeanUtils.copyProperties(clientRequest, client);
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
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec id : " + id));
        return ClientResponse.convert(client);
    }

    public boolean deleteClient(Integer id) {
        daoCompte.deleteById(id);
        return true;
    }
    
    
    public List<ReservationResponse> getReservationsByClientId(Integer id){
    	Compte compte = (Client) daoCompte.findById(id).get();
    	
    	if(!(compte instanceof Client)) {
    		throw new RuntimeException("Le compte avec l'id " + id + "n'est pas un client.");
    	}
    	
    	Client client = (Client) compte;
    
    	return client.getReservation().stream()
                .map(ReservationResponse::convert)
                .collect(Collectors.toList());
    	
    }

    //EMPLOYE
    public EmployeResponse createEmploye(EmployeRequest employeRequest) {
        Employe employe = EmployeRequest.convert(employeRequest);
        employe = daoCompte.save(employe); 
        return EmployeResponse.convert(employe);
    }

    public EmployeResponse updateEmploye(Integer id, EmployeRequest employeRequest) {
        Employe employe = (Employe) daoCompte.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé avec id : " + id));
        BeanUtils.copyProperties(employeRequest, employe);
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
                .orElseThrow(() -> new RuntimeException("Employé non trouvé avec id : " + id));
        return EmployeResponse.convert(employe);
    }

    public boolean deleteEmploye(Integer id) {
        daoCompte.deleteById(id);
        return true;
    }
}