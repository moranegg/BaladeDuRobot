package metier;


public class Entrepot {
	private int[][] matrice;
	private int longueur;
	private int largeur;
	private Robot robot;

	public Entrepot(int lo, int la, int[][] m){
		this.largeur = la;
		this.longueur =lo;
		this.matrice = m;
		
	}
	/***Getters and Setter ***/
	public int[][] getMatrice(){
		return this.matrice;
	}

	public int getLongueur() {
		return longueur;
	}

	public int getLargeur() {
		return largeur;
	}

	/****************************/

}
