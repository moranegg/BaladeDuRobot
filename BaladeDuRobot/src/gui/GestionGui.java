package gui;

import javax.swing.JFrame;

import fichiers.Fichier;
import graphes.Graphe;
import metier.Entrepot;
import metier.Instance;
import metier.Robot;

/**
 * 
 *Dans un pattern MVC controle les actions sur l'IHM
 *
 */
public class GestionGui {
	Instance instance;

	public void chargerFichier(String nomFichier) {
		Fichier fich = new Fichier(nomFichier);
		try{
			instance = fich.interpretationFichier();
			//System.out.print(instance.toString());
			genererGraphe(instance);
			
		}catch(Exception e){
			
		}
		
	}

	public void valider(String longueur, String largeur, String nbObstacles,
			String departColonne, String departLigne, String arriveeColonne, 
			String arriveeLigne,
			String direction) {
		System.out.println(longueur+" "+largeur+" "+direction);
		
		try{
			//valeurs entrepot
			int lo = Integer.parseInt(longueur);
			int la = Integer.parseInt(largeur);
			//creation matrice avec nbObstacles// TODO mise en place des obstacles random
			int nbo = Integer.parseInt(nbObstacles);
			int[][] matrice = new int[lo][la];
			for(int i=1; i<= lo; i++){
				for(int j=0; j< la; j++){	
					matrice[i-1][j] = 0;	
				}	
			}
			
			//valeurs robot
			int dc = Integer.parseInt(departColonne);
			int dl = Integer.parseInt(departLigne);
			int ac = Integer.parseInt(arriveeColonne);
			int al = Integer.parseInt(arriveeLigne);
			
			Robot robot = new Robot(dc,dl,ac,al,direction);
			Entrepot entrepot = new Entrepot(lo,la, matrice);
			Instance instance = new Instance(entrepot,robot);
			genererGraphe(instance);
			
		}catch(Exception e){
			e.printStackTrace();
			//affichage de la fenetre d'erreur
		}
	
	}

	private void genererGraphe(Instance i) {
		System.out.print("generate graphe");
		Graphe g= new Graphe(i);
		g.graphGenerate();
		g.graphDisplay();
		g.pathCompute();
		
	}
}
