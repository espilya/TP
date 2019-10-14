//Ya que no dice nada sobre lo que va esta clase he supuesto que era para los proyectiles

public class BombList{ 
	public static final int TAM_LIST = 4;
	Bomb[] ListProyectil = new Bomb[TAM_LIST];
	private int contador = 0;
	
	public void SetBombsPos(int x, int y, int i) {
		ListProyectil[i].SetBombPos(x, y);
		if(x == -1 && y == -1) 
			ListProyectil[i].reset();
		else
			ListProyectil[i].noReset();
			
	}
	
	public int GetProyectilX(int i) {
		return ListProyectil[i].GetBombX();
	}
	
	public int GetProyectilY(int i) {
		return ListProyectil[i].GetBombY();
	}

	public void SetContador(int x)
	{
		contador = x;
	}
	
	public int GetContador() {
		return contador;
	}
	
	public String toString(int i) {
		return "]";
	}
	
	public void reset()
	{
		for(int i = 0; i < contador; i++)
		{
			ListProyectil[i].reset();
		}
	}
	
	public void deletebomb(int i)
	{
		ListProyectil[i].reset();
	}
	
	public int CheckBomb(int i) { // status==1(existe proyectil), 0 no existe
		int status = -1;
			if(ListProyectil[i].CheckBomb()) 
				status = 1;
			else if(!ListProyectil[i].CheckBomb())
				status = 0;
		return status;
	}
	

}