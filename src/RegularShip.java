public class RegularShip{
	private int pos[] = new int[2];
	private int hp;

	//rand
	RegularShip(){
		hp = 2;
	}
	//UCMShip tiene 3 puntos de vida y cada disparo le hace 1, solo hay una nave alien que dispara
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
	
	public String to_string() {
		return "C[" + this.hp +"]";
	}
}