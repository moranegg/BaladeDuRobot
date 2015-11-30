package metier;

import java.util.Random;

public class Instance {

	private Entrepot entrepot;
	private Robot robot;
	private String solution;

	
	public static int randInt(int min, int max) {

	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public Instance(Entrepot entrepot,Robot robot){
		this.entrepot = entrepot;
		this.robot = robot;		
	}
	
	public Instance(int nbObstacles,int x,int y,Robot r){
		this.robot = r;
		if(nbObstacles >= x*y/4){
			System.out.println("Trop d'obstacles risquent de poser problème lors de la génération");
			System.exit(0);
		}
		else{
			int[][] mat=new int[x][];
			for (int i=0 ; i<mat.length; i++){
				mat[i]=new int[y];
			}
			
			//Placement des obstacles
			for (int cpt = nbObstacles;cpt > 0 ; cpt--){
				
				int randx = randInt(0,x-1);
				int randy = randInt(0,y-1);
				while(mat[randx][randy] == 1){
					randx = randInt(0,x-1);
					randy = randInt(0,y-1);
				}
				mat[randx][randy] = 1;
			}
			
			this.entrepot = new Entrepot(x,y,mat);
		}
		
	}
	
	public Instance(int nbObstacles,int x,int y){
		if(nbObstacles >= x*y/4){
			System.out.println("Trop d'obstacles risquent de poser problème lors de la génération");
			System.exit(0);
		}
		else{
			int[][] mat=new int[x][];
			for (int i=0 ; i<mat.length; i++){
				mat[i]=new int[y];
			}
			
			//Placement des obstacles
			for (int cpt = nbObstacles;cpt > 0 ; cpt--){
				
				int randx = randInt(0,x-1);
				int randy = randInt(0,y-1);
				while(mat[randx][randy] == 1){
					randx = randInt(0,x-1);
					randy = randInt(0,y-1);
				}
				mat[randx][randy] = 1;
			}
			
			this.entrepot = new Entrepot(x,y,mat);
			
			//Génération postition du robot, gestion des effets de bords et hors obstacle
			boolean nok = true;
			int dx = 0;
			int dy = 0;
			int sx = 0;
			int sy = 0;
			while(nok){
				sx = randInt(0,x);
				sy = randInt(0,y);
				System.out.println("sx : "+sx+" sy : "+sy);
				
				if(sx == 0 && sy == 0){
					if(mat[0][0] == 0)
						nok = false;
				}
				else if(sx == 0 && sy != 0 && sy != y){
					if(mat[0][sy] == 0 && mat[0][sy-1] == 0)
						nok = false;
				}
				else if(sx != 0 && sy == 0 && sx != x){
					if(mat[sx][0] == 0 && mat[sx-1][0] == 0)
						nok = false;
				}
				else if(sx == x && sy == y){
					if(mat[x-1][y-1] == 0)
						nok = false;
				}
				else if(sx == x && sy != 0 && sy != y){
					if(mat[x-1][sy] == 0 && mat[x-1][sy-1] == 0)
						nok = false;
				}
				else if(sx != 0 && sy == y && sx != x){
					if(mat[sx][y-1] == 0 && mat[sx-1][y-1] == 0)
						nok = false;
				}
				else if(sx == 0 && sy == y){
					if(mat[0][y-1] == 0)
						nok = false;
				}
				else if(sx == x && sy == 0){
					if(mat[x-1][0] == 0)
						nok = false;
				}
				else if(mat[sx][sy] == 0 && mat[sx-1][sy] == 0 && mat[sx][sy-1] == 0 && mat[sx-1][sy-1] == 0){
					nok = false;
				}

			}

			
			nok = true;
			while(nok){
				dx = randInt(0,x);
				dy = randInt(0,y);
				System.out.println("sx : "+sx+" sy : "+sy);

				
				if(dx == 0 && dy == 0){
					if(mat[0][0] == 0)
						nok = false;
				}
				else if(dx == 0 && dy != 0 && dy != y){
					if(mat[0][dy] == 0 && mat[0][dy-1] == 0)
						nok = false;
				}
				else if(dx != 0 && dy == 0 && dx != x){
					if(mat[dx][0] == 0 && mat[dx-1][0] == 0)
						nok = false;
				}
				else if(dx == x && dy == y){
					if(mat[x-1][y-1] == 0)
						nok = false;
				}
				else if(dx == x && dy != 0 && dy != y){
					if(mat[x-1][dy] == 0 && mat[x-1][dy-1] == 0)
						nok = false;
				}
				else if(dx != 0 && dy == y && dx != x){
					if(mat[dx][y-1] == 0 && mat[dx-1][y-1] == 0)
						nok = false;
				}
				else if(dx == 0 && dy == y){
					if(mat[0][y-1] == 0)
						nok = false;
				}
				else if(dx == x && dy == 0){
					if(mat[x-1][0] == 0)
						nok = false;
				}
				else if(mat[dx][dy] == 0 && mat[dx-1][dy] == 0 && mat[dx][dy-1] == 0 && mat[dx-1][dy-1] == 0){
					nok = false;
				}

			}
			
			int s = randInt(0,3);
			String sens = "nord"; 
			switch(s){
			case 0:
				sens = "nord";
				break;
			case 1:
				sens = "est";
				break;
			case 2:
				sens = "ouest";
				break;
			case 3:
				sens = "sud";
			}
			
			this.robot = new Robot(sx, sy, dx, dy, sens);
			System.out.println(this);
		}
		
	}
	
	/***Getters***/
	public Entrepot getEntrepot() {
		return entrepot;
	}

	public Robot getRobot() {
		return robot;
	}
	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public void setEntrepot(Entrepot entrepot) {
		this.entrepot = entrepot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	/****************************/
	public String toString(){
		return this.entrepot.toString() +"\n"+ this.robot.toString();
	}
	
	public String printSolution(){
		if(this.solution ==null)
		{
			return ""+-1;
		}else{
			return solution;
		}
	}

	
	

}
