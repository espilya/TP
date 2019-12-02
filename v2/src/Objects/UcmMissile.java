package Objects;
public class UcmMissile extends Weapon{
	private final int harm = 1;
	
	UcmMissile(int v, int h)
	{
		super(v, h);
	}
	public int GetHarm()
	{
		return harm;
	}
	
	public String toString() {
		return "oo";
	}
}