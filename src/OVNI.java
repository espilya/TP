public class OVNI{
	private int pos[] = new int[2];
	private int hp;
	private int points;

	OVNI(){
		this.hp = 1;
		this.points = 25;
	}
	public void shipHitByUCMShip() {
		this.hp -= 1;
	}
	
	public int GetShipX() {
		return pos[0];
	}
	
	public int GetShipY() {
		return pos[1];
	}
	
	public void setShipPos(int x, int y) {
		this.pos[0] = x;
		this.pos[1] = y;
	}	
	
	public int GetShipHP() {
		return hp;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public String toString() {
		return "O[" + this.hp +"]";
	}
	
	public void reset()
	{
		hp = 1;
	}
}