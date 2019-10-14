public class Bomb{
	private int pos[] = new int[2];
	private boolean exist;
	
	Bomb(){
		exist = true;
	}
	
	public boolean CheckBomb() {
		return exist;
	}

	public int GetBombX() {
		return pos[0];
	}
	
	public int GetBombY() {
		return pos[1];
	}
	
	public void SetBombPos(int x, int y) {
		this.pos[0] = x;
		this.pos[1] = y;
	}
	
	public String to_string() {
		return ".";
	}
	
	public void reset()
	{
		exist = false;
	}
	public void noReset()
	{
		exist = true;
	}
}