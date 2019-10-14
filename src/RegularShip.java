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
	
	public int GetRegX() {
		return pos[0];
	}
	
	public int GetRegY() {
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
		return "C[" + this.hp +"]";
	}
	
	public void reset()
	{
		hp = 3;
	}
}