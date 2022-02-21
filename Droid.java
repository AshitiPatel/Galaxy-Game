import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Ashiti
 * Date: November 2021
 * Description: This program is the Droid class. For the overall project, this class is responsible for the creation of Droids, and contains methods that can be used to move the droids to the respective positions, as well as access important and necessary data regarding the droids from the outside.
 * Method List: Droid(ImageIcon img)	-	Default constructor
 * 				Droid(ImageIcon img, int x, int y)	-	Overlaod constructor
 * 				int getNumStepsMoved()	-	Method to return the number of steps moved
 * 				void setNumStepsMoved(int numStepsMoved)	-	Method to set the number of steps moved
 * 				int getNumStepsToMove()		-	Method to return the number of steps to move
 * 				void setNumStepsToMove(int numStepsToMove)	-	Method to set the number of steps to move
 * 				int getTotalStepsMoved()	-	Method to return the total steps moved
 * 				void setTotalStepsMoved(int steps)	-	Method to set the total steps moved
 * 				static int checkSumValue(int sum)	-	Method to check whether the player's turn is skipped or whether it stays depending on the sum of the values obtained after the dice roll
 * 				void botMove(Droid pic, int finishLine, int panXLimit, int panYLimit)	-	Method for moving droid on the box path
 * 				void linearBotMove(Droid pic, int finishLine)	-	Method for moving droid on the linear path
 * 				void botVelocities(Droid pic, int panXLimit, int panYLimit)		-	Method to finad and calculate the velocities of the droid
 * 				static void main(String[] args)		-	Self-testing main method
 * 				
 */
public class Droid extends ImagePicture {

	// attributes
	private int numStepsMoved, numStepsToMove, totalStepsMoved, yVel, xVel;

	/**
	 * Default Constructor
	 */
	public Droid(ImageIcon img) {
		super(img);
		numStepsToMove = 0;
		numStepsMoved = 0;
		repaint();
	}

	/**
	 * Overload Constructor
	 */
	public Droid(ImageIcon img, int x, int y) {
		super(img, x, y);
		numStepsToMove = 0;
		numStepsMoved = 0;
		repaint();
	}

	/**
	 * @return the numStepsMoved
	 */
	public int getNumStepsMoved() {
		return numStepsMoved;
	}

	/**
	 * @param numStepsMoved the numStepsMoved to set
	 */
	public void setNumStepsMoved(int numStepsMoved) {
		this.numStepsMoved = numStepsMoved;
	}

	/**
	 * @return the numStepsToMove
	 */
	public int getNumStepsToMove() {
		return numStepsToMove;
	}

	/**
	 * @param numStepsToMove the numStepsToMove to set
	 */
	public void setNumStepsToMove(int numStepsToMove) {
		this.numStepsToMove = numStepsToMove;
	}

	/**
	 * @return the totalStepsMoved
	 */
	public int getTotalStepsMoved() {
		return totalStepsMoved;
	}

	/**
	 * @param totalStepsMoved the totalStepsMoved to set
	 */
	public void setTotalStepsMoved(int steps) {
		this.totalStepsMoved += steps;
	}

	/**
	 * Method to check whether the player's turn is skipped or whether it stays depending on the sum of the values obtained after the dice roll
	 * @param sum
	 * @return
	 */
	public static int checkSumValue(int sum) {
		// when turn is to be skipped
		if ((sum == 2) || (sum == 3) || (sum == 12)) {
			return 0;
		}
		// when turn counts
		return sum * 7;

	}

	/**
	 * Method for moving the droid (for box path)
	 * @param pic
	 * @param xPosLimit
	 */
	public void botMove(Droid pic, int finishLine, int panXLimit, int panYLimit) {

		//loop to keep the droid moving until the number of steps to move are reached
		while (pic.getNumStepsMoved() < pic.getNumStepsToMove()) {

			// get velocities
			pic.botVelocities(pic, panXLimit, panYLimit);

			// move the image by 1
			pic.setxPos(pic.getxPos() + xVel);
			pic.setyPos(pic.getyPos() + yVel);

			// delay the computer to make the movement look smooth
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// inc steps moved by 1
			pic.setNumStepsMoved(pic.getNumStepsMoved() + 1);

			if ((pic.getxPos() == 0) && (pic.getyPos() < finishLine)) { // when finish line CROSSED
				break;
			} // breakout statement

		}

		pic.setTotalStepsMoved(pic.getNumStepsMoved());
		pic.setNumStepsMoved(0); // for next time

	}// method

	/**
	 * Method to move the droid for linear game
	 * @param pic
	 * @param finishLine
	 */
	public void linearBotMove(Droid pic, int finishLine) {

		while (pic.getNumStepsMoved() < pic.getNumStepsToMove()) {

			// move the image by 1
			pic.setxPos(pic.getxPos() + 1);

			// delay the computer to make the movement look smooth
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// inc steps moved by 1
			pic.setNumStepsMoved(pic.getNumStepsMoved() + 1);

			if ((pic.getxPos() > finishLine)) { // when finish line CROSSED
				break;
			} // breakout statement

		}

		pic.setTotalStepsMoved(pic.getNumStepsMoved());
		pic.setNumStepsMoved(0); // for next time

	}

	/**
	 * Method to find turns and calculate the velocities of the droids
	 * @param pic
	 * @param panXLimit
	 * @param panYLimit
	 */
	public void botVelocities(Droid pic, int panXLimit, int panYLimit) {

		// the beginning
		if (((pic.getxPos() + pic.getMyWidth()) < panXLimit) && (pic.getyPos() == 0)) {
			this.xVel = 1;
			this.yVel = 0;
		}
		// first turn or its continuation
		else if ((((pic.getxPos() + pic.getMyWidth()) == panXLimit) && (pic.getyPos() == 0))
				|| (((pic.getyPos() + pic.getMyHeight()) < panYLimit)
						&& ((pic.getxPos() + pic.getMyWidth()) == panXLimit))) {
			this.xVel = 0;
			this.yVel = 1;
		}

		else if (((pic.getyPos() + pic.getMyHeight()) == panYLimit)
				&& (((pic.getxPos() + pic.getMyWidth()) <= panXLimit) && pic.getxPos() > 0)) {
			this.xVel = -1;
			this.yVel = 0;

		} else if ((pic.getxPos() == 0) && ((pic.getyPos() + pic.getMyHeight()) == panYLimit)) {
			this.xVel = 0;
			this.yVel = -1;

		}

	}

	/**
	 * @param args
	 * Self-testing main method
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		Droid pic = new Droid(new ImageIcon("picture.jpg"), 0, 0);

		frame.setSize(400, 500);

		frame.add(pic);
		frame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Wait");

		// round 1 (linear)
		pic.setNumStepsToMove(checkSumValue(4));

		pic.linearBotMove(pic, 400);

		pic.setTotalStepsMoved(pic.getNumStepsMoved());
		pic.setNumStepsMoved(0); // for next time
		JOptionPane.showMessageDialog(null, "Total Steps Moved: " + pic.getTotalStepsMoved());

		// second round (box)
		pic.setNumStepsToMove(checkSumValue(2));

		pic.botMove(pic, 50, 400, 500);

		pic.setTotalStepsMoved(pic.getNumStepsMoved());
		pic.setNumStepsMoved(0); // for next time
		JOptionPane.showMessageDialog(null, "Total Steps Moved: " + pic.getTotalStepsMoved());

		// third round (box)
		pic.setNumStepsToMove(checkSumValue(11));

		pic.botMove(pic, 50, 400, 500);

		pic.setTotalStepsMoved(pic.getNumStepsMoved());
		pic.setNumStepsMoved(0); // for next time
		JOptionPane.showMessageDialog(null, "Total Steps Moved: " + pic.getTotalStepsMoved());

		// fourth round (box) [I know that the die value will never be this large but i
		// want to test the velocities code and finish line part]
		pic.setNumStepsToMove(checkSumValue(400));

		pic.botMove(pic, 50, 400, 500);

		pic.setTotalStepsMoved(pic.getNumStepsMoved());
		pic.setNumStepsMoved(0); // for next time
		JOptionPane.showMessageDialog(null, "Total Steps Moved: " + pic.getTotalStepsMoved());

	}
}


//Sana (themeMenu, selection, gamewindow and scoreboard) & Ashiti (gamewindow, scoreboard, and gamePlay)



