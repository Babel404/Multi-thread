public class Chargeur implements Runnable {
	Chariot c;
	AleaStock s;
	boolean sVide;
	
	public Chargeur(AleaStock s, Chariot c){
		this.c=c;
		this.s=s;
		sVide=(s.getNbObj()==0);
	}
	public void run(){
		while(!sVide){
			c.getLock().lock();
			try{
				while((s.getNbObj()>0) && !c.plein(s.prochain())){
						c.ajoute(s.extrait());
				}
				if(s.getNbObj()==0){		
					System.out.println("Chargeur voit que S vide");
					
					sVide=true;
					c.setStockVide(sVide);
				}
				c.getChargeFini().signalAll();
				if(!sVide){
					try {
						c.getDechargeFini().await();
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
		
		System.out.println("Fin du chargeur");
	}
}