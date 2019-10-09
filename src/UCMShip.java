

public class UCMShip{
	private int pos[] = new int[2];
	private int hp;

	//rand
	UCMShip(){
		hp = 3;
	}
	//UCMShip tiene 3 puntos de vida y cada disparo le hace 1, solo hay una nave alien que dispara
	public void shipHitByAlien(int damage) {
		this.hp -= damage;
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
	public String toString() {
		return "^__^";
	}
	
	
	

	

	
	
}