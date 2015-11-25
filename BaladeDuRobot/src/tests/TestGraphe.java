package tests;

import metier.Entrepot;
import metier.Instance;
import metier.Robot;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

import fichiers.Fichier;
import graphes.Graphe;

public class TestGraphe {
		
	public static void main (String[] args){
		Fichier fichier = new Fichier("Samples/uc001-test.txt");    
		Instance i = fichier.interpretationFichier();
		
		Graphe graph = new Graphe(i);
		graph.graphGenerate();
		graph.graphDisplay();
		System.out.println(graph.pathCompute());

		
		

	}
	
}
