package uitest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Freegfx extends JPanel{
	
	private SquareGFXSingleton squareGFX;
	private BufferedImage cur;
	private int width;
	private int height;
	private int x;
	private int y;
	private boolean state = false;
	
	public Freegfx(int x, int y) {
		
		squareGFX = SquareGFXSingleton.squareGFX();
		cur = squareGFX.getOff();
		this.x = x;
		this.y = y;
	}
	
	public int getposX() {
		return x;
	}

	public int getposY() {
		return y;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(cur, 0, 0, width, height, null);
	}
	
	public void setImgSize(int w, int h){
		width = w;
		height = h;		
	}
	
	public int change(){
		int sol = 0;
		if(state == false){
			cur = squareGFX.getOn();
			state = true;
			sol = 1;
		}
		else if(state == true){
			cur = squareGFX.getOff();
			state = false;
			sol = -1;

		}
		return sol;
	}

	public boolean isState() {
		return state;
	}
}