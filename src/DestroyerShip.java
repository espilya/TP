public class DestroyerShip{
	private int pos[] = new int[2];
	private int hp;
	private int points;
	private boolean HaDisparado;
	
	
	//rand
	DestroyerShip(){
		hp = 1;
		points = 10;
	}

	public void shipHitByUCMShip(int harm) {
		this.hp -= harm;
	}
	
	public int GetDestV() {
		return pos[0];
	}
	
	public int GetDestH() {
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
		hp = 1;
	}
	
	public boolean GetHaDisparado()
	{
		return HaDisparado;
	}
}