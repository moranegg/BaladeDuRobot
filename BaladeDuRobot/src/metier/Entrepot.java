package metier;


public class Entrepot {
	private int[][] matrice;
	private int longueur;
	private int largeur;
	

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

	public void setMatrice(int[][] matrice) {
		this.matrice = matrice;
	}
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public int getLargeur() {
		return largeur;
	}

	/****************************/
	public String toString(){
		String retour= this.longueur+ " "+this.largeur;
		for(int i=0; i< longueur; i++){
			retour += "\n";
			for(int j=0; j< largeur; j++){
				//System.out.print(matrice[i][j] );
				retour += matrice[i][j];
				retour += " ";
			}
			//retour += "\n";
			//System.out.print("\n" );
		}
		return retour;
	}


}
