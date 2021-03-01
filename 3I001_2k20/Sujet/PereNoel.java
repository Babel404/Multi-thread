import java.util.Random;

public class PereNoel implements Runnable {
	Thread[] th;
	Lutin[] lutins;
	int nbPlaintes = 0;

	public void ajouterLutins (Lutin[] l, Thread[] t) {
		this.th = t; 	
		lutins = l;
	}
	
	public void sePlaindre(int i) throws InterruptedException {
		if (nbPlaintes == 0) {
			th[(i+1)%3].interrupt();
			th[(i+2)%3].interrupt();	
		}	
		System.out.println("Lutin " + i + " attend le Pere Noel");
		/***********  A COMPLETER  ***********/
	}
		

    public void run() {
		/***********  A COMPLETER  ***********/
		System.out.println("Pere Noel se termine");
	}
}
