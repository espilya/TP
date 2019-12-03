package Objects;

import logic.Game;

public class RegularShip extends AlienShip{
	private int hp;
	private final int points = 5;
	private final int FinalHP = 2;
	public final String Detail = "RegularShip";


	public RegularShip(String t, Game g){
		super(t, g);
		hp = FinalHP;
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
		hp = 2;
	}
	
	public String toString()
	{
		return "R[" + hp + "]";
	}
	
	public int GetFinHP() {
		return FinalHP;
	}
}