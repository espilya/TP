package Objects;
import logic.Game;

public class UCMShip extends Ship{
	private int hp;
	Game game;
	private final int harm = 1;
	private final int FinalHP = 3;

	
	public UCMShip(Game game, int v, int h){
		super(v, h);
		hp = FinalHP;
		this.game = game;
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
	
	
	

	

	
	
}