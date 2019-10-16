public class Bomb{
	private int pos[] = new int[2];
	private boolean exist;
	
	Bomb(int v, int h){
		this.pos[0] = v;
		this.pos[1] = h;
		exist = false;
	}
	
	public boolean CheckBomb() {
		return exist;
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
	
	public String to_string() {
		return ".";
	}
	
	public void reset()
	{
		exist = false;
	}
	public void bombExist()
	{
		exist = true;
	}
}