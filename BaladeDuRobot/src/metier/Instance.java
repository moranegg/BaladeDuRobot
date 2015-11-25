package metier;


public class Instance {

	private Entrepot entrepot;
	private Robot robot;
	private String solution;

	public Instance(Entrepot entrepot,Robot robot){
		this.entrepot = entrepot;
		this.robot = robot;		
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
