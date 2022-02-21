import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Ashiti
 * Date: 27 October 2021
 * Description: Draws a picture on a JFrame
 *
 */
public class Picture extends JComponent{

	/*
	 * Private data for picture
	 */
	private Color c;
	private int xPos, yPos, myWidth, myHeight;

	/**
	 * Default constructor
	 */
	public Picture() {
		//Initialize my private variables
		this.c = Color.RED;
		this.xPos = 0;
		this.yPos = 0;
		this.myWidth = 50;
		this.myHeight = 25;
		repaint();		//forces the calling of paint
	}

	/*
	 * A constructor for a specified location
	 */
	public Picture(int x, int y, int w, int h) {
		//initialize my private variables 
		this.c = Color.RED;
		this.xPos = x;
		this.yPos = y;
		this.myWidth = w;
		this.myHeight = h;
		repaint();		//forces the calling of paint
	}

	/**
	 * @param c the c to set
	 */
	public void setC(Color c) {
		this.c = c;
		repaint();		//forces the calling of paint
	}


	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
		repaint();		//forces the calling of paint
	}


	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
		repaint();		//forces the calling of paint
	}


	/**
	 * @param myWidth the myWidth to set
	 */
	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
		repaint();		//forces the calling of paint
	}


	/**
	 * @param myHeight the myHeight to set
	 */
	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
		repaint();		//forces the calling of paint
	}

	/**
	 * @return the c
	 */
	public Color getC() {
		return c;
	}
	
	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @return the myWidth
	 */
	public int getMyWidth() {
		return myWidth;
	}

	/**
	 * @return the myHeight
	 */
	public int getMyHeight() {
		return myHeight;
	}

	/*
	 * My paint method to paint the picture object
	 */
	public void paint (Graphics g) {
		//draws a default picture
		g.setColor(this.c);
		g.drawRect(this.xPos, this.yPos, this.myWidth, this.myHeight);
	}

	/**
	 * @param args
	 * Self-testing main method
	 */
	public static void main(String[] args) {

		//Create a JFrame to place my picture
		JFrame f = new JFrame("Testing");

		//create an object of my Picture
		Picture p1 = new Picture();

		f.setSize(400, 350); 		//sets the size of my frame

		f.add(p1);		//add the picture object to the frame

		f.setVisible(true);		//paint it!

		JOptionPane.showMessageDialog(null, "Wait");

		//test the setter for my picture
		p1.setC(Color.BLUE);
		p1.setxPos(50);
		p1.setyPos(100);
		p1.setMyWidth(25);
		p1.setMyHeight(100);

		JOptionPane.showMessageDialog(null, "Wait");

		//test the overloaded constructor
		Picture p2 = new Picture(200, 50, 100, 50);

		//add it to the JFrame
		f.add(p2);
		f.setVisible(true);

		//test to move the picture across the JFrame
		int x = 0;

		for(int i = 0; i < 3; i++) {
			x = p2.getxPos();
			x = x - 50;
			p2.setxPos(x);
			JOptionPane.showMessageDialog(null, "Wait");
		}



	}

}
