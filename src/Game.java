

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

	
//	private int contador; 
//	private int puntuacion;
	private int life = 0;
	private int nOfCycles = 0;
	private int points = 0;
	private int remainingAliens = 0;
	private boolean shockWave = true;
	private static Controller command = new Controller();
	private static boolean update;
	
	public static  void game()
	{
		update(command.getCommand());
	}
	
	
	
	//private  Random rand;
	private static void update(Controller.command action)
	{
		update = true;
		switch(action)
		{//primero se efectua la accion del usuario y despues actuan las naves
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
		
		
		case exit:
			update =false;
		break;
		
		case help:
			System.out.println(help());
			update =false;
		break;
		
		case error:
			System.out.println(error());
			update =false;
		break;
		
		case list:
			update =false;
		break;
		
		case reset:
			update =false;
		break;
		}
		if(update)
		{
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
	
}