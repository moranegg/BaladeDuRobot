package uitest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectifGFXSingleton {

	private static ObjectifGFXSingleton self = null;
	private BufferedImage robot = null;
	
	private ObjectifGFXSingleton(){
		try {
			robot = ImageIO.read(new File("resources/objectif.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ObjectifGFXSingleton objectifGFX(){
		int[][] mat=new int[10][];
		for (int i=0 ; i<mat.length; i++){
			mat[i]=new int[10];
		}
		if(self == null)
			self = new ObjectifGFXSingleton();
		return self;
	}

	public BufferedImage getRobot() {
		return robot;
	}
	
	
}
