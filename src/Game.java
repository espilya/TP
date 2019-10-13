

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
		destroyer(0), regular(1), OVNI(2), UCMShip(3), misil(4), proyectil(5);
		private final int value;

	    private casilla(int value) {
	        this.value = value;
	    }
	}

//	private int contador; 
//	private int puntuacion;
	private casilla celda;
	private command action;
	private int life = 0;
	private int nOfCycles = 0;
	private int points = 0;
	private int remainingAliens = 0;
	private boolean shockWave = true;
	private static boolean update;
	private static UCMShip player = new UCMShip();
	private static RegularShipList regularShips = new RegularShipList();
	private static DestroyerShipList destroyerShips = new DestroyerShipList();
	private static BombList bombs = new BombList();
	private static OVNI ovni = new OVNI();
	private static int[][][] board = new int[numRows][numCols][2];//[numRows][numCols][0] = valor enumerado, [numRows][numCols][1] = indice lista (es caso de que sea destroyer, regular o bomb)
	static GamePrinter Print = new GamePrinter(numRows, numCols);
	
	public void SetCommand(command x)
	{
		action = x;
	}
	
	
	public String toString(int x, int y){
		//Falta el misil de la UCMShip, falta crear el misil en el game antes de ponerlo
		String aux = "";
		if(board[x][y][0] >= 0)
		{
			aux = "";
		}
		else
		{
		//Dar a celda el enum correspondiente a board[x][y][0]
			
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
		}
		}
		
		return aux;
		}
		
	
	public void initialize() {//En funcion de la dificultad habra mas o menos aliens
		this.life = 100;
		this.nOfCycles = 1;
		this.points = 0;
		this.remainingAliens = 8;
		this.shockWave = true;
		this.nOfCycles = 1;
		player.setShipPos(numRows/2,0); 
	}
	
	public static void Print()
	{
		Board();
		Print.encodeGame();
	}
	
	private static void Board()
	{
		ResetBoard();
		//Falta el misil de la UCMShip, falta crear el misil en el game antes de ponerlo
		board[player.GetShipX()][player.GetShipY()][0] = 3;
		board[ovni.GetShipX()][ovni.GetShipY()][0] = 2;
		for(int i = 0; i < bombs.GetContador(); i++)
		{
			board[bombs.GetProyectilX(i)][bombs.GetProyectilY(i)][0] = 5;
			board[bombs.GetProyectilX(i)][bombs.GetProyectilY(i)][1] = i;
		}
		
		for(int i = 0; i < destroyerShips.GetContador(); i++)
		{
			board[destroyerShips.GetDestX(i)][destroyerShips.GetDestY(i)][0] = 0;
			board[destroyerShips.GetDestX(i)][destroyerShips.GetDestY(i)][1] = i;
		}
		
		for(int i = 0; i < regularShips.GetContador(); i++)
		{
			board[regularShips.GetRegX(i)][regularShips.GetRegY(i)][0] = 1;
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
		String errorStr = "_un mensaje de error_\n";
		return errorStr;
	}
	
	//public int[] getBattlefieldSize() {
		//int battlefield[] = {numRows, numCols};
		//return battlefield;
	//}
	
}