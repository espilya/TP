package logic;

import java.util.Random;

import Objects.GameObject;
import Objects.GameObjectBoard;
import Objects.UCMShip;
import exceptions.CommandExecuteException;
import interfaces.IPlayerController;

public class Game implements IPlayerController {

	private final int numRows = 8;
	private final int numCols = 9;
	private int nOfCycles;
	private int points;
	private int nSuperMisil;

	private static BoardInitializer initializer;
	private static GameObjectBoard board;
	private static UCMShip player;

	private Level level;
	private Random rand;

	private boolean shockwave; // Comprobar si es necesario
	private static boolean exit;

	public int getnOfCycles() {
		return this.nOfCycles;
	}

	public Game(Level level, Random random) {
		this.rand = random;
		this.level = level;
		initializer = new BoardInitializer();
		initGame();
	}

	// Bien, hacer funciones a las que llama
	private void initGame() {
		nOfCycles = 0;
		nSuperMisil = 0;
		shockwave = true;
		exit = false;
		board = initializer.initialize(this, this.level);
		initializer.initializeEnemy();
		player = new UCMShip(this, this.numCols / 2, this.numRows - 1);
		board.add(player);
	}

	// Bien
	public boolean Lose() {
		return !player.isAlive() || board.AliensHaveLanded();
	}

	// Bien
	public boolean Win() {
		return board.allDead();
	}

	// Bien
	public boolean isFinished() {
		return Win() || Lose() || exit;
	}

	// Bien
	public void addObject(GameObject object) {
		board.add(object);
	}

	// Bien
	public String infoToString() {
		String aux = "No";
		if (this.shockwave) {
			aux = "Si";
		}
		String texto = "Life: " + player.getLive() + "\nNumber of Cycles: " + nOfCycles + "\nPoints: " + points
				+ "\nRemaining Aliens: " + board.remainingAliens() + "\nShockwave: " + aux + "\nSuperMisil: "
				+ this.nSuperMisil;
		return texto;
	}

	// Bien
	public String toString(int h, int v) {

		return board.toString(h, v);
	}

	// Bien
	public void update() {
		board.computerAction(this.nOfCycles % level.getNumCyclesToMoveOneCell() == 0);
		board.update(); // No se que tiene que hacer
		nOfCycles += 1;
	}

	// Hacer reset game
	public void reset() {
		this.initGame();
	}

	// Bien
	public boolean shootMissile(boolean x) throws CommandExecuteException {
		boolean aux = player.shoot(x);
		if (aux) {
			board.add(player.getProyectil());
		}
		update();
		if (!aux || !x)
			throw new CommandExecuteException("'Disparar misil' Ya existe un misil. Se economico!");
		else if(!aux || x)
			throw new CommandExecuteException("'Disparar supermisil' No dispones de supermissil");
		return aux;
	}

	public int getUCMLife() {
		return player.getLive();
	}

	// Bien
	public int GetNumRows() {
		return numRows;
	}

	// Bien
	public int GetNumCols() {
		return numCols;
	}

	// Bien
	public void SetExit(boolean x) {
		exit = x;
	}

	// Bien
	public boolean move(int numCells) throws CommandExecuteException {
		int i = numCells;
		if (player.MoveX(i)) {
			update();
			return true;
		} else {
			if (i > 0) {
				i--;
			} else {
				i++;
			}

			if (player.MoveX(i)) {
				update();
				return true;
			} else {
				throw new CommandExecuteException(
						"'Movimiento' UCMShip se saldra del zona de ataque de aliens y no podra defender la tierra.");
			}
		}

	}

	public boolean shockWave() throws CommandExecuteException {
		boolean aux = this.shockwave;
		if (this.shockwave) {
			board.ShockWave();
		}
		update();
		if (!aux)
			throw new CommandExecuteException("'ShockWave' No dispones de armas tipo ShockWave.");
		return aux;
	}

	// Bien
	public void receivePoints(int points) {
		this.points += points;
	}

	public int GetPoints() {
		return points;
	}

	// Hacer entero
	public void enableShockWave() {
		this.shockwave = true;
	}

	// Bien
	public String List() {
		String aux;
		aux = "^__^ : Harm - 1, Shield - 3\n" + "[R]egular Ship : Harm - 0, Shield - 2, Points - 5\n"
				+ "[D]estroyer Ship : Harm - 1, Shield - 1, Points - 10\n"
				+ "[O]vni : Harm - 0, Shield - 1, Points - 25\n";
		return aux;
	}

	// Bien
	public double getNextDouble() {
		return rand.nextDouble();
	}

	// Bien
	public double getProbShoot() {
		return this.level.getShootFrequency();
	}

	// Bien
	public boolean Exit() {
		return exit;
	}

	// Bien
	public Level getLevel() {
		return level;
	}

	// Bien
	public boolean isOnBoard(GameObject object) {
		return object.getCol() >= 0 && object.getCol() < this.numCols && object.getRow() >= 0
				&& object.getRow() < this.numRows;
	}

	// No Hace nada
	public void enableMissile() {
	}

	public boolean shootMissile() {

		return false;
	}

	public int getSuperMisil() {
		return nSuperMisil;
	}

	public void reduceSuperMisil() {
		nSuperMisil--;
	}

	public boolean buySuperMisil() {
		boolean aux = points >= 0;
		if (aux) {
			points -= 20;
			nSuperMisil++;
		}
		return aux;
	}

	public void explosion(int i, int j) {
		// TODO Auto-generated method stub

	}
}
