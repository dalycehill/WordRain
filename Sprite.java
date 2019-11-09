import java.awt.Rectangle;

public class Sprite {
	//variables 
	protected String spriteName;
	protected int spriteX, spriteY, spriteW, spriteH;
	protected Rectangle r;
	
	//getters
	public String getSpriteName() {
		return spriteName;
	}
	public int getSpriteX() {
		return spriteX;
	}
	public int getSpriteY() {
		return spriteY;
	}
	public int getSpriteW() {
		return spriteW;
	}
	public int getSpriteH() {
		return spriteH;
	}
	public Rectangle getRectangle() {
		return r;
	}

	//setters
	public void setSpriteName(String spriteName) {
		this.spriteName = spriteName;
	}
	public void setSpriteX(int spriteX) {
		this.spriteX = spriteX;
		r.x = this.spriteX;
	}
	public void setSpriteY(int spriteY) {
		this.spriteY = spriteY;
		r.y = this.spriteY;
	}
	public void setSpriteW(int spriteW) {
		this.spriteW = spriteW;
		r.width = this.spriteW;
	}
	public void setSpriteH(int spriteH) {
		this.spriteH = spriteH;
		r.height = this.spriteH;
	}
	
	//constructors
	public Sprite() {
		super();
		r = new Rectangle(0,0,0,0);
	}
	
	public Sprite(String spriteName, int spriteX, int spriteY, int spriteW, int spriteH) {
		super();
		this.spriteName = spriteName;
		this.spriteX = spriteX;
		this.spriteY = spriteY;
		this.spriteW = spriteW;
		this.spriteH = spriteH;
		r = new Rectangle(spriteX,spriteY,spriteW,spriteH);
	}
	
	//display function
	public void Display() {
		System.out.println("X, Y: " +spriteX+ "," +spriteY);
	}
}