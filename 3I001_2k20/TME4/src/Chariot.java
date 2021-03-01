import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Chariot {
	private int nbMax;
	private float poidsMax;
	private ArrayList<AleaObjet> charge;
	private ReentrantLock rl = new ReentrantLock();
	private Condition chargeFini = rl.newCondition();
	private Condition dechargeFini = rl.newCondition();
	private boolean b, stockVide;
	
	public Chariot(int n, int p){
		nbMax = n;
		poidsMax=p;
		charge = new ArrayList<AleaObjet>();
	}
	
	
	public boolean isStockVide() {
		return stockVide;
	}


	public void setStockVide(boolean stockVide) {
		this.stockVide = stockVide;
	}


	public int nbObjet(){
		return charge.size();
	}
	
	public String toString(){
		return("Chariot : Objets " + charge.size() + " / " + nbMax +" | Poids : " + poidsTotal() + " / " + poidsMax);
	}
	
	public boolean plein(AleaObjet o){
		try{
			rl.lock();
			b = (charge.size()>=nbMax)||(poidsTotal()+o.getPoids()>poidsMax);
		}
		finally{
			if(rl.isHeldByCurrentThread()){
				rl.unlock();
			}
		}
		return b;
	}
	
	public ReentrantLock getLock(){
		return rl;
	}
	
	public double poidsTotal(){
		double p=0.0;
		for(int i = 0; i < charge.size(); i++){
			p=p+charge.get(i).getPoids();
		}
		return p;
	}
	
	public void ajoute(AleaObjet o){
		charge.add(o);
		System.out.println("Ajout de "+o.getId()+ " \n" +toString());
	}
	
	public AleaObjet extrait(){
		AleaObjet tmp = charge.get(0);
		charge.remove(0);
		System.out.println("Ajout de "+tmp.getId()+ " \n" +toString());
		return tmp;
	}

	public Condition getChargeFini(){
		return chargeFini;
	}
	
	public Condition getDechargeFini(){
		return dechargeFini;
	}
	
}