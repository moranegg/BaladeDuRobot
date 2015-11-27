package fichiers;

import java.awt.Point;
import java.util.ArrayList;

import metier.Entrepot;
import metier.Instance;
import metier.Robot;

public class GenererInstance {
	ArrayList<Instance> instances  = new ArrayList<Instance>();
	
	public void genererInstanceMultiple(){
		
	}
	
	public Instance genererInstance(int taille, int nbObstacle){
		int departLigne= 0;
		int departColonne= 0;
		int objectifLigne =0; 
		int ObjectifColonne=0;
		String d= "";
		int [][] matrice= new int[taille][taille];
		ArrayList<Point> obstacles = new ArrayList<Point>();
		//generation des point d'obstacle x y
		for(int i =0; i <nbObstacle; i++){
			
		}
		//generation de la matrice
		for(int a =0; a <taille; a++){
			for(int b =0; b <taille; b++){
				//if()
			}
		}
		Entrepot e= new Entrepot(taille, taille, matrice);
		Robot r = new Robot(departLigne,departColonne,objectifLigne, 
				ObjectifColonne,d);
		Instance i =  new Instance(e,r);
		return i;
	}
	
	public void genererFichiers(){
		int cpt =1;
		for(Instance i: instances){
			String nomFichier= "uc00"+cpt;
			Fichier.enregistre(i, nomFichier);
			cpt++;
		}
	}

}
