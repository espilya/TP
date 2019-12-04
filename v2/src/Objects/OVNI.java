package Objects;

import logic.Game;

public class OVNI extends EnemyShip{

	public OVNI(Game game, int x, int y) {
		super(game, game.GetNumCols(), game.GetNumRows());
		live = FinalHP;
		exists = false;
	}

	private boolean exists;
	private final int points = 25;
	private final int FinalHP = 1;
	protected static final String Detail = "OVNI";

	private void create()
	{
		if(game.getNextDouble() <= game.getLevel().getOvniFrequency())
		{
			exists = true;
			pos[0] = game.GetNumCols() - 1;
			pos[1] = game.GetNumRows() - 1;
		}
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
		exists = false;
		game.enableShockWave();
		return false;
	}

	public boolean MoveX(int x)
	{
		if(exists)
		{
			pos[0] -= 1;
			if(pos[0] < 0)
			{
				exists = false;
			}
		}
		else
			create();

		return true;
	}

	protected String getDetail()
	{
		return Detail;
	}

	public void reset()
	{
		live = FinalHP;
	}
}
