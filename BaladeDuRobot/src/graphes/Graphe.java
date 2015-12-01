package graphes;
import metier.Entrepot;
import metier.Instance;
import metier.Robot;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class Graphe {
	private Instance instance;
	private Graph graph;
	
	public Graphe(Instance instance){
		this.instance = instance;
		this.graph = new SingleGraph("BaladeDuRobot");
		
		
	}
	
	private void addEdges(int[][] preGraph){
		for (int i=0 ; i<preGraph.length; i++){
			for (int j=0 ; j<preGraph[0].length; j++){
				if(graph.getNode(i+"_"+j+"_sud").getAttribute("stat").equals("unallowed")){
					//System.out.println("Unallowed");
				}
				else{
					//Traitement Est
					for(int k = 1;k<=3 && (k+j)<preGraph[0].length;k++){
						if(graph.getNode(i+"_"+(j+k)+"_est").getAttribute("stat").equals("unallowed"))
							break;
						graph.addEdge("a"+k+"_"+i+"_"+j+"_"+i+"_"+(j+k)+"_est", i+"_"+j+"_est", i+"_"+(j+k)+"_est",true).addAttribute("temps", 1);;
					}
					//Traitement Ouest
					for(int k = 1;k<=3 && (j-k)>=0;k++){
						if(graph.getNode(i+"_"+(j-k)+"_ouest").getAttribute("stat").equals("unallowed"))
							break;
						graph.addEdge("a"+k+"_"+i+"_"+j+"_"+i+"_"+(j-k)+"_ouest", i+"_"+j+"_ouest", i+"_"+(j-k)+"_ouest",true).addAttribute("temps", 1);;
					}
					//Traitement Nord
					for(int k = 1;k<=3 && (i-k)>=0;k++){
						if(graph.getNode((i-k)+"_"+j+"_nord").getAttribute("stat").equals("unallowed"))
							break;
						graph.addEdge("a"+k+"_"+i+"_"+j+"_"+(i-k)+"_"+j+"_nord", i+"_"+j+"_nord", (i-k)+"_"+j+"_nord",true).addAttribute("temps", 1);;
					}
					//Traitement Sud
					for(int k = 1;k<=3 && (i+k)<preGraph.length;k++){
						if(graph.getNode((i+k)+"_"+j+"_sud").getAttribute("stat").equals("unallowed"))
							break;
						graph.addEdge("a"+k+"_"+i+"_"+j+"_"+(i+k)+"_"+j+"_sud", i+"_"+j+"_sud", (i+k)+"_"+j+"_sud",true).addAttribute("temps", 1);;
					}
					graph.addEdge("D_"+i+"_"+j+"_ne", i+"_"+j+"_nord", i+"_"+j+"_est",true).addAttribute("temps", 1);
					graph.addEdge("G_"+i+"_"+j+"_no", i+"_"+j+"_nord", i+"_"+j+"_ouest",true).addAttribute("temps", 1);
					graph.addEdge("G_"+i+"_"+j+"_se", i+"_"+j+"_sud", i+"_"+j+"_est",true).addAttribute("temps", 1);
					graph.addEdge("D_"+i+"_"+j+"_so", i+"_"+j+"_sud", i+"_"+j+"_ouest",true).addAttribute("temps", 1);
					
					graph.addEdge("G_"+i+"_"+j+"_en", i+"_"+j+"_est", i+"_"+j+"_nord", true).addAttribute("temps", 1);
					graph.addEdge("D_"+i+"_"+j+"_on", i+"_"+j+"_ouest", i+"_"+j+"_nord",true).addAttribute("temps", 1);
					graph.addEdge("D_"+i+"_"+j+"_es", i+"_"+j+"_est",i+"_"+j+"_sud", true).addAttribute("temps", 1);
					graph.addEdge("G_"+i+"_"+j+"_os", i+"_"+j+"_ouest", i+"_"+j+"_sud",true).addAttribute("temps", 1);
				}
							
			}
		}
	}
	
	private void addNodes(int[][] preGraph){
		int offset = 10;
		for (int i=0 ; i<preGraph.length; i++){
			for (int j=0 ; j<preGraph[0].length; j++){					
				graph.addNode(i+"_"+j+"_nord").addAttribute("xy", 0+i*offset, 1+j*offset);
				graph.addNode(i+"_"+j+"_ouest").addAttribute("xy", 1+i*offset, 0+j*offset);
				graph.addNode(i+"_"+j+"_est").addAttribute("xy", 1+i*offset, 2+j*offset);
				graph.addNode(i+"_"+j+"_sud").addAttribute("xy", 2+i*offset, 1+j*offset);
				if(preGraph[i][j] == 0){
					
					graph.getNode(i+"_"+j+"_nord").addAttribute("stat", "allowed");
					graph.getNode(i+"_"+j+"_ouest").addAttribute("stat", "allowed");
					graph.getNode(i+"_"+j+"_est").addAttribute("stat", "allowed");
					graph.getNode(i+"_"+j+"_sud").addAttribute("stat", "allowed");
				}
				else{

					graph.getNode(i+"_"+j+"_nord").addAttribute("stat", "unallowed");
					graph.getNode(i+"_"+j+"_ouest").addAttribute("stat", "unallowed");
					graph.getNode(i+"_"+j+"_est").addAttribute("stat", "unallowed");
					graph.getNode(i+"_"+j+"_sud").addAttribute("stat", "unallowed");
					
					graph.getNode(i+"_"+j+"_nord").addAttribute("ui.style", "fill-color: red;");
					graph.getNode(i+"_"+j+"_ouest").addAttribute("ui.style", "fill-color: red;");
					graph.getNode(i+"_"+j+"_est").addAttribute("ui.style", "fill-color: red;");
					graph.getNode(i+"_"+j+"_sud").addAttribute("ui.style", "fill-color: red;");
				}
			}
		}
		//Obstacles cosmétiques :
		
		int[][] mat = instance.getEntrepot().getMatrice();
		for (int i=0 ; i<mat.length; i++){
			for (int j=0 ; j<mat[0].length; j++){	
				graph.addNode("obstacle_"+i+"_"+j).addAttribute("xy", i*offset+offset/2+1, j*offset+offset/2+1);
				if(mat[i][j] == 1){


					graph.getNode("obstacle_"+i+"_"+j).addAttribute("ui.style", "shape: box;size: 40, 40;");
					//graph.getNode("obstacle_"+i+"_"+j).addAttribute("ui.style");

				}
				else{
					graph.getNode("obstacle_"+i+"_"+j).addAttribute("ui.style", "shape: box;size: 40, 40;fill-color:white;stroke-mode: plain;");
				}
			}
		}
		
	}
	public void graphGenerate(){
		int[][] preGraph = matToPreGraph(instance.getEntrepot().getMatrice());
		addNodes(preGraph);
		addEdges(preGraph);

	}
	
	public String pathCompute(){
		BFS bfs = new BFS();
		
		bfs.init(graph);
		String source = instance.getRobot().getDepart().x+"_"+instance.getRobot().getDepart().y+"_"+instance.getRobot().getDirection();

		bfs.setSource(graph.getNode(source));
        bfs.compute();

		
		/*Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "temps");
		dijkstra.init(graph);
		String source = instance.getRobot().getDepart().x+"_"+instance.getRobot().getDepart().y+"_"+instance.getRobot().getDirection();
		System.out.println("Source : "+source);
        dijkstra.setSource(graph.getNode(source));
        dijkstra.compute();
		
        // Print the shortest path from A to B
        return dijkstra.getPath(graph.getNode(dest)).toString();*/
        
        String dest = instance.getRobot().getObjectif().x+"_"+instance.getRobot().getObjectif().y+"_"+instance.getRobot().getDirection();
        System.out.println("Dest : "+dest);
        instance.setSolution(bfs.getPath(graph.getNode(dest)));
        return bfs.getPath(graph.getNode(dest));
	}
	
	public void graphDisplay(){
		System.setProperty("org.graphstream.ui.renderer",
                "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Viewer v = graph.display(false);
		v.getDefaultView().getCamera().setViewRotation(90);
		v.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
	}
	
	public  int[][] matToPreGraph(int[][] mat) {
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
		
		/*for (int i=0 ; i<preGraph.length; i++){
			for (int j=0 ; j<preGraph[0].length; j++){
				System.out.print(preGraph[i][j]+" ");
			}
			System.out.println();
		}*/
		
		return preGraph;
	}
	
	
}
