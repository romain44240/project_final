package projet.response;

import java.util.List;

public class FactureResponse {
    
    private int total;
    private List<ProduitFactureResponse> produits;

    public FactureResponse() {}

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProduitFactureResponse> getProduits() {
        return produits;
    }

    public void setProduits(List<ProduitFactureResponse> produits) {
        this.produits = produits;
    }
}
