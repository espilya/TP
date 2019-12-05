package Objects;

import logic.Game;

public abstract class EnemyShip extends Ship{

	public EnemyShip(Game game, int x, int y) {
		super(game, x, y);
	}

	public boolean receiveMissileAttack(int damage)
	{
		live -= damage;
		return true;
	}
	
	public boolean receiveShockWaveAttack(int damage)
	{
		live -= damage;
		return true;
	}
}
