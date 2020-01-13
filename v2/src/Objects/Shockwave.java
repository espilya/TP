package Objects;

import logic.Game;

public class Shockwave extends Weapon{

	public Shockwave(Game game, int x, int y) {
		super(game, -1, -1);
	}  


	public boolean isAlive()
	{
		return true;
	}

	public String toString() {
		return null;
	}
}
