import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Ashiti
 *
 */
public class ImagePicture extends Picture {

	/*
	 * Private data for image picture
	 */
	private ImageIcon image;
	
	/**
	 * First constructor
	 */
	public ImagePicture(ImageIcon img) {
		//Initialize the data for my ImagePicture 
		super(); 	//call the default constructor from Picture class
		this.image = img;
		this.setMyWidth(img.getIconWidth());
		this.setMyHeight(img.getIconHeight());
	}
	
	/**
	 * Constructor that creates an object in a specified position
	 */
	public ImagePicture(ImageIcon img, int x, int y) {
		//call the superclass constructor
		super(x, y, img.getIconWidth(), img.getIconHeight());
		this.image = img;
	}
	
	/*
	 * The paint method for painting an ImageIcon
	 * (an example of polymorphism cuz it overrides the pain method on the Picture class)
	 */
	public void paint (Graphics g) {
		this.image.paintIcon(this, g, this.getxPos(), this.getyPos());
		
	}
	
	/**
	 * @param args
	 * Self-testing Main method
	 */
	public static void main(String[] args) {
		
		//Create a JFrame
		JFrame f = new JFrame ("Testing the image");

		f.setSize(400, 350);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null, "Wait");
		
		//instantiate(or create) an ImagePicture object with minion on it
		ImagePicture p1 = new ImagePicture(new ImageIcon("minion.png"));
		
		//add the p1 object to my JFrame
		//repaint my JFrame
		f.add(p1);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null, "Wait");
		
		//move the minion
		p1.setxPos(80);
		
		//create a new object with the new constructor
		ImagePicture p2 = new ImagePicture(new ImageIcon("gru.png"), 100, 150);
		
		//add the new object to the frame
		f.add(p2);
		f.setVisible(true);

	}

}
