

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


//*** getDato(){
//	return ***
//}

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
	
	private int semilla;
	private boolean[] hadisparado = new boolean[4];
	private final int damage = 1;
	private static int[] misilpos = new int[2];
	private static boolean MisilValido; //Hay o no un misil que no ha impactado
	private casilla celda;
	private command action;
	private double frecDisp;
	private static int life;
	private int vel;
	private int nOfCycles;
	private int points;
	private int remainingAliens = 0;
	private boolean shockWave = true;
	private static boolean update;
	private static UCMShip player = new UCMShip();
	private static RegularShipList regularShips = new RegularShipList();
	private static DestroyerShipList destroyerShips = new DestroyerShipList();
	private static BombList bombs = new BombList();
	private static OVNI ovni = new OVNI();
	private static Level level = new Level();
	private static int[][][] board = new int[numRows][numCols][2];//[numRows][numCols][0] = valor enumerado, [numRows][numCols][1] = indice lista (en caso de que sea destroyer, regular o bomb)
	static GamePrinter GPrint = new GamePrinter(numRows, numCols);
	
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
		
	
	public boolean initialize(String dificultad, int semilla) {//En funcion de la dificultad habra mas o menos aliens
		boolean x = false;
		
		if(level.setDifficulty(dificultad))
		{
			x = true;
			MisilValido = false;
			this.life = 3;
			this.nOfCycles = 1;
			this.points = 0;
			this.shockWave = true;
			this.nOfCycles = 1;
			player.setShipPos(numRows/2,0); 
			this.vel = - vel;
			this.frecDisp = level.getFrecDisparo();
			player.setShipPos(7, 4);
			this.semilla = semilla;
			
			//if(numrand(de 0 a 9) < level.getProbOvni() * 10)
			//{
			//this.remainingAliens ++;
			//ovni.setShipPos(0, 8);
			//}
			//else
			//{
			//ovni.shipHitByUCMShip(damage);
			//}
			
			this.remainingAliens += level.getNumberDestroyerShip() + level.getNumberRegularShip();
			
			destroyerShips.SetContador(level.getNumberDestroyerShip());
			regularShips.SetContador(level.getNumberRegularShip());
			
			for(int i = 0; i < 4; i++)
			{
				hadisparado[i] = false;
			}
			
			switch(level.getDifficulty())
			{
			case easy:
				for(int i = 0; i < 4; i++)
				{
					regularShips.SetRegShip(1, 4 + i, i);
				}
				for(int i = 0; i < 2; i++)
				{
					destroyerShips.SetDestShip(2, 5 + i, i);
				}
				break;
				
				
			case hard:
				for(int i = 0; i < 8; i++)
				{
					regularShips.SetRegShip((i / 4) + 1, (i % 4) + 3, i);
				}
				for(int i = 0; i < 2; i++)
				{
					destroyerShips.SetDestShip(3, 4 + i, i);
				}
				break;
				
				
			case insane: 
				for(int i = 0; i < 8; i++)
				{
					regularShips.SetRegShip((i / 4) + 1, (i % 4) + 3, i);
				}
				for(int i = 0; i < 4; i++)
				{
					destroyerShips.SetDestShip(3, 3 + i, i);
				}
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
		if(MisilValido)
		{
			board[misilpos[0]][misilpos[1]][0] = casilla.valor(casilla.misil);
		}
		
		if(life > 0)
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
	
	
	//private  Random rand;
	public void update()    
	{
		switch(action)
		{//primero se efectua la accion del usuario y despues actuan las naves
		case exit:
		break;
		
		case help:
			System.out.println(help());
		break;
		
		case error:
			System.out.println(error());
		break;
		
		case list:
		break;
		
		case reset:
		break;
		default:
			//Actualizar Proyectiles
			switch(action)
			{
			case moveL1:
				break;
				
				case moveL2:
				break;
				
				case moveR1:
				break;
				
				case moveR2:
				break;
				
				case shockwave:
				break;
				
				case none:
				break;
				
				case shoot:
				break;
				default: 
			}
			//Actualizar naves
		}
	}
	
	private static String help() {
		String helpStr = "move <left|right><1|2>: Moves UCM-Ship to the indicated direction.\n"
				+ "shoot: UCM-Ship launches a missile.\n" + "shockWave: UCM-Ship releases a shock wave.\n"
				+ "list: Prints the list of available ships.\n" + "reset: Starts a new game.\n"
				+ "help: Prints this help message.\n" + "exit: Terminates the program.\n"
				+ "[none]: Skips one cycle.\r\n";
		return helpStr;
	}
	
	private static String error() {
		String errorStr = "El comando introducido no es valido\n";
		return errorStr;
	}

	
}