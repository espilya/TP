public class Proyectil{
	private int[] pos = new int[2];
	
	public void SetProyectilX(int x) {
		pos[0] = x;
	}
	
	public void SetProyectilY(int y) {
		pos[1] = y;
	}
	
	public int GetProyectilX() {
		return pos[0];
	}
	
	public int GetProyectilY() {
		return pos[1];
	}
}