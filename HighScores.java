import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Aliyah Abboobakar
 *         Date: November 24th, 2021.
 *         Description: A class to calculate the scores of each player and
 *         determine winner. Is also able
 *         to write data into a text file to be read again.
 *         Method List:
 *         HighScores(int p1, int p2) - method to set the steps of each player
 *         (steps = points)
 *         int teamTotal() - calculates to total points for a team
 *         boolean winner(HighScores teampts) - method to determine winner
 *         between 2 teams
 *         static String[] loadPhraseFile(String fileName) - reads file
 *         static void newTextFile (String output, String textFile) - writes to
 *         file
 *         static void main(String[] args) - main method
 * 
 * 
 */
public class HighScores {

    private int player1, player2;

    /**
     * constructor
     */
    public HighScores(int p1, int p2) {
        this.player1 = p1;
        this.player2 = p2;

    }

    /*
     * method to calculate the total points in a single team
     */
    public int teamTotal() {

        return this.player1 + this.player2;
    }

    /*
     * method to calculate who has the higher team points, therefore determining the
     * winner.
     */
    public boolean winner(HighScores teampts) {
        if (teampts.teamTotal() < this.teamTotal())
            return false;
        else
            return true;
    }

    /**
     * Method to open a file and load the info within. Returns a string array
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String[] loadPhraseFile(String fileName) throws IOException {
        // open a file for reading
        FileReader fr = new FileReader(fileName);
        BufferedReader input = new BufferedReader(fr);

        FileReader fR = new FileReader(fileName);
        BufferedReader input2 = new BufferedReader(fR);

        int size = 0;
        String line;

        while (!(line = input2.readLine()).equals(".")) {
            size++;
        }

        // close the file stream
        input2.close();

        // create the array to save the information in the file
        String fileInfo[] = new String[size];

        // now read from the file into the array
        for (int i = 0; i < size; i++) {
            fileInfo[i] = input.readLine();
        }

        // Close the file stream
        input.close();

        return fileInfo;
    }

    /*
     * * Method to store the analysis of the points of each team into a new text
     * file
     */
    public static void newTextFile(String output, String textFile) throws IOException {
        // open a file to write to
        FileWriter fileL = new FileWriter(textFile);
        PrintWriter outputL = new PrintWriter(fileL);

        // write to file
        outputL.println(output + "\n.");

        // close the file
        fileL.close();

    }

    /**
     * @param args
     * @throws IOException
     *                     SELF TESTING MAIN METHOD
     */
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Test");
        Droid pic = new Droid(new ImageIcon("picture.png"));

        frame.setSize(800, 500);
        frame.add(pic);
        frame.setVisible(true);

        JOptionPane.showMessageDialog(null, "Wait");

        Droid pic2 = new Droid(new ImageIcon("picture.png"), 100, 100);

        frame.add(pic2);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(null, "Wait");

        int steps = pic2.getNumStepsMoved();

        if (Droid.checkSumValue(5) > 0) {
            pic2.setNumStepsToMove(5);

            for (int j = 0; j <= pic2.getNumStepsToMove(); j++) {
                if (pic2.getNumStepsMoved() < pic2.getNumStepsToMove()) {

                    pic2.setxPos(pic2.getxPos() + 1);
                    steps++;
                    pic2.setNumStepsMoved(steps);

                    System.out.println("");
                    // try {
                    // Thread.sleep(10); //delay the computer
                    // }
                    // catch (Exception error) {
                    //
                    // }
                }
            }
        }
        pic2.setNumStepsMoved(5);
        pic2.setTotalStepsMoved(5);
        JOptionPane.showMessageDialog(null, "Total Steps Moved: " + pic2.getTotalStepsMoved());

        // second round

        if (Droid.checkSumValue(11) > 0) {
            pic2.setNumStepsToMove(11);

            for (int j = 0; j <= pic2.getNumStepsToMove(); j++) {
                if (pic2.getNumStepsMoved() < pic2.getNumStepsToMove()) {

                    pic2.setxPos(pic2.getxPos() + 1);
                    steps++;
                    pic2.setNumStepsMoved(steps);

                    System.out.println("");
                }
            }
        }
        pic2.setTotalStepsMoved(11);
        JOptionPane.showMessageDialog(null, "Total Steps Moved: " + pic2.getTotalStepsMoved());

        // ^^same code as Droid class. from here we can test the HighScores class^^
        int player1 = pic.getTotalStepsMoved();
        int player2 = pic2.getTotalStepsMoved();

        HighScores team1 = new HighScores(player1, player2);

        System.out.print("\nChecks if winner method works correctly:\n");

        // is able to find the team's total points which is 16
        System.out.print("Team 1 pts.:" + team1.teamTotal());

        // in order to determine winner we need to create the other team

        int t2player1 = 12;
        int t2player2 = 6;

        HighScores team2 = new HighScores(t2player1, t2player2);

        // is able to find the total points of team 2
        System.out.println("\nTeam 2 pts.:" + team2.teamTotal());

        // now we need to determine who the winner is
        // check if winner method works correctly
        String winner = "";

        if (team1.winner(team2))
            winner = ("team 1 loses with " + team1.teamTotal() + " points.");
        else
            winner = ("team 2 loses with " + team2.teamTotal() + " points.");

        System.out.println(winner);

        // after determining who wins we need to write this information into a text file
        // so that it can be read again

        // declare the output array
        String output[];

        System.out.print("\nscores text file should display:\n");

        // Load the file into the array. Call on class, then method, then text file.
        output = loadPhraseFile("scores.txt");

        // displays the array in console

        for (int i = 0; i < output.length; i++) {
            System.out.println(output[i]);
        }

        // TEST THE FILE WRITER METHOD

        // write new scores into file
        String input[], output2;

        // Load the file into the array. Call on class, then method, then text file.
        input = loadPhraseFile("scores.txt");

        int highScore;

        if (team1.winner(team2))
            highScore = team2.teamTotal();
        else
            highScore = team1.teamTotal();

        System.out.print("\nLevel One text file should display:\n");

        String text = "";
        text = "Team 1: " + team1.teamTotal() + "\nTeam2: " + team2.teamTotal() + "\nHighScore: " +
                highScore;

        newTextFile(text, "LevelOne.txt");

        // READ THE NEW FILE

        output = loadPhraseFile("LevelOne.txt");

        // displays the array in console

        for (int i = 0; i < output.length; i++) {
            System.out.println(output[i]);
        }

        // therefore the file is able to be read again, and the UI is able to read and
        // display this info.

    }// end of main

}// end of class
