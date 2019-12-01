public class Misil{
	private int pos[] = new int[2];
	
	Misil(){
	}

	public int GetMisilV() {
		return pos[0];
	}
	
	public int GetMisilH() {
		return pos[1];
	}
	
	public void SetMisilPos(int v, int h) {
		this.pos[0] = v;
		this.pos[1] = h;
	}
	
	public String toString() {
		return "oo";
	}
}