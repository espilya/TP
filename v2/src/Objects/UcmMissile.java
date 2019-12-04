package Objects;

import logic.Game;

public class UcmMissile extends Weapon{

	public UcmMissile(Game game, int x, int y) {
		super(game, x, y);
		live = 1;
	}

	private final int harm = 1;
	public final String Detail = "Misil";
	
	public int GetHarm()
	{
		return harm;
	}

	public boolean MoveY(int x) { 
		this.pos[0]++;
		if(x < 0 && this.pos[0] <= game.GetNumRows())
			return false;
		else
			return true;
	}
	
	public String toString() {
		return "oo";
	}
	
	public String GetDetail() {
		return Detail;
	}
	
	public void Hit(int harm)
	{
		live = 0;
	}
}