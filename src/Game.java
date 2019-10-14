

//Encapsula la l�gica del juego. Tiene, entre otros, el m�todo update que actualiza el 
//estado de todos los elementos del juego. Contiene una instancia de RegularShipList, una 
//DestroyerShipList y una BombList, entre otras instancias de objetos.

//Tambi�n mantiene el contador de ciclos y la puntuaci�n del jugador. Entre otros,
//tiene un atributo privado Random rand para genera los valores aleatorios.
//Tambi�n es posible que alguna de estas clases necesite un atributo en el que almacenan 
//el juego, eso es, una instancia de la clase Game (que ser� la �nica en el
//programa) y que, como veremos, contiene la l�gica del juego. De este modo, estas
//clases podr�n usar los m�todos de la clase Game para consultar si pueden hacer o
//no una determinada acci�n.


import java.util.Random; 
//public static final @tipo@ @NOMBRE@  = @valor@;

public class Game{

	private static int numRows = 9; 
	private static int numCols = 8;
	
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
	
	private static long seed;
	private final int damage = 1;
	private static int[] misilpos = new int[2];
	private static boolean HayMisil; //Hay o no un misil que no ha impactado
	private casilla celda;
	private command action;
	private static boolean HayOvni;
	private static double frecDisp;
	private static int vel;
	private static int nOfCycles;
	private static int points;
	private static int remainingAliens = 0;
	private static boolean shockWave = true;
	private static boolean update;
	private static UCMShip player = new UCMShip();
	private static RegularShipList regularShips = new RegularShipList();
	private static DestroyerShipList destroyerShips = new DestroyerShipList();
	private static BombList bombs = new BombList();
	private static OVNI ovni = new OVNI();
	private static Level level = new Level();
	private static int[][][] board = new int[numRows][numCols][2];//[numRows][numCols][0] = valor enumerado, [numRows][numCols][1] = indice lista (en caso de que sea destroyer, regular o bomb)
	static GamePrinter GPrint = new GamePrinter(numRows, numCols);
	private static String difficulty;
	private static boolean gameOver = false;
	private static boolean shipsDir = true;
	
	public static double[] GenerateNumbers(long seed, int amount) {
	    double[] randomList = new double[amount];
	    for (int i=0;i<amount;i++) {
	        Random generator = new Random(seed);
	        randomList[i] = Math.abs((double) (generator.nextLong() % 0.001) * 10000);
	        seed--;
	    }
	    return randomList;
	}
	
	public void SetCommand(command x)
	{
		action = x;
	}
	
	
	public String toString(int x, int y){
	
		String aux;
		celda = casilla.tipo(board[x][y][0]);
			
		switch (celda)
		{
		case destroyer:
			aux = destroyerShips.toString();
			break;
			
		case regular:
			aux = regularShips.toString();
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
			aux = bombs.toString();
			break;
			
		case empty:
			aux = "";
			break;
			
		default:
			aux = "Error";
		}
		
		return aux;
	}
		
	
	public static boolean initialize(String dificultad, long seed) {
		seed = 101;
		double random[] = GenerateNumbers(seed, 5);
		int randomA = (int)random[0];
		int randomB = (int)random[1];
		int randomC = (int)random[2];
		int randomD = (int)random[3];
		int randomE = (int)random[4];
		
		boolean x = false;
		difficulty = dificultad;
		if(level.setDifficulty(dificultad))
		{
			x = true;
			HayOvni = false;
			HayMisil = false;
			player.SetHP(3);
			points = 0;
			shockWave = true;
			nOfCycles = 1;
			vel = level.getVelocidad();
			frecDisp = level.getFrecDisparo();
			player.setShipPos(7, 4);
			
			//if(numrand(de 0 a 9) < level.getProbOvni() * 10)
			//{
			//this.remainingAliens ++;
			//ovni.setShipPos(0, 8);
			//HayOvni = true;
			//}
			//else
			//{
			//ovni.shipHitByUCMShip(damage);
			//}
			
			remainingAliens += level.getNumberDestroyerShip() + level.getNumberRegularShip();
			
			destroyerShips.SetContador(level.getNumberDestroyerShip());
			regularShips.SetContador(level.getNumberRegularShip());
			
			switch(level.getDifficulty())
			{
			case easy:
				for(int i = 0; i < 4; i++)
					regularShips.SetRegShip(1, 4 + i, i);
				for(int i = 0; i < 2; i++)
					destroyerShips.SetDestShip(2, 5 + i, i);
				break;
				
			case hard:
				for(int i = 0; i < 8; i++)
					regularShips.SetRegShip((i / 4) + 1, (i % 4) + 3, i);
				for(int i = 0; i < 2; i++)
					destroyerShips.SetDestShip(3, 4 + i, i);
				break;
				
			case insane: 
				for(int i = 0; i < 8; i++)
					regularShips.SetRegShip((i / 4) + 1, (i % 4) + 3, i);
				for(int i = 0; i < 4; i++)
					destroyerShips.SetDestShip(3, 3 + i, i);
				break;
			}
		}
		
		return x;
	}
	
	
	public static void Print()
	{
		Board();
		GPrint.encodeGame();
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
		board[player.GetShipX()][player.GetShipY()][0] = casilla.valor(casilla.UCMShip);
		}
		
		if(ovni.GetShipHP() > 0)
		{
		board[ovni.GetShipX()][ovni.GetShipY()][0] = casilla.valor(casilla.OVNI);
		}
		
		for(int i = 0; i < bombs.GetContador(); i++)
		{
			board[bombs.GetProyectilX(i)][bombs.GetProyectilY(i)][0] = casilla.valor(casilla.proyectil);
			board[bombs.GetProyectilX(i)][bombs.GetProyectilY(i)][1] = i;
		}
		
		for(int i = 0; i < destroyerShips.GetContador(); i++)
		{
			board[destroyerShips.GetDestX(i)][destroyerShips.GetDestY(i)][0] = casilla.valor(casilla.destroyer);
			board[destroyerShips.GetDestX(i)][destroyerShips.GetDestY(i)][1] = i;
		}
		
		for(int i = 0; i < regularShips.GetContador(); i++)
		{
			board[regularShips.GetRegX(i)][regularShips.GetRegY(i)][0] = casilla.valor(casilla.regular);
			board[regularShips.GetRegX(i)][regularShips.GetRegY(i)][1] = i;
		}
	}
	
	private static void ResetBoard() 
	{
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numCols; j++)
			{
				board[i][j][0] = -1;
			}
		}
	}
	
	public void userCommand() {
		switch(action){
		case exit:
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
	

	public void update()    
	{
		userCommand();
		killedOrNot(); 	  //comprueba estado
		computerAction(); // actualiza mov naves/proyectiles
		killedOrNot(); 	  //comprueba estado
		
//		1. Draw.
//		2. User command. El usuario puede realizar una acci�n, por ejemplo: 	moverse lateralmente o realizar un disparo. El usuario puede no hacer nada en un ciclo y dejar
//			pasar el tiempo.
//		3. Computer action. El ordenador puede decidir si una nave destructora realiza un
//			disparo o si aparece un ovni (ver m�s adelante) en la primera fila del tablero.
//		4. Update. Se actualizan los objetos que est�n en el tablero.
//		3. Computer action. El ordenador puede decidir si una nave destructora realiza un
//			disparo o si aparece un ovni (ver m�s adelante) en la primera fila del tablero.
		nOfCycles++;
	}
	
	private static String help() {
		return "move <left|right><1|2>: Moves UCM-Ship to the indicated direction.\n"
		+ "shoot: UCM-Ship launches a missile.\n" + "shockWave: UCM-Ship releases a shock wave.\n"
		+ "list: Prints the list of available ships.\n" + "reset: Starts a new game.\n"
		+ "help: Prints this help message.\n" + "exit: Terminates the program.\n"
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
			   "^__^: Harm: 1 - Shield: 3";
	}
	
	private static void reset()
	{
		if(initialize(difficulty, seed))
		{
			for(int i = 0; i < destroyerShips.GetContador(); i++)
			{
				destroyerShips.reset();
			}
			
			for(int i = 0; i < regularShips.GetContador(); i++)
			{
				regularShips.reset();
			}
			
			if(HayOvni)
			{
				ovni.reset();
			}
		}
	}
	
	private static void moveUCM(int i)
	{
		if(player.GetShipX() + i >= 0 && player.GetShipX() + i < numRows)
		{
			player.setShipPos(player.GetShipX() + i, player.GetShipY());
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
				if(player.GetShipX() + i >= 0 && player.GetShipX() + i < numRows)
				{
					player.setShipPos(player.GetShipX() + i, player.GetShipY());
				}
			}
		}
		
	}
	
	private static void shockwave()
	{
		for(int i = 0; i < destroyerShips.GetContador(); i++)
		{
			destroyerShips.shipHitByUCMShip(i, player.GetHarm());
		}
		
		for(int i = 0; i < regularShips.GetContador(); i++)
		{
			regularShips.shipHitByUCMShip(i, player.GetHarm());
		}
		
		if(HayOvni)
		{
			ovni.shipHitByUCMShip();
		}
	}
	
	private static void shoot()
	{
		if(!HayMisil)
		{
			HayMisil = true;
			misilpos[0] = player.GetShipX();
			misilpos[1] = player.GetShipY();
		}
	}
	
	private static void actualizarProyectiles() {
		updateMissil();
		updateBomb();
	}
	
	private static void updateBomb(){
		for(int i = 0; i < destroyerShips.GetContador(); i++) {
			if(destroyerShips.GetDestShipHP(i)>0)
				if(bombs.CheckBomb(i) == 1) {
					bombs.SetBombsPos(bombs.GetProyectilX(i) -1, bombs.GetProyectilY(i), i);
				}
				else {
					bombs.SetBombsPos(destroyerShips.GetDestX(i), destroyerShips.GetDestY(i), i);
				}
		}
	}
	private static void updateMissil() {
		if(HayMisil) {
			if(misilpos[1]+1 >= 0)
				misilpos[1] += 1;
			else if(misilpos[1]+1 < 0) {
				HayMisil = false;
				misilpos[0] = -1;
				misilpos[1] = -1;
			}
		}
	}
	
	private static void actualizarNaves() { // falta OVNI
		boolean shipsMoveDown = false;
		for(int i = 0; i<regularShips.GetContador(); i++) {
			if(regularShips.GetRegShipHP(i) > 0 &&   
					(regularShips.GetRegX(i)+vel >= numCols || regularShips.GetRegX(i)-vel < numCols)) {
				shipsMoveDown = true;
			}
		}
		for(int i = 0; i<destroyerShips.GetContador(); i++) {
			if(destroyerShips.GetDestShipHP(i) > 0 &&   
					(destroyerShips.GetDestX(i)+vel >= numCols || destroyerShips.GetDestX(i)-vel < numCols) ) {
				shipsMoveDown = true;
			}
		}
		if(shipsMoveDown) {
			shipsDown();
			//if(gameOver)
			//	gameOverPrint();
			shipsDir = !shipsDir;
		}
		shipsMove();
	}
	
	private static void computerAction() {
		actualizarProyectiles();
		actualizarNaves();
	}

	private static void shipsDown() {
		boolean earth = false;
		for(int i = 0; i<regularShips.GetContador(); i++) {
			if(regularShips.GetRegShipHP(i) > 0 && regularShips.GetRegY(i)+1 >= numRows) {
				earth = true;
			}
		}
		for(int i = 0; i<destroyerShips.GetContador(); i++) {
			if(destroyerShips.GetDestShipHP(i) > 0 && destroyerShips.GetDestY(i)+1 >= numRows) {
				earth = true;
			}
		}
		if(earth)
			gameOver = true;
	}
	
	private static void shipsMove() {
		int move;
		if(shipsDir) 
			move = vel;
		else
			move = -vel;
		for(int i = 0; i<regularShips.GetContador(); i++) {
			if(regularShips.GetRegShipHP(i) > 0) {
				regularShips.SetRegShip(regularShips.GetRegX(i) + move,regularShips.GetRegY(i), i);
			}
		}
		for(int i = 0; i<destroyerShips.GetContador(); i++) {
			if(destroyerShips.GetDestShipHP(i) > 0) {
				destroyerShips.SetDestShip(destroyerShips.GetDestX(i) + move,destroyerShips.GetDestY(i), i);
			}
		}
	}

	private static void killedOrNot() {
		for(int i = 0; i<bombs.GetContador(); i++) {
			if(bombs.CheckBomb(i) == 1) {
				if(bombs.GetProyectilX(i) == player.GetShipX() && bombs.GetProyectilY(i) == player.GetShipY()) 
					player.shipHitByAlien();
			}
		}
		for(int i = 0; i<regularShips.GetContador(); i++) {
			if(regularShips.GetRegShipHP(i) > 0 && misilpos[0] == regularShips.GetRegX(i) && misilpos[0] == regularShips.GetRegY(i)) {
				regularShips.shipHitByUCMShip(i, 1);
			}
		}
		for(int i = 0; i<destroyerShips.GetContador(); i++) {
			if(destroyerShips.GetDestShipHP(i) > 0 && misilpos[0] == destroyerShips.GetDestX(i) && misilpos[0] == destroyerShips.GetDestY(i)) {
				destroyerShips.shipHitByUCMShip(i, 1);
			}
		}
		if(player.GetHP() == 0)
			gameOver = true;
	}
	private static void gameOverPrint() {
	}
}
