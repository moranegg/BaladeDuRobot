package metier;


public class Instance {

	private Entrepot entrepot;
	private Robot robot;

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
	
	/****************************/
	public String toString(){
		return this.entrepot.toString() +"\n"+ this.robot.toString();
	}
}
