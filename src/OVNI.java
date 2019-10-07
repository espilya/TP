public class OVNI{
	private int pos[] = new int[2];
	private int hp;

	UCMShip(){
		hp = 1;
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
}