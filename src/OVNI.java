public class OVNI{
	private int pos[] = new int[2];
	private int hp;
	private int points;

	OVNI(){
		this.hp = 1;
		this.points = 25;
	}
	public void shipHitByUCMShip(int damage) {
		this.hp -= damage;
	}
	
	public int[] getShipPos() {
		return pos;
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
	
	public String to_string() {
		return "O[" + this.hp +"]";
	}
}