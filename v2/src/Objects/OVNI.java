package Objects;

import logic.Game;

public class OVNI extends EnemyShip{

	private int hp;
	private final int points = 25;
	private final int FinalHP = 1;
	public final String Detail = "OVNI";

	public OVNI(String t, Game g){
		super(t, g);
		hp = FinalHP;
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
	
	public int getFinalHP()
	{
		return FinalHP;
	}
	
	public String toString() {
		return "O[" + hp + "]";
	}
	
	public void reset()
	{
		hp = 1;
	}
}