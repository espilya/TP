public class DestroyerShip{
	private int pos[] = new int[2];
	private int hp;
	private int points;
	
	//rand
	DestroyerShip(){
		hp = 1;
		points = 10;
	}
	//UCMShip tiene 3 puntos de vida y cada disparo le hace 1, solo hay una nave alien que dispara
	public void shipHitByUCMShip(int damage) {
		this.hp -= damage;
	}
	
	public int GetDestX() {
		return pos[0];
	}
	
	public int GetDestY() {
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
	
	public String to_string() {
		return "D[" + this.hp +"]";
	}
}