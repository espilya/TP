//Ya que no dice nada sobre lo que va esta clase he supuesto que era para los proyectiles

public class BombList{ 
	public static final int TAM_LIST = 4;
	Proyectil[] ListProyectil = new Proyectil[TAM_LIST];
	private int contador = 0; //El contador se comprueba en el game
	

	public int GetProyectilX(int i) {
		return ListProyectil[i].GetProyectilX();
	}
	
	public int GetProyectilY(int i) {
		return ListProyectil[i].GetProyectilY();
	}
	
	
	public void SetProyectilX(int x, int i) {
		ListProyectil[i].SetProyectilX(x);
	}
	
	public void SetProyectilY(int y, int i) {
		ListProyectil[i].SetProyectilY(y);
	}
	
	public int GetContador() {
		return contador;
	}
	
	public String toString(int i) {
		return ".";
	}

}