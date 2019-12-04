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
	private final String unknownCommandMsg = "Unknown command";
	private final String PROMPT = "";
	
	public static boolean continuar() 
	{ 
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
		Command command = null;

		System.out.println(PROMPT);
		String[] words = in.nextLine().toLowerCase().trim().split ("\\s+");
		while(command == null)
		{
			command = CommandGenerator.parseCommand(words);
			if(command == null)
				System.out.format(unknownCommandMsg);
			else
				command.execute(game);
		}
	}
}