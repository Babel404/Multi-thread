import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/*  int mon_int = Integer.parseInt(mon_String)*/

public class MatriceEntiere {
    private int lignes;
    private int colonnes;
    private int matrice[][];
  //  private FileInputStream fis = null;
//    private FileInputStream fos = null;
     
    public MatriceEntiere(int lignes, int colonnes){
      this.lignes=lignes;
      this.colonnes=colonnes;
      matrice = new int[lignes][colonnes];
    }
   
    //A FINIR
    //try{
   
    public MatriceEntiere(File fichier) throws NumberFormatException, IOException{
          /*
          NOT TESTED YET
         */
     
    	  BufferedReader in = new BufferedReader(new FileReader(fichier));
          //fis = new FileInputStream(fichier);
          //byte buf;  //may changed
          int i=0;
          String string;
          String[] entiers;
          this.lignes=Integer.parseInt(in.readLine());
         // System.out.println(lig);
          this.colonnes=Integer.parseInt(in.readLine());
         // System.out.println(col);
          matrice = new int[lignes][colonnes];
          //byte[] buf2 = new byte[colonnes];
         // System.out.println("CREATION MATRICE OK"+matriceX.lignes);
          while ((string = in.readLine()) != null){
  			entiers = string.split(" ");
  			int j = 0;
  			for(String temp : entiers)
  			{
  				matrice[i][j] = Integer.parseInt(temp);
  				j++;
  			}
  			i++;
         }
      }
   // }catch(IOException e) {
    //	System.out.println("Erreur");
    //}

    public int getElem(int i,int j){
    	return matrice[i][j];
    }
   
    public void setElem(int i, int j, int val){
    	matrice[i][j]=val;
    }
   
    //A FAIRE
    public String toString(){
    	String string="";
    	String newline=System.getProperty("line.separator");
    	string+="Matrice de taille : ["+lignes+"]["+colonnes+"]\n";
    	for(int i=0;i<lignes;i++) {
    		for(int j=0;j<colonnes;j++) {
    			string+=matrice[i][j]+" ";
    		}
    		string+=newline;
    	}
    	
	return string;
      
    }
   
    public void print(){
      System.out.println(this.toString());
    }
    
    public void setzero() {
    	for(int i=0;i<lignes;i++) {
    		for(int j=0;j<colonnes;j++) {
    			matrice[i][j]=0;
    		}
    	}
    }
    
    public MatriceEntiere transpose() {
    	MatriceEntiere res = new MatriceEntiere(colonnes,lignes);
    	res.setzero();
    	for(int i=0;i<colonnes;i++) {
    		for(int j=0;j<lignes;j++) {
    			res.setElem(i, j, matrice[j][i]);
    		}
    	}
    	return res;
    }
    
    public MatriceEntiere sum(MatriceEntiere matB) throws TaillesNonConcordantesException{
    	if ((matB.lignes!=lignes)||(matB.colonnes!=colonnes)) 
    		throw new TaillesNonConcordantesException("Dimension non conformes");
    	
    	int sum=0;
    	MatriceEntiere res = new MatriceEntiere(lignes,colonnes);
    	res.setzero();
    	
    	for(int i=0; i<lignes; i++) {
    		for(int j=0; j<colonnes; j++) {
    			sum=matrice[i][j]+matB.matrice[i][j];
    			res.setElem(i, j, sum);
    		}
    	}
    	
    	return res;
    }
    
    public MatriceEntiere scalaire(int scal) {
    	MatriceEntiere res=new MatriceEntiere(lignes,colonnes);
    	res.setzero();
    	for(int i=0; i<lignes; i++) {
    		for(int j=0; j<colonnes; j++) {
    			res.setElem(i, j, matrice[i][j]*scal);
    		}
    	}
    	return res;
    }
    
    public MatriceEntiere mult(MatriceEntiere matB) throws TaillesNonConcordantesException{
    	if(matB.lignes!=colonnes)
    		throw new TaillesNonConcordantesException("Matrices non conformes à la multiplication");
    	
    	MatriceEntiere res = new MatriceEntiere(lignes,matB.colonnes);
    	int sum=0;
    	for(int i=0;i<res.lignes;i++) {
    		for(int j=0;j<res.colonnes;j++) {
    			for(int k=0;k<colonnes;k++) {
    				sum+=matrice[i][k]*matB.getElem(k, j);
    			}
    			res.setElem(i, j, sum);
    			sum=0;
    		}
    	}
    	return res;
    }
    
    public int getNbLignes(){
    	return lignes;
    }
   
    public int getNbColonnes(){
    	return colonnes;
    }
   
    public static int produitLigneColonnes(MatriceEntiere m1, int i, MatriceEntiere m2, int j) throws TaillesNonConcordantesException {
    	if(!(m1.colonnes == m2.lignes)){
    			throw new TaillesNonConcordantesException("Matrices non conformes à la multiplication");
    	}
    	int res=0;
    	for(int k=0; k<m1.colonnes; k++){
    			res+=m1.matrice[i][k]*m2.matrice[k][j];
    	}
    	return res;
    }
   
}
