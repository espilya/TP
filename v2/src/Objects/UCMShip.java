package Objects;


public class UCMShip extends Ship{
	private int hp;

	//rand
	public UCMShip(int v, int h){
		super(v, h);
		hp = 3;
	}
	//UCMShip tiene 3 puntos de vida y cada disparo le hace 1, solo hay una nave alien que dispara
	
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
	
	
	

	

	
	
}