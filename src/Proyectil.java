public class Proyectil{
	private int[] pos = new int[2];
	
	public void SetProyectilV(int v) {
		pos[0] = v;
	}
	
	public void SetProyectilH(int h) {
		pos[1] = h;
	}
	
	public int GetProyectilV() {
		return pos[0];
	}
	
	public int GetProyectilH() {
		return pos[1];
	}
}