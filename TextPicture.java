import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextPicture extends Picture {

	// private data/attributes
	private String title;
	private Font font;

	/*
	 * constructor
	 */
	public TextPicture(String text, int x, int y) {
		super();
		setxPos(x);
		setyPos(y);
		this.font = new Font("Arial", Font.ITALIC, 20);
		this.title = text;
		repaint();
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
		repaint();
	}

	/**
	 * @param font the font to set
	 */
	public void setFont(Font font) {
		this.font = font;
		repaint();
	}

	/**
	 * paint method
	 */
	public void paint(Graphics g) {
		g.setColor(this.getC());
		g.setFont(this.font);
		g.drawString(this.title, getxPos(), getyPos());
	}

	/*
	 * Self-testing main method
	 */
	public static void main(String[] args) {

		// create a JFrame
		JFrame display = new JFrame("Testing the program!!");
		display.setSize(400, 200);

		// create a new text picture
		TextPicture textPic = new TextPicture("My Title", 100, 100);

		// add picture to JFrame
		display.add(textPic);

		// test the picture for basics
		textPic.setC(Color.blue);
		display.setVisible(true);

		// pause before the next trial
		JOptionPane.showMessageDialog(null, "Wait");

		// trial for changing title (with some additional changes to make it look
		// different)
		textPic.setC(Color.darkGray);
		textPic.setxPos(75);
		textPic.setyPos(75);
		textPic.setTitle("Title changed!!");

		// pause before the next trial
		JOptionPane.showMessageDialog(null, "Wait");

		// trial for changing font (with some additional changes to make it look
		// different)
		textPic.setC(Color.green);
		textPic.setxPos(50);
		textPic.setyPos(50);
		Font newFont = new Font("Calibri", Font.BOLD, 25); // new font
		textPic.setFont(newFont);
		textPic.setTitle("Font changed");

	}

}