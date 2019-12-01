public class BombList{ 
	public static final int TAM_LIST = 4;
	Bomb[] ListProyectil = new Bomb[TAM_LIST];
	private int indice = 0;
	
	public void initialize(int i)
	{
		indice = i;
		for(int j = 0; j < i; j++)
		{
			ListProyectil[i] = new Bomb();
		}
	}
	
	public void Add(int i){
		ListProyectil[i] = new Bomb();
	}
	
	public void SetBombsPos(int v, int h, int i) {
		ListProyectil[i] = new Bomb();
		ListProyectil[i].SetBombPos(v, h);
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
	
	public String toString() {
		return ".";
	}
	
	public void deleteBombs()
	{
		for(int i = 0; i < indice; i++) {
			ListProyectil[i] = null;
		}
	}
	
	public void deleteBomb(int i)
	{
		ListProyectil[i] = null;
	}
	
	public boolean CheckBomb(int i) {
		return ListProyectil[i] != null;
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