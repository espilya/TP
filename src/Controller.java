
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

public class Controller { // ---------------------------------------------- segun el pdf, en el 'Controller' tiene que estar incluido el 'Game' 
	private String input;
	private Game game = new Game();

	public command getCommand() { //devolver comandos al 'Game'. Para que 
		Scanner in = new Scanner(System.in); 
        input = in.next();
    	input.toLowerCase();
		analize(input);
		return action;
	}

	private void analize(String input) {
		switch (input) {
		
		case "move":
		case "m":
			checkMov();
			break;
			
		case "shoot":
		case "s":
			game.SetCommand(game.command.shoot);
			break;
			
		case "shockwave":
		case "w":
			game.SetCommand(game.command.shockwave);

			break;
			
		case "reset":
		case "r":
			game.SetCommand(game.command.reset);

			break;
			
		case "list":
		case "l":
			game.SetCommand(game.command.list);

			break;
			
		case "exit":
		case "e":
			game.SetCommand(game.command.exit);

			break;
			
		case "help":
		case "h":
			game.SetCommand(game.command.help);
	
			break;
			
		case "":
			game.SetCommand(game.command.none);

			break;

		default: // error
			game.SetCommand(game.command.error);

		}
	}
	
    /// Below Statement used for getting String including sentence
    //String s = ss.nextLine(); 
	/// Below Statement used for return the first word in the sentence
    //String s = ss.next();
	
	private void checkMov() {  //move <left|right><1|2>
		Scanner in = new Scanner(System.in); 
		boolean ok = true;
		String dir;
		int step;
        dir = in.next();
    	step = in.nextInt();
        if(dir.equals("left")){
        	if(step == 1)
        		game.SetCommand(game.command.moveL1);
        	else if(step == 2) 
        		game.SetCommand(game.command.moveL2);
        	else
        		ok = false;
        }
        else if(dir.equals("right")){
        	if(step == 1)
        		game.SetCommand(game.command.moveR1);
        	else if(step == 2) 
        		game.SetCommand(game.command.moveR2);
        	else
        		ok = false;
        }
        else 
        	ok = false;
        if(!ok) game.SetCommand(game.command.error);
	}

}