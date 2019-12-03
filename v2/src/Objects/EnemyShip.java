package Objects;

import logic.Game;

public class EnemyShip extends Ship{

	EnemyShip(String t, Game g) {
		super(t, g);
	}
	
	public boolean moveX(int x)
	{
		boolean aux;
		aux = col + x > 0 && col + x < game.GetNumCols();
		if(aux)
		{
			col += x;
		}
		return aux;
	}
	
	public boolean moveY(int y)
	{
		boolean aux;
		aux = row + y < game.GetNumCols() - 1;
		if(aux)
		{
			row += y;
		}
		return aux;
	}
}
