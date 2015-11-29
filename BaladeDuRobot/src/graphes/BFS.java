package graphes;



import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;


public class BFS {

	private Graph graph;
	private Node source;
	private HashMap<Node,Integer> distance;
	private HashMap<Node,Node> parent;
	
	public BFS(){
		distance = new HashMap<Node,Integer>();
		parent = new HashMap<Node,Node>();
	}
	
	public void init(Graph graph){
		this.graph = graph;
	}
	
	public void setSource(Node node){
		
		this.source = node;
	}
	
	public void compute(){
		  /* 1 Breadth-First-Search(G, v):
			 2
			 3     for each node n in G:            
			 4         n.distance = INFINITY        
			 5         n.parent = NIL
			 6 
			 7     create empty queue Q      
			 8 
			 9     v.distance = 0
			10     Q.enqueue(v)                      
			11 
			12     while Q is not empty:        
			13     
			14         u = Q.dequeue()
			15     
			16         for each node n that is adjacent to u:
			17             if n.distance == INFINITY:
			18                 n.distance = u.distance + 1
			19                 n.parent = u
			20                 Q.enqueue(n) */
				
		for(Node node : graph.getEachNode()) {
			distance.put(node, -1);
			parent.put(node, null);			
		}
		
		Queue<Node> file = new LinkedList<Node>();
				
		distance.put(source, 0);
		file.add(source);
		Node u;
		
		while(!file.isEmpty()){
			//System.out.println("plop"+file.toString());
			u = file.remove();
			
			for(Edge e : u.getEachLeavingEdge()) {
				
				Node n = e.getTargetNode();
				//System.out.println("Debug , Source : ""node : "+n+" Dist : "+distance.get(n));
				
				if(distance.get(n) == -1){
					distance.put(n, distance.get(u)+1);
					parent.put(n, u);
					file.add(n);
				}				
			}
		}
		
		//Debug tab data
		for(Node node : graph.getEachNode()) {
			System.out.println("Key : "+node+" dist : "+distance.get(node)+" parent : "+parent.get(node));
	
		}
		
	}
	
	public String getPath(Node destination){
		
		
		List<Node> path= new LinkedList<Node>();
		List<String> pathedges= new LinkedList<String>();

		//Constructions noeuds solution
			Node u = parent.get(destination);
			path.add(destination);
			while(u != source){
				path.add(0 ,u);
				u = parent.get(u);
			}
		
		//Construction arcs solutions 
			pathedges.add(source.getEdgeBetween(path.get(0)).toString());
			for(int i = 0;i<path.size()-1;i++){
				
				pathedges.add(path.get(i).getEdgeBetween(path.get(i+1)).toString());
			}
					
		//Retrait des artéfacts de rotations en fin de parcours
			while(pathedges.get(pathedges.size()-1).split("_")[0].equals("D") || pathedges.get(pathedges.size()-1).split("_")[0].equals("G")){
				pathedges.remove(pathedges.size()-1);
				path.remove(path.size()-1);
			}
		
		//Reglages pour display
			for (Node n : path)
	            n.addAttribute("label", n.getId());
			
		//Coloration des arcs
			source.getEdgeBetween(path.get(0)).addAttribute("ui.style", "fill-color: blue;");
			source.getEdgeBetween(path.get(0)).addAttribute("ui.style", "size: 4;");
			for(int i = 0;i<path.size()-1;i++){
				path.get(i).getEdgeBetween(path.get(i+1)).addAttribute("ui.style", "fill-color: blue;");
				path.get(i).getEdgeBetween(path.get(i+1)).addAttribute("ui.style", "size: 4;");
			}

		//Coloration des noeuds
			for(Node node : path){
				graph.getNode(node.toString()).addAttribute("ui.style", "fill-color: blue;");
				graph.getNode(node.toString()).addAttribute("ui.style", "size: 15;");
			}	
			graph.getNode(source.toString()).addAttribute("ui.style", "fill-color: green;");
			graph.getNode(source.toString()).addAttribute("ui.style", "size: 15;");

			graph.getNode(path.get(path.size()-1).toString()).addAttribute("ui.style", "fill-color: green;");
			graph.getNode(path.get(path.size()-1).toString()).addAttribute("ui.style", "size: 15;");
			
		//Conversion format exercice
			for(int i = 0;i<pathedges.size();i++){
				pathedges.set(i,pathedges.get(i).split("_")[0]);
			}
			
		//Ajouts du temps
			pathedges.add(0 ,String.valueOf(pathedges.size()));
		
		
		return pathedges.toString(); 
	}
	
}
