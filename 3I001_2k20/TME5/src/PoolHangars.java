public class PoolHangars {
	
	private int nbHangars;
	
	/* tableau des hangars (no 0 n'est pas rempli, indices des hangars ≥ 1
	 * comme 0 correspond au segment d'accueil cela simplifie l'accès aux hangars
	 */
	private Hangar[] tHangars; 

	/* segment d'accueil et tournant sont crées à la création du pool
	 * garantie que chaque loco soit référence les segments associés au hangar
	 */
	private SegAccueil sAccueil;
	private SegTournant sTournant;
	
	public PoolHangars (int nbHangars) {
		this.nbHangars = nbHangars;
		tHangars = new Hangar[nbHangars +1];
		int i;
		for (i = 1; i <= nbHangars; i++) {
			tHangars[i] = new Hangar();
		}
		sAccueil = new SegAccueil();
		sTournant = new SegTournant(this);
	}
	public SegAccueil getSegaccueil() {
		return sAccueil;
	}
	public SegTournant getSegTournant() {
		return sTournant;
	}
	public Hangar getHangar(int no) {
		
		return tHangars[no];
	}
	public int getNbHangars() {
		return nbHangars;
	}
	public String toString() {
		
		String res = "Etat du pool:";
		int i;
		for (i = 1; i <= nbHangars; i++) {
			res += "\n"+ tHangars[i];
		}
		return res;
	}
	
	public int chercheHangarLibre() { // retourne indice ≥ 1 du hangar dans tHangars
		
		int val = 1;
		int i;
		for (i = 1; i <= nbHangars; i ++) {
			if (tHangars[i].estLibre()) {
				val = i;
				return val;
			}
		}
		return val;	
	}		
}