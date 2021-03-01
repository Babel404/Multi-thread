public class Dechargeur implements Runnable{
	Chariot c;
	boolean sVide;
	public Dechargeur(Chariot c){
		this.c=c;
 	}
	public void run(){
		while(!sVide){
			c.getLock().lock();
			try{
				while(c.nbObjet()>0){
						c.extrait();
				}
				sVide = c.isStockVide();
				c.getDechargeFini().signalAll();
				
				if(!sVide){
					try {
						c.getChargeFini().await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else{
					if(c.getLock().isHeldByCurrentThread()){
						c.getLock().unlock();
					}
				}
			}finally{
				if(c.getLock().isHeldByCurrentThread()){
					c.getLock().unlock();
				}
			}
		}
		System.out.println("Fin du dechargeur");		
	}
}