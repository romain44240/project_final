package projet.request;

import org.springframework.beans.BeanUtils;

import projet.model.Consommable;

public class ConsommableRequest extends ProduitRequest {
	
	public static Consommable convert(ConsommableRequest consommableRequest) {
		Consommable consommable = new Consommable();
		
		BeanUtils.copyProperties(consommableRequest, consommable);
		
		return consommable;
	}
}
