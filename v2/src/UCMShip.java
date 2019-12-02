

public class UCMShip extends Ship{
	private int pos[] = new int[2];
	private int hp;
	private int harm = 1;

	//rand
	UCMShip(){
		hp = 3;
	}
	//UCMShip tiene 3 puntos de vida y cada disparo le hace 1, solo hay una nave alien que dispara
	public void shipHitByAlien() {
		this.hp -= 1;
	}
	
	public int GetShipV() {
		return pos[0];
	}
	
	public int GetShipH() {
		return pos[1];
	}
	
	public int GetHP()
	{
		return hp;
	}
	public void SetHP(int x)
	{
		hp = x;
	}
	
	public int GetHarm()
	{
		return harm;
	}
	
	public void setShipPos(int v, int h) {
		this.pos[0] = v;
		this.pos[1] = h;
	}
	public String toString() {
		return "^__^";
	}
	
	public void reset()
	{
		hp = 3;
	}
	
	
	

	

	
	
}