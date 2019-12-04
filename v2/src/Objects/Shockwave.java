package Objects;

import logic.Game;

public class Shockwave extends Weapon{

	public Shockwave(Game game, int x, int y) {
		super(game, x, y);
		// TODO Auto-generated constructor stub
	}

	protected final String Detail = "Shockwave";

	public boolean isAlive()
	{
		return true;
	}

	public void Hit(int harm)
	{

	}

	protected String getDetail()
	{
		return Detail;
	}

	public String toString() {
		return null;
	}
}
