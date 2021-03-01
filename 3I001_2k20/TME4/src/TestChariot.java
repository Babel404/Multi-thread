
public class TestChariot {
	public static void main(String[] args){
		AleaStock stock = new AleaStock(30);
		Chariot chariot = new Chariot(4, 45);
		Chargeur chargeur = new Chargeur(stock, chariot);
		Thread thChargeur = new Thread(chargeur);
		Dechargeur dechargeur = new Dechargeur(chariot);
		Thread thDechargeur = new Thread(dechargeur);
		thChargeur.start();
		thDechargeur.start();
		
		try{
			thChargeur.join();
			thDechargeur.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Fin du programme");
	}
}