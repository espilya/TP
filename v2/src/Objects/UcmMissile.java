package Objects;

import logic.Game;

public class UcmMissile extends Weapon{
	private final int harm = 1;
	public final String Detail = "Misil";
	
	UcmMissile(String t, Game g)
	{
		super(t, g);
	}
	public int GetHarm()
	{
		return harm;
	}
	
	public String toString() {
		return "oo";
	}
	
	public boolean moveX(int x)
	{
		return true;
	}
	
	public boolean moveY(int y)
	{
		row -= y;
		return row < game.GetNumCols();
	}
}