
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
	private Game game;
//	private Scanner in;
	private String input;
//	private command action;
	// rand

//	enum command {
//		move, shoot, shockwave, reset, list, exit, help, none, error
//	}
//	enum movement {
//		left1, left2, right1, right2, none
//	}

	//Arreglar tema input y convertir mayusculas a minusculas
	private String input() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//String input = reader.readLine().toLowerCase();
		return input;
	}

	private String analize() //Determina que opcion has elegido
	{	
		do
		{
			System.out.println("Elige la opcion: Move, Shoot, Shockwave, Reset, List, Exit o Help \n");
			String input = input();
//			command action;
			switch (input) {
			case "move":
			case "m":
//				action = command.move;
				input = "Move" + SpecifyMove();
				break;
					
			case "shoot":
			case "s":
//				action = command.shoot;
				input = "Shoot";
				break;
					
			case "shockwave":
			case "w":
//				action = command.shockwave;
				input = "Shockwave";
				break;
					
			case "reset":
			case "r":
//				action = command.reset;
				input = "Reset";
				break;
					
			case "list": //Lista ovnis
			case "l":
//				action = command.list;
				input = "List";
				break;
					
			case "exit":
			case "e":
//				action = command.exit;
				input = "Exit";
				break;
					
			case "help":
			case "h":
//				action = command.help;
				input = "Help";
				break;
					
			case "": // none
//				action = command.none;
				input = "None";
				break;
		
			default: // error
//				action = command.error;
				input = "Error";
			}
		}while(input != "Error");
		
//		return action;
		return input;
	}

	
	private String SpecifyMove() {
		String mov = " ";
		mov = CheckMov("Direccion: l/r", "l", "r");
		mov += " " + CheckMov("Distancia: 1/2", "1", "2");
		return mov;
	}
	
	private String CheckMov(String text, String cond1, String cond2)
	{
		String input;
		System.out.println(text + "\n");
		boolean ok = false;
		do {
			input = input();
			if(input == cond1 || input == cond2) 
			{
				ok = true;
			}
			else
			{
				System.out.println("El valor introducido no es valido, introduzcalo de nuevo \n");
			}
			} while(!ok);
		return input;
	}

	//EL mensaje del Help, List y Error va en el game, aqui solo se determina cual es el comando introducido
	
	//private String help() {
	//	String helpStr = "move <left|right><1|2>: Moves UCM-Ship to the indicated direction.\n"
	//			+ "shoot: UCM-Ship launches a missile.\n" + "shockWave: UCM-Ship releases a shock wave.\n"
	//			+ "list: Prints the list of available ships.\n" + "reset: Starts a new game.\n"
	//			+ "help: Prints this help message.\n" + "exit: Terminates the program.\n"
	//			+ "[none]: Skips one cycle.\r\n";
	//	return helpStr;
	//}
	
}