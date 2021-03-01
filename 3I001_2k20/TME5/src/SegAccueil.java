import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class SegAccueil {
	
	/* Système de synchro :
	 * - les locos se bloquent sur la condition entrance du lock
	 */
	 // variable de synchro pour attente des locos si segment reservé par une loco
	private boolean reserve;
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition entranceQueue = lock.newCondition();

	public SegAccueil() {
	
		reserve = false;
	}
	public void reserver() throws InterruptedException {
		try {
			lock.lock();
			while (reserve) {
				entranceQueue.await();
			}
			reserve = true;
			System.out.println("Segment d'acceuil reservé");
		}
		finally {
			lock.unlock();
		}
	}
	public void liberer() {
		try {
			lock.lock();
			reserve = false;
			entranceQueue.signalAll();
		}
		finally {
			lock.unlock();
		}
	}	
}