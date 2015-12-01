package uitest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PathComponent extends JPanel{
	
	private PathGFXSingleton pathGFX;
	private BufferedImage cur;
	private int width;
	private int height;
	private int x;
	private int y;
	
	public PathComponent(int x, int y) {
		
		pathGFX = PathGFXSingleton.pathGFX();
		cur = pathGFX.getPath();
		this.x = x;
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