import java.util.ArrayList;

public class Groupe implements Runnable {
	private int id;
	private static int cpt = 0;
	private int nbPersonnes;
	private Salle s;
	private ArrayList<Integer[]> listPos;
	
	public Groupe(int nbPersonnes, Salle s) {
		this.nbPersonnes=nbPersonnes;
		cpt++;
		this.id=cpt;
		this.s=s;
	}
	
	public void addPos(int rang, int place){
		Integer[] tmp = {rang, place};
		listPos.add(tmp);
	}

	public void cancelAll(){
		while (!listPos.isEmpty())
		{
			this.cancelOne();
		}
	}

	public void cancelOne(){
		if (!listPos.isEmpty())
		{
			Integer[] tmp = listPos.get(0);
			listPos.remove(0);
			s.cancel(tmp[0], tmp[1]);
		}
	}
	
	public void run() {
		s.reserver(nbPersonnes);
	}

}
