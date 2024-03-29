package objects;

import logic.Game;

public abstract class AlienShip extends EnemyShip{

	protected int dir;
	
	public AlienShip(Game game, int x, int y) {
		super(game, x, y);
		this.dir = -1;
	}

	public AlienShip(Game game, int x, int y, int dir) {
		super(game, x, y);
		this.dir = dir;
	}

	public boolean MoveX(int x)
	{
		dir = dir * x;
		pos[0] += dir;
		return pos[0] >= 0 && pos[0] < game.GetNumCols();
	}

	public boolean MoveY()
	{
		pos[1] += 1;
		return pos[1] < game.GetNumRows() - 1;
	}
	
	public boolean isAlien() {
		return true;
	}
	
	public int getDir() {
		return dir;
	}
	
	public void serDir(int dir) {
		this.dir = dir;
	}
}
