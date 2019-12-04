package Objects;
import interfaces.IAttack;
import logic.Game;

public abstract class GameObject implements IAttack {

	protected int pos[] = new int[2]; //x, y
	protected int live;
	protected Game game;
	
	public GameObject(Game game, int x, int y , int live) {
		pos[0] = x; pos[1] = y;
		this. game = game;
		this. live = live;
	}
	public int getRow(){return pos[0];}
	public int getCol(){return pos[1];}
	
	public boolean isAlive() {
		return this.live > 0;
	}
	
	public int getLive() {
		return this.live;
	}

	public boolean isOnPosition( int x, int y ) {
		return (x==pos[0] && y==pos[1]);
	}
	
	public void getDamage (int damage) {
		this.live = (damage >= this.live)? 0 : this.live-damage;
	}
	
	public boolean isOut() {
		return !game.isOnBoard( /* coordinadas */ );
	}
	
		public abstract void computerAction();
		public abstract void onDelete();
		public abstract void move();
		public abstract String toString();	
}