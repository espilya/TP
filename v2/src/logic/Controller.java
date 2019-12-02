package logic;
import java.util.Scanner;

import commands.Command;
import commands.CommandGenerator;

public class Controller { 
	private static String input;
	private static int numRows = 8; 
	private static int numCols = 9;
	static GamePrinter GPrint = new GamePrinter(numRows, numCols);
	static Scanner in = new Scanner(System.in); 
	static final String str = "\n\n\n\n\n\n\n\n";
	private static boolean print;
	private static boolean exit = false;

	private static void user_input() { 
		System.out.print(">"); 
        input = in.next();
    	input = input.toLowerCase();
	}
	
	public static boolean continuar() { 
		boolean answer;
		System.out.print(str);
		System.out.print("Desea continuar con el juego? (y/n)\n>");
		System.out.print(">"); //>
		String inp = in.next();
    	inp = input.toLowerCase();
        if(inp.equals("y"))
        	answer = true;
        else
        	answer = false;
        return answer;
	}
	
	public void run(Game game) { 
		while (!game.Win() && !game.GameOver()){
			System.out.println(PROMPT);
			String[] words = in.nextLine().toLowerCase().trim().split ("\\s+");
			Command command = CommandGenerator.parse(words);
			if (command != null) {
			if (command.execute(game))
			System.out.println(game);
			}
			else {
			System.out.format(unknownCommandMsg);
			}
			}
	}

	public boolean Exit()
	{
		return exit;
	}

	public boolean Print()
	{
		return print;
	}
}