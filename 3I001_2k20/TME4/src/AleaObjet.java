
public class AleaObjet {
	private double poids;
	private int id;
	private static int cpt = 0;
	
	public AleaObjet(double min, double max){
		id=cpt++;
		poids=min+(max*(Math.random()));
	}
	
	public double getPoids(){
		return poids;
	}
	
	public int getId(){
		return id;
	}
	
}