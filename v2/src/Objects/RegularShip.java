package Objects;

import logic.Game;

public class RegularShip extends AlienShip{

	public RegularShip(Game game, int x, int y) {
		super(game, x, y);
		live = FinalHP;
	}

	private final int points = 5;
	private final int FinalHP = 2;


	public int getPoints() {
		return points;
	}

	public void reset()
	{
		live = FinalHP;
	}

	public String toString()
	{
		return "R[" + live + "]";
	}
	
	public boolean isRegular() {
		return true;
	}
}
