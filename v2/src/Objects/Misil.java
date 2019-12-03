package Objects;
public class Misil{
	private int pos[] = new int[2];
	
	Misil(){
	}

	public int GetPosX() {
		return pos[0];
	}
	
	public int GetPosY() {
		return pos[1];
	}
	
	public void SetPos(int x, int y) {
		this.pos[0] = x;
		this.pos[1] = y;
	}
	
	public void Move() {
		this.pos[0]++;
	}
	
	public String toString() {
		return "oo";
	}
}