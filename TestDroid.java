import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Ashii
 *
 */
public class TestDroid {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Test");
		Droid pic = new Droid(new ImageIcon("picture.jpg"), 0, 0);

		frame.setSize(400, 500);

		frame.add(pic);
		frame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Wait");
		
		// second round (box)
				pic.setNumStepsToMove(Droid.checkSumValue(2));

				pic.botMove(pic, 50, 400, 500);

				pic.setTotalStepsMoved(pic.getNumStepsMoved());
				pic.setNumStepsMoved(0); // for next time
				JOptionPane.showMessageDialog(null, "Total Steps Moved: " + pic.getTotalStepsMoved());

				// third round (box)
				pic.setNumStepsToMove(Droid.checkSumValue(11));

				pic.botMove(pic, 50, 400, 500);

				pic.setTotalStepsMoved(pic.getNumStepsMoved());
				pic.setNumStepsMoved(0); // for next time
				JOptionPane.showMessageDialog(null, "Total Steps Moved: " + pic.getTotalStepsMoved());
		
		
		
		
		

	}

}
