package projet.model;

public interface Views {
	public static interface ViewBasic {
	}
	
	public static interface ViewAchat extends ViewBasic {
	}
	
	public static interface ViewAchatDetail extends ViewAchat{
	}
	
	public static interface ViewProduit extends ViewBasic{
	}
	
	public static interface ViewProduitDetail extends ViewProduit{
	}
	
	public static interface ViewCompte extends ViewBasic {
	}
	
	public static interface ViewCompteDetail extends ViewCompte{
	}
	
	public static interface ViewReservation extends ViewBasic {
	}
	
	public static interface ViewCommande extends ViewBasic {
	}
	
	public static interface ViewSurface extends ViewBasic {
	}
	
	public static interface ViewSurfaceDetail extends ViewCompte{
	}
}
