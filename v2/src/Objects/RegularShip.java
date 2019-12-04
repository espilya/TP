package Objects;

import logic.Game;

public class RegularShip extends AlienShip{
	
	public RegularShip(Game game, int x, int y) {
		super(game, x, y);
		live = FinalHP;
	}

	private final int points = 5;
	private final int FinalHP = 2;
	public final String Detail = "RegularShip";

	
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
	
	public String GetDetail() {
		return Detail;
	}
}