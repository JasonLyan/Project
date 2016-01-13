package Period8Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class MyWindow extends JFrame implements KeyListener{

	int width = 500;
	int height = 500;
	Hero smile;
	Hero twitchsmile;
	boolean itemPickedUp;
	BufferedImage landscape;

	public static void main(String[] args) {
		new MyWindow();
	}

	public MyWindow(){
		//the following are JFrame methods
		smile = new Hero("SmilePride", "/Images/Heroes/KappaPride.png", 150, 50);
		twitchsmile = new Hero("KappaPride", "/Images/Heroes/TwitchPride.png", 150, 250);
		landscape = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) landscape.getGraphics();
		paintLandscape(g2);
		setVisible(true);
		setSize(width, height);//units in pixels (px)
		setLocation(350,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//static constant reference for special close operation
		addKeyListener(this);
	}

	private void paintLandscape(Graphics2D g2) {
		int squareD = 10;
		int margin = 1;
		for(int x = 0; x < width; x += squareD + margin){
			for(int y = 0; y < height; y += squareD + margin){
				int a = (int)(Math.random() * 256);
				int b = (int)(Math.random() * 256);
				int c = (int)(Math.random() * 256);
				g2.setColor(new Color(a, b, c));
				g2.fillRect(x, y, squareD, squareD);
			}
		}
	}

	public void paint(Graphics g){
		//Graphics is a crayon box
		//Graphics2D is like an art kit
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)image.getGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, width, height);
		g2.setColor(Color.black);
		g2.drawImage(landscape, 0, 0, null);
		g2.drawImage(smile.getImage(), smile.getX(), smile.getY(), null);
		if(Math.abs(smile.getX()-twitchsmile.getX()) + Math.abs(smile.getY() - twitchsmile.getY()) < 10){
			itemPickedUp = true;
		}
		if(!itemPickedUp)g2.drawImage(twitchsmile.getImage(), twitchsmile.getX(), twitchsmile.getY(), null);
		//		g2.drawOval(125, 100, 250, 250);
		//		g2.setColor(Color.yellow);
		//		g2.fillOval(125, 100, 250, 250);
		//		g2.setColor(Color.black);
		//		g2.drawOval(175, 170, 50, 50);
		//		g2.fillOval(175, 170, 50, 50);
		//		g2.drawOval(275, 170, 50, 50);
		//		g2.fillOval(275, 170, 50, 50);
		//(x, y, width, height, startRADIANS, lengthRADIANS)
		//		g2.drawArc(200, 200, 100, 100, 180, 180);
		//		g2.drawArc(175, 165, 50, 50, 0, 180);
		//		g2.drawArc(275, 165, 50, 50, 0, 180);
		g.drawImage(image, 0, 0, null);
	}

	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_UP){
			smile.moveUp();
		}
		if(key == KeyEvent.VK_DOWN){
			smile.moveDown();
		}
		if(key == KeyEvent.VK_LEFT){
			smile.moveLeft();
		}
		if(key == KeyEvent.VK_RIGHT){
			smile.moveRight();
		}
		repaint();
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}
}
