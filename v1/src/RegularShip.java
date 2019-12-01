public class RegularShip{
	private int pos[] = new int[2];
	private int hp;
	private int points;

	//rand
	RegularShip(){
		hp = 2;
		points = 5;
	}
	//UCMShip tiene 3 puntos de vida y cada disparo le hace 1, solo hay una nave alien que dispara
	public void shipHitByUCMShip(int harm) {
		this.hp -= harm;
	}
	
	public int GetRegV() {
		return pos[0];
	}
	
	public int GetRegH() {
		return pos[1];
	}
	
	public void setShipPos(int v, int h) {
		this.pos[0] = v;
		this.pos[1] = h;
	}
	
	public int GetShipHP() {
		return hp;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public void reset()
	{
		hp = 2;
	}
}