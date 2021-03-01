public class Loco implements Runnable {
	
	private final int id;
	private static int cptLocos = 0;
	private static Object mutex = new Object();
	
	private PoolHangars pHangars;
	
	private SegAccueil sAccueil;
	private SegTournant sTournant;
	
	public Loco(PoolHangars pHangars) {
		synchronized (mutex) {
			cptLocos ++;
			id = cptLocos;
		}
		this.pHangars = pHangars;
		sAccueil = pHangars.getSegaccueil();
		sTournant = pHangars.getSegTournant();
	}
	public void trace (String msg) {
		System.out.println("Loco "+ id +" "+ msg);
	}
	
	public void run() {
		try {
			trace("initialisé");
			sAccueil.reserver(); // attente 1 sur seg tournant
			trace("réserve le segment d'Accueil");
			sTournant.appeler(0);
			trace("attend la position du segment tourant");
			sTournant.attendrePositionOK(); // attente 2 sur seg tournant
			sTournant.entrer(id);
			trace("libère le segment d'Accueil");
			sAccueil.liberer();
			trace("attend la position du segment tournant");
			sTournant.attendrePositionOK();
			pHangars.getHangar(sTournant.getPosition()).entrer(id);
			sTournant.sortir(id);
		}
		catch (InterruptedException e) {
			System.out.println("Loco#" + id + " interrompue (ne devrait pas arriver)");
		}
	}
	
}