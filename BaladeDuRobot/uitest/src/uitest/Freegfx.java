package uitest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Freegfx extends JPanel{
	
	private BufferedImage image;
	private int width;
	private int height;
	private int x;
	private int y;
	
	public Freegfx(int x, int y) {
		try {
			image = ImageIO.read(new File("resources/free.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, width, height, null);
	}
	
	public void setImgSize(int w, int h){
		width = w;
		height = h;		
	}
}