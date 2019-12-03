package Objects;
import logic.Game;

public class UCMShip extends Ship{
	private int hp;
	private final int harm = 1;
	private final int FinalHP = 3;
	public final String Detail = "UCMShip";
	
	public UCMShip(String t, Game g){
		super(t, g);
		hp = FinalHP;

	}
	
	public void Hit(int harm) {
		this.hp -= harm;
	}
	
	public int GetHP(){
		return hp;
	}
	
	public String toString() {
		return "^__^";
	}
	
	public void reset(){
		hp = 3;
	}
	public boolean isAlive() {
		return hp > 0;
	}

	public int getHarm() {
		return harm;
	}

	public int GetFinHP() {
		return FinalHP;
	}
	
	public boolean moveX(int x)
	{
		boolean aux;
		aux = col + x > 0 && col + x < game.GetNumCols();
		if(aux)
		{
			col += x;
		}
		return aux;
	}	
	
	public boolean moveY(int y)
	{
		return true;
	}

	

	
	
}