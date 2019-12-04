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

	public void hit(int harm)
	{
		receiveBombAttack(1);
		live = 0;
	}

	public String toString()
	{
		return ".";
	}

	public boolean MoveY()
	{
		pos[1]++;
		return pos[1] < game.GetNumRows();
	}

	protected String getDetail()
	{
		return Detail;
	}
}
