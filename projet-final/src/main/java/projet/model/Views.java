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
	
}
