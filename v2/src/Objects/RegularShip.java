package Objects;
public class RegularShip extends AlienShip{
	private int hp;
	private final int points = 5;

	//rand
	RegularShip(int v, int h){
		super(v, h);
		hp = 2;
	}
	//UCMShip tiene 3 puntos de vida y cada disparo le hace 1, solo hay una nave alien que dispara
	public void Hit(int harm) {
		this.hp -= harm;
	}
	
	public int GetShipHP() {
		return hp;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void reset()
	{
		hp = 2;
	}
	
	public String toString()
	{
		return "R[" + hp + "]";
	}
}