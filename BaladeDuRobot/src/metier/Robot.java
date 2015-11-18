package metier;

import java.awt.Point;

public class Robot {
	private Point depart;
	private Point objectif;
	private String direction;
	
	public Robot(int departLigne, int departColonne, int objectifLigne, 
			int ObjectifColonne, String d){
		this.depart = new Point(departLigne,departColonne);
		this.objectif = new Point(objectifLigne, ObjectifColonne);
		this.direction = d;
	}
	/***Getters and Setter ***/
	public Point getDepart() {
		return depart;
	}

	public void setDepart(Point depart) {
		this.depart = depart;
	}

	public Point getObjectif() {
		return objectif;
	}

	public void setObjectif(Point objectif) {
		this.objectif = objectif;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	/*******************************/

}
