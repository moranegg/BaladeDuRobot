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
/**
 * 
 * Test en fonction du nombre d'obstacle
 *
 */
public class TestExecObstacle {
		
	public static void main (String[] args){
		long [][] durationTotal = new long [5][10];
		long [] moyenne = new long [5];
		
		for(int i=0; i<10; i++){
			Instance i10 = new Instance(20,20,10);
			
			Graphe graph10 = new Graphe(i10);
			long startTime = System.nanoTime();
			graph10.graphGenerate();
			//graph10.graphDisplay();
			System.out.println(graph10.pathCompute());
			long endTime = System.nanoTime();
	
			long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
			durationTotal[0][i]= duration;
			//System.out.println("instance 10x10\t"+duration/1000000 + "ms");
			/***************/
			Instance i20 = new Instance(20,20,20);
			
			Graphe graph20 = new Graphe(i20);
			startTime = System.nanoTime();
			graph20.graphGenerate();
			//graph20.graphDisplay();
			System.out.println(graph20.pathCompute());
			endTime = System.nanoTime();
			duration = (endTime - startTime);  
			System.out.println("instance 20x20\t"+duration/1000000 + "ms");
			durationTotal[1][i]= duration;
			/***************/
			Instance i30 = new Instance(20,20,30);
			
			Graphe graph30 = new Graphe(i30);
			startTime = System.nanoTime();
			graph30.graphGenerate();
			//graph30.graphDisplay();
			System.out.println(graph30.pathCompute());
			endTime = System.nanoTime();
			duration = (endTime - startTime);  
			//System.out.println("instance 30x30\t"+duration/1000000 + "ms");
			durationTotal[2][i]= duration;
			/***************/
			Instance i40 = new Instance(20,20,40);
			
			Graphe graph40 = new Graphe(i40);
			startTime = System.nanoTime();
			graph40.graphGenerate();
			//graph40.graphDisplay();
			System.out.println(graph40.pathCompute());
			endTime = System.nanoTime();
			duration = (endTime - startTime);  
			System.out.println("instance 40x40\t"+duration/1000000 + "ms");
			durationTotal[3][i]= duration;
			/***************/
			Instance i50 = new Instance(20,20,50);
			
			Graphe graph50 = new Graphe(i50);
			startTime = System.nanoTime();
			graph50.graphGenerate();
			//graph50.graphDisplay();
			System.out.println(graph50.pathCompute());
			endTime = System.nanoTime();
			duration = (endTime - startTime); 
			System.out.println("instance 50x50\t"+duration/1000000 + "ms");
			durationTotal[4][i]= duration;
			/***************/
		}
		for(int i=0;i<5;i++){
			long total=0;
			for(int j=0;j<10;j++){
				total+= durationTotal[i][j];
				System.out.print(""+durationTotal[i][j]/1000000 + ",");
			}
			moyenne[i]=total/10;
			System.out.println(""+(double)moyenne[i]/1000000 + "!");
			
		}


	}
	
}
