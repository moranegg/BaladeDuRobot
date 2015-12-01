package uitest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SquareGFXSingleton {

	private static SquareGFXSingleton self = null;
	private BufferedImage off = null;
	private BufferedImage on = null;
	
	private SquareGFXSingleton(){
		try {
			off = ImageIO.read(new File("resources/free.jpg"));
			on = ImageIO.read(new File("resources/nfree.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SquareGFXSingleton squareGFX(){
		if(self == null)
			self = new SquareGFXSingleton();
		return self;
	}

	public BufferedImage getOff() {
		return off;
	}

	public BufferedImage getOn() {
		return on;
	}
	
	
}
