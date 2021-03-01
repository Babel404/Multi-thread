import java.io.*;

public class TestProduitParallele
{
	public static void main(String args[])
	{
		if (args.length != 2)
		{
			System.err.println("Syntax : exec first_matrix second_matrix");
			return ;
		}
		try
		{
			MatriceEntiere matrice1 = new MatriceEntiere(new File(args[0]));
			MatriceEntiere matrice2 = new MatriceEntiere(new File(args[1]));
			int nbl = matrice1.getNbLignes();
			int nbc = matrice2.getNbColonnes();
			MatriceEntiere res = new MatriceEntiere(nbl, nbc);
			Thread[][] th = new Thread[nbl][nbc];
			System.out.println(args[0] + " * " + args[1]);
			for(int i = 0; i < nbl; i++)
			{
				for(int j = 0; j < nbc; j++)
				{
					th[i][j] = new Thread(new CalculElem(i, j, matrice1, matrice2, res));
					th[i][j].start();
				}
			}
			for(int i = 0; i < nbl; i++)
			{
				for(int j = 0; j < nbc; j++)
					th[i][j].join();
			}
			res.print();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e);
		}
	}
}
