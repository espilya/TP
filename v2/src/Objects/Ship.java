package Objects;

public abstract class Ship extends GameObject{
	Ship(int v, int h) {
		super(v, h);
	}
	
	public abstract void Hit(int harm);
	
}

