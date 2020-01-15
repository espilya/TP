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
	
	public boolean receiveExplosion(int damage, int x, int y) 
	{
		if((pos[0] == x || pos[0] == x + 1 || pos[0] == x - 1) && (pos[1] == y || pos[1] == y + 1 || pos[1] == y - 1))
				live -= damage;
		return true;
	}
}
