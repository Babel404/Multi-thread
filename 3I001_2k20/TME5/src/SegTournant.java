import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SegTournant implements Runnable{

	private final Lock l = new ReentrantLock();
	private Condition isOk = l.newCondition();
	private Condition isFull = l.newCondition();
	private Condition isCalled = l.newCondition();
	private Condition isEmpty = l.newCondition();
	private int currentPos = 0;
	private int calledPos = -1 ;
	private PoolHangars PH;

	
	public SegTournant(PoolHangars PH) {
		this.PH=PH;
	}

	public void appeler(int n) {
		// TODO Auto-generated method stub
		System.out.println("On appelle le segTournant pour : " + n);
		isCalled.signalAll();
		l.lock();
		calledPos = n;
		l.unlock();
		System.out.println("On a fini d'appeler");
		
	}

	public void attendrePositionOK() {
		// TODO Auto-generated method stub
		System.out.println("On attent la position");
		l.lock();
		try{
			while(currentPos != calledPos) {
				isOk.await();
			}
		}catch(InterruptedException e){
			e.getMessage();
		}
		finally{
			l.unlock();
		}
		System.out.println("On a fini d'attendre la position");
	}

	public void entrer(int n) throws InterruptedException{
		// TODO Auto-generated method stub
		System.out.println("le train entre dans le segment tournant pour la pos : " + n);
		try{
			while(calledPos == currentPos) {
				isCalled.await();
			}
		}catch(InterruptedException e){
			e.getMessage();
		}
		l.lock();
		calledPos = n;
		l.unlock();
		isFull.signalAll();
		System.out.println("Le train est entré");
	}

	public int getPosition() {
		// TODO Auto-generated method stub
		return currentPos;
	}

	public void sortir(int n) {
		// TODO Auto-generated method stub
		System.out.println("Le train sort en position : " + n);
		l.lock();
		calledPos = -1;
		l.unlock();
		isEmpty.signalAll();
		System.out.println("Le train est sorti");
	}

	@Override
	public void run() {
		try{
			while(true) {
				attendreAppel();
				seDeplacer();
				attendreEntree();
				seDeplacer();
				attendreVide();
			}
		}
	catch(InterruptedException e) {
		System.out.println("Terminaison sur interruption du segment tournant");
		}
	}

	private void attendreVide() {
		// TODO Auto-generated method stub
		System.out.println("On attend qu'une locomotive sorte");
		l.lock();
		try{
			while(calledPos == -1) {
				isEmpty.await();
			}
		}catch(InterruptedException e){
			e.getMessage();
		}
		finally{
			l.unlock();
		}
		System.out.println("une locomotive va sortir");
	}

	private void attendreEntree() {
		// TODO Auto-generated method stub
		System.out.println("On commence a attendre que la locomotive entre");
		l.lock();
		try{
			while(currentPos != calledPos) {
				isFull.await();
			}
		}catch(InterruptedException e){
			e.getMessage();
		}
		finally{
			l.unlock();
		}
		System.out.println("la locomotive est entree");
	}

	private void seDeplacer() throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("On commence le deplacement vers la position : " + calledPos);
		Thread.sleep(2000);
		currentPos = calledPos;
		isOk.signalAll();
		l.unlock();
		System.out.println("fin du deplacement");
	}

	private void attendreAppel() {
		// TODO Auto-generated method stub
		System.out.println("On attend un appel poiur entrer");
		l.lock();
		try{
			while(calledPos == -1) {
				isCalled.await();
			}
		}catch(InterruptedException e){
			e.getMessage();
		}
		finally{
			l.unlock();
		}
		System.out.println("l'appel a ete recu");
	}

}