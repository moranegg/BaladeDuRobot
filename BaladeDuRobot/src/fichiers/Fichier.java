package fichiers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import metier.Entrepot;
import metier.Robot;

/**
 * Un objet de lecture de fichier txt 
 */
public class Fichier {
	private String nomPhysique; //nom du fichier 
	private File fichier;
	String fichierToString;
	private Entrepot entrepot;
	private Robot robot;
	/**
	 * Constructeur Fichier
	 * @param nomPhysique
	 */
	public Fichier(String nomPhysique){
        this.nomPhysique = nomPhysique;
        this.fichier =new File(this.nomPhysique);
        
    }
	/***Getters and Setter ***/
	public String getNomPhysique() {
		return nomPhysique;
	}

	public void setNomPhysique(String nomPhysique) {
		this.nomPhysique = nomPhysique;
	}
	public File getFichier() {
		return fichier;
	}
	public void setFichier(File fichier) {
		this.fichier = fichier;
	}
	public Entrepot getEntrepot() {
		return entrepot;
	}
	public void setEntrepot(Entrepot entrepot) {
		this.entrepot = entrepot;
	}
	public Robot getRobot() {
		return robot;
	}
	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	/********************************/
	/**
	 * méthode de lecture du fichier qui suvegarde 
	 * la structure sur laquelle ont veut travailler-> Robot et Entrepot
	 * @return
	 */
	public String lire()
	{
		
		try {
			FileInputStream fis = new FileInputStream(this.fichier) ;
 
			int content= 0;
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				fichierToString+=((char) content);
			}
			return fichierToString;

		} catch (FileNotFoundException e ) {
			System.out.println("Le fichier n'est pas trouvé");
		} catch (IOException e){
			System.out.println ("problem de lecture de fichier");
		}
		return null;
	}
	

	/**
	 * afficher le fichier complètement
	 */
	public void afficherStream(){
		System.out.println(fichierToString);
	}
	/**
	 * transformer fichier en Solution
	 */
	public void interpretationFichier(){
		String[] tab = fichierToString.split("\n");
		try{
			int longueur = Integer.parseInt(tab[0].split(" ")[0]);
			//gestion erreur longeur n'est ppas un entier
			int largeur= Integer.parseInt(tab[0].split(" ")[1]);
			//gestion erreur largeur n'est ppas un entier
			int[][] matrice = new int[longueur][largeur];
			//gestion erreur matrice dans le fichier n'est pas adapter au definitions
			for(int i=1; i< longueur; i++){
				String[] ligne = tab[i].split(" ");
				for(int j=0; j< largeur; j++){
					matrice[i-1][j] = Integer.parseInt(ligne[j]);
				}
			}
			this.entrepot= new Entrepot(longueur, largeur, matrice);
			
			String[] robot = tab[longueur+1].split(" ");
			int departLigne= Integer.parseInt(robot[0]);
			int departColonne= Integer.parseInt(robot[1]);
			int objectifLigne= Integer.parseInt(robot[2]);
			int objectifColonne= Integer.parseInt(robot[3]);
			String direction = robot[4];
			this.robot= new Robot(departLigne, departColonne, objectifLigne,
					objectifColonne, direction);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	
}