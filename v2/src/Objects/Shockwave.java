package Objects;

import logic.Game;

public class Shockwave extends Weapon{

	public Shockwave(Game game, int x, int y) {
		super(game, -1, -1);
	}  

	protected final String Detail = "Shockwave";

	public boolean isAlive()
	{
		return true;
	}

	protected String getDetail()
	{
		return Detail;
	}

	public String toString() {
		return null;
	}
}
