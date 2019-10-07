

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

	private int numRows = 9; 
	private int numCols = 8;
	String[][] board;
	
	public enum command {
		moveL1, moveL2, moveR1, moveR2, shoot, shockwave, reset, list, exit, help, none, error
	}

//	private int contador; 
//	private int puntuacion;
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
	
	public void SetCommand(command x)
	{
		action = x;
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
	
	
	
	public static void Board()
	{
		
	}
	
	
	
	//private  Random rand;
	public static void update()    /// ---------- leer el PDF y modificar el Update
	//El update se adapta a lo que pone en el pdf 
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
	
	public int[] getBattlefieldSize() {
		int battlefield[] = {numRows, numCols};
		return battlefield;
	}
	
}