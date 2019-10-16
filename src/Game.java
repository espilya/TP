

//TODO:
//	-Representacion de help,list,error sin imprimir el tablero ??
//	-Mejor represetacion de Win y GameOver
//	-Arreglar los static, non-static

import java.util.Random;

public class Game{
	
	public enum command {
		moveL1, moveL2, moveR1, moveR2, shoot, shockwave, reset, list, exit, help, none, error
	}
	
	public enum casilla {
		destroyer(0), regular(1), OVNI(2), UCMShip(3), misil(4), proyectil(5), empty(6), error(7);
		private final int value;

	    private casilla(int value) {
	        this.value = value;
	    }
	    
	    private static int valor(casilla x)
	    {
	    	int y;
	    	switch(x)
	    	{
	    	case destroyer:
	    		y = 0;
	    		break;
	    		
	    	case regular:
	    		y = 1;
	    		break;
	    		
	    	case OVNI:
	    		y = 2;
	    		break;
	    		
	    	case UCMShip:
	    		y = 3;
	    		break;
	    		
	    	case misil:
	    		y = 4;
	    		break;
	    		
	    	case proyectil:
	    		y = 5;
	    		break;
	    		
	    	case empty:
	    		y = 6;
	    		break;
	    		
	    	default:
	    		y = 7;
	    	}
	    	return y;
	    }
	    
	    private static casilla tipo(int value)
	    {
	    	casilla x;
	    	switch (value)
	    	{
	    	case 0:
	    		x = casilla.destroyer;
	    	break;
	    		
	    	case 1:
	    		x = casilla.regular;
	    	break;
	    	
	    	case 2:
	    		x = casilla.OVNI;
	    	break;
	    	
	    	case 3:
	    		x = casilla.UCMShip;
	    	break;
	    	
	    	case 4:
	    		x = casilla.misil;
	    	break;
	    	
	    	case 5:
	    		x = casilla.proyectil;
	    	break;
	    	
	    	case 6:
	    		x = casilla.empty;
	    	break;
	    	
	    	default: 
	    		x = casilla.error;
	    	}
	    	return x;
	    }
	}
	
	
	private casilla celda;
	private command action;

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
	//static GamePrinter GPrint = new GamePrinter(numRows, numCols);
	
	private static String difficulty;
	
	static Random rand;
	
	private static int[][][] board;
	//[numCols][numRows][0] = valor enumerado, [numCols][numRows][1] = indice lista (en caso de que sea destroyer, regular o bomb)
	
	private static boolean gameOver = false;
	private static boolean gameWin;
	private static boolean shipsDir = true;
	private static boolean print;
	private static boolean shockWave = true;
	private static boolean HayOvni;
	private static boolean HayMisil; //Hay o no un misil que no ha impactado
	private static boolean exit;
	
	public Game() {
		
	}
	
	
	
	public void SetCommand(command x)
	{
		action = x;
	}
	
	
	public String toString(int v, int h){
	
		String aux;
		celda = casilla.tipo(board[v][h][0]);
			
		switch (celda)
		{
		case destroyer:
			aux = destroyerShips.toString(board[v][h][1]);
			break;
			
		case regular:
			aux = regularShips.toString(board[v][h][1]);
			break;
			
		case OVNI:
			aux = ovni.toString();
			break;
			
		case UCMShip:
			aux = player.toString();
			break;
			
		case misil:
			aux = "oo";
			break;
			
		case proyectil:
			aux = bombs.toString(board[v][h][1]);
			break;
			
		case empty:
			aux = "";
			break;
			
		default:
			aux = "Error";
		}
		
		return aux;
	}
		
	public static boolean initialize(String dificultad, int seed, int rows, int cols) {
		semilla = seed;
		if(seed == -1)
		{
			Random r1 = new Random();
			semilla = r1.nextInt();
		}
		rand = new Random(semilla);
		
		numRows = rows;
		numCols = cols;
		board = new int[numRows][numCols][2];
		boolean x = false;
		exit = false;
		difficulty = dificultad;
		if(level.setDifficulty(dificultad))
		{
			x = true;
			shipsDir = false;
			gameWin = false;
			gameOver = false;
			HayOvni = false;
			HayMisil = false;
			player.SetHP(3);
			points = 0;
			shockWave = true;
			nOfCycles = 1;
			vel = level.getVelocidad();
			frecDisp = level.getFrecDisparo();
			player.setShipPos(7, 4);
			
			remainingAliens = level.getNumberDestroyerShip() + level.getNumberRegularShip();
			
//			if(rand.nextInt(10) < level.getProbOvni() * 10)
//			{
//			remainingAliens++;
//			ovni.setShipPos(8);
//			HayOvni = true;
//			}
			
			bombs.initialize(level.getNumberDestroyerShip());
			regularShips.initialize(level.getNumberRegularShip());
			destroyerShips.initialize(level.getNumberDestroyerShip());
			
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
		
		return x;
	}
	
	
	private static void Board()
	{
		ResetBoard();
		if(HayMisil)
		{
			board[misilpos[0]][misilpos[1]][0] = casilla.valor(casilla.misil);
		}
		
		if(player.GetHP() > 0)
		{
		board[player.GetShipV()][player.GetShipH()][0] = casilla.valor(casilla.UCMShip);
		}
		
		if(ovni.GetShipHP() > 0)
		{
		board[ovni.GetShipV()][ovni.GetShipH()][0] = casilla.valor(casilla.OVNI);
		}
		
		for(int i = 0; i < bombs.GetIndice(); i++)
		{
			if(bombs.CheckBomb(i) && bombs.GetProyectilV(i) >= 0)
			{
				board[bombs.GetProyectilV(i)][bombs.GetProyectilH(i)][0] = casilla.valor(casilla.proyectil);
				board[bombs.GetProyectilV(i)][bombs.GetProyectilH(i)][1] = i;
			}
		}
		
		for(int i = 0; i < destroyerShips.GetIndice(); i++)
		{
			if(destroyerShips.GetDestShipHP(i) > 0)
			{
				board[destroyerShips.GetDestV(i)][destroyerShips.GetDestH(i)][0] = casilla.valor(casilla.destroyer);
				board[destroyerShips.GetDestV(i)][destroyerShips.GetDestH(i)][1] = i;
			}
		}
		
		for(int i = 0; i < regularShips.GetIndice(); i++)
		{
			if(regularShips.GetRegShipHP(i) > 0)
			{
				board[regularShips.GetRegV(i)][regularShips.GetRegH(i)][0] = casilla.valor(casilla.regular);
				board[regularShips.GetRegV(i)][regularShips.GetRegH(i)][1] = i;
			}
		}
	}
	
	public static void Print()
	{
		String shockwave = "No";
		if(shockWave)
		{
			shockwave = "Si";
		}
		String texto = "Life: " + player.GetHP() + "\nNumber of Cycles: " + nOfCycles + "\nPoints: " + points +
				"\nRemaining Aliens: " + remainingAliens + "\nShockwave: " + shockwave;
		System.out.println(texto);
		
		Board();
	}
	
	private static void ResetBoard() 
	{
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				board[i][j][0] = casilla.valor(casilla.empty);
			}
		}
	}
	
	private void userCommand() {
		print = false;
		switch(action){
		case exit:
			exit = true;
		break;
		
		case help:
			System.out.println(help());
		break;
		
		case error:
			System.out.println(error());
		break;
		
		case list:
			System.out.println(list());
		break;
		
		case reset:
			reset();
		break;
		default:
			print = true;
			switch(action)
			{
				case moveL1:
					moveUCM(-1);
				break;
				
				case moveL2:
					moveUCM(-2);
				break;
				
				case moveR1:
					moveUCM(1);
				break;
				
				case moveR2:
					moveUCM(2);
				break;
				
				case shockwave:
					shockwave();
				break;
				
				case none:
				break;
				
				case shoot:
					shoot();
				break;
			}
		}
	}
	
	public boolean update()    
	{
		gameOver = true;
		System.out.println(action);
		userCommand();
		if(print = true)
		{
			nOfCycles++;
			updateMissil();
			killedOrNot(); 
			updateBombs();
			updateOVNI();
			killedOrNot(); 
			if((nOfCycles - 1) % vel == 0)
			{
				updateNaves();
				killedOrNot(); 
			}
		}
		return print;
	}
	
	private static String help() {
		return "[M]ove <left|right><1|2>: Moves UCM-Ship to the indicated direction.\n"
		+ "[S]hoot: UCM-Ship launches a missile.\n" 
		+ "shock[W]ave: UCM-Ship releases a shock wave.\n"
		+ "[L]ist: Prints the list of available ships.\n" 
		+ "[R]eset: Starts a new game.\n"
		+ "[H]elp: Prints this help message.\n" 
		+ "[E]xit: Terminates the program.\n"
		+ "[none]: Skips one cycle.\r\n";
	}
	
	private static String error() {
		return "El comando introducido no es valido\n";
	}

	private static String list()
	{
		return "[R]egular Ship: Points - 5, Harm - 0, Shield - 3 \n" +
			   "[D]estroyer Ship: Points - 10, Harm - 1, Shield - 1 \n" +
			   "[O]vni: Points - 25, Harm - 0, Shield - 1 \n" + 
			   "^__^: Harm: 1 - Shield: 3\n";
	}
	
	private static void reset()
	{
		if(initialize(difficulty, semilla, numRows, numCols))
		{
			destroyerShips.reset();
			regularShips.reset();
			bombs.deleteBombs();
			player.reset();
			
			remainingAliens = 0;
			nOfCycles = 0;
			points = 0;
			
			shipsDir = true;
			shockWave = true;
			HayOvni = false;
			HayMisil = false;
		}
	}
	
	private static void moveUCM(int i)
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
	
	private static void shockwave()
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
			ovni.shipHitByUCMShip();
			remainingAliens--;
		}
	}
	
	private static void shoot()
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
		gameWin = true;
		if(regularShips.GetContador() > 0 || destroyerShips.GetContador() > 0 || HayOvni) 
			gameWin = false;		
		return gameWin;
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
		if(!HayOvni && rand.nextInt(10) < level.getProbOvni() * 10)
		{
		remainingAliens++;
		ovni.setShipPos(8);
		HayOvni = true;
		}
		
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
	}

	private static void shipsDown() {
		boolean earth = false;
		for(int i = 0; i < regularShips.GetIndice(); i++) {
			if(regularShips.GetRegShipHP(i) > 0 && regularShips.GetRegV(i) == numRows - 2) {
				earth = true;
			}
		}
		for(int i = 0; i<destroyerShips.GetIndice(); i++) {
			if(destroyerShips.GetDestShipHP(i) > 0 && destroyerShips.GetDestV(i) == numRows - 2) {
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
			if(regularShips.GetRegShipHP(i) > 0) 
			{
				regularShips.SetRegShip(regularShips.GetRegV(i), regularShips.GetRegH(i)+ move, i);		
			}
		}
		
		
		for(int i = 0; i<destroyerShips.GetIndice(); i++) 
		{
			if(destroyerShips.GetDestShipHP(i) > 0) 
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
			if(HayMisil && regularShips.GetRegShipHP(i) > 0 && misilpos[0] == regularShips.GetRegV(i) && misilpos[1] == regularShips.GetRegH(i)) 
			{
				regularShips.shipHitByUCMShip(i, 1);
				HayMisil = false;
				if(regularShips.GetRegShipHP(i) == 0)
				{
					remainingAliens--;
				}
			}
		}
		
		for(int i = 0; i<destroyerShips.GetIndice(); i++) 
		{
			if(HayMisil && destroyerShips.GetDestShipHP(i) > 0 && misilpos[0] == destroyerShips.GetDestV(i) && misilpos[1] == destroyerShips.GetDestH(i)) 
			{
				destroyerShips.shipHitByUCMShip(i, 1);
				HayMisil = false;
				remainingAliens--;
			}
		}
		
		if(HayMisil && ovni.GetShipHP() > 0 && ovni.GetShipV() == misilpos[0] && ovni.GetShipH() == misilpos[1])
		{
			ovni.shipHitByUCMShip();
			HayOvni = false;
			shockWave = true;
			HayMisil = false;
			remainingAliens--;
		}
		
		
		if(player.GetHP() == 0)
		{
			gameOver = true;
		}
	}
	
	
	public static void gameOverPrint() 
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
	
	public boolean Exit()
	{
		return exit;
	}

}
