package uitest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PathGFXSingleton {

	private static PathGFXSingleton self = null;
	private BufferedImage path = null;
	
	private PathGFXSingleton(){
		try {
			path = ImageIO.read(new File("resources/path.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static PathGFXSingleton pathGFX(){
		int[][] mat=new int[10][];
		for (int i=0 ; i<mat.length; i++){
			mat[i]=new int[10];
		}
		if(self == null)
			self = new PathGFXSingleton();
		return self;
	}

	public BufferedImage getPath() {
		return path;
	}
	
	
}
