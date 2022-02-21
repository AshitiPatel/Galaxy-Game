import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author Sana (themeMenu, selection, gamewindow and scoreboard) & Ashiti (gamewindow, scoreboard, and gamePlay)
 *         Date: November 2021
 *         Description: This program is the GalaxyGame UI. It allows you to
 *         choose between a marvel and star wars game.
 *         The characters race to the finish and the scores are displayed in a
 *         scoreboard.
 *         Methods List: GalaxyGame() - Constructor
 *         void setSelection(ImagePicture back, String imageBack, JPanel p1,
 *         JPanel p2, JPanel path, TextPicture txtP1, TextPicture txtP2,
 *         TextPicture txtPath, JTextField inpP1, JTextField inpP2,
 *         JComboBox<String> drop, JComboBox<String> drop2,
 *         JComboBox<String> drop3, JComboBox<String> drop4, JComboBox<String>
 *         dropPath, TextPicture title, String[] characters, String[] paths,
 *         String titleText, Color colour, JFrame selection, JButton exit,
 *         JButton menu, JButton play, int x, TextPicture c1, TextPicture c2,
 *         TextPicture c3, TextPicture c4)
 *         void linearPathMovement(Droid pic, int finishLineXPos)
 * 
 *
 */
public class GalaxyGame extends JFrame implements ActionListener {

	// Declare private variables and arrays
	private TextPicture txtTheme, txtPick, txtStarSel, txtMarvSel, txtPlayer1, txtPlayer2, txtPath, txtTitleGame,
	txtSum, txtTurn;
	private TextPicture[] txtChar, playerScores;
	private ImagePicture imgDarth, imgIron, imgBack, imgBackStar, imgBackMarv, imgDice, imgBgMarv, imgBgStar;
	private JButton btnPlayMarv, btnPlayStar, btnMenuMarv, btnMenuStar;
	private JButton[] btnExits, btnMenus, btnRestarts;
	private JFrame themeMenu, selectionMarv, selectionStar, starWarsGame, marvelGame, scoreboard;
	private JPanel player1, player2, path, player1M, player2M, pathM, gameMarv, gameStar;
	private JTextField inpPlayer1, inpPlayer2, inpPlayer1M, inpPlayer2M;
	private JComboBox<String> dropChar, dropChar2, dropChar3, dropChar4, dropPath, dropCharM, dropChar2M, dropChar3M,
	dropChar4M, dropPathM;
	private String[] charactersStar = { "C-3PO", "R2-D2", "R5-D4", "BB-8" },
			charactersMarv = { "Iron Man", "Captain America", "Captain Marvel", "Groot" }, paths = { "Line", "Box" };
	private Music starWars, musicObject, avengers;
	private Droid[] droids;
	private Droid droid;
	private String team1, team2, image1, image2, image3, image4;
	private int pathInd, whoseTurn = 0;
	private int[] indicesM, indicesS;
	private Die d1, d2;

	// Declare font variable
	static Font font;

	/**
	 * Galaxy Game Constructor
	 */
	public GalaxyGame() {
		// Create JFrames for the entire game
		themeMenu = new JFrame("Select a Theme!");
		selectionMarv = new JFrame("Marvel Selection");
		selectionStar = new JFrame("Star Wars Selection");
		starWarsGame = new JFrame("Star Wars Game");
		marvelGame = new JFrame("Marvel Game");
		scoreboard = new JFrame("Scoreboard");

		// Create all the exit buttons
		btnExits = new JButton[5];
		for (int i = 0; i < btnExits.length; i++) { // Initialize and add action listeners to all exit buttons
			btnExits[i] = new JButton("Exit");
			btnExits[i].addActionListener(this);
		}
		droids = new Droid[4]; // Create 4 droid objects
		indicesM = new int[4]; // Create 4 indices for marvel
		indicesS = new int[4]; // Create 4 indices for star wars
		txtChar = new TextPicture[4]; // Create txtChar array
		playerScores = new TextPicture[4]; // Create player scores array

		// Set each player score to 0
		for (int i = 0; i < playerScores.length; i++) {
			playerScores[i] = new TextPicture("0", 100, 100);
		}

		// Create all the text for themeMenu using TextPicture and call the setText
		// method
		txtTheme = new TextPicture("Galaxy Game", 200, 50); // title
		GalaxyGame.setText(txtTheme, -135, 25, 800, 70, GalaxyGame.customFont(font, "Game Of Squids.ttf", 60f),
				Color.WHITE);
		txtPick = new TextPicture("Select a Theme", 110, 30); // instructions
		GalaxyGame.setText(txtPick, 95, 200, 600, 70, GalaxyGame.customFont(font, "Game Of Squids.ttf", 20f),
				Color.WHITE);

		// Add hover over instructions
		txtPick.setToolTipText("Click on the Iron Man or Darth Vader image to select a theme.");

		// Create the images using ImagePicture and set their locations/width/height
		imgDarth = new ImagePicture(new ImageIcon("darth.png")); // Darth Vader
		imgDarth.setBounds(450, 100, 200, 450);
		imgIron = new ImagePicture(new ImageIcon("iron man.png")); // Iron Man
		imgIron.setBounds(0, 100, 200, 450);
		imgBack = new ImagePicture(new ImageIcon("red.png")); // Background
		imgBack.setBounds(0, 0, 650, 450);

		// Call setButtons to create the exit button on themeMenu
		GalaxyGame.setButtons(btnExits[0], 280, 300, 100, 30, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 20f));

		// Add all the themeMenu components to the frame
		themeMenu.add(txtTheme);
		themeMenu.add(txtPick);
		themeMenu.add(imgDarth);
		themeMenu.add(imgIron);
		themeMenu.add(btnExits[0]);
		themeMenu.add(imgBack);

		// Call the music class to play intense music on themeMenu
		musicObject = new Music();
		musicObject.playMusic("epic.wav");

		// Creating buttons for the star wars and marvel selection frames
		btnPlayMarv = new JButton("Play"); // Play Marvel
		btnPlayStar = new JButton("Play"); // Play Star Wars
		btnMenuMarv = new JButton("Menu"); // Menu Marvel
		btnMenuStar = new JButton("Menu"); // Menu Star
		GalaxyGame.setButtons(btnExits[1], 600, 375, 100, 30, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 20f)); // Exit
		// Star
		// Wars
		GalaxyGame.setButtons(btnExits[2], 600, 375, 100, 30, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 20f)); // Exit
		// Marvel
		GalaxyGame.setButtons(btnPlayMarv, 450, 375, 100, 30, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 20f));
		GalaxyGame.setButtons(btnPlayStar, 450, 375, 100, 30, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 20f));
		GalaxyGame.setButtons(btnMenuMarv, 300, 375, 100, 30, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 20f));
		GalaxyGame.setButtons(btnMenuStar, 300, 375, 100, 30, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 20f));

		// Create all the restart buttons
		btnRestarts = new JButton[2];
		for (int i = 0; i < btnRestarts.length; i++) {
			btnRestarts[i] = new JButton("Restart"); // initialize restart buttons
			btnRestarts[i].addActionListener(this); // add action listeners
			GalaxyGame.setButtons(btnRestarts[i], 650, 550, 100, 30,
					GalaxyGame.customFont(font, "Akira Jimbo.ttf", 20f)); // call set button method
		}

		// Create all the menu buttons
		btnMenus = new JButton[2];
		for (int i = 0; i < btnMenus.length; i++) {
			btnMenus[i] = new JButton("Menu"); // initialize menu buttons
			btnMenus[i].addActionListener(this); // add action listeners
			GalaxyGame.setButtons(btnMenus[i], 750, 550, 100, 30, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 20f)); // call
			// set
			// button
			// method
		}

		// Call set button method for the exit buttons on the game frames
		for (int i = 3; i < btnExits.length; i++) {
			GalaxyGame.setButtons(btnExits[i], 850, 550, 100, 30, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 20f));
		}

		// Add action listeners to the play and menu buttons
		btnPlayMarv.addActionListener(this);
		btnPlayStar.addActionListener(this);
		btnMenuMarv.addActionListener(this);
		btnMenuStar.addActionListener(this);

		// add the selection buttons to the selection frames
		selectionStar.add(btnPlayStar); // Star Wars Selection
		selectionStar.add(btnMenuStar);
		selectionStar.add(btnExits[1]);
		selectionMarv.add(btnPlayMarv); // Marvel Selection
		selectionMarv.add(btnMenuMarv);
		selectionMarv.add(btnExits[2]);

		// add all the game buttons to the game frames
		starWarsGame.add(btnRestarts[0]);
		starWarsGame.add(btnMenus[0]);
		starWarsGame.add(btnExits[3]);
		marvelGame.add(btnRestarts[1]);
		marvelGame.add(btnMenus[1]);
		marvelGame.add(btnExits[4]);

		// Call setJFrame method to create frames
		GalaxyGame.setJFrame(themeMenu, 430, 665, true);
		GalaxyGame.setJFrame(selectionMarv, 600, 1000, false);
		GalaxyGame.setJFrame(selectionStar, 600, 1000, false);
		GalaxyGame.setJFrame(starWarsGame, 650, 1000, false);
		GalaxyGame.setJFrame(marvelGame, 650, 1000, false);
		GalaxyGame.setJFrame(scoreboard, 430, 665, false);

		// Add mouse listeners to the Iron Man and Darth Vader images on themeMenu
		// https://stackoverflow.com/questions/8680189/clickable-images-in-java
		imgIron.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				themeMenu.setVisible(false); // close themeMenu
				selectionMarv.setVisible(true); // open selection marvel menu
				musicObject.stopMusic(); // stop music from themeMenu

				// Create music object and play avengers music
				avengers = new Music();
				avengers.playMusic("AvengersThemeMusic.wav");

				// Create drop down menus for character selection
				dropCharM = new JComboBox<String>(charactersMarv);
				dropChar2M = new JComboBox<String>(charactersMarv);
				dropChar3M = new JComboBox<String>(charactersMarv);
				dropChar4M = new JComboBox<String>(charactersMarv);

				// Create drop down for path selection
				dropPathM = new JComboBox<String>(paths);

				// Create textfields for name input for player 1 and 2
				inpPlayer1M = new JTextField("Enter Team 1 Name.", 20);
				inpPlayer2M = new JTextField("Enter Team 2 Name.", 20);

				// Call the set selection method to set the frame
				setSelection(imgBackMarv, "marvelBack2 (1).jpg", player1M, player2M, pathM,
						txtPlayer1, txtPlayer2, txtPath,
						inpPlayer1M, inpPlayer2M, dropCharM, dropChar2M, dropChar3M, dropChar4M, dropPathM,
						txtMarvSel,
						charactersMarv, paths, "Marvel Selection",
						Color.WHITE, selectionMarv, btnExits[2], btnMenuMarv,
						btnPlayMarv, 30, txtChar);
			}
		});
		imgDarth.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				themeMenu.setVisible(false); // close themeMenu
				selectionStar.setVisible(true); // open selection star wars menu
				musicObject.stopMusic(); // stop music from themeMenu

				// Create music object and play star wars music
				starWars = new Music();
				starWars.playMusic("Star Wars Music Theme.wav");

				// Create drop down menus for character selection
				dropChar = new JComboBox<String>(charactersStar);
				dropChar2 = new JComboBox<String>(charactersStar);
				dropChar3 = new JComboBox<String>(charactersStar);
				dropChar4 = new JComboBox<String>(charactersStar);

				// Create drop down for path selection
				dropPath = new JComboBox<String>(paths);

				// Create textfields for name input for player 1 and 2
				inpPlayer1 = new JTextField("Enter Team 1 Name.", 20);
				inpPlayer2 = new JTextField("Enter Team 2 Name.", 20);

				// call the set selection method to set the frame
				setSelection(imgBackStar, "starWarsBack3.jpg", player1, player2, path,
						txtPlayer1, txtPlayer2, txtPath,
						inpPlayer1, inpPlayer2, dropChar, dropChar2, dropChar3, dropChar4, dropPath,
						txtStarSel,
						charactersStar, paths, "Star Wars Selection",
						Color.YELLOW, selectionStar, btnExits[1], btnMenuStar,
						btnPlayStar, -20, txtChar);
			}
		});
		imgDice.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e, int finishLine) {

				gamePlay(finishLine);
				System.out.println("Dice Image clicked");

			}
			});

	}




	/**
	 * This method creates the selection menu frames
	 */
	public void setSelection(ImagePicture back, String imageBack, JPanel p1, JPanel p2, JPanel path, TextPicture txtP1,
			TextPicture txtP2,
			TextPicture txtPath, JTextField inpP1, JTextField inpP2, JComboBox<String> drop, JComboBox<String> drop2,
			JComboBox<String> drop3,
			JComboBox<String> drop4, JComboBox<String> dropPath, TextPicture title, String[] characters, String[] paths,
			String titleText, Color colour, JFrame selection, JButton exit, JButton menu,
			JButton play, int x, TextPicture[] c1) {

		// Create the background image for the selection star wars window
		back = new ImagePicture(new ImageIcon(imageBack));
		back.setBounds(0, 0, 1000, 1000);

		// Create the JPanels for the selection frame and set using setJPanel
		p1 = new JPanel(); // Team 1
		GalaxyGame.setJPanels(p1, 30, 50, 300, 250, 250, 250, 241, 200);
		p2 = new JPanel(); // Team 2
		GalaxyGame.setJPanels(p2, 650, 50, 300, 250, 250, 250, 241, 200);
		path = new JPanel(); // Path
		GalaxyGame.setJPanels(path, 390, 150, 200, 150, 250, 250, 241, 200);

		// create all the text for the selection frames using setText and TextPicture
		txtP1 = new TextPicture("Team 1", 200, 50); // Team 1 title
		GalaxyGame.setText(txtP1, -120, 0, 800, 70, GalaxyGame.customFont(font, "Game Of Squids.ttf", 30f),
				Color.BLACK);
		txtP2 = new TextPicture("Team 2", 200, 50); // Team 2 title
		GalaxyGame.setText(txtP2, -125, 0, 800, 70, GalaxyGame.customFont(font, "Game Of Squids.ttf", 30f),
				Color.BLACK);
		txtPath = new TextPicture("Path", 200, 50); // Path title
		GalaxyGame.setText(txtPath, -145, 0, 800, 70, GalaxyGame.customFont(font, "Game Of Squids.ttf", 30f),
				Color.BLACK);
		title = new TextPicture(titleText, 200, 50); // Frame title
		GalaxyGame.setText(title, x, 440, 900, 70, GalaxyGame.customFont(font, "Game Of Squids.ttf", 40f), colour);

		for (int i = 0; i < c1.length; i++) {
			c1[i] = new TextPicture("Player " + (i + 1), 200, 50); // Create each player text
		}
		// Set all player text using setText
		GalaxyGame.setText(c1[0], -170, 110, 800, 70, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 25f),
				Color.BLACK);
		GalaxyGame.setText(c1[1], -10, 110, 800, 70, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 25f),
				Color.BLACK);
		GalaxyGame.setText(c1[2], -170, 110, 800, 70, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 25f),
				Color.BLACK);
		GalaxyGame.setText(c1[3], -10, 110, 800, 70, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 25f),
				Color.BLACK);

		// Set the position/width/height of the input textfields
		inpP1.setBounds(20, 80, 250, 30);
		inpP2.setBounds(20, 80, 250, 30);

		// Set the position/width/height of the drop down menus
		drop.setBounds(20, 190, 100, 20);
		drop2.setBounds(180, 190, 100, 20);
		drop3.setBounds(20, 190, 100, 20);
		drop4.setBounds(180, 190, 100, 20);
		dropPath.setBounds(50, 85, 100, 20);

		// Set buttons to visible
		exit.setVisible(true);
		play.setVisible(true);
		menu.setVisible(true);

		// Add the components to the selection frame
		selection.add(p1);
		selection.add(p2);
		selection.add(path);
		selection.add(title);
		selection.add(back);

		// Add the components to the team 1 panel
		p1.add(txtP1);
		p1.add(inpP1);
		p1.add(drop);
		p1.add(drop2);
		p1.add(c1[0]);
		p1.add(c1[1]);

		// Add the components to the team 2 panel
		p2.add(txtP2);
		p2.add(inpP2);
		p2.add(drop3);
		p2.add(drop4);
		p2.add(c1[2]);
		p2.add(c1[3]);

		// Add the components to the path panel
		path.add(dropPath);
		path.add(txtPath);
	}
	
	/**
	 * Method to setup the game window
	 * @param frame
	 * @param title
	 * @param drawingPanel
	 * @param playersDroid
	 * @param restart
	 * @param leave
	 * @param backgroundDrawingPanel
	 * @param dice
	 * @param die1
	 * @param die2
	 * @param sumDigit
	 * @param turnAllowance
	 * @param team1
	 * @param team2
	 * @param playerScores
	 * @param colour
	 * @param index
	 * @param image1
	 * @param image2
	 * @param image3
	 * @param image4
	 */
	public void gameWindow(JFrame frame, TextPicture title, JPanel drawingPanel, Droid[] playersDroid,
			JButton restart, JButton leave,
			ImagePicture backgroundDrawingPanel, ImagePicture dice, Die die1, Die die2,
			TextPicture sumDigit, TextPicture turnAllowance,
			String team1, String team2, TextPicture[] playerScores,
			Color colour, int[] index, String image1, String image2, String image3, String image4) {

		// title code
		title = new TextPicture("May the best team win!", 1000, 1000);
		GalaxyGame.setText(title, 100, 100, 100, 100, GalaxyGame.customFont(font, "Akira Jimbo.ttf", 30f), colour);

		// Create all the text using TextPicture and setText
		TextPicture txtTeam1 = new TextPicture(team1, 200, 50); // Team 1 title
		GalaxyGame.setText(txtTeam1, -175, -20, 500, 70, GalaxyGame.customFont(font,
				"Game Of Squids.ttf", 15f),
				Color.WHITE);
		TextPicture txtTeam2 = new TextPicture(team2, 200, 50); // Team 2 title
		GalaxyGame.setText(txtTeam2, 550, -20, 500, 70, GalaxyGame.customFont(font,
				"Game Of Squids.ttf", 15f),
				Color.WHITE);
		TextPicture txtTitleSL = new TextPicture("May the best team win!", 200, 50); // Frame title
		GalaxyGame.setText(txtTitleSL, 80, -20, 900, 70,
				GalaxyGame.customFont(font, "Game Of Squids.ttf", 25f), Color.YELLOW);

		// >>>>>drawing panel code
		drawingPanel = new JPanel();
		setJPanels(drawingPanel, 50, 200, 900, 560, 0, 0, 0, 0);

		backgroundDrawingPanel = new ImagePicture(new ImageIcon("starry.jpg"));
		backgroundDrawingPanel.setBounds(0, 0, 1000, 1000);

		// set buttons to visible
		restart.setVisible(true);
		leave.setVisible(true);

		// add dice
		dice = new ImagePicture(new ImageIcon("diceImage.jpg"));
		dice.setBounds(30, 500, 120, 90);
		dice.setVisible(true);

		die1 = new Die();
		die2 = new Die();

		sumDigit = new TextPicture("0", 230, 560);
		setText(sumDigit, 230, 500, 500, 90,
				GalaxyGame.customFont(font, "Akira Jimbo.ttf", 60f),
				Color.WHITE);

		turnAllowance = new TextPicture("----", 230, 610);
		setText(turnAllowance, 400, 500, 500, 90,
				GalaxyGame.customFont(font, "Akira Jimbo.ttf", 40f),
				Color.WHITE);

		frame.add(playersDroid[0]);
		frame.add(txtTeam1);
		frame.add(txtTeam2);
		frame.add(txtTitleSL);
		frame.add(title);
		frame.add(drawingPanel);
		frame.add(sumDigit);
		frame.add(turnAllowance);
		frame.add(dice);
		frame.add(backgroundDrawingPanel);

	}

	/**
	 * Method to move the correct droids
	 * @param finishLine
	 */
	public void gamePlay(int finishLine) {

		// roll the dice
		d1.rollDie();
		d2.rollDie();

		// check value and change the turn allowance
		if (Droid.checkSumValue(d1.getValue() + d2.getValue()) > 0) {
			// change text on for the turn allowance
			txtTurn.setTitle("Turn Allowed");

			// change the dice sum text
			txtSum.setTitle(Integer.toString(d1.getValue() + d2.getValue()));
		} else {
			// change text on for the turn allowance
			txtTurn.setTitle("Turn Skipped");
			// change the dice sum text
			txtSum.setTitle(Integer.toString(d1.getValue() + d2.getValue()));
		}
		// set the number of steps to move
		droids[whoseTurn].setNumStepsToMove(Droid.checkSumValue(d1.getValue() + d2.getValue()));

		// move the respective droid according to the path selected by the user
		if (pathInd == 1) {
			droids[whoseTurn].botMove(droids[whoseTurn], finishLine, 900, 560);

		} else {
			droids[whoseTurn].linearBotMove(droids[whoseTurn], finishLine);
		}

		// change the value of whose turn to
		if (whoseTurn == 3) {
			whoseTurn = 0;
		} else {
			whoseTurn++;
		}

	}
	
	/**
	 * Setting up scoreboard window
	 * @param frame
	 * @param title
	 * @param winningTeamTitle
	 * @param winningTeamName
	 * @param winningTeamTotalPts
	 * @param winningTeamPoints
	 * @param winPlayers
	 * @param winPlayersPoints
	 * @param otherTeam
	 * @param otherTeamName
	 * @param otherTeamTotalPts
	 * @param otherPlayersPoints
	 */
	public void scoreboardSetup(JFrame frame, TextPicture title, TextPicture
			winningTeamTitle, String winningTeamName,
			TextPicture winningTeamTotalPts, String winningTeamPoints,
			TextPicture winPlayers[], TextPicture winPlayersPoints[], TextPicture otherTeam, String otherTeamName, TextPicture otherTeamTotalPts, String otherTeamPoints,TextPicture otherPlayersPoints[]) {

		// setup the title
		title = new TextPicture("Scoreboard", 210, 0);
		GalaxyGame.setText(title, 210, 0, 50, 50, GalaxyGame.customFont(font, "Game Of Squids.ttf", 50f), Color.BLUE);

		// setup winning team title
		winningTeamTitle = new TextPicture("WINNER: "+winningTeamName, 5, 60);
		GalaxyGame.setText(winningTeamTitle, 5, 60, 500, 30,
				GalaxyGame.customFont(font, "Game Of Squids.ttf", 30f), Color.RED);

		//setup winning team total points
		winningTeamTotalPts = new TextPicture("Total Points: " + winningTeamPoints, 5, 95);
		GalaxyGame.setText(winningTeamTotalPts, 5, 95, 500, 30,
				GalaxyGame.customFont(font, "Game Of Squids.ttf", 30f), Color.RED);

		//setup player1 of winning team
		winPlayers[0] = new TextPicture("Player 1 Points:" + droids[0].getTotalStepsMoved(), 5, 115);
		GalaxyGame.setText(winPlayers[0], 5, 115, 500, 20,
				GalaxyGame.customFont(font, "Game Of Squids.ttf", 20f), Color.BLACK);

		//setup player2 of winning team
		winPlayers[1] = new TextPicture("Player 2 Points:" + droids[1].getTotalStepsMoved(), 5, 135);
		GalaxyGame.setText(winPlayers[1], 5, 135, 500, 20,
				GalaxyGame.customFont(font, "Game Of Squids.ttf", 20f), Color.BLACK);

		//>>>Next Team setup
		otherTeam = new TextPicture(otherTeamName, 5, 160);
		GalaxyGame.setText(otherTeam, 5, 160, 500, 30,
				GalaxyGame.customFont(font, "Game Of Squids.ttf", 30f), Color.RED);
		
		//setup otherteam total points
				otherTeamTotalPts = new TextPicture("Total Points: " + otherTeamPoints, 5, 195);
				GalaxyGame.setText(otherTeamTotalPts, 5, 195, 500, 30,
						GalaxyGame.customFont(font, "Game Of Squids.ttf", 30f), Color.RED);
		
		//setup player 1 
		otherPlayersPoints[0] = new TextPicture("Player 1 Points:" + winPlayersPoints[0], 5, 225);
		GalaxyGame.setText(otherPlayersPoints[0], 5, 225, 500, 20,
				GalaxyGame.customFont(font, "Game Of Squids.ttf", 20f), Color.BLACK);
		
		//setup player 2
		otherPlayersPoints[1] = new TextPicture("Player 1 Points:" + winPlayersPoints[0], 5, 245);
		GalaxyGame.setText(otherPlayersPoints[1], 5, 245, 500, 20,
				GalaxyGame.customFont(font, "Game Of Squids.ttf", 20f), Color.BLACK);

	}

	/**
	 * This method sets the coordinates, height, width, rgb and opacity of the
	 * JPanels
	 */
	public static void setJPanels(JPanel panel, int x, int y, int w, int h, int r, int g, int b, int a) {
		panel.setBounds(x, y, w, h); // set the coordinates and dimensions
		panel.setBackground(new Color(r, g, b, a)); // set the background colour
		panel.setLayout(null); // set layout to null
	}

	/**
	 * This method is for all action events
	 */
	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() == btnExits[0]) || (e.getSource() == btnExits[1]) || (e.getSource() == btnExits[2])
				|| (e.getSource() == btnExits[3]) || (e.getSource() == btnExits[4])) { // if any exit button is clicked
			System.exit(0); // exit
		} else if (e.getSource() == btnMenuMarv) { // if the menu button on the marvel selection window is clicked
			selectionMarv.setVisible(false); // close selection window
			themeMenu.setVisible(true); // open menu window

			// stop music from the selection window
			avengers.stopMusic();
			musicObject.playMusic("epic.wav"); // play theme music
		} else if (e.getSource() == btnMenuStar) { // if the menu button on the star wars selection window is clicked
			selectionStar.setVisible(false); // close selection window
			themeMenu.setVisible(true); // open menu window

			// stop music from the selection window
			starWars.stopMusic();
			musicObject.playMusic("epic.wav"); // play theme music
		} else if (e.getSource() == btnPlayMarv) { // if the play button on marvel selection is clicked
			// Get the selected indices from the drop down menus
			pathInd = (int) dropPathM.getSelectedIndex();
			indicesM[0] = (int) dropCharM.getSelectedIndex();
			indicesM[1] = (int) dropChar2M.getSelectedIndex();
			indicesM[2] = (int) dropChar3M.getSelectedIndex();
			indicesM[3] = (int) dropChar4M.getSelectedIndex();

			// Get the text from the text fields
			team1 = inpPlayer1M.getText();
			team2 = inpPlayer2M.getText();

			selectionMarv.setVisible(false);
			marvelGame.setVisible(true);

			for (int i = 0; i < droids.length; i++) {
				if (indicesM[i] == 0) { // if first index
					// Create and return the droid with the 1st character
					droids[i] = new Droid(new ImageIcon("capMarvel.png"), 200, 200);
				} else if (indicesM[i] == 1) { // if second index
					// Create and return the droid with the 2nd character
					droids[i] = new Droid(new ImageIcon("ironMan.png"), 200, 200);
				} else if (indicesM[i] == 2) { // if third index
					// Create and return the droid with the 3rd character
					droids[i] = new Droid(new ImageIcon("groot.png"), 200, 200);
				} else {
					// Create and return the droid with the 4th character
					droids[i] = new Droid(new ImageIcon("captainAmerica.png"), 200, 200);

				}
			}

			// marvelGame.add(droids[0]);

			gameWindow(marvelGame, txtTitleGame, gameMarv, droids, btnRestarts[1], btnExits[1], imgBgMarv,
					imgDice, d1, d2, txtSum, txtTurn, team1, team2, playerScores,
					Color.BLUE, indicesM, "ironMan.png", "captainAmerica.png", "capMarvel.png", "groot.png");

		} else if (e.getSource() == btnPlayStar) { // if the play button on star wars selection is clicked
			// Get the selected indices from the drop down menus
			pathInd = (int) dropPath.getSelectedIndex();
			indicesS[0] = (int) dropChar.getSelectedIndex();
			indicesS[1] = (int) dropChar2.getSelectedIndex();
			indicesS[2] = (int) dropChar3.getSelectedIndex();
			indicesS[3] = (int) dropChar4.getSelectedIndex();

			// Get the text from the text fields
			team1 = inpPlayer1.getText();
			team2 = inpPlayer2.getText();

			selectionStar.setVisible(false); // Set the selection star wars frame to invisible
			starWarsGame.setVisible(true);

//			if (pathInd == 0) { // If linear game is being played
//				// // Create all the text using TextPicture and setText
//				// txtTeam1 = new TextPicture(team1, 200, 50); // Team 1 title
//				// GalaxyGame.setText(txtTeam1, -175, -20, 500, 70, GalaxyGame.customFont(font,
//				// "Game Of Squids.ttf", 15f),
//				// Color.WHITE);
//				// txtTeam2 = new TextPicture(team2, 200, 50); // Team 2 title
//				// GalaxyGame.setText(txtTeam2, 525, -20, 500, 70, GalaxyGame.customFont(font,
//				// "Game Of Squids.ttf", 15f),
//				// Color.WHITE);
//				// txtTitleSL = new TextPicture("Linear Mode", 200, 50); // Frame title
//				// GalaxyGame.setText(txtTitleSL, -150, 525, 900, 70,
//				// GalaxyGame.customFont(font, "Game Of Squids.ttf", 35f), Color.YELLOW);
//
//				// // Create all the images using ImagePicture and set their
//				// dimensions/positions
//				// imgDeathStar = new ImagePicture(new ImageIcon("death star (1).png")); //
//				// Death Star image
//				// imgDeathStar.setBounds(860, 250, 100, 100);
//				// imgBackSL = new ImagePicture(new ImageIcon("starry.jpg")); // Background
//				// image
//				// imgBackSL.setBounds(0, 0, 1000, 1000);
//
//				// // Add all the components to the linear star wars game frame
//				// starWarsGame.add(droids[0]);
//				// linearGame.add(txtTeam1);
//				// linearGameS.add(txtTeam2);
//				// linearGameS.add(imgDeathStar);
//				// linearGameS.add(txtTitleSL);
//				// linearGameS.add(imgBackSL);
//
//			} else if (pathInd == 1) { // If the box game is being played
//
//			}
		} else if (e.getSource() == btnMenus[0]) { // If menu button on star wars game
			starWarsGame.setVisible(false); // close star wars game
			themeMenu.setVisible(true); // open themeMenu
			starWars.stopMusic(); // stop star wars music
			musicObject.playMusic("epic.wav"); // play intense music
		} else if (e.getSource() == btnMenus[1]) { // If menu button on marvel game
			marvelGame.setVisible(false); // close marvel game
			themeMenu.setVisible(true); // open themeMenu
			avengers.stopMusic(); // stop avengers music
			musicObject.playMusic("epic.wav"); // play intense music
		}
	}

	/**
	 * This method sets the characters of each of the player selections, it
	 * returns
	 * a droid object
	 */
	public Droid setCharacters(int index, int x, int y, String image, String image2, String image3, String image4) {
		Droid droid; // Declare a droid object
		if (index == 0) { // if first index
			// Create and return the droid with the 1st character
			droid = new Droid(new ImageIcon(image), x, y);
			return droid;
		} else if (index == 1) { // if second index
			// Create and return the droid with the 2nd character
			droid = new Droid(new ImageIcon(image2), x, y);
			return droid;
		} else if (index == 2) { // if third index
			// Create and return the droid with the 3rd character
			droid = new Droid(new ImageIcon(image3), x, y);
			return droid;
		}
		// Create and return the droid with the 4th character
		droid = new Droid(new ImageIcon(image4), x, y);
		return droid;
	}

	/*
	 * This method sets the main characteristics of the JFrame including:
	 * default close operation, size, layout, visibility, and location
	 */
	public static void setJFrame(JFrame name, int height, int width, boolean visible) {
		name.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		name.setSize(width, height); // Set the size of the frame
		name.setLayout(null); // Set layout to null
		name.setVisible(visible); // Set visibility
		name.setLocationRelativeTo(null); // Set the location of the frame to the center of screen
	}

	/*
	 * This method sets the length/width/coordinates and font of the buttons
	 */
	public static void setButtons(JButton name, int x, int y, int width, int height, Font font) {
		name.setBounds(x, y, width, height); // Set length/width/coordinates
		name.setFont(font); // set font
	}

	/*
	 * This method sets the length/width/coordinates, font, and colour of text
	 */
	public static void setText(TextPicture name, int x, int y, int width, int height, Font font, Color colour) {
		name.setBounds(x, y, width, height); // Set length/width/coordinates
		name.setC(colour); // set colour
		name.setFont(font); // set font
	}

	/*
	 * Method to create and set custom fonts
	 */
	public static Font customFont(Font f, String font, float size) {
		// https://youtu.be/43duJsYmhxQ
		try { // try for errors
			// Create and derive font
			f = Font.createFont(Font.TRUETYPE_FONT, new File(font)).deriveFont(size);

			// Register the font in the graphics environment
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(font)));

		} catch (IOException e) { // catch for IOException
			e.printStackTrace();
		} catch (FontFormatException e) { // catch for FontFormatException
			e.printStackTrace();
		}

		return f; // return the font
	}

	/**
	 * @param args
	 *             Main Method
	 */
	public static void main(String[] args) {
		GalaxyGame game = new GalaxyGame(); // Call my constructor

	}

}
