
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
	private String input;
	private Game game = new Game();

	public void C_Main() { //devolver comandos al 'Game'. Para que 
		Scanner in = new Scanner(System.in); 
        input = in.next();
    	input.toLowerCase();
		analize(input);
	}

	private void analize(String input) {
		switch (input) {
		
		case "move":
		case "m":
			checkMov();
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
			
		case "":
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
	
	private void checkMov() {  //move <left|right><1|2>
		Scanner in = new Scanner(System.in); 
		boolean ok = true;
		String dir;
		int step;
        dir = in.next();
    	step = in.nextInt();
        if(dir.equals("left")){
        	if(step == 1)
        		game.SetCommand(Game.command.moveL1);
        	else if(step == 2) 
        		game.SetCommand(Game.command.moveL2);
        	else
        		ok = false;
        }
        else if(dir.equals("right")){
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