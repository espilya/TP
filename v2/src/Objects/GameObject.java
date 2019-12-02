package Objects;
import interfaces.IAttack;

public abstract class GameObject implements IAttack{

	protected int row;
	protected int col;
	
//	private static int semilla;
//	private static double frecDisp;
//	private static int vel;
//	private static int nOfCycles;
//	private static int points;
//	private static int remainingAliens = 0;
	
	GameObject(int v, int h)
	{
		row = v;
		col = h;
	}
	GameObject()
	{
		
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}

	public abstract String toString();
	
	
}