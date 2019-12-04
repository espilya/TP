package Objects;

import logic.Game;

public class Bomb extends Weapon{
	
	public Bomb(Game game, int x, int y) {
		super(game, x, y);
		live = 1;
	}

	private final int harm = 1;
	public final String Detail = "Bomb";
	
	
	public int GetHarm()
	{
		return harm;
	}
	
	public void Hit(int harm)
	{
		live = 0;
	}
	
	public String toString()
	{
		return ".";
	}
	
	public boolean MoveY(int x)
	{
		if(x == 0)
		{
			pos[1] -= 1;
			return pos[1] < game.GetNumRows();
		}
		return true;
	}

	public String GetDetail() {
		return Detail;
	}
}