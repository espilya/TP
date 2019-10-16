public class OVNI{
	private int pos[] = new int[2];
	private int hp;
	private int points;

	OVNI(){
		this.hp = 1;
		this.points = 25;
		pos[1] = 0;
	}
	public void shipHitByUCMShip() {
		this.hp -= 1;
	}
	
	public int GetShipH() {
		return pos[0];
	}
	
	public int GetShipV() {
		return pos[1];
	}
	
	public void setShipPos(int h) {
		this.pos[0] = h;
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