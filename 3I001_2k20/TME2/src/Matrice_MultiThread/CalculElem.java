
public class CalculElem implements Runnable{
	private int i;
	private int j;
	private MatriceEntiere m1;
	private MatriceEntiere m2;
	private MatriceEntiere m;
	
	public CalculElem(int i, int j, MatriceEntiere m1, MatriceEntiere m2, MatriceEntiere m) {
		this.i = i;
		this.j = j;
		this.m1 = m1;
		this.m2 = m2;
		this.m = m;
	}
	@Override
	public void run(){
		try {
			this.m.setElem(i, j, MatriceEntiere.produitLigneColonnes(this.m1, this.i, this.m2, this.j));
		}catch(TaillesNonConcordantesException e) {
			System.out.println(e.getMessage());
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(i);
		}
	}
}
	
