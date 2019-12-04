package Objects;
import interfaces.IAttack;
import logic.Game;

//TODO ESTA BIEN SALVO LOS MOVES Y SHOCKWAVE
//AÑADIR FORMA DE MANTENER OVNI CREADO Y QUE SI NO ESTA EN EL TABLERO SE APLIQUE LA PROBABILIDAD DE APARICION
//Creo que la mejor forma es que en el propio move del OVNI, si esta fuera del tablero llame a una funcion privada
//para ver si vuelve a aparecer o no

public abstract class GameObject implements IAttack {

	protected int pos[] = new int[2]; //x, y
	protected int live;
	protected Game game;

	public GameObject(Game game, int x, int y) {
		pos[0] = x; pos[1] = y;
		this. game = game;
	}

	public int getRow(){return pos[0];}
	public int getCol(){return pos[1];}

	public void setPos(int row, int col)
	{
		pos[0] = row;
		pos[1] = col;
	}

	public boolean isAlive() {
		return this.live > 0;
	}

	public int getLive() {
		return this.live;
	}

	public boolean isOnPosition( int x, int y ) {
		return (x == pos[0] && y == pos[1]);
	}

	public void getDamage (int damage) {
		this.live = (damage >= this.live)? 0 : this.live-damage;
	}

	public void hit(int damage) {
		
	}

	public int GetHarm()
	{
		return 1;
	}

	public boolean isOut() {
		return !game.isOnBoard(this);
	}

	public boolean MoveX(int x)
	{
		return true;
	}

	public boolean shoot()
	{
		return false;
	}

	public boolean MoveY()
	{
		return true;
	}

	public boolean die()
	{
		return true;
	}

	public GameObject getProyectil()
	{
		return null;
	}

		//public abstract void computerAction();   para que?
		//public abstract void onDelete();		para que?
		public abstract String toString();

		protected abstract String getDetail();
}
