import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Word extends Sprite implements Runnable {
	//variables
	private Boolean moving, active, WordHit, WordComplete, WordNotComplete, longWord;
	private int WordPoints;
	private JLabel WordLabel, scoreLabel, livesLabel, WordTypedLabel;

	private Head Head;
	private Player Player;
	private JLabel HeadLabel, PlayerLabel;
	private JButton playButton;
	private MovingWords movingWords;
	private Thread t;

	//getters 
	public Boolean getMoving() {
		return moving;
	}
	public Boolean getActive() { 
		return active; 
	}
	public Boolean getWordHit() {
		return WordHit;
	}
	public Boolean getWordComplete() {
		return WordComplete;
	}
	public Boolean getWordNotComplete() {
		return WordNotComplete;
	}
	public Boolean getLongWord() {
		return longWord;
	}
	public int getWordPoints() {
		return WordPoints;
	}
	public JLabel getScoreLabel() {
		return scoreLabel;
	}
	public JLabel getLivesLabel() {
		return livesLabel;
	}
	public JLabel getWordTypedLabel() {
		return WordTypedLabel;
	}
	
	//setters
	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	public void setActive(Boolean temp) { 
		this.active = temp; 
	}	
	public void setWordHit(Boolean temp) {
		this.WordHit = temp;
	}
	public void setWordComplete(Boolean temp) {
		this.WordComplete = temp;
	}
	public void setWordNotComplete(Boolean temp) {
		this.WordNotComplete = temp;
	}
	public void setLongWord(Boolean temp) {
        this.longWord = temp;
    }
	public void setWordPoints(int temp) {
		this.WordPoints = temp;
	}
	public void setWordLabel(JLabel temp) {
		this.WordLabel = temp;
	}
	public void setScoreLabel(JLabel temp) {
		this.scoreLabel = temp;
	}
	public void setLivesLabel(JLabel temp) {
		this.livesLabel = temp;
	}
	public void setWordTypedLabel(JLabel temp) {
		this.WordTypedLabel = temp;
	}

	public void setHead(Head temp) {
		this.Head = temp;
	}
	public void setHeadLabel(JLabel temp) {
		this.HeadLabel = temp;
	}
	public void setPlayer(Player temp) {
		this.Player = temp;
	}
	public void setPlayerLabel(JLabel temp) {
		this.PlayerLabel = temp;
	}
	public void setPlayButton(JButton temp) {
		this.playButton = temp;
	}
	public void setMovingWords (MovingWords temp) {
		this.movingWords = temp;
	}
	
	//constructors
	public Word() {
		super("",0,0,100,100);
		this.moving = false;
		this.active = false;
		this.WordHit = false;
		this.WordComplete = false;
		this.WordNotComplete = false;
		this.longWord = false;
		this.WordPoints = 0;
	}

	public Word(int i) {
		super("",0,0,100,100);
		this.moving = false;
		this.active = false;
		this.WordHit = false;
		this.WordComplete = false;
		this.WordNotComplete = false;
		this.longWord = false;
		this.WordPoints = 0;
		//set the graphic name
		switch(i) {
			case 0: this.setSpriteName("pictures/java.png"); break;
			case 1: this.setSpriteName("pictures/word.png"); break;
			case 2: this.setSpriteName("pictures/rain.png"); break;
			case 3: this.setSpriteName("pictures/rock.png"); break;
			case 4: this.setSpriteName("pictures/exam.png"); break;
			case 5: this.setSpriteName("pictures/luck.png"); break;
			case 6: this.setSpriteName("pictures/yoyo.png"); break;
			case 7: this.setSpriteName("pictures/quack.png"); break;
			case 8: this.setSpriteName("pictures/crown.png"); break;
			case 9: this.setSpriteName("pictures/water.png"); break;
			case 10: this.setSpriteName("pictures/major.png"); break;
			case 11: this.setSpriteName("pictures/fruit.png"); break;
			case 12: this.setSpriteName("pictures/vixen.png"); break;
			case 13: this.setSpriteName("pictures/jacket.png"); break;
			case 14: this.setSpriteName("pictures/letter.png"); break;
			case 15: this.setSpriteName("pictures/yonder.png"); break;
			case 16: this.setSpriteName("pictures/cheque.png"); break;
			case 17: this.setSpriteName("pictures/orange.png"); break;
			case 18: this.setSpriteName("pictures/analyst.png"); break;
			case 19: this.setSpriteName("pictures/society.png"); break;
			case 20: this.setSpriteName("pictures/upscale.png"); break;
			case 21: this.setSpriteName("pictures/virtual.png"); break;
			case 22: this.setSpriteName("pictures/long.png"); break;
			default: System.out.println(""); break;
		}
	}
	
	//display function
	public void Display() {
		System.out.println("X,Y: " +spriteX+ ", " +spriteY+ "moving: " +moving+ "act:" +active+ "hit: " +WordHit+ "comp: " +WordComplete);
	}
	
	//stop & start functions
	public void stopWords() {
		this.moving = false;
		this.spriteY = 1000;
	}
	public void startWords() {
		this.moving = true;
		t = new Thread (this, "Word");
		t.start();
	}

	//run function
	public void run() {	
		Player.setSpriteName("pictures/player.png");
		PlayerLabel.setIcon(new ImageIcon(getClass().getResource(Player.getSpriteName())));

		while (moving) {
			int wY = this.spriteY;
			
			//move the words
			wY += WordRainProperties.CHARACTER_STEP - 5;

			//if word doesn't hit player or head send it away
			if (wY > WordRainProperties.SCREEN_HEIGHT) {
				wY = 1000;
				movingWords.setTypedWord("");
			} 
			//set the word location
			this.setSpriteY(wY);
			WordLabel.setLocation(this.spriteX, this.spriteY);
			
			//detect collision w/ player or head
			Rectangle rPlayer = Player.getRectangle();
			Rectangle rHead = Head.getRectangle();
			Rectangle rWord = this.r; 
			if (rPlayer.intersects(rWord) || rHead.intersects(rWord)) {
				//get rid of word
				wY = 1000;
				this.setSpriteY(wY);
				WordLabel.setLocation(this.spriteX, this.spriteY);
				WordLabel.setVisible(false);
				
				//change head to next word in list
				this.WordHit = true;
			} 
			
			//pause
			try {
				Thread.sleep(200);
			} catch (Exception except) {
				
			}
		}
	}

}