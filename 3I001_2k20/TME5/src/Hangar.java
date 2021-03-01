public class Hangar {
	
	private int no; // no de hangar, 1 ≤ no ≤ pHangars.nbHangars;
	private static int cptHangars = 0;
	private static Object mutex = new Object();

	private int idLoco; // 0 si pas de loco dans le hangar
	
	// implique que l'app n'a qu'un seul pool dans lequel vont tous les hangars.
	// sinon un compteur de hangars par pool

	public Hangar () {
		synchronized (mutex) {
			cptHangars ++;
			no = cptHangars;
		}
		idLoco = 0;
	}
	public boolean estLibre() {
		return idLoco == 0;
	}
	public String toString() {
		String res = "Hangar "+ no;
		
		if (idLoco == 0) res += " vide";
		else res += " contient Loco "+ idLoco;
		
		return res;
	}
	public void entrer(int id) {
		// pas besoin de synchro, une seule loco peut s'appreter à entrer
		this.idLoco = id;
		System.out.println("Loco#"+ idLoco +" entre dans le hangar#"+ no); 
	}
	public void sortir() {
		idLoco = 0;
		System.out.println("Loco#"+ idLoco +" sort du hangar#"+ no);
	}
}