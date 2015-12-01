package uitest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class RobotPanel extends JPanel{
	
	private RobotGFXSingleton robotGFX;
	private BufferedImage cur;
	private int width;
	private int height;
	private int x;
	private int y;
	
	public RobotPanel(int x, int y, String sens) {
		
		robotGFX = RobotGFXSingleton.robotGFX();
		rotate(sens);
		this.x = x;
		this.y = y;
	}
	
	public int getposX() {
		return x;
	}

	public int getposY() {
		return y;
	}
	public void setposX(int x) {
		this.x = x;
	}

	public void setposY(int y) {
		this.y = y;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(cur, 0, 0, width, height, null);
	}
	
	public void setImgSize(int w, int h){
		width = w;
		height = h;		
	}
	
	public void rotate(String sens){
		if(sens.equals("nord")){
			cur = robotGFX.getRobotn();
		}
		if(sens.equals("sud")){
			cur = robotGFX.getRobots();
		}
		if(sens.equals("est")){
			cur = robotGFX.getRobote();
		}
		if(sens.equals("ouest")){
			cur = robotGFX.getRoboto();
		}
	}

}