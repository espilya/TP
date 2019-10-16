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
		ListProyectil[i].noReset();
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
		return "]";
	}
	
	public void reset()
	{
		for(int i = 0; i < indice; i++)
		{
			ListProyectil[i].reset();
		}
	}
	
	public void deletebomb(int i)
	{
		ListProyectil[i].reset();
	}
	
	public boolean CheckBomb(int i) { // status==1(existe proyectil), 0 no existe
		boolean status = false;
			if(ListProyectil[i].CheckBomb()) 
				status = true;
		return status;
	}
	

}