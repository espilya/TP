package Objects;

import logic.Game;

public class DestroyerShip extends AlienShip{
	private int hp;
	private final int points = 10;
	private boolean HaDisparado;
	private final int harm = 1;
	private final int FinalHP = 1;
	public final String Detail = "DestroyerShip";
	
	//rand
	public DestroyerShip(String t, Game g){
		super(t, g);
		hp = FinalHP;
	}

	public int GetFinHP()
	{
		return FinalHP;
	}
	
	public int getHarm()
	{
		return harm;
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