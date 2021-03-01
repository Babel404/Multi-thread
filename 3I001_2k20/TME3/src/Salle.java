
public class Salle {
	private int nbPlaces;
	private int nbRangs;
	private boolean[][] tab;
	private int placesLibres;
	
	public Salle(int nbRangs, int nbPlaces) {
		
		this.nbPlaces=nbPlaces;
		this.nbRangs=nbRangs;
		placesLibres=nbRangs*nbPlaces;
		tab = new boolean[nbRangs][nbPlaces];
		for(int i=0;i<nbRangs;i++) {
			for(int j=0;j<nbPlaces;j++) {
				tab[i][j]=true;
			}
		}
	}
	
	public boolean capaciteOk(int n) {
		if (placesLibres < n) {
			return false;
		}else {
			return true;
		}
	}
	
	public int nContiguesAuRangI(int n, int i) {
		int cpt = 0;
		int deb = -1;
		for(int j=0; cpt<n && j<nbPlaces; j++) {
			if (!tab[i][j]){
				cpt = 0;
				deb = -1;
			}else{
				cpt++;
				if (deb == -1)
					deb = j;
			}
		}
		return deb;
	}
	
	public boolean reserverContigue(int n) {
		int deb = -1;
		int i;
		
		for(i = 0; i < nbRangs && deb != -1; i++){
			deb = nContiguesAuRangI(n, i);
		}
		i--;

		if (deb != -1) {
			placesLibres = placesLibres-n;
			while (n > 0) {
				tab[i][deb] = false;
				deb++;
				n--;
			}
			return true;
		}else
			return false;
	}

	public synchronized boolean reserver(int n){
		if (!capaciteOk(n)){
			return (false);
		}

		if (!reserverContigue(n)){
			for(int i = 0; i < nbRangs && n > 0; i++){
				for(int j = 0; j < nbPlaces && n > 0; j++){
					if (tab[i][j]){
						tab[i][j] = false;
						n--;
					}
				}
			}
		}
		return true;
	}

	public synchronized void cancel(int rang, int place) {
		tab[rang][place] = true;
	}

	public String toString()
	{
		String res = "";

		for(int i = 0; i < nbRangs; i++)
		{
			for(int j = 0; j < nbPlaces; j++) {
				res += (tab[i][j]) ? "1 " : "0 ";
			}
			res += "\n";
		}
		return (res);
	}
	
	public int getNbPlacesLibres() {
		return placesLibres;
	}
}
