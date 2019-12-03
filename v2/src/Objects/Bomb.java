package Objects;

import logic.Game;

public class Bomb extends Weapon{
	private final int harm = 1;
	public final String Detail = "Bomb";
	
	Bomb(String t, Game g){
		super(t, g);
	}
	
	public int GetHarm()
	{
		return harm;
	}
	
	public String toString()
	{
		return ".";
	}
	
	public boolean move(int y)
	{
		row += y;
		return row > 0;
	}
}