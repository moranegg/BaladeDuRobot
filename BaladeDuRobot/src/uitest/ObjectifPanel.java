package uitest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ObjectifPanel extends JPanel{
	
	private ObjectifGFXSingleton objectifGFX;
	private BufferedImage cur;
	private int width;
	private int height;
	private int x;
	private int y;
	
	public ObjectifPanel(int x, int y) {
		
		objectifGFX = ObjectifGFXSingleton.objectifGFX();
		cur = objectifGFX.getRobot();
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
}