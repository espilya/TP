package Objects;

import logic.Game;

public class EnemyShip extends Ship{

	EnemyShip(String t, Game g) {
		super(t, g);
	}
	
	public boolean moveX(int x)
	{
		boolean aux;
		int mov = col + x;
		aux = (mov >= 0) && (mov < game.GetNumCols());
		if(aux)
		{
			col += x;
		}
		return aux;
	}
	

	public boolean moveY(int y)
	{
		boolean aux;
		int mov = row + y;
		aux = mov > 0;
		if(aux)
		{
			row += y;
		}
		return aux;
	}
}
