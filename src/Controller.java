
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
	private Scanner in;
	private String input;
	private command action;
	// rand
	
	//public lee. analiza, devuelve action

	enum command {
		move, shoot, shockwave, reset, list, exit, help, none, error
	}
	enum movement {
		left1, left2, right1, right2
	}

	private String input() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine().toLowerCase();
		// System.out.println(input);
		return input;
	}
	
//	private movement checkMov() {
////		boolean ok = false;
////		String dir = "Direccion: l/r";
////		String dist = "Distancia: 1/2";
////		int aux = 1;
////		movement mov;
////		System.out.println(dir);/////////
////		do {
////		String num = input();
////		if(num == "l" && num == "r") 
////			ok = true;
////		} while(!ok);
////		if(num == "2")
////		{
////			aux = 2;
////		}
////		ok = false;
////		do {
////		String num = input();
////		if(num == "1" && num == "2") 
////			ok = true;
////		} while(!ok);
//		return ;
//	}

	private String analize(String input) {
		switch (input) {
		case "move":
		case "m":
//		checkMov();
		
		break;
			
		case "shoot":
		case "s":
			action = command.shoot;
			break;
			
		case "shockwave":
		case "w":
			action = command.shockwave;
			break;
			
		case "reset":
		case "r":
			action = command.reset;
			break;
			
		case "list":
		case "l":
			action = command.list;
			break;
			
		case "exit":
		case "e":
			action = command.exit;
			break;
			
		case "help":
		case "h":
			action = command.help;
			System.out.println(help());
			break;
			
		case "":
			action = command.none;
			break;

		default: // error
			action = command.error;
			System.out.println(error());
		}
		return input;
	}

	private String help() {
		String helpStr = "move <left|right><1|2>: Moves UCM-Ship to the indicated direction.\n"
				+ "shoot: UCM-Ship launches a missile.\n" + "shockWave: UCM-Ship releases a shock wave.\n"
				+ "list: Prints the list of available ships.\n" + "reset: Starts a new game.\n"
				+ "help: Prints this help message.\n" + "exit: Terminates the program.\n"
				+ "[none]: Skips one cycle.\r\n";
		return helpStr;
	}
	
	private String error() {
		String errorStr = "un mensaje de error\n";
		return errorStr;
	}

}