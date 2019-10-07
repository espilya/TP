//Ya que no dice nada sobre lo que va esta clase he supuesto que era para los proyectiles

public class BombList{ 
	private int damageUCM = 1;
	private int damageAliens = 1;
	public static final int TAM_LIST = 20;
	private int misil[] = new int[2]; //UCMShip
	private int proyectil[] = new int[2]; //DestroyerShip
	private int ListProyectil[] = new proyectil[TAM_LIST]; 
	private int contador = 0; //El contador se comprueba en el game
	
	public void setDamageUCM(int x) {
		damageUCM = x;
	}
	
	public void setDamageAliens(int x) {
		damageAliens = x;
	}
	
	public int GetDamageUCM() {
		return damageUCM;
	}
	
	public int GetDamageAliens() {
		return damageAliens;
	}

	public int GetMisil() {
		return misil;
	}

	public int GetProyectil(int x) {
		return ListProyectil[x];
	}
	
	public int GetContador() {
		return contador;
	}

	public void SetMisil(int x, int y) {
		misil[0] = x;
		misil[1] = y;
	}
	
	public void SetProyectil(int x, int y, int i) {
		ListProyectil[i][0] = x;
		ListProyectil[i][1] = y;
	}
	
	public void SetContador(int x)
	{
		contador = x;
	}
}