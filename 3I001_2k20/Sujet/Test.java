public class Test {
    public static void main(String [] args) {
    	Lutin[] lutins = new Lutin[3];
    	
    	PereNoel pereNoel = new PereNoel();
    	lutins[0] = new Lutin (null, 0, pereNoel);
    	lutins[1] = new Lutin (lutins[0], 1, pereNoel);
    	lutins[2] = new Lutin (lutins[1], 2, pereNoel);
    	
    	lutins[0].suivant(lutins[1]);
    	lutins[1].suivant(lutins[2]);
    	lutins[2].suivant(null);
    	
    	Thread[] thl = new Thread[3];
    	thl[0] = new Thread(lutins[0]);
    	thl[1] = new Thread(lutins[1]);
    	thl[2] = new Thread(lutins[2]); 
    	
    	pereNoel.ajouterLutins(lutins, thl);  
    	
    	Thread thpn = new Thread (pereNoel); 
  
      	thpn.start();
    	thl[0].start();
    	thl[1].start();   
    	thl[2].start();
      
    }
}
