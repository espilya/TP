package Objects;

import logic.Game;

public abstract class AlienShip extends EnemyShip{

	private int dir;

	public AlienShip(Game game, int x, int y) {
		super(game, x, y);
		dir = -1;
	}

	public boolean MoveX(int x)
	{
		dir = dir * x;
		pos[0] += dir;
		return pos[0] >= 0 && pos[0] < game.GetNumCols();
	}

	public boolean MoveY()
	{
		pos[1] -= 1;
		return pos[1] > 0;
	}
}
