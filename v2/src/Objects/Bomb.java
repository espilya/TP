package Objects;
public class Bomb extends Weapon{
	private final int harm = 1;
	
	Bomb(int v, int h){
		super(v, h);
	}
	
	public int GetHarm()
	{
		return harm;
	}
	
	public String toString()
	{
		return ".";
	}
}