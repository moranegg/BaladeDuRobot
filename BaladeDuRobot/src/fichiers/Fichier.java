package fichiers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import metier.Entrepot;
import metier.Instance;
import metier.Robot;

/**
 * Un objet de lecture de fichier txt 
 */
public class Fichier {
	private String nomPhysique; //nom du fichier 
	private File fichier;
	String fichierToString;

	/**
	 * Constructeur Fichier
	 * @param nomPhysique
	 */
	public Fichier(String nomPhysique){
		this.nomPhysique = nomPhysique;
		this.fichier =new File(this.nomPhysique);
		this.fichierToString = this.lire(this.fichier);
		

	}
	/***Getters **/
	public String getNomPhysique() {
		return nomPhysique;
	}


	public File getFichier() {
		return fichier;
	}
	
	public String getFichierToString(){
		return fichierToString;
	}
	/********************************/



	/**
	 * méthode de lecture du fichier qui suvegarde 
	 * la structure sur laquelle ont veut travailler-> Robot et Entrepot
	 * @return
	 */
	public String lire(File f)
	{
		try {
			byte[] bytes = Files.readAllBytes(f.toPath());
			return new String(bytes,"UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

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
	public Instance interpretationFichier(){
		String[] complet = fichierToString.split("\n");
		try{
			/** interpretation entrepot**/
			int longueur = Integer.parseInt(complet[0].split(" ")[0]);
			//gestion erreur longeur n'est ppas un entier
			int largeur= Integer.parseInt(complet[0].split(" ")[1]);
			//gestion erreur largeur n'est ppas un entier
			int[][] matrice = new int[longueur][largeur];
			//gestion erreur matrice dans le fichier n'est pas adapter au definitions
			for(int i=1; i<= longueur; i++){
				String[] ligne = complet[i].split(" ");
				for(int j=0; j< largeur; j++){
					
					matrice[i-1][j] = Integer.parseInt(ligne[j]);
					
				}
				
			}
			Entrepot entrepot= new Entrepot(longueur, largeur, matrice);
			/** interpretation robot**/
			String[] r = complet[longueur+1].split(" ");
			int departLigne= Integer.parseInt(r[0]);
			int departColonne= Integer.parseInt(r[1]);
			int objectifLigne= Integer.parseInt(r[2]);
			int objectifColonne= Integer.parseInt(r[3]);
			String d = r[4];
			if(d.equals("sud")||d.equals("nord")||d.equals("ouest")|| d.equals("est")){
				Robot robot= new Robot(departLigne, departColonne, objectifLigne,
						objectifColonne, d);
				Instance instance= new Instance(entrepot,robot);
				return instance;
			}else{
				throw new Exception();
			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * enregistre une instance dans un fichier
	 */
	public static boolean enregistre(Instance i , String nomFichier){
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter( nomFichier));
		    writer.write( i.toString());
		    return true;

		}
		catch ( IOException e)
		{
			return false;
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
	}


}