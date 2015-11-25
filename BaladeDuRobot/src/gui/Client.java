package gui;

import metier.Instance;
import fichiers.Fichier;
import graphes.Graphe;

public class Client {
	
	public static void main(String[] atgs){
		Fichier fich = new Fichier("Samples/uc001-test.txt");
		Instance i = fich.interpretationFichier();
		
		System.out.print(i.toString());
		Graphe g= new Graphe(i);
		g.graphGenerate();
		g.graphDisplay();
		g.computePath();
		System.out.println(i.printSolution());
	}
}
