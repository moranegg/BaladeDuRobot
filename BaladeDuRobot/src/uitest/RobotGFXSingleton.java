package uitest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RobotGFXSingleton {

	private static RobotGFXSingleton self = null;
	private BufferedImage robotn = null;
	private BufferedImage robots = null;
	private BufferedImage robote = null;
	private BufferedImage roboto = null;
	
	private RobotGFXSingleton(){
		try {
			robotn = ImageIO.read(new File("resources/robotn.png"));
			robots = ImageIO.read(new File("resources/robots.png"));
			roboto = ImageIO.read(new File("resources/roboto.png"));
			robote = ImageIO.read(new File("resources/robote.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static RobotGFXSingleton robotGFX(){
		if(self == null)
			self = new RobotGFXSingleton();
		return self;
	}

	public BufferedImage getRobotn() {
		return robotn;
	}

	public BufferedImage getRobots() {
		return robots;
	}

	public BufferedImage getRobote() {
		return robote;
	}

	public BufferedImage getRoboto() {
		return roboto;
	}	
	
}
