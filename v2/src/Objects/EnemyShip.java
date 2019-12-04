package Objects;

import logic.Game;

public abstract class EnemyShip extends Ship{

	public EnemyShip(Game game, int x, int y) {
		super(game, x, y);
	}


	public boolean moveX(int x)
	{
		boolean aux;
		int mov = pos[0] + x;
		aux = (mov >= 0) && (mov < game.GetNumCols());
		if(aux)
		{
			pos[0] += x;
		}
		return aux;
	}
	

	public boolean moveY()
	{
		boolean aux;
		aux = pos[1] - 1 > 0;
		if(aux)
		{
			pos[1] -= 1;
		}
		return aux;
	}
}
