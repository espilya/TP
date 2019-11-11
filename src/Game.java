//TODO:
//	-Mejor represetacion de Win y GameOver

import java.util.Random;

public class Game{
	
	private static int numRows = 8; 
	private static int numCols = 9;
	private static int semilla;
	private static int[] misilpos = new int[2];
	private static double frecDisp;
	private static int vel;
	private static int nOfCycles;
	private static int points;
	private static int remainingAliens = 0;
	
	private static UCMShip player = new UCMShip();
	private static RegularShipList regularShips = new RegularShipList();
	private static DestroyerShipList destroyerShips = new DestroyerShipList();
	private static BombList bombs = new BombList();
	private static OVNI ovni = new OVNI();
	private static Level level = new Level();
	
	static Random rand;
	
	
	private static boolean gameOver = false;
	private static boolean shipsDir = true;
	private static boolean shockWave = true;
	private static boolean HayOvni;
	private static boolean HayMisil; //Hay o no un misil que no ha impactado
	
	public Game() {
		
	}

	
	
	public String toString(int v, int h){
		String aux;
		int pos;
		if(player.GetShipH() == h && player.GetShipV() == v) 
		{
			aux = player.toString();
		}
		else
		{
			if(misilpos[0] == h && misilpos[1] == v)
			{
				aux = "oo";
			}
			else
			{
				pos = destroyerShips.buscar(h, v);
				if(pos != -1) 
				{
					aux = destroyerShips.toString(pos);
				}
				else
				{
					pos = regularShips.buscar(h, v);
					if(pos != -1)
					{
						aux = regularShips.toString(pos);
					}
					else
					{
						pos = bombs.buscar(h, v);
						if(pos != -1)
						{
							aux = bombs.toString(pos);	
						}
						else
						{
							aux = "";
						}
					}
				}
			}
		}
		return aux;
	}
		
	public boolean initialize(String dificultad, int seed, int rows, int cols) {
		semilla = seed;
		if(seed == -1)
		{
			Random r1 = new Random();
			semilla = r1.nextInt();
		}
		rand = new Random(semilla);
		
		numRows = rows;
		numCols = cols;
		boolean x = false;
		if(level.setDifficulty(dificultad))
		{
			x = true;
			vel = level.getVelocidad();
			frecDisp = level.getFrecDisparo();
			
			regularShips.initialize(level.getNumberRegularShip());
			destroyerShips.initialize(level.getNumberDestroyerShip());
		}
		return x;
	}

	
	public void Print()
	{
		String shockwave = "No";
		if(shockWave)
		{
			shockwave = "Si";
		}
		String texto = "Life: " + player.GetHP() + "\nNumber of Cycles: " + nOfCycles + "\nPoints: " + points +
				"\nRemaining Aliens: " + remainingAliens + "\nShockwave: " + shockwave;
		System.out.println(texto);
	}

		
	public void update()    
	{
		nOfCycles++;
		updateMissil();
		killedOrNot(); 
		
		if((nOfCycles - 1) % vel == 0)
		{
			updateBombs();
			killedOrNot(); 
			updateOVNI();
			updateNaves();
			killedOrNot(); 
		}
	}
	
	public String help() {
		return "[M]ove <left|right><1|2>: Moves UCM-Ship to the indicated direction.\n"
		+ "[S]hoot: UCM-Ship launches a missile.\n" 
		+ "shock[W]ave: UCM-Ship releases a shock wave.\n"
		+ "[L]ist: Prints the list of available ships.\n" 
		+ "[R]eset: Starts a new game.\n"
		+ "[H]elp: Prints this help message.\n" 
		+ "[E]xit: Terminates the program.\n"
		+ "[none]: Skips one cycle.\r\n";
	}
	
	public String error() {
		return "El comando introducido no es valido\n";
	}

	public String list()
	{
		return "[R]egular Ship: Points - 5, Harm - 0, Shield - 3 \n" +
			   "[D]estroyer Ship: Points - 10, Harm - 1, Shield - 1 \n" +
			   "[O]vni: Points - 25, Harm - 0, Shield - 1 \n" + 
			   "^__^: Harm: 1 - Shield: 3\n";
	}

	
 	public void reset()
	{
		regularShips.reset();
		destroyerShips.reset();
		bombs.deleteBombs();
		
		shipsDir = false;
		gameOver = false;
		HayOvni = false;
		HayMisil = false;
		player.SetHP(3);
		points = 0;
		shockWave = true;
		nOfCycles = 1;
		player.setShipPos(7, 4);
		
		remainingAliens = level.getNumberDestroyerShip() + level.getNumberRegularShip();
		
		if(rand.nextInt(10) < level.getProbOvni() * 10)
		{
			remainingAliens++;
			ovni.setShipPos(8);
			HayOvni = true;
		}
		
		switch(level.getDifficulty())
		{
		case easy:
			for(int i = 0; i < level.getNumberRegularShip(); i++)
				regularShips.SetRegShip(1, 3 + i, i);
			
			for(int i = 0; i < level.getNumberDestroyerShip(); i++)
				destroyerShips.SetDestShip(2, 4 + i, i);
			break;
			
		case hard:
			for(int i = 0; i < level.getNumberRegularShip(); i++)
				regularShips.SetRegShip((i / 4) + 1, (i % 4) + 3, i);
			
			for(int i = 0; i < level.getNumberDestroyerShip(); i++)
				destroyerShips.SetDestShip(3, 4 + i, i);
			break;
			
		case insane: 
			for(int i = 0; i < level.getNumberRegularShip(); i++)
				regularShips.SetRegShip((i / 4) + 1, (i % 4) + 3, i);
			
			for(int i = 0; i < level.getNumberDestroyerShip(); i++)
				destroyerShips.SetDestShip(3, 3 + i, i);
			break;
		}	
	}
	
	public void moveUCM(int i)
	{
		if(player.GetShipH() + i >= 0 && player.GetShipH() + i < numCols)
		{
			player.setShipPos(player.GetShipV(), player.GetShipH() + i);
		}
		else
		{
			if(i == 2 || i == -2)
			{
				if(i == 2)
				{
					i = 1;
				}
				else
				{
					i = -1;
				}
				if(player.GetShipH() + i >= 0 && player.GetShipH() + i < numCols)
				{
					player.setShipPos(player.GetShipV(), player.GetShipH() + i);
				}
			}
		}
		
	}
	
	public void shockwave()
	{
		for(int i = 0; i < destroyerShips.GetIndice(); i++)
		{
			if(destroyerShips.GetDestShipHP(i) > 0)
			{
				destroyerShips.shipHitByUCMShip(i, player.GetHarm());
				remainingAliens--;
			}
			
		}
		
		for(int i = 0; i < regularShips.GetIndice(); i++)
		{
			if(regularShips.GetRegShipHP(i) > 0)
			{
				regularShips.shipHitByUCMShip(i, player.GetHarm());
				if(regularShips.GetRegShipHP(i) == 0)
				{
					remainingAliens--;
				}
			}
		}
		
		if(HayOvni)
		{
			HayOvni = false;
			ovni.shipHitByUCMShip();
			remainingAliens--;
		}
	}
	
	public void shoot()
	{
		if(!HayMisil)
		{
			HayMisil = true;
			misilpos[0] = player.GetShipV();
			misilpos[1] = player.GetShipH();
		}
	}
	
	public boolean Win()
	{
		return remainingAliens == 0;
	}
	
	private static void updateBombs(){
		for(int i = 0; i < destroyerShips.GetIndice(); i++) {
				if(bombs.CheckBomb(i)) {
					if(bombs.GetProyectilV(i) + 1 < numRows)
					{
					bombs.SetBombsPos(bombs.GetProyectilV(i) + 1, bombs.GetProyectilH(i), i);
					}
					else
					{
						bombs.deleteBomb(i);
					}
						
				}
				else {
					if(destroyerShips.GetDestShipHP(i) > 0 && (rand.nextInt(10) < (frecDisp * 10))) {
						bombs.Add(i);
						bombs.SetBombsPos(destroyerShips.GetDestV(i) + 1, destroyerShips.GetDestH(i), i);
					}
				}
		}
	}
	
	private static void updateMissil() {
		if(HayMisil) {
			if(misilpos[0] - 1 >= 0)
				misilpos[0] -= 1;
			else 
				HayMisil = false;	
		}
	}
	
	private static void updateNaves() { 
		boolean shipsMoveDown = false;
		int move;
		if(shipsDir) 
			move = 1;
		else
			move = -1;
		for(int i = 0; i < regularShips.GetIndice(); i++) {
			if(regularShips.GetRegShipHP(i) > 0 &&   
					(regularShips.GetRegH(i) + move >= numCols || regularShips.GetRegH(i) + move < 0)) {
				shipsMoveDown = true;
			}
		}
		for(int i = 0; i<destroyerShips.GetIndice(); i++) {
			if(destroyerShips.GetDestShipHP(i) > 0 &&   
					(destroyerShips.GetDestH(i) + move >= numCols || destroyerShips.GetDestH(i) + move < 0) ) {
				shipsMoveDown = true;
			}
		}
		
		
		if(shipsMoveDown) {
			shipsDown();
			shipsDir = !shipsDir;
		}
		else
		{
			shipsMove();
		}
	}
	
	private static void updateOVNI() {

		if(HayOvni)
		{
			if(ovni.GetShipH() - 1 >= 0)
			{
				ovni.setShipPos(ovni.GetShipH() - 1);
			}
			else
			{
				HayOvni = false;
				remainingAliens --;
			}
			
		}
		else
		{
			if(rand.nextInt(10) < level.getProbOvni() * 10)
			{
				remainingAliens++;
				ovni.reset();
				ovni.setShipPos(8);
				HayOvni = true;
			}
		}
	}

	private static void shipsDown() {
		boolean earth = false;
		for(int i = 0; i < regularShips.GetIndice(); i++) {
			if(regularShips.exists(i) && regularShips.GetRegV(i) == numRows - 2) {
				earth = true;
			}
		}
		for(int i = 0; i<destroyerShips.GetIndice(); i++) {
			if(destroyerShips.exists(i) && destroyerShips.GetDestV(i) == numRows - 2) {
				earth = true;
			}
		}
		if(earth)
			gameOver = true;
		else {
			for(int i = 0; i < regularShips.GetIndice(); i++) {
				regularShips.SetRegShip(regularShips.GetRegV(i)+1, regularShips.GetRegH(i), i);
			}
			for(int i = 0; i<destroyerShips.GetIndice(); i++) {
				destroyerShips.SetDestShip(destroyerShips.GetDestV(i)+1, destroyerShips.GetDestH(i), i);
			}
		}
	}
	
	private static void shipsMove() {
		int move;
		if(shipsDir) 
			move = 1;
		else
			move = -1;
		
		for(int i = 0; i < regularShips.GetIndice(); i++) 
		{
			if(regularShips.exists(i)) 
			{
				regularShips.SetRegShip(regularShips.GetRegV(i), regularShips.GetRegH(i)+ move, i);		
			}
		}
		
		
		for(int i = 0; i<destroyerShips.GetIndice(); i++) 
		{
			if(destroyerShips.exists(i)) 
			{
				destroyerShips.SetDestShip(destroyerShips.GetDestV(i), destroyerShips.GetDestH(i)+ move, i);
			}
		}
	}

	private static void killedOrNot() 
	{
		for(int i = 0; i < bombs.GetIndice(); i++) {
			if(bombs.CheckBomb(i)) {
				if(bombs.GetProyectilV(i) == misilpos[0] && bombs.GetProyectilH(i) == misilpos[1])
				{
					HayMisil = false;
					bombs.deleteBomb(i);
				}
				else
				{
					if(bombs.GetProyectilV(i) == player.GetShipV() && bombs.GetProyectilH(i) == player.GetShipH()) 
					{
						player.shipHitByAlien();
						bombs.deleteBomb(i);
					}
				}
			}
		}
		
		for(int i = 0; i < regularShips.GetIndice(); i++) 
		{
			if(HayMisil && regularShips.exists(i) && misilpos[0] == regularShips.GetRegV(i) && misilpos[1] == regularShips.GetRegH(i)) 
			{
				regularShips.shipHitByUCMShip(i, 1);
				HayMisil = false;
			}
		}
		
		for(int i = 0; i<destroyerShips.GetIndice(); i++) 
		{
			if(HayMisil && destroyerShips.exists(i) && misilpos[0] == destroyerShips.GetDestV(i) && misilpos[1] == destroyerShips.GetDestH(i)) 
			{
				destroyerShips.shipHitByUCMShip(i, 1);
				HayMisil = false;
			}
		}
		
		if(HayMisil && HayOvni && ovni.GetShipHP() > 0 && ovni.GetShipV() == misilpos[0] && ovni.GetShipH() == misilpos[1])
		{
			ovni.shipHitByUCMShip();
			HayOvni = false;
			shockWave = true;
			HayMisil = false;
		}
		remainingAliens = destroyerShips.GetContador() + regularShips.GetContador();
	}
	

	public void gameOverPrint() 
	{
		String texto = "\n\n\n\n\n\n HAS PERDIDO \n\n\n\n\n\n";
		System.out.println(texto);
	}
	
	public void gameWinPrint()
	{
		String texto = "\n\n HA GANADO \n\n";
		System.out.println(texto);
	}
	
	public boolean GameOver()
	{
		return (player.GetHP() == 0 || gameOver);
	}
	
	public int GetNumRows()
	{
		return numRows;
	}
	
	public int GetNumCols()
	{
		return numCols;
	}

}
