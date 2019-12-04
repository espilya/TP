package Objects;

import logic.Game;

public abstract class Ship extends GameObject{

	public Ship(Game game, int x, int y) {
		super(game, x, y);
	}

	public boolean receiveShockWaveAttack(int damage)
	{
		live -= damage;
		return true;
	}
}
