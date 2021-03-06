import java.util.Random;
import java.util.concurrent.locks.*;
import java.util.concurrent.*;

public class Lutin implements Runnable {
    private Lutin avant, apres;
    private PereNoel pn;
    private int moi;
    private Random gen = new Random();
    private final int NB_PAQUETS = 5;
    private int nb_paquet_traité=0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition take = lock.newCondition();
    private Condition give = lock.newCondition();
    
    
	/***********  A COMPLETER  ***********/

    public Lutin(Lutin avant, int moi, PereNoel p) {
        this.avant = avant;
        this.moi = moi;
        pn = p;
    }
    
    public void suivant (Lutin succ) {
    	apres = succ;
    }

	private void jeBosse(int i) throws InterruptedException {
		Thread.sleep(100 + gen.nextInt(300));
		System.out.println(moi + " a traite le cadeau " + i + " et transmet");
		notifyAll();
		nb_paquet_traité++;
	}
	
	public void prendre() throws InterruptedException {
		lock.lock();
		try {
			for(int i=0;i<NB_PAQUETS;i++) {
				if (avant==null) {
					jeBosse(i);
				}else {
					if(avant.nb_paquet_traité >= nb_paquet_traité) {
						take.await();
					}else {
						jeBosse(i);
					}
				}
				
			}
		} catch (InterruptedException e) {
			e.getMessage();
		} finally {
			lock.unlock();
		}
	}
	
	public void donner() throws InterruptedException {
		lock.lock();
		try {
			if(apres.nb_paquet_traité<nb_paquet_traité) {
				give.await();
			}
		} catch (InterruptedException e) {
			e.getMessage();
		} finally {
			lock.unlock();
		}
	}
	
	public void run() {
		try {
			int i = 1;
			while (i <= NB_PAQUETS) {
			//while (true) {
				if (avant != null) {
					prendre();
				}	
     		
     			jeBosse(i);
     		
				if (apres != null) {
					apres.donner();
				}
				if (i == NB_PAQUETS && moi == 1) {
					/* utile seulement pour la partie 2 */
					break;
				}
				i++;
			}
		}
		catch (InterruptedException e) {
			System.out.println ("Lutin " + moi + " appele.");
		}
	/*	finally {
			System.out.println ("Lutin " + moi + " en lutte !");
			try {
				pn.sePlaindre(moi);
			}
			catch (InterruptedException e2) {
				System.out.println("Hell, should not happen !");
			}
		}
		System.out.println ("Lutin " + moi + " recu par le Pere Noel");
*/
	}
}
