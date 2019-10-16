
//Clase para controlar la ejecuci�n del juego, preguntando al usuario qu�
//quiere hacer y actualizando la partida de acuerdo a lo que �ste indique. La clase
//Controller necesita al menos dos atributos privados:
//
//private Game game;
//private Scanner in;
//
//El objeto in sirve para leer de la consola las �rdenes del usuario. La clase Controller 
//implementa el m�todo p�blico public void run() que controla el bucle principal
//del juego. Concretamente, mientras la partida no est� finalizada, solicita �rdenes al
//usuario y las ejecuta.
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Scanner;

public class Controller { 
	private static String input;
	private static int numRows = 8; 
	private static int numCols = 9;
	static GamePrinter GPrint = new GamePrinter(numRows, numCols);
	static Scanner in = new Scanner(System.in); 

	private static void user_input() { //devolver comandos al 'Game'. Para que 
		System.out.println("Tu siguiente comando sera:");
        input = in.next();
    	input.toLowerCase();
	}
	
	public static void run(Game game, String dif, int seed) { 
		user_input();
		analize(game, input);

		//GamePrinter GP = new GamePrinter(G.GetNumRows(), G.GetNumCols());
		//do
		//{
			//System.out.println("1");
			//game.initialize(dif, seed, numRows, numCols);
			//System.out.println(GPrint.toString(game));
			//C_Main()
			//do{
				//System.out.println("2");
			//	user_input();
			//	if(game.update())
			//	{
			//		System.out.println("3");
			//		game.Print();
			//		System.out.println(GPrint.toString(game));
			//	}
			//}while(!game.Win() && !game.Exit() && !game.GameOver());
			//if(game.GameOver()){
			//	game.gameOverPrint();
			//}
			//else if(game.Win()){
			//7	game.gameWinPrint();
			//}
		
		//}while(true);
		//System.out.println(GP.toString());
		//Controller
	
		//Game
		//Game.update
		//Game
		
	}

	private static void analize(Game game, String input) {
		switch (input) {
		
		case "move":
		case "m":
			checkMov(game);
			break;
			
		case "shoot":
		case "s":
			game.SetCommand(Game.command.shoot);
			break;
			
		case "shockwave":
		case "w":
			game.SetCommand(Game.command.shockwave);

			break;
			
		case "reset":
		case "r":
			game.SetCommand(Game.command.reset);

			break;
			
		case "list":
		case "l":
			game.SetCommand(Game.command.list);

			break;
			
		case "exit":
		case "e":
			game.SetCommand(Game.command.exit);

			break;
			
		case "help":
		case "h":
			game.SetCommand(Game.command.help);
	
			break;
			
		case "none":
		case "n":
			game.SetCommand(Game.command.none);

			break;

		default: // error
			game.SetCommand(Game.command.error);

		}
	}
	
    /// Below Statement used for getting String including sentence
    //String s = ss.nextLine(); 
	/// Below Statement used for return the first word in the sentence
    //String s = ss.next();
	
	private static void checkMov(Game game) {  //move <left|right><1|2>
		
		boolean ok = true;
		String dir;
		int step;
        dir = in.next();
        dir.toLowerCase();
    	step = Integer.parseInt(in.next());
        if(dir.equals("left") || dir.equals("l")){
        	if(step == 1)
        		game.SetCommand(Game.command.moveL1);
        	else if(step == 2) 
        		game.SetCommand(Game.command.moveL2);
        	else
        		ok = false;
        }
        else if(dir.equals("right") || dir.equals("r")){
        	if(step == 1)
        		game.SetCommand(Game.command.moveR1);
        	else if(step == 2) 
        		game.SetCommand(Game.command.moveR2);
        	else
        		ok = false;
        }
        else 
        	ok = false;
        if(!ok) game.SetCommand(Game.command.error);
	}

}