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
	
	
	//Cambiar constructores: 
	//	1 SOLO CONSTRUCTOR
	//	todos deben incluir un Game
	//	la posicion se debe guardar en las clases que estan al fondo en la herencia 
	//		(shockwave no tiene, por lo que se tiene que quitar la posicion de aqui)
	//	crear un enum (uno para cada tipo de objeto) y pasarlo tambien
	//		el enum tiene que ser una clase aparte 
	
	GameObject(int v, int h)
	{
		row = v;
		col = h;
	}
	
	GameObject()
	{
		row = -1;
		col = -1;
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