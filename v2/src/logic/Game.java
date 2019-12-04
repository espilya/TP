package logic;

import java.util.Random;

import Objects.GameObject;
import Objects.GameObjectBoard;
import Objects.UCMShip;
import interfaces.IPlayerController;

public class Game implements IPlayerController{

	private final int numRows = 8;
	private final int numCols = 9;
	private int nOfCycles;
	private int points;
	private boolean misil;

	private static BoardInitializer initializer;
	private static GameObjectBoard board;
	private static UCMShip player;

	private Level level;
	private Random rand;

	private boolean shockwave; //Comprobar si es necesario
	private static boolean exit;

	//Bien
	public Game (Level level, Random random){
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
		}

	//Bien, hacer funciones a las que llama
	public void initGame () {
		nOfCycles = 0;
		misil = false;
		shockwave = true;
		exit = false;
		board = initializer.initialize(this, this.level);
		initializer.initializeEnemy();
		player = new UCMShip(this, this.numCols / 2, this.numRows - 1);
		board.add(player);
		}

	//Bien
	public boolean Lose() {
		return !player.isAlive () || board.AliensHaveLanded();
	}

	//Bien, corregir funciones a las que llama
	public boolean Win () {
		return board.allDead();
	}

	//Bien
	public boolean isFinished() {
		return Win() || Lose() || exit;
		}

	//Creo que sobra, ya esta el add del GameObjectBoard
	public void addObject(GameObject object) {
		board.add(object);
		}

	//Bien
	public String infoToString() {
		String aux = "No";
		if(this.shockwave)
		{
			aux = "Si";
		}
		String texto = "Life: " + player.getLive() + "\nNumber of Cycles: " + nOfCycles + "\nPoints: " + points +
				"\nRemaining Aliens: " + board.remainingAliens() + "\nShockwave: " + aux;
		return texto;
		}

	//Bien
	public String toString(int h, int v){
		
		return board.toString(h, v);
	}

	//Bien
	public void update() {
		board.computerAction(this.nOfCycles % level.getNumCyclesToMoveOneCell() == 0);
		board.update(); //No se que tiene que hacer
		nOfCycles += 1;
	}

	//Hacer reset game
	public void reset()
	{
		this.initGame();
	}

	//Bien
	public boolean shootMissile()
	{
		boolean aux = player.shoot();
		if(aux)
		{
			board.add(player.getProyectil());
		}
		update();
		return aux;
	}

	//Bien
	public void gameOverPrint()
	{
		String texto = "\n\n\n\n\n\n HAS PERDIDO \n\n\n\n\n\n";
		System.out.println(texto);
	}

	//Bien
	public void gameWinPrint()
	{
		String texto = "\n\n HA GANADO \n\n";
		System.out.println(texto);
	}

	//Bien
	public int GetNumRows()
	{
		return numRows;
	}

	//Bien
	public int GetNumCols()
	{
		return numCols;
	}

	//Bien
	public void SetExit(boolean x)
	{
		exit = x;
	}

	//Bien
	public boolean move(int numCells) {
		int i = numCells;
		if(player.getCol() + i >= 0 && player.getCol() + i < numCols)
		{
			player.setPos(player.getRow(), player.getCol() + i);
		}
		else
		{
			if(i > 0)
			{
				i--;
			}
			else
			{
				i++;
			}
			if(player.getCol() + i >= 0 && player.getCol() + i < numCols)
			{
				player.setPos(player.getRow(), player.getCol() + i);
			}
			else
			{
				return false;
			}
		}
		update();
		return true;
	}

	public boolean shockWave() {
		boolean aux = this.shockwave;
		if(this.shockwave)
		{
			board.ShockWave();
		}
		update();
		return aux;
	}

	//Bien
	public void receivePoints(int points) {
		this.points += points;
	}

	//Hacer entero
	public void enableShockWave() {
		this.shockwave = true;
	}

	//Bien
	public String List() {
		String aux;
		aux = "^__^ : Harm - 1, Shield - 3\n"
		+ "[R]egular Ship : Harm - 0, Shield - 2, Points - 5\n"
		+ "[D]estroyer Ship : Harm - 1, Shield - 1, Points - 10\n"
		+ "[O]vni : Harm - 0, Shield - 1, Points - 25\n";
		return aux;
	}

	//Bien
	public double getNextDouble() {
		return rand.nextDouble();
	}

	//Bien
	public double getProbShoot() {
		return this.level.getShootFrequency();
	}

	//Bien
	public boolean Exit() {
		return exit;
	}

	//Bien
	public Level getLevel() {
		return level;
	}


	public boolean isOnBoard(GameObject object) {
		return object.getCol() >= 0 && object.getCol() < this.numCols && object.getRow() >= 0 && object.getRow() < this.numRows;
	}

	@Override
	public void enableMissile() {
		misil = true;
	}

	public void disableMissile() {
		misil = false;
	}
	
	public boolean GetMisil()
	{
		return misil;
	}
}
