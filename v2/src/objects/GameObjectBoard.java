package objects;

import logic.Game;
import logic.Level;

public class GameObjectBoard {
	GameObject[] GObject = new GameObject[30];
	boolean HaveLanded;
	int nOfAliens;
	int contador;
	Game game;

	public GameObjectBoard(Game game, Level level) {
		this.game = game;
		init(level);
	}

	private void init(Level level) {
		HaveLanded = false;
		contador = 0;
		nOfAliens = level.getNumDestroyerAliens() + level.getNumRegularAliens();
	}

	// Bien
	public void add(GameObject object) {
		GObject[contador] = object;
		contador++;
	}

	// Bien
	private GameObject getObjectInPosition(int col, int row) {
		int i = 0;
		while (i < contador) {
			if (GObject[i].getRow() == row && GObject[i].getCol() == col) {
				return GObject[i];
			}
			i++;
		}
		return null;
	}

	// Bien
	private int getIndex(GameObject x) {
		int i = 0;
		while (i < contador) {
			if (GObject[i] == x) {
				return i;
			}
			i++;
		}
		return -1;
	}

	// Bien
	private void remove(GameObject object) {
		int i = this.getIndex(object);

		int points = object.getPoints();
		game.receivePoints(points);

		if (object.isAlien())
			this.nOfAliens--;

		while (i < contador - 1) {
			GObject[i] = GObject[i + 1];
			i++;
		}
		contador--;
		GObject[contador] = null;
	}

	public void update() {

	}

	private void checkAttacks() { // hace falta cambiar para el caso de nave explosiva. me parece que se repite la
									// cosa
		int i, j;
		boolean stop, add;
		i = 0;
		while (i < contador - 1) {
			j = i + 1;
			add = true;
			while (j < contador) {
				if (this.GObject[i].getRow() == this.GObject[j].getRow()
						&& this.GObject[i].getCol() == this.GObject[j].getCol()) {
					add = false;
					if (GObject[i].isMisil()) {
						GObject[j].receiveMissileAttack(GObject[i].GetHarm());
						GObject[i].receiveBombAttack(GObject[j].GetHarm());
						if (!GObject[j].isAlive() && GObject[j].die()) {
							remove(GObject[j]);
						}
						remove(GObject[i]);
					}

					else if (GObject[i].isBomb()) {
						GObject[j].receiveBombAttack(GObject[i].GetHarm());
						GObject[i].receiveMissileAttack(GObject[j].GetHarm());
						if (!GObject[j].isAlive() && GObject[j].die()) {
							remove(GObject[j]);
						}
						remove(GObject[i]);
					}

					else if (GObject[j].isMisil()) {
						GObject[j].receiveBombAttack(GObject[i].GetHarm());
						GObject[i].receiveMissileAttack(GObject[j].GetHarm());
						remove(GObject[j]);
						if (!GObject[i].isAlive() && GObject[i].die()) {
							remove(GObject[i]);
						}
					}

					else if (GObject[j].isBomb()) {
						GObject[j].receiveMissileAttack(GObject[i].GetHarm());
						GObject[i].receiveBombAttack(GObject[j].GetHarm());
						remove(GObject[j]);
						if (!GObject[i].isAlive() && GObject[i].die()) {
							remove(GObject[i]);
						}
					} else {
						add = true;
					}
				}
				j++;
			}
			if (add)
				i++;
		}
	}

	public void computerAction(boolean aliensMove) {
		MoveMisil();
		checkAttacks();
		if (aliensMove) {
			makeExplosive();
			MoveOther();
			shoot();
		}
		MoveBombs();
		checkAttacks();
	}

	public void ShockWave() {
		int i = 0;
		while (i < contador) {
			if (!GObject[i].isUCMShip())
				GObject[i].receiveShockWaveAttack(1);

			if (!GObject[i].isAlive() && GObject[i].die())
				remove(GObject[i]);
			else
				i++;
		}
	}

	private void MoveMisil() {
		for (int i = 0; i < contador; i++) {
			if (GObject[i].isMisil() && !GObject[i].MoveY() && GObject[i].die()) {
				this.remove(GObject[i]);
			}
		}
	}

	private void MoveOther() {
		MoveOVNI();
		if (MoveAlienShipsX(1)) {
			MoveAlienShipsX(-1);
			MoveAlienShipsY();
		}
	}

	private boolean MoveAlienShipsX(int x) {
		boolean aux = false;
		for (int i = 0; i < contador; i++) {
			if (GObject[i].isAlien()) {
				if (!GObject[i].MoveX(x))
					aux = true;
			}
		}
		return aux;
	}

	private void MoveBombs() {
		int i = 0;
		while (i < contador) {
			if (GObject[i].isBomb() && !GObject[i].MoveY()) {
				this.remove(GObject[i]);
			} else {
				i++;
			}
		}
	}

	private void MoveOVNI() {
		for (int i = 0; i < contador; i++) {
			if (GObject[i].isOvni()) {
				GObject[i].MoveX(i);
			}
		}
	}

	private void MoveAlienShipsY() {
		for (int i = 0; i < contador; i++) {
			if (GObject[i].isAlien() && !GObject[i].MoveY()) {
				HaveLanded = true;
			}
		}
	}

	private void makeExplosive() {
		for (int i = 0; i < contador; i++) {
			if (GObject[i].isRegular() && game.getNextDouble() < game.getLevel().getTurnExplodeFrequency()) {
				int y = GObject[i].getCol();
				int x = GObject[i].getRow();
				int live = GObject[i].getLive();
				int dir = ((AlienShip) GObject[i]).getDir();
				GObject[i] = new ExplosivShip(game, x, y, live, dir);
			}
		}
	}

	// Bien
	private void shoot() {
		for (int i = 0; i < contador; i++) {
			if (GObject[i].isDestroyer() && GObject[i].shoot()) {
				GObject[contador] = GObject[i].getProyectil();
				contador++;
			}
		}
	}

	// Bien
	public boolean AliensHaveLanded() {
		return this.HaveLanded;
	}

	// Bien
	public String toString(int col, int row) {
		GameObject object = getObjectInPosition(col, row);
		if (object == null || !object.isAlive()) {
			return "";
		}
		return object.toString();
	}

	// Bien
	public boolean allDead() {
		return this.nOfAliens == 0;
	}

	// Bien
	public int remainingAliens() {
		return nOfAliens;
	}

	public void explote(int i, int j) {
		for (int a = i - 1; a <= i + 1; a++) {
			for (int b = j - 1; b <= j + 1; b++) {
				if (a != i || b != j) {
					GameObject aux = getObjectInPosition(a, b);
					if (aux != null) {
						aux.receiveMissileAttack(1);
						aux.receiveBombAttack(1);
					}
				}
			}
		}
	}
	
	public void stringify() {
		
	}

}
