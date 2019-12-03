package Objects;

import logic.Game;

public class UcmMissile extends Weapon{
	private final int harm = 1;
	public final String Detail = "Misil";
	private int pos[] = new int[2];
	
	UcmMissile(String t, Game g)
	{
		super(t, g);
	}
	
	public int GetHarm()
	{
		return harm;
	}
	
	public int GetPosX() {
		return pos[0];
	}
	
	public int GetPosY() {
		return pos[1];
	}
	
	public void SetPos(int x, int y) {
		this.pos[0] = x;
		this.pos[1] = y;
	}
	
	//devuelve /false/ solo si el misil salio del tablero  ==>  el jugador puede disparar otra vez
	//devuelve /true/ si todo fue bien
	public boolean Move() { 
		this.pos[0]++;
		if(this.pos[0] >= game.GetNumRows())
			return false;
		else
			return true;
	}
	
	public String toString() {
		return "oo";
	}
}