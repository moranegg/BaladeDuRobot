package tests;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

import graphes.Graphe;

public class TestGraphe {
	
	@Test
	public void testGraphStream() {
		
		Graph graph = new SingleGraph("example");
		
		graph.addNode("A").addAttribute("xy", 0, 1);
        graph.addNode("B").addAttribute("xy", 1, 2);
        graph.addNode("C").addAttribute("xy", 1, 1);
        graph.addNode("D").addAttribute("xy", 1, 0);
        graph.addNode("E").addAttribute("xy", 2, 2);
        graph.addNode("F").addAttribute("xy", 2, 1);
        graph.addNode("G").addAttribute("xy", 2, 0);
        graph.addEdge("AB", "A", "B").addAttribute("length", 14);
        graph.addEdge("AC", "A", "C").addAttribute("length", 9);
        graph.addEdge("AD", "A", "D").addAttribute("length", 7);
        graph.addEdge("BC", "B", "C").addAttribute("length", 2);
        graph.addEdge("CD", "C", "D").addAttribute("length", 10);
        graph.addEdge("BE", "B", "E").addAttribute("length", 9);
        graph.addEdge("CF", "C", "F").addAttribute("length", 11);
        graph.addEdge("DF", "D", "F").addAttribute("length", 15);
        graph.addEdge("EF", "E", "F").addAttribute("length", 6);
        for (Node n : graph)
                n.addAttribute("label", n.getId());
        for (Edge e : graph.getEachEdge())
                e.addAttribute("label", "" + (int) e.getNumber("length"));
        
		graph.display(false);

	}
	
	private static int[][] matToPreGraph(int[][] mat) {
		int[][] preGraph=new int[mat.length+1][];
		for (int i=0 ; i<preGraph.length; i++)
			preGraph[i]=new int[mat[0].length+1];
		
		for (int i=0 ; i<mat.length; i++){
			for (int j=0 ; j<mat[0].length; j++){
				if(mat[i][j] == 1){
					preGraph[i][j]=1;
					preGraph[i+1][j]=1;
					preGraph[i][j+1]=1;
					preGraph[i+1][j+1]=1;
				}
					
			}
		}
		
		for (int i=0 ; i<preGraph.length; i++){
			for (int j=0 ; j<preGraph[0].length; j++){
				System.out.print(preGraph[i][j]+" ");
			}
			System.out.println();
		}
		
		return preGraph;
	}
	
	public static void main (String[] args){
		
		Graph graph = new SingleGraph("example");
		
		int[][] matrice=new int[9][];
		for (int i=0 ; i<matrice.length; i++)
			matrice[i]=new int[10];
		
		matrice[0][6] = 1;
		matrice[1][8] = 1;
		matrice[2][3] = 1;
		matrice[3][2] = 1;
		matrice[4][6] = 1;
		matrice[5][5] = 1;
		matrice[6][3] = 1;
		matrice[6][4] = 1;
		matrice[8][0] = 1;
		matrice[8][8] = 1;
		
		
		for (int i=0 ; i<matrice.length; i++){
			for (int j=0 ; j<matrice[0].length; j++){
				System.out.print(matrice[i][j]+" ");
			}
			System.out.println();
		}
		
		int[][] preGraph = matToPreGraph(matrice);
		
		/*graph.addNode("0_0n").addAttribute("xy", 0, 1);
        graph.addNode("0_0o").addAttribute("xy", 1, 0);
        graph.addNode("0_0e").addAttribute("xy", 1, 2);
        graph.addNode("0_0s").addAttribute("xy", 2, 1);
        
		graph.addNode("1_0n").addAttribute("xy", 0+3, 1);
        graph.addNode("1_0o").addAttribute("xy", 1+3, 0);
        graph.addNode("1_0e").addAttribute("xy", 1+3, 2);
        graph.addNode("1_0s").addAttribute("xy", 2+3, 1);*/
		int offset = 4;
		for (int i=0 ; i<preGraph.length; i++){
			for (int j=0 ; j<preGraph[0].length; j++){
				if(preGraph[i][j] == 0){
					graph.addNode(i+"_"+j+"n").addAttribute("xy", 0+i*offset, 1+j*offset);
					graph.addNode(i+"_"+j+"o").addAttribute("xy", 1+i*offset, 0+j*offset);
					graph.addNode(i+"_"+j+"e").addAttribute("xy", 1+i*offset, 2+j*offset);
					graph.addNode(i+"_"+j+"s").addAttribute("xy", 2+i*offset, 1+j*offset);
					
					graph.getNode(i+"_"+j+"n").addAttribute("stat", "allowed");
					graph.getNode(i+"_"+j+"o").addAttribute("stat", "allowed");
					graph.getNode(i+"_"+j+"e").addAttribute("stat", "allowed");
					graph.getNode(i+"_"+j+"s").addAttribute("stat", "allowed");
				}
				else{
					graph.addNode(i+"_"+j+"n").addAttribute("xy", 0+i*offset, 1+j*offset);
					graph.addNode(i+"_"+j+"o").addAttribute("xy", 1+i*offset, 0+j*offset);
					graph.addNode(i+"_"+j+"e").addAttribute("xy", 1+i*offset, 2+j*offset);
					graph.addNode(i+"_"+j+"s").addAttribute("xy", 2+i*offset, 1+j*offset);
					
					graph.getNode(i+"_"+j+"n").addAttribute("stat", "unallowed");
					graph.getNode(i+"_"+j+"o").addAttribute("stat", "unallowed");
					graph.getNode(i+"_"+j+"e").addAttribute("stat", "unallowed");
					graph.getNode(i+"_"+j+"s").addAttribute("stat", "unallowed");
					
					graph.getNode(i+"_"+j+"n").addAttribute("ui.style", "fill-color: red;");
					graph.getNode(i+"_"+j+"o").addAttribute("ui.style", "fill-color: red;");
					graph.getNode(i+"_"+j+"e").addAttribute("ui.style", "fill-color: red;");
					graph.getNode(i+"_"+j+"s").addAttribute("ui.style", "fill-color: red;");
				}
			}
		}
		for (int i=0 ; i<preGraph.length; i++){
			for (int j=0 ; j<preGraph[0].length; j++){
				if(graph.getNode(i+"_"+j+"s").getAttribute("stat").equals("unallowed")){
					//System.out.println("Unallowed");
				}
				else{
					//Traitement Est
					for(int k = 1;k<=3 && (k+j)<preGraph[0].length;k++){
						if(graph.getNode(i+"_"+(j+k)+"e").getAttribute("stat").equals("unallowed"))
							break;
						graph.addEdge(i+"_"+j+"_"+i+"_"+(j+k)+"e", i+"_"+j+"e", i+"_"+(j+k)+"e",true).addAttribute("temps", 1);;
					}
					//Traitement Ouest
					for(int k = 1;k<=3 && (j-k)>=0;k++){
						if(graph.getNode(i+"_"+(j-k)+"o").getAttribute("stat").equals("unallowed"))
							break;
						graph.addEdge(i+"_"+j+"_"+i+"_"+(j-k)+"o", i+"_"+j+"o", i+"_"+(j-k)+"o",true).addAttribute("temps", 1);;
					}
					//Traitement Nord
					for(int k = 1;k<=3 && (i-k)>=0;k++){
						if(graph.getNode((i-k)+"_"+j+"n").getAttribute("stat").equals("unallowed"))
							break;
						graph.addEdge(i+"_"+j+"_"+(i-k)+"_"+j+"n", i+"_"+j+"n", (i-k)+"_"+j+"n",true).addAttribute("temps", 1);;
					}
					//Traitement Sud
					for(int k = 1;k<=3 && (i+k)<preGraph.length;k++){
						if(graph.getNode((i+k)+"_"+j+"s").getAttribute("stat").equals("unallowed"))
							break;
						graph.addEdge(i+"_"+j+"_"+(i+k)+"_"+j+"s", i+"_"+j+"s", (i+k)+"_"+j+"s",true).addAttribute("temps", 1);;
					}
					graph.addEdge(i+"_"+j+"ne", i+"_"+j+"n", i+"_"+j+"e").addAttribute("temps", 1);
					graph.addEdge(i+"_"+j+"no", i+"_"+j+"n", i+"_"+j+"o").addAttribute("temps", 1);
					graph.addEdge(i+"_"+j+"se", i+"_"+j+"s", i+"_"+j+"e").addAttribute("temps", 1);
					graph.addEdge(i+"_"+j+"so", i+"_"+j+"s", i+"_"+j+"o").addAttribute("temps", 1);
				}
							
			}
		}

        
        for (Node n : graph)
            n.addAttribute("label", n.getId());
		graph.display(false);
		
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "temps");
		dijkstra.init(graph);
        dijkstra.setSource(graph.getNode("7_2s"));
        dijkstra.compute();
        
        // Print the shortest path from A to B
        System.out.println(dijkstra.getPath(graph.getNode("2_7s")));

	}
	
}
