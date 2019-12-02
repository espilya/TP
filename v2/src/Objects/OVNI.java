package Objects;
public class OVNI extends EnemyShip{

	private int hp;
	private int points;

	public OVNI(int v, int h){
		super(v, h);
		hp = 1;
		points = 25;
		row = 0;
	}
	public void Hit(int harm) {
		this.hp -= harm;
	}
	
	public int GetShipHP() {
		return hp;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public String toString() {
		return "O[" + hp +"]";
	}
	
	public void reset()
	{
		hp = 1;
	}
}