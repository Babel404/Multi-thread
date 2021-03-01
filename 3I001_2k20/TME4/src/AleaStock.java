import java.util.ArrayList;

public class AleaStock {
	private int taille;
	private ArrayList<AleaObjet> stock;
	
	public AleaStock(int t){
		taille = t;
		stock = new ArrayList<AleaObjet>();
		for(int i=0; i<t; i++){
			stock.add(new AleaObjet(1.0, 20.0));		
		}
	}
	
	public int getTaille() {
		return taille;
	}
	
	public int getNbObj(){
		return stock.size();
	}
	
	public AleaObjet extrait(){
		if(getNbObj()!=0){
			AleaObjet tmp = stock.get(0);
			stock.remove(0);
			return tmp;
		}
		System.out.println("PLUS RIEN DANS STOCK");
		return null;
		
	}
	
	public AleaObjet prochain(){
		return stock.get(0);
	}

}