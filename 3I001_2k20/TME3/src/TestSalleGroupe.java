
public class TestSalleGroupe {
	public static void main(String[] args){
		Thread[] th = new Thread[10];
		Salle s = new Salle(50, 50);
		System.out.println(s.getNbPlacesLibres());
		for (int i = 0; i < 10; i++){
			th[i] = new Thread(new Groupe(23, s));
		}
		for (int i = 0; i < 10; i++){
			th[i].start();
		}
		try{
			for (int i = 0; i < 10; i++){
				th[i].join();
			}
		}catch (InterruptedException e){
			System.err.println("Mommy, I can't sleep.");
		}finally{
			System.out.println(s);
			System.out.println(s.getNbPlacesLibres());
		}
	}
}