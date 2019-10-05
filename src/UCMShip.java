

public class UCMShip{
	private int pos[] = new int[2];
	private int hp;

	//rand
	UCMShip(){
		hp = 100;
	}
	
	public void shipHitByDefaultAlien() {
		this.hp -= 10;
	}
	
	public void shipHitByOtherAlienShip() {
		this.hp -= 20;
	}
	
	public int[] getShipPos() {
		return pos;
	}
	
	public void setShipPos(int x, int y) {
		this.pos[0] = x;
		this.pos[1] = y;
	}
	
	
	

	

	
	
}