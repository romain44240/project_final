package projet.response;

import projet.model.Achat;

public class ProduitFactureResponse {
    
    private String nom;
    private double prix;
    private int quantite;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public static ProduitFactureResponse convert(Achat achat) {
        ProduitFactureResponse pfr = new ProduitFactureResponse();

        pfr.setNom(achat.getProduit().getNom());
        pfr.setPrix(achat.getProduit().getPrix());
        pfr.setQuantite(achat.getQuantite());

        return pfr;
    }
}
