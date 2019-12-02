package Objects;
public class DestroyerShip extends AlienShip{
	private int hp;
	private final int points = 10;
	private boolean HaDisparado;
	
	
	//rand
	DestroyerShip(int v, int h){
		super(v, h);
		hp = 1;
	}

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
		hp = 1;
	}
	
	public boolean GetHaDisparado()
	{
		return HaDisparado;
	}
	
	public String toString()
	{
		return "D[" + hp + "]";
	}
}