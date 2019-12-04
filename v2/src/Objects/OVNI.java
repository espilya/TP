package Objects;

import logic.Game;

public class OVNI extends EnemyShip{

	public OVNI(Game game, int x, int y) {
		super(game, game.GetNumCols(), game.GetNumRows() - 1);
		live = FinalHP;
	}

	private final int points = 25;
	private final int FinalHP = 1;
	public final String Detail = "OVNI";

	public String GetDetail() {
		return Detail;
	}
	
	public int GetPoints() {
		return this.points;
	}
	
	public int GetFinalHP()
	{
		return FinalHP;
	}
	
	public String toString() {
		return "O[" + live + "]";
	}
	
	public boolean die()
	{
		game.enableShockWave();
		return false;
	}
	
	public void reset()
	{
		live = FinalHP;
	}
}