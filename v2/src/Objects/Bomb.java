package Objects;

import logic.Game;

public class Bomb extends Weapon{

	public Bomb(Game game, int x, int y) {
		super(game, x, y);
		live = 1;
	}

	private final int harm = 1;
	protected static final String Detail = "Bomb";

	public boolean receiveMissileAttack(int damage)
	{
		live = 0;
		return true;
	}

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

	public boolean MoveY()
	{
		pos[1]--;
		return pos[1] >= 0;
	}

	protected String getDetail()
	{
		return Detail;
	}
}
