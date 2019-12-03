package Objects;

import logic.Game;

public class Shockwave extends Weapon{

	public final String Detail = "Shockwave";
	
	Shockwave(String t, Game g){
		super(t, g);
	}

	public boolean moveY(int y)
	{
		return true;
	}
	
	public boolean moveX(int x)
	{
		return true;
	}
}