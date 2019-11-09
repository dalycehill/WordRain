import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class MovingWords implements Runnable {
    //variables
    private Boolean gameRunning, leftWord, middleWord, rightWord;    
    private int leftX, middleX, rightX, score, leftDistance, middleDistance, rightDistance, lives;
    private String typedWord, currentWord;
    private JDialog lostDialog, wonDialog;

    private Head Head;
    private JLabel HeadLabel, lostLabel, wonLabel;
    private Word Words[];
    private JLabel WordLabels[];
    private int WordNumber[];
    private Thread t;

    //getters 
    public Boolean getGameRunning() { 
        return gameRunning; 
    }
    public Boolean getLeftWord() { 
        return leftWord; 
    }
    public Boolean getMiddleWord() { 
        return middleWord; 
    }
    public Boolean getRightWord() { 
        return rightWord; 
    }
    public int getLeftX() {
        return leftX;
    } 
    public int getMiddleX() {
        return middleX;
    } 
    public int getRightX() {
        return rightX;
    }
    public int getScore() {
        return score;
    }
    public int getLeftDistance() {
        return leftDistance;
    }
    public int getMiddleDistance() {
        return middleDistance;
    }
    public int getRightDistance() {
        return rightDistance;
    }
    public int getLives() {
		return lives;
	}
    public String getTypedWord() { 
        return typedWord; 
    }
    public String getCurrentWord() {
        return currentWord;
    }

    //setters
    public void setGameRunning(Boolean temp) { 
        this.gameRunning = temp; 
    }
    public void setLeftWord(Boolean temp) { 
        this.leftWord = temp; 
    }
    public void setMiddleWord(Boolean temp) { 
        this.middleWord = temp; 
    }
    public void setRightWord(Boolean temp) { 
        this.rightWord = temp; 
    }  
    public void setLeftX(int temp) {
		this.leftX = temp;
    }
    public void setMiddleX(int temp) {
		this.middleX = temp;
    }
    public void setRightX(int temp) {
		this.rightX = temp;
    }
    public void setScore(int temp) {
		this.score = temp;
    }
    public void setLeftDistance(int temp) {
		this.leftDistance = temp;
    }
    public void setMiddleDistance(int temp) {
		this.middleDistance = temp;
    }
    public void setRightDistance(int temp) {
		this.rightDistance = temp;
    }
    public void setLives(int temp) {
		this.lives = temp;
	}
    public void setTypedWord (String temp) { 
        this.typedWord = temp; 
    }
    public void setCurrentWord (String temp) { 
        this.currentWord = temp; 
    }

    public void setHead(Head temp) {
		this.Head = temp;
	}
	public void setHeadLabel(JLabel temp) {
		this.HeadLabel = temp;
    }
    public void setWords (Word[] temp) {
    	this.Words = temp;
    }
    public void setWordLabels (JLabel[] temp) {
    	this.WordLabels = temp;
    }
    public void setWordNumber(int[] temp) {
        this.WordNumber = temp;
    }
    public void setLostDialog(JDialog temp) {
        this.lostDialog = temp;
    }
    public void setWonDialog(JDialog temp) {
        this.wonDialog = temp;
    }
    public void setLostLabel(JLabel temp) {
        this.lostLabel = temp;
    }
    public void setWonLabel(JLabel temp) {
        this.wonLabel = temp;
    }
    
    //constructor
    public MovingWords() {
    	this.gameRunning = false;
    	this.leftWord = false;
    	this.middleWord = false;
    	this.rightWord = false; 
    	this.leftX = 120;
    	this.middleX = 350;
    	this.rightX = 570; 
    	this.score = 0;
    	this.leftDistance = 0;
    	this. middleDistance = 0;
    	this.rightDistance = 0;
    	this.lives = 3;
    	this.typedWord = "";  
    	this.currentWord = "";
    }

    //stop & start functions
    public void stopMovingWords() {
    	this.gameRunning = false;
	}
    public void startMovingWords() {
    	this.gameRunning = true;
		t = new Thread (this, "Next Word");
		t.start();    	
    }
    
    //typing words function
	public void typingWords(KeyEvent e) {
        
        //if java, jacket, major... add j
		if (e.getKeyCode() == KeyEvent.VK_J && ( typedWord.equals("") && (currentWord.equals("java") || currentWord.equals("jacket")) || (currentWord.equals("major") && typedWord.equals("MA")) ) ) {
                typedWord = typedWord + "J";
        }
        
        //if java, jacket, water, rain, major, exam, quack, virtual, orange, upscale, analyst, or pneumonoultramicroscopicsilicovolcanoconiosis... add a
        if ( e.getKeyCode() == KeyEvent.VK_A && ( (typedWord.equals("") && currentWord.equals("analyst")) || 
        
        ((currentWord.equals("java") || currentWord.equals("jacket")) && (typedWord.equals("JAV") || typedWord.equals("J"))) ||

        (currentWord.equals("water") && typedWord.equals("W")) || (currentWord.equals("rain") && typedWord.equals("R")) ||

        (currentWord.equals("major") && typedWord.equals("M")) || (currentWord.equals("exam") && typedWord.equals("EX")) ||

        (currentWord.equals("quack") && typedWord.equals("QU")) || (currentWord.equals("virtual") && typedWord.equals("VIRTU")) ||

        (currentWord.equals("orange") && typedWord.equals("OR")) || (currentWord.equals("upscale") && typedWord.equals("UPSC")) ||
        
        (currentWord.equals("analyst") && typedWord.equals("AN")) || (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && (typedWord.equals("PNEUMONOULTR") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOLC"))) ) ) {
            typedWord = typedWord + "A";
        }

		//if java, vixen, virtual, or pneumonoultramicroscopicsilicovolcanoconiosis... add v
		if ( e.getKeyCode() == KeyEvent.VK_V && ( (typedWord.equals("") && (currentWord.equals("vixen") || currentWord.equals("virtual"))) || 
		
		(currentWord.equals("java") && typedWord.equals("JA")) || (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICO")) ) ) {
            typedWord = typedWord + "V";
		}

        //if word, water, or crown... add w
		if ( e.getKeyCode() == KeyEvent.VK_W && ( (typedWord.equals("") && currentWord.equals("word") || currentWord.equals("water")) || (currentWord.equals("crown") && typedWord.equals("CRO")) ) ) {
            typedWord = typedWord + "W";
		}

        //if word, rock, yoyo, yonder, major, crown, orange, society, or pneumonoultramicroscopicsilicovolcanoconiosis... add o 
        if ( e.getKeyCode() == KeyEvent.VK_O && ( (typedWord.equals("") && currentWord.equals("orange")) || 

        (currentWord.equals("word") && typedWord.equals("W")) || (currentWord.equals("rock") && typedWord.equals("R")) || 
        
        ((currentWord.equals("yoyo") || currentWord.equals("yonder")) && (typedWord.equals("Y") || typedWord.equals("YOY"))) || 

        (currentWord.equals("major") && typedWord.equals("MAJ")) || (currentWord.equals("crown") && typedWord.equals("CR")) || (currentWord.equals("society") && typedWord.equals("S")) ||
        
        (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && (typedWord.equals("PNEUM") || typedWord.equals("PNEUMON") || typedWord.equals("PNEUMONOULTRAMICR") || 
        		
        typedWord.equals("PNEUMONOULTRAMICROSC") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILIC") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOV") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOLCAN") || 
        		
        typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOLCANOC") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOLCANOCONI"))) ) ) {
            typedWord = typedWord + "O";
		}

        //if word, rock, rain, water, letter, yonder, crown, major, fruit, virtual, orange, or pneumonoultramicroscopicsilicovolcanoconiosis... add r
        if ( e.getKeyCode() == KeyEvent.VK_R && ( (typedWord.equals("") && (currentWord.equals("rock") || currentWord.equals("rain"))) || 

        (currentWord.equals("word") && typedWord.equals("WO")) || (currentWord.equals("water") && typedWord.equals("WATE")) ||

        (currentWord.equals("letter") && typedWord.equals("LETTE")) || (currentWord.equals("yonder") && typedWord.equals("YONDE")) || 
        
        (currentWord.equals("crown") && typedWord.equals("C")) || (currentWord.equals("major") && typedWord.equals("MAJO")) || 

        (currentWord.equals("fruit") && typedWord.equals("F")) || (currentWord.equals("virtual") && typedWord.equals("VI")) || 
        
        (currentWord.equals("orange") && typedWord.equals("O")) || (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && (typedWord.equals("PNEUMONOULT") || typedWord.equals("PNEUMONOULTRAMIC"))) ) ) {
            typedWord = typedWord + "R";
        }
        
        //if word or yonder... add d
        if ( e.getKeyCode() == KeyEvent.VK_D && ( (currentWord.equals("word") && typedWord.equals("WOR")) || (currentWord.equals("yonder") && typedWord.equals("YON")) ) ) {
            typedWord = typedWord + "D";
        }
        
        //if rock, luck, quack, society, upscale, jacket, crown, cheque, or pneumonoultramicroscopicsilicovolcanoconiosis... add c
        if ( e.getKeyCode() == KeyEvent.VK_C && ( (typedWord.equals("") && currentWord.equals("yonder")) ||

        (currentWord.equals("rock") && typedWord.equals("RO")) || (currentWord.equals("luck") && typedWord.equals("LU")) || 
        
        (currentWord.equals("quack") && typedWord.equals("QUA")) || (currentWord.equals("society") && typedWord.equals("SO")) || 
        
        (currentWord.equals("upscale") && typedWord.equals("UPS")) || (currentWord.equals("jacket") && typedWord.equals("JA")) || 
        
        (currentWord.equals("crown") && typedWord.equals("")) || (currentWord.equals("cheque") && typedWord.equals("")) || 
        
        (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && (typedWord.equals("PNEUMONOULTRAMI") || 
        		
        typedWord.equals("PNEUMONOULTRAMICROS") || typedWord.equals("PNEUMONOULTRAMICROSCOPI") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILI") || 
        
        typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOL") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOLCANO"))) ) ) {
            typedWord = typedWord + "C";
		}

        //if rock, jacket, luck, or quack... add k
        if ( e.getKeyCode() == KeyEvent.VK_K && ( (currentWord.equals("rock") && typedWord.equals("ROC")) || 
        
        (currentWord.equals("jacket") && typedWord.equals("JAC")) || (currentWord.equals("luck") && typedWord.equals("LUC")) || 
        
        (currentWord.equals("quack") && typedWord.equals("QUAC")) ) ) {
            typedWord = typedWord + "K";
		}

        //if rain, fruit, society, vixen, virtual, or pneumonoultramicroscopicsilicovolcanoconiosis... add i
        if ( e.getKeyCode() == KeyEvent.VK_I && ( (currentWord.equals("rain") && typedWord.equals("RA")) || 
    
        (currentWord.equals("fruit") && typedWord.equals("FRU")) || (currentWord.equals("society") && typedWord.equals("SOC")) || 
        
        (typedWord.equals("V") && (currentWord.equals("vixen") || currentWord.equals("virtual"))) || 
        
        (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && (typedWord.equals("PNEUMONOULTRAM") || typedWord.equals("PNEUMONOULTRAMICROSCOP") || 
        		
        typedWord.equals("PNEUMONOULTRAMICROSCOPICS") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSIL") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOLCANOCON") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOLCANOCONIOS"))) ) ) {
            typedWord = typedWord + "I";
		}

        //if rain, crown, yonder, orange, analyst, vixen, or pneumonoultramicroscopicsilicovolcanoconiosis... add n
        if ( e.getKeyCode() == KeyEvent.VK_N && ( (currentWord.equals("rain") && typedWord.equals("RAI")) ||

        (currentWord.equals("crown") && typedWord.equals("CROW")) || (currentWord.equals("yonder") && typedWord.equals("YO")) || 
        
        (currentWord.equals("orange") && typedWord.equals("ORA")) || (currentWord.equals("analyst") && typedWord.equals("A")) || 
        
        (currentWord.equals("vixen") && typedWord.equals("VIXE")) || 
        
        (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && (typedWord.equals("P") || typedWord.equals("PNEUMO")) || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOLCA") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOLCANOCO"))) ) {
            typedWord = typedWord + "N";
        }
        
        //if water, letter, fruit, virtual, society, jacket, analyst, or pneumonoultramicroscopicsilicovolcanoconiosis... add t
        if (e.getKeyCode() == KeyEvent.VK_T && ( (currentWord.equals("water") && typedWord.equals("WA")) || 
        
        (currentWord.equals("letter") && (typedWord.equals("LE") || typedWord.equals("LET"))) || (currentWord.equals("fruit") && typedWord.equals("FRUI")) || 
        
        (currentWord.equals("virtual") && typedWord.equals("VIR")) || (currentWord.equals("society") && typedWord.equals("SOCIE")) || 
        
        (currentWord.equals("jacket") && typedWord.equals("JACKE")) || (currentWord.equals("analyst") && typedWord.equals("ANALYS")) || (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && typedWord.equals("PNEUMONOUL")) ) ) {
            typedWord = typedWord + "T";
		}

        //if exam, water, letter, yonder, cheque, vixen, orange, society, upscale, jacket, or pneumonoultramicroscopicsilicovolcanoconiosis... add e 
        if (e.getKeyCode() == KeyEvent.VK_E && ( (currentWord.equals("exam") && typedWord.equals("")) || (currentWord.equals("water") && typedWord.equals("WAT")) || 
        
        (currentWord.equals("letter") && (typedWord.equals("LETT") || typedWord.equals("L"))) || (currentWord.equals("yonder") && typedWord.equals("YOND")) || 
        
        (currentWord.equals("cheque") && (typedWord.equals("CH") || typedWord.equals("CHEQU"))) || (currentWord.equals("vixen") && typedWord.equals("VIX")) || 
        
        (currentWord.equals("orange") && typedWord.equals("ORANG")) || (currentWord.equals("society") && typedWord.equals("SOCI")) || 
        
        (currentWord.equals("upscale") && typedWord.equals("UPSCAL")) || (currentWord.equals("jacket") && typedWord.equals("JACK")) || 
        
        (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && typedWord.equals("PN")) ) ) {
            typedWord = typedWord + "E";                
		}

        //if exam or vixen... add x
        if (e.getKeyCode() == KeyEvent.VK_X && ( (currentWord.equals("exam") && typedWord.equals("E")) || (currentWord.equals("vixen") && typedWord.equals("VI")) ) ) {
            typedWord = typedWord + "X";                
		}

        //if exam, major, or pneumonoultramicroscopicsilicovolcanoconiosis... add m
		if (e.getKeyCode() == KeyEvent.VK_M && ( (currentWord.equals("exam") && typedWord.equals("EXA")) || (currentWord.equals("major") && typedWord.equals("")) ||
		(currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && (typedWord.equals("PNEU") || typedWord.equals("PNEUMONOULTRA"))) ) ) {
            typedWord = typedWord + "M";                
		}

        //if luck, letter, virtual, analyst, upscale, or pneumonoultramicroscopicsilicovolcanoconiosis... add l 
        if (e.getKeyCode() == KeyEvent.VK_L && ( ((currentWord.equals("luck") || currentWord.equals("letter")) && typedWord.equals("")) || (currentWord.equals("virtual") && typedWord.equals("VIRTUA")) ||
        
        (currentWord.equals("analyst") && typedWord.equals("ANA")) || (currentWord.equals("upscale") && typedWord.equals("UPSCA")) || 
        
        (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && (typedWord.equals("PNEUMONOU") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSI") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVO"))) ) ) {
            typedWord = typedWord + "L";                
        }
        
        //if luck, quack, cheque, virtual, fruit, upscale, or pneumonoultramicroscopicsilicovolcanoconiosis... add u
        if (e.getKeyCode() == KeyEvent.VK_U && ( (currentWord.equals("luck") && typedWord.equals("L")) || (currentWord.equals("quack") && typedWord.equals("Q")) || 
        
        (currentWord.equals("cheque") && typedWord.equals("CHEQ")) || (currentWord.equals("virtual") && typedWord.equals("VIRT")) || (currentWord.equals("fruit") && typedWord.equals("FR")) ||
        
        (currentWord.equals("upscale") && typedWord.equals("")) || 
        
        (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && (typedWord.equals("PNE") || typedWord.equals("PNEUMONO"))) ) ) {
            typedWord = typedWord + "U";                
        }

        //if yoyo, yonder, analyst, or society... add y
        if (e.getKeyCode() == KeyEvent.VK_Y && ( (currentWord.equals("yoyo") && (typedWord.equals("") || typedWord.equals("YO"))) || 
        
        (currentWord.equals("yonder") && typedWord.equals("")) || (currentWord.equals("analyst") && typedWord.equals("ANAL")) || 
        
        (currentWord.equals("society") && typedWord.equals("SOCIET")) ) ) {
            typedWord = typedWord + "Y";                
		}
        
        //if upscale, analyst, society, or pneumonoultramicroscopicsilicovolcanoconiosis... add s
        if (e.getKeyCode() == KeyEvent.VK_S && ( (currentWord.equals("upscale") && typedWord.equals("UP")) || 
        
        (currentWord.equals("analyst") && typedWord.equals("ANALY")) || (currentWord.equals("society") && typedWord.equals("")) ||
        
        (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && (typedWord.equals("PNEUMONOULTRAMICRO") || 
        		
        typedWord.equals("PNEUMONOULTRAMICROSCOPIC") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOLCANOCONIO") || typedWord.equals("PNEUMONOULTRAMICROSCOPICSILICOVOLCANOCONIOSI"))) ) ) {
            typedWord = typedWord + "S";                
		}

        //if quack or cheque... add q
        if (e.getKeyCode() == KeyEvent.VK_Q && ((currentWord.equals("quack") && typedWord.equals("")) || (currentWord.equals("cheque") && typedWord.equals("CHE")) ) ) {
            typedWord = typedWord + "Q";                
		}

        //if fruit... add f
        if (e.getKeyCode() == KeyEvent.VK_F && (currentWord.equals("fruit") && typedWord.equals("")) ) {
            typedWord = typedWord + "F";                
        }
        
        //if orange... add g
        if (e.getKeyCode() == KeyEvent.VK_G && (currentWord.equals("orange") && typedWord.equals("ORAN")) ) {
            typedWord = typedWord + "G";                
        }
        
        //if upscale or pneumonoultramicroscopicsilicovolcanoconiosis ... add p
        if (e.getKeyCode() == KeyEvent.VK_P && ( (currentWord.equals("upscale") && typedWord.equals("U")) || 
        (currentWord.equals("pneumonoultramicroscopicsilicovolcanoconiosis") && (typedWord.equals("") || typedWord.equals("PNEUMONOULTRAMICROSCO"))) ) ) {
            typedWord = typedWord + "P";                
		}
        
        //if cheque... add h
        if (e.getKeyCode() == KeyEvent.VK_H && (currentWord.equals("cheque") && typedWord.equals("C")) ) {
            typedWord = typedWord + "H";                
        }
        
        //if major... add j
        if (e.getKeyCode() == KeyEvent.VK_J && (currentWord.equals("major") && typedWord.equals("MA")) ) {
            typedWord = typedWord + "J";                
		}

	}

    public void run() {
		while (gameRunning) {
			if (!leftWord) {
                //if there isn't a word on the left, find a non-active word & set it active on the left
				for (int i=0;i<Words.length;i++) {
                    int j = WordNumber[i];
					if (!Words[j].getActive()) {
						this.leftWord = true;
						Words[j].setSpriteX(leftX);
						Words[j].setActive(true);
						WordLabels[j].setVisible(true);
                        Words[j].startWords();
                        break;
                    }
				}
			} else if (!middleWord) {
                //if there isn't a word in the middle, find a non-active word & set it active in the middle
				for (int i=0;i<Words.length;i++) {
                    int j = WordNumber[i];
					if (!Words[j].getActive()) {
						this.middleWord = true;
						Words[j].setSpriteX(middleX);
						Words[j].setActive(true);
						WordLabels[j].setVisible(true);
                        Words[j].startWords();
						break;
					}
				}
			} else if (!rightWord) {
                //if there isn't a word on the right, find a non-active word & set it active in the right                
				for (int i=0;i<Words.length;i++) {
                    int j = WordNumber[i];
					if (!Words[j].getActive() && !Words[j].getLongWord()) {
						this.rightWord = true;
						Words[j].setSpriteX(rightX);
						Words[j].setActive(true);
						WordLabels[j].setVisible(true);
                        Words[j].startWords();
						break;
					}
				}
            } 

            //if there is a word on the left & it's come off the screen, set leftWord = false
            //(only if it's a word that should be on the left, else break;)
            if (leftWord) {
                for (int i=0;i<Words.length;i++) {
                    int j = WordNumber[i];
                    if ((Words[j].getSpriteY() >= WordRainProperties.SCREEN_HEIGHT || Words[j].getWordHit()) && Words[j].getMoving()) {
                        if (i % 3 == 0) {
                        	this.leftWord = false;
                            Words[j].stopWords();
                            WordLabels[j].setVisible(false);
                            break;
                        } else {
                            break;
                        }
                    }
                }
                
                //update the head picture to the next (closest) word if that word is on the left
                for (int i=0;i<Words.length;i++) {
                    int j = WordNumber[i];
                    if (Words[j].getMoving() && Words[j].getSpriteX() == leftX) {
                        leftDistance = WordRainProperties.SCREEN_HEIGHT - Words[j].getSpriteY();
                        
                        //if the left distance is <= middle distance & right distance & the word is not complete
                        if (leftDistance <= middleDistance && !Words[j].getWordComplete()) {
                        	//get the current word
                        	this.currentWord = (Words[j].getSpriteName().replace("pictures/", "")).replace(".png", "");
                        	if (currentWord.equals("long")) {
                        		//set head of long.png
                        		this.currentWord = "pneumonoultramicroscopicsilicovolcanoconiosis";
                        		Head.setSpriteName("pictures/head.png");
                        		HeadLabel.setIcon(new ImageIcon(getClass().getResource(Head.getSpriteName())));	
                        	} else {
                        		//set head
	                            Head.setSpriteName(Words[j].getSpriteName());
					            HeadLabel.setIcon(new ImageIcon(getClass().getResource(Head.getSpriteName())));	
                        	}
                        	
                        } 
                        break;
                    }
                }

            } 

            //if there is a word in the middle & it's come off the screen, set middleWord = false
            //(only if it's a word that should be in the middle, else break;)
            if (middleWord) {
                for (int i=0;i<Words.length;i++) {  
                    int j = WordNumber[i];
                    if ((Words[j].getSpriteY() >= WordRainProperties.SCREEN_HEIGHT || Words[j].getWordHit()) && Words[j].getMoving()) {
                        if (i % 2 == 0 && i!=0) {
                        	this.middleWord = false;
                            Words[j].stopWords();
                            WordLabels[j].setVisible(false);
                            break;
                        } else {
                            break;
                        }
                    }
                }

                //update the head picture to the next (closest) word if that word is in the middle
                for (int i=0;i<Words.length;i++) {  
                    int j = WordNumber[i];
                    
                    //if middle word is moving, get the distance between the word and the bottom of the screen
                    if (Words[j].getMoving() && Words[j].getSpriteX() == middleX) {
                        middleDistance = WordRainProperties.SCREEN_HEIGHT - Words[j].getSpriteY();
                        
                        //if the middle distance is <= left distance & right distance & the word is not complete
                        if ((middleDistance <= leftDistance && middleDistance <= rightDistance) && !Words[j].getWordComplete()) {
                        	//get the current word
                        	this.currentWord = (Words[j].getSpriteName().replace("pictures/", "")).replace(".png", "");
                        	if (currentWord.equals("long")) {
                        		//set head of long.png
                        		this.currentWord = "pneumonoultramicroscopicsilicovolcanoconiosis";
                        		Head.setSpriteName("pictures/head.png");
                        		HeadLabel.setIcon(new ImageIcon(getClass().getResource(Head.getSpriteName())));	
                        	} else {
	                            Head.setSpriteName(Words[j].getSpriteName());
					            HeadLabel.setIcon(new ImageIcon(getClass().getResource(Head.getSpriteName())));	
                        	}
                        	
                        }
                        break;
                    }
                }
            } 

            //if there is a word on the right & it's come off the screen, set rightWord = false
            if (rightWord) {
                for (int i=0;i<Words.length;i++) {
                    int j = WordNumber[i];
                    if ((Words[j].getSpriteY() >= WordRainProperties.SCREEN_HEIGHT || Words[j].getWordHit()) && Words[j].getMoving()) {
                		this.rightWord = false;
                        Words[j].stopWords();
                        WordLabels[j].setVisible(false);
                        break;
                    }
                }
                
                //update the head picture to the next (closest) word if that word is on the right
                for (int i=0;i<Words.length;i++) {
                    int j = WordNumber[i];
                  //if right word is moving, get the distance between the word and the bottom of the screen
                    if (Words[j].getMoving() && Words[j].getSpriteX() == rightX) {
                        rightDistance = WordRainProperties.SCREEN_HEIGHT - Words[j].getSpriteY();
                        
                        //if the right distance is <= left distance & middle distance & the word is not complete
                        if ((rightDistance <= leftDistance && rightDistance <= middleDistance) && !Words[j].getWordComplete()) {
                            this.currentWord = (Words[j].getSpriteName().replace("pictures/", "")).replace(".png", "");
                            //set head
                            Head.setSpriteName(Words[j].getSpriteName());
				            HeadLabel.setIcon(new ImageIcon(getClass().getResource(Head.getSpriteName())));
                        }
                        break;
                    }
                }
            }
        
            //update score if player gets hit or completes word
            for (int i=0;i<Words.length;i++) {
                int j = WordNumber[i];
                Words[j].getWordTypedLabel().setText("Typed: " +typedWord);
                
                //if word is not completed, not moving, and the player is hit, set score & lives
                if ((!Words[j].getWordNotComplete() && !Words[j].getMoving()) && Words[j].getWordHit()) {
                    this.score -= 10;
                    Words[j].getScoreLabel().setText("Score:" + score);
                    Words[j].setWordNotComplete(true);

                    this.lives -= 1;
                    if (lives == 3) {
                        Words[j].getLivesLabel().setIcon(new ImageIcon(getClass().getResource("pictures/lives3.png")));
                    } else if (lives == 2) {
                        Words[j].getLivesLabel().setIcon(new ImageIcon(getClass().getResource("pictures/lives2.png")));
                    } else if (lives == 1) {
                        Words[j].getLivesLabel().setIcon(new ImageIcon(getClass().getResource("pictures/lives1.png")));
                    } else if (lives == 0) {
                        Words[j].getLivesLabel().setIcon(new ImageIcon(getClass().getResource("pictures/lives0.png")));					
                    } else if (lives < 0) {
                    	//if all lives are used up stop game
                        Words[j].stopWords();
                        stopMovingWords();
                        lostLabel.setText("<html><body>You Lost! Try Again! Score: " +score+ "</body></html>");
                        lostDialog.setVisible(true);
                    }
                    break;
                }
                
                //if word is completed...
                if (Words[j].getMoving()) {
                	//if word is moving, current word equals the word typed, word isn't completed, & current word isn't blank
                    if ((currentWord.equalsIgnoreCase(typedWord) && !Words[j].getWordComplete()) && !currentWord.equals("")) {
                    	//set the score & left, middle, or rightWord to false
                        score += Words[j].getWordPoints();
                        Words[j].getScoreLabel().setText("Score:" + score);

                        if (Words[j].getSpriteX() == leftX) {
                            leftWord = false;
                        } else if (Words[j].getSpriteX() == middleX) {
                            middleWord = false;
                        } else if (Words[j].getSpriteX() == rightX) {
                            rightWord = false;
                        }
                       
                        //reset typedWord, stop word, & set head to welldone.png
                        this.typedWord = "";
                        WordLabels[j].setVisible(false);
                        Words[j].stopWords();
                        Words[j].setWordComplete(true);
                        Head.setSpriteName("pictures/welldone.png");
				        HeadLabel.setIcon(new ImageIcon(getClass().getResource(Head.getSpriteName())));
                        break;
                    }
                    break;
                }
                
            }
            
            int last = WordNumber[WordNumber.length-1];
            if (lives >= 0 && Words[last].getSpriteY() == 1000) {
            	//if not all lives are used up & all words in array have been used
	                wonLabel.setText("<html><body>You Won! Score: " +score+ "<br/>Enter your name: </body></html>");
	                wonDialog.setVisible(true);
	                stopMovingWords();
	                break;
            }

            //pause
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
            }
		}	
    }

}