import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioInputStream;

/**
 * 
 */

/**
 * @author Sana
 * Date: November 2021
 * Description: This program plays a .wav music file on a JFrame/JOptionPane
 * 				This program is based off of this tutorial: https://youtu.be/TErboGLHZGA
 * Methods List: void playMusic(String path) - Method to Play Music
 * 				 void stopMusic() - Method to Stop Music
 * 				 void main(String[] args) - Self Testing Main Method
 *
 */
public class Music {
	
	//Declare private clip variable
	private Clip clip;

	/**
	 * Method to Play Music
	 */
	public void playMusic(String path) {
	File musicPath = new File(path); //Create a musicPath file variable
	
	if (musicPath.exists()) { //if the file exists
		try { //try for errors
			//Create an audio input stream for the music
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			clip = AudioSystem.getClip(); //Declare and intialize a clip variable
			clip.open(audioInput); //open the audio
			clip.start(); //start the clip
			clip.loop(Clip.LOOP_CONTINUOUSLY); //play the music on loop
			
		}catch(Exception e){
			System.out.println("Exception Error"); //output any errors 
		}
	}
	else { //if file does not exist output an error message
		System.out.println("File does not exist");
	}
	
	
	}
	
	/*
	 * Method to stop music
	 */
	public void stopMusic() {
		clip.stop();
	}
	
	/**
	 * @param args
	 * Self Testing Main Method
	 */
	public static void main(String[] args) { 
		 Music musicObject = new Music(); //create a music object
		 musicObject.playMusic("epic.wav"); //play the music from file
		 
		 JOptionPane.showMessageDialog(null, "Press ok"); //press ok to stop

	}

}
