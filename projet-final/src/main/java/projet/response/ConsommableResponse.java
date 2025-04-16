package projet.response;

import org.springframework.beans.BeanUtils;

import projet.model.Consommable;

public class ConsommableResponse extends ProduitResponse {
	
	public ConsommableResponse() {}
	
	public static ConsommableResponse convert(Consommable consommable) {
		ConsommableResponse consommableResponse = new ConsommableResponse();
		
		BeanUtils.copyProperties(consommable, consommableResponse);
		
		return consommableResponse;
	}
}
