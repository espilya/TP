//Ya que no dice nada sobre lo que va esta clase he supuesto que era para los proyectiles

public class BombList{ 
	public static final int TAM_LIST = 4;
	Bomb[] ListProyectil = new Bomb[TAM_LIST];
	private int indice = 0;
	
	public void initialize(int n){
		indice = n; 
		for(int i = 0; i < n; i++) {
			ListProyectil[i] = new Bomb();
		}
	}
	
	public void SetBombsPos(int v, int h, int i) {
		ListProyectil[i].SetBombPos(v, h);
		ListProyectil[i].bombExist();
	}
	
	
	public int GetProyectilV(int i) {
		return ListProyectil[i].GetBombV();
	}
	
	public int GetProyectilH(int i) {
		return ListProyectil[i].GetBombH();
	}

	
	public int GetIndice() {
		return indice;
	}
	
	public String toString(int i) {
		return ".";
	}
	
	public void deleteBombs()
	{
		for(int i = 0; i < indice; i++) {
			ListProyectil[i].reset();
		}
	}
	
	public void deleteBomb(int i)
	{
		//ListProyectil[i] = null;
		ListProyectil[i].reset();
	}
	
	public boolean CheckBomb(int i) {
		//boolean status = false;
		//if(ListProyectil[i] != null && ListProyectil[i].CheckBomb()) 
			//status = true;
		return ListProyectil[i].CheckBomb();
	}
	
	public int buscar(int x, int y)
	{
		int i = -1;
		for(int j = 0; j < indice && i == -1; j++)
		{
			if(ListProyectil[i].GetBombH() == x && ListProyectil[i].GetBombV() == y)
			{
				i = j;
			}
		}
		return i;
	}
}