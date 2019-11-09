import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Random;

public class WordRain extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	//declare objects	
	private Head Head;
	private Player Player;
	private Word[] Words;
	private int[] WordNumber;
	private MovingWords movingWords;

	//labels
	private JLabel HeadLabel, PlayerLabel, scoreLabel, livesLabel, WordTypedLabel, instructionsLabel, lostLabel, wonLabel;
	private JLabel[] WordLabels;

	//image icons	
	private ImageIcon HeadImage, PlayerImage;
	private ImageIcon[] WordImages;
	
	//buttons, dialogs, & textarea
	private JButton playButton, gotItButton, submitButton;
	private JDialog instructionsDialog, lostDialog, wonDialog;
	private JTextField nameTextField;

	//container
	private Container content;
	
	public WordRain() { 
		//initialize variables
		Words = new Word[23];
		WordLabels = new JLabel[23];
		WordImages = new ImageIcon[23];
		WordNumber = new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22};
		//randomize WordNumber array
		Random random = new Random();
		for (int i = 0; i < WordNumber.length; i++) {
			int randomIndexToSwap = random.nextInt(WordNumber.length);
			int temp = WordNumber[randomIndexToSwap];
			WordNumber[randomIndexToSwap] = WordNumber[i];
			WordNumber[i] = temp;
		}

		Head = new Head();
		Player = new Player();
		
		HeadLabel = new JLabel();
		PlayerLabel = new JLabel();
		scoreLabel = new JLabel();
		livesLabel = new JLabel();
		WordTypedLabel = new JLabel();
		instructionsLabel = new JLabel();
		lostLabel = new JLabel();
		wonLabel = new JLabel();

		HeadImage = new ImageIcon(getClass().getResource(Head.getSpriteName()));
		PlayerImage = new ImageIcon(getClass().getResource(Player.getSpriteName()));
		
		playButton = new JButton();
		gotItButton = new JButton();
		submitButton = new JButton();
		instructionsDialog = new JDialog();
		lostDialog = new JDialog();
		wonDialog = new JDialog();
		nameTextField = new JTextField();

		movingWords =  new MovingWords();
		content = getContentPane();
		
		//initialize the array of words
		for (int i=0;i<Words.length;i++) {
			
			Words[i] = new Word(i);
			WordLabels[i] = new JLabel();
			WordImages[i] = new ImageIcon(getClass().getResource(Words[i].getSpriteName()));

			Words[i].setWordLabel(WordLabels[i]);
			Words[i].setHead(Head);
			Words[i].setHeadLabel(HeadLabel);
			Words[i].setPlayer(Player);
			Words[i].setPlayerLabel(PlayerLabel);
			Words[i].setPlayButton(playButton);
			Words[i].setScoreLabel(scoreLabel);
			Words[i].setLivesLabel(livesLabel);
			Words[i].setWordTypedLabel(WordTypedLabel);
			Words[i].setMovingWords(movingWords);
			
			//set width for long.png
			if (i==22) {
				Words[i].setLongWord(true); 
				Words[i].setSpriteW(500);
			}

			Words[i].setSpriteY(-200);
			WordLabels[i].setIcon(WordImages[i]);
			WordLabels[i].setSize(Words[i].getSpriteW(), Words[i].getSpriteH());
			WordLabels[i].setLocation(Words[i].getSpriteX(), Words[i].getSpriteY());

			//set the points for each word (based on the number of letters in the word)
			switch (i) {
				case 0:case 1:case 2:case 3:case 4:case 5:
				case 6: Words[i].setWordPoints(20); break;
				case 7:case 8:case 9:case 10:case 11:
				case 12: Words[i].setWordPoints(50); break;
				case 13:case 14:case 16:
				case 17: Words[i].setWordPoints(60); break;
				case 18:case 19:case 20:
				case 21: Words[i].setWordPoints(70); break;
				case 22: Words[i].setWordPoints(100); break;
				default: Words[i].setWordPoints(0); break;
			}

			Words[i].setMoving(false);
			WordLabels[i].setVisible(false);
			add(WordLabels[i]);
		}

		//gui
		setSize(WordRainProperties.SCREEN_WIDTH, WordRainProperties.SCREEN_HEIGHT);
		content.setBackground(Color.gray);
		setLayout(null);
		
		//head
		Head.setHeadLabel(HeadLabel);
		Head.setSpriteX(320);
		Head.setSpriteY(420);
		HeadLabel.setIcon(HeadImage);
		HeadLabel.setSize(Head.getSpriteW(), Head.getSpriteH());
		HeadLabel.setLocation(Head.getSpriteX(), Head.getSpriteY());
		add(HeadLabel);
		
		//player
		Player.setPlayerLabel(PlayerLabel);
		Player.setSpriteX(320);
		Player.setSpriteY(480);
		PlayerLabel.setIcon(PlayerImage);
		PlayerLabel.setSize(Player.getSpriteW(), Player.getSpriteH());
		PlayerLabel.setLocation(Player.getSpriteX(), Player.getSpriteY());
		add(PlayerLabel);

		//score 
		scoreLabel.setLocation(WordRainProperties.SCREEN_WIDTH-750, WordRainProperties.SCREEN_HEIGHT-620);
		scoreLabel.setSize(70,20);
		scoreLabel.setBackground(Color.white);
		scoreLabel.setOpaque(true);
		scoreLabel.setText("Score:");
		add(scoreLabel);

		//lives
		livesLabel.setLocation(WordRainProperties.SCREEN_WIDTH-675, WordRainProperties.SCREEN_HEIGHT-620);
		livesLabel.setSize(80,20);
		livesLabel.setIcon(new ImageIcon(getClass().getResource("pictures/lives3.png")));
		add(livesLabel);

		//WordTyped 
		WordTypedLabel.setLocation(WordRainProperties.SCREEN_WIDTH-750, WordRainProperties.SCREEN_HEIGHT-60);
		WordTypedLabel.setSize(WordRainProperties.SCREEN_WIDTH,WordRainProperties.SCREEN_HEIGHT-600);
		WordTypedLabel.setBackground(Color.white);
		WordTypedLabel.setOpaque(true);
		WordTypedLabel.setText("Typed: ");
		add(WordTypedLabel);

		//buttons
		playButton.setLocation(WordRainProperties.SCREEN_WIDTH-100, WordRainProperties.SCREEN_HEIGHT-620);
		playButton.setSize(85,20);
		playButton.setText("Start");
		playButton.addActionListener(this);
		playButton.setFocusable(false);
		add(playButton);
		
		gotItButton.setSize(70,20);
		gotItButton.setLocation(WordRainProperties.SCREEN_WIDTH-690,WordRainProperties.SCREEN_HEIGHT-540);
		gotItButton.setText("Got It");
		gotItButton.addActionListener(this);
		gotItButton.setFocusable(false);

		submitButton.setSize(80,20);
		submitButton.setLocation(WordRainProperties.SCREEN_WIDTH-700,WordRainProperties.SCREEN_HEIGHT-550);
		submitButton.setText("Submit");
		submitButton.addActionListener(this);
		submitButton.setFocusable(false);

		//instructions 
		instructionsLabel.setSize(10,10);
		instructionsLabel.setText("<html><body>Type in the word on the head </br>of the player before that word hits the bottom.<br/> Avoid falling words.</body></html>");
		instructionsLabel.setVerticalAlignment(instructionsLabel.NORTH);
		
		instructionsDialog.setTitle("Instructions");
		instructionsDialog.setModal(true);
		instructionsDialog.setBounds(200,200,200,150);
		instructionsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		instructionsDialog.add(gotItButton);
		instructionsDialog.add(instructionsLabel);
		instructionsDialog.setVisible(true);
		
		//lost 
		lostLabel.setSize(20,20);
		lostLabel.setText("<html><body>You Lost! Try Again! Score: </body></html>");

		lostDialog.setTitle("You Lost!");
		lostDialog.setModal(true);
		lostDialog.setBounds(200,200,100,100);
		lostDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		lostDialog.add(lostLabel);
		lostDialog.setVisible(false);

		//won
		nameTextField.setBounds(40,40,100,30);
		wonLabel.setSize(20,20);
		wonLabel.setText("<html><body>You Won! Score: <br/>Enter your name: </body></html>");
		wonLabel.setVerticalAlignment(wonLabel.NORTH);
		wonLabel.setHorizontalAlignment(wonLabel.CENTER);
		
		wonDialog.setTitle("You Won!");
		wonDialog.setModal(true);
		wonDialog.setBounds(200,200,200,150);
		wonDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		wonDialog.add(nameTextField);
		wonDialog.add(submitButton);
		wonDialog.add(wonLabel);
		wonDialog.setVisible(false);

		//movingWords
		movingWords.setWords(Words);
		movingWords.setWordLabels(WordLabels);
		movingWords.setHead(Head);
		movingWords.setHeadLabel(HeadLabel);
		movingWords.setWordNumber(WordNumber);
		movingWords.setLostDialog(lostDialog);
		movingWords.setLostLabel(lostLabel);
		movingWords.setWonDialog(wonDialog);
		movingWords.setWonLabel(wonLabel);	

		content.addKeyListener(this);
		content.setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String [] args) {
		WordRain wordRain = new WordRain();
		wordRain.setVisible(true);
	}

	//displays records in database
	public static void DisplayRecords(ResultSet rs) throws SQLException {
		while ( rs.next() ) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int score = rs.getInt("score");
			
			System.out.println("id: " + id);
			System.out.println("name: " + name);
			System.out.println("score: " + score);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		//play button
		if (e.getSource() == playButton) {
			movingWords.startMovingWords();
			playButton.setVisible(false);
		} else if (e.getSource() == gotItButton) {
			//instructions gotItButton
			instructionsDialog.dispose();
		} else if (e.getSource() == submitButton) {
			//submit name & score button
			Connection conn = null;
			Statement stmt = null;
			
			try {
				//load the DB driver
				Class.forName("org.sqlite.JDBC");
				String dbURL = "jdbc:sqlite:product.db";
				conn = DriverManager.getConnection(dbURL);
				if (conn != null) {
					System.out.println("Connection established");
					
					conn.setAutoCommit(false);
					DatabaseMetaData dm = (DatabaseMetaData)conn.getMetaData();
					System.out.println("Driver Name: " + dm.getDriverName());
					System.out.println("Driver version: " + dm.getDriverVersion());
					System.out.println("Product Name: " + dm.getDatabaseProductName());
					System.out.println("Product verison: " + dm.getDatabaseProductVersion());
					
					stmt = conn.createStatement();
					String sql = "";
					ResultSet rs = null;
					
					//create table
					sql = "CREATE TABLE IF NOT EXISTS players ("+ 
						  "id INTEGER PRIMARY KEY, " +
						  "name TEXT NOT NULL, " + 
						  "score INT NOT NULL " + ")";
					stmt.executeUpdate(sql);
					conn.commit();
					
					//insert name and score
					String name = nameTextField.getText();
					int finalScore = movingWords.getScore();
					sql = "INSERT INTO players (name, score) VALUES ('" +
					       name + "', " + finalScore +  ")";
					stmt.executeUpdate(sql);
					conn.commit();
	
					//retrieve id, name, & score
					sql = "SELECT * FROM players";
					rs = stmt.executeQuery(sql);
					System.out.println("Past player scores: ");
					DisplayRecords(rs);
					rs.close();
									
					conn.close();
				} else {
					System.out.println("Cannot establish connection");
				}
							
			} catch (ClassNotFoundException err) {
				err.printStackTrace();
			} catch (SQLException err) {
				err.printStackTrace();
			} catch (Exception err) {
				err.printStackTrace();
			} finally {
				//cleanup
			}
			System.exit(ABORT);
		}

	}
	
	//move the player & head left or right if the game has started
	public void keyPressed(KeyEvent e) {
		if (!playButton.isVisible()) {
			Head.moveHead(e);
			Player.movePlayer(e);
		}
		movingWords.typingWords(e);
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
