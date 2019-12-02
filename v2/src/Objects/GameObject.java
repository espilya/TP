package Objects;
import interfaces.IAttack;

public abstract class GameObject implements IAttack{

	protected int row;
	protected int col;
	
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