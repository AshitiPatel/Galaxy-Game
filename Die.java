
/**
 * @(#)Die.java
 *
 *
 * @author
 * @version 1.00 2014/10/14
 */

public class Die {

	/**
	 * Creates a new instance of <code>Die</code>.
	 */

	private int faces; // attributes of a die
	private int value;

	public Die() {
		this.faces = 6;
		rollDie(); // creating a Die also rolls it
	}

	public Die(int faces) {
		this.faces = faces;
		rollDie();
	}

	// method to roll my die
	public void rollDie() {
		this.value = (int) (Math.random() * this.faces + 1);
		// Random num = new Random();
		// this.value = num.nextInt(faces)+1;
	}

	// method to read my Die value
	public int getValue() {
		return this.value;
	}

	// public boolean checkDieValue (Die otherDie)
	// {
	// check if my own value is equal to
	// the value of the otherDie
	// return true if they are equal
	// return false if not

	// }
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		Die d; // declare a die d
		d = new Die(); // instantiate or create the d object based on Die

		System.out.println(d.getValue()); // get its initial value

		d.rollDie(); // roll the die d

		System.out.println(d.getValue()); // get its new value

		Die d1 = new Die(); // declare and instantiate or create the d object based on Die

		// roll the dice d and d1 100 times and
		// display their values whenever they roll the same number
		for (int i = 0; i < 100; i++) {
			d.rollDie();
			d1.rollDie();
			if (d.getValue() == d1.getValue()) {
				System.out.println(d1.getValue() + " " + d.getValue());
			}
		}

		// create a crazy Die and test it.

		Die d2 = new Die(12);
		System.out.println(d1.getValue());

		d2.rollDie();

		System.out.println(d2.getValue());

	}
}
