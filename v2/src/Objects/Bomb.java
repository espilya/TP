package Objects;
public class Bomb extends Weapon{
	private int pos[] = new int[2];
	
	Bomb(){
	}

	public int GetBombV() {
		return pos[0];
	}
	
	public int GetBombH() {
		return pos[1];
	}
	
	public void SetBombPos(int v, int h) {
		this.pos[0] = v;
		this.pos[1] = h;
	}
}