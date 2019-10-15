

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



//public static final @tipo@ @NOMBRE@  = @valor@;

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

	private final int damage = 1;
	
	private static int numRows = 9; 
	private static int numCols = 8;
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
	static GamePrinter GPrint = new GamePrinter(numRows, numCols);
	
	private static String difficulty;
	
	private static int[][][] board = new int[numCols][numRows][2];//[numCols][numRows][0] = valor enumerado, [numCols][numRows][1] = indice lista (en caso de que sea destroyer, regular o bomb)
	
	private static boolean gameOver = false;
	private static boolean gameWin;
	private static boolean shipsDir = true;
	private static boolean print;
	private static boolean shockWave = true;
	private static boolean update;
	private static boolean HayOvni;
	private static boolean HayMisil; //Hay o no un misil que no ha impactado
	private static boolean exit;
	
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
		
	
	public static boolean initialize(String dificultad, int seed) {
		boolean x = false;
		exit = false;
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
			semilla = seed;
			
			//if(numrand(de 0 a 9) < level.getProbOvni() * 10)
			//{
			//this.remainingAliens ++;
			//ovni.setShipPos(8);
			//HayOvni = true;
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
			if(bombs.CheckBomb(i) == 1)
			{
				board[bombs.GetProyectilX(i)][bombs.GetProyectilY(i)][0] = casilla.valor(casilla.proyectil);
				board[bombs.GetProyectilX(i)][bombs.GetProyectilY(i)][1] = i;
			}
		}
		
		for(int i = 0; i < destroyerShips.GetContador(); i++)
		{
			if(destroyerShips.GetDestShipHP(i) > 0)
			{
				board[destroyerShips.GetDestX(i)][destroyerShips.GetDestY(i)][0] = casilla.valor(casilla.destroyer);
				board[destroyerShips.GetDestX(i)][destroyerShips.GetDestY(i)][1] = i;
			}
		}
		
		for(int i = 0; i < regularShips.GetContador(); i++)
		{
			if(regularShips.GetRegShipHP(i) > 0)
			{
				board[regularShips.GetRegX(i)][regularShips.GetRegY(i)][0] = casilla.valor(casilla.regular);
				board[regularShips.GetRegX(i)][regularShips.GetRegY(i)][1] = i;
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
				board[i][j][0] = -1;
			}
		}
	}
	
	public void userCommand() {
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
		userCommand();
		if(print = true)
		{
			updateMissil();
			killedOrNot(); 	//comprueba estado
			computerAction(); // actualiza mov naves/proyectiles
			killedOrNot(); 	  //comprueba estado
			nOfCycles++;
		}
		
//		1. Draw.
//		2. User command. El usuario puede realizar una acci�n, por ejemplo: 	moverse lateralmente o realizar un disparo. El usuario puede no hacer nada en un ciclo y dejar
//			pasar el tiempo.
//		3. Computer action. El ordenador puede decidir si una nave destructora realiza un
//			disparo o si aparece un ovni (ver m�s adelante) en la primera fila del tablero.
//		4. Update. Se actualizan los objetos que est�n en el tablero.
//		3. Computer action. El ordenador puede decidir si una nave destructora realiza un
//			disparo o si aparece un ovni (ver m�s adelante) en la primera fila del tablero.
		return print;
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
		if(initialize(difficulty, semilla))
		{
			destroyerShips.reset();
			regularShips.reset();
			bombs.reset();
			
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
	
	public boolean Win()
	{
		gameWin = true;
		for(int i = 0; i < regularShips.GetContador() && !gameWin; i++) 
		{
			if(regularShips.GetRegShipHP(i) > 0) 
			{
				gameWin = false;		
			}
		}
		
		for(int i = 0; i < destroyerShips.GetContador() && !gameWin; i++) 
		{
			if(destroyerShips.GetDestShipHP(i) > 0) 
			{
				gameWin = false;		
			}
		}
		
		if(HayOvni)
		{
			gameWin = false;
		}
		
		return gameWin;
	}
	
	private static void updateBomb(){
		for(int i = 0; i < destroyerShips.GetContador(); i++) {
			if(destroyerShips.GetDestShipHP(i) > 0 && bombs.GetProyectilY(i) + 1 < numRows)
			{
				if(bombs.CheckBomb(i) == 1) {
					bombs.SetBombsPos(bombs.GetProyectilX(i), bombs.GetProyectilY(i) + 1, i);
				}
				else {
					//if(num rand(0 - 9) < FrecDisp * 10)
					bombs.SetBombsPos(destroyerShips.GetDestX(i), destroyerShips.GetDestY(i) + 1, i);
				}
			}
			else
			{
				
			}
		}
	}
	
	private static void updateMissil() {
		if(HayMisil) {
			if(misilpos[1] - 1 >= 0)
				misilpos[1] -= 1;
			else 
				HayMisil = false;	
		}
	}
	
	private static void actualizarNaves() { 
		boolean shipsMoveDown = false;
		for(int i = 0; i < regularShips.GetContador(); i++) {
			if(regularShips.GetRegShipHP(i) > 0 &&   
					(regularShips.GetRegX(i)+vel >= numCols || regularShips.GetRegX(i)-vel < 0)) {
				shipsMoveDown = true;
			}
		}
		for(int i = 0; i<destroyerShips.GetContador(); i++) {
			if(destroyerShips.GetDestShipHP(i) > 0 &&   
					(destroyerShips.GetDestX(i)+vel >= numCols || destroyerShips.GetDestX(i)-vel < 0) ) {
				shipsMoveDown = true;
			}
		}
		
		if(HayOvni)
		{
			ovni.setShipPos(ovni.GetShipY() - vel);
			if(ovni.GetShipY() < 0)
			{
				HayOvni = false;
			}
		}
		
		if(shipsMoveDown) {
			shipsDown();
			//if(gameOver)
			//	gameOverPrint();
			shipsDir = !shipsDir;
		}
		else
		{
			shipsMove();
		}
	}
	
	//Mirar ovni computerAction()
	private static void computerAction() {
		actualizarNaves();
		updateBomb();
		//if(numrand(de 0 a 9) < level.getProbOvni() * 10 && !HayOvni)
		//{
		//this.remainingAliens ++;
		//ovni.setShipPos(8);
		//HayOvni = true;
		//}
	}

	private static void shipsDown() {
		boolean earth = false;
		for(int i = 0; i < regularShips.GetContador(); i++) {
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
			move = -vel;
		else
			move = vel;
		
		for(int i = 0; i < regularShips.GetContador(); i++) 
		{
			if(regularShips.GetRegShipHP(i) > 0) 
			{
				regularShips.SetRegShip(regularShips.GetRegX(i) + move, regularShips.GetRegY(i), i);		
			}
		}
		
		
		for(int i = 0; i<destroyerShips.GetContador(); i++) 
		{
			if(destroyerShips.GetDestShipHP(i) > 0) 
			{
				destroyerShips.SetDestShip(destroyerShips.GetDestX(i) + move, destroyerShips.GetDestY(i), i);
			}
		}
	}

	private static void killedOrNot() 
	{
		for(int i = 0; i < bombs.GetContador(); i++) {
			if(bombs.CheckBomb(i) == 1) {
				if(bombs.GetProyectilX(i) == misilpos[0] && bombs.GetProyectilY(i) == misilpos[1])
				{
					HayMisil = false;
					bombs.deletebomb(i);
				}
				else
				{
					if(bombs.GetProyectilX(i) == player.GetShipX() && bombs.GetProyectilY(i) == player.GetShipY()) 
					{
						player.shipHitByAlien();
						bombs.deletebomb(i);
					}
				}
			}
		}
		
		for(int i = 0; i < regularShips.GetContador(); i++) 
		{
			if(regularShips.GetRegShipHP(i) > 0 && misilpos[0] == regularShips.GetRegX(i) && misilpos[0] == regularShips.GetRegY(i)) 
			{
				regularShips.shipHitByUCMShip(i, 1);
				HayMisil = false;
			}
		}
		
		for(int i = 0; i<destroyerShips.GetContador(); i++) 
		{
			if(destroyerShips.GetDestShipHP(i) > 0 && misilpos[0] == destroyerShips.GetDestX(i) && misilpos[0] == destroyerShips.GetDestY(i)) 
			{
				destroyerShips.shipHitByUCMShip(i, 1);
				HayMisil = false;
			}
		}
		
		if(ovni.GetShipHP() > 0 && ovni.GetShipX() == misilpos[0] && ovni.GetShipY() == misilpos[1])
		{
			ovni.shipHitByUCMShip();
			HayOvni = false;
			shockWave = true;
		}
		
		
		if(player.GetHP() == 0)
		{
			gameOver = true;
		}
	}
	
	public static void gameOverPrint() 
	{
		
	}
	
	public void gameWinPrint()
	{
		
	}
	
	public boolean GameOver()
	{
		return this.gameOver;
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
