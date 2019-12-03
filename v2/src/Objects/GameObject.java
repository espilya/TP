package Objects;
import interfaces.IAttack;
import logic.Game;

public class GameObject implements IAttack{

	protected int row;
	protected int col;
	protected final String type;
	protected Game game;
	
//	private static int semilla;
//	private static double frecDisp;
//	private static int vel;
//	private static int nOfCycles;
//	private static int points;
//	private static int remainingAliens = 0;
	
	GameObject(String t, Game g)
	{
		type = t;
		game = g;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}

	public String toString()
	{
		return null;
	}		
}