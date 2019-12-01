import java.util.Scanner;

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
		exit = false;
		do
		{
			user_input();
			analize(game, input);
		}while(!print && !exit);
	}

	private static void analize(Game game, String input) {
		print = false;
		switch (input) {
			
		case "reset":
		case "r":
			game.reset();

			break;
			
		case "list":
		case "l":
			System.out.print(game.list());
			break;
			
		case "exit":
		case "e":
			exit = true;
			break;
			
		case "help":
		case "h":
			System.out.print(game.help());
	
			break;
			
		default:
			print = true;
			switch (input) 
			{
			
			case "move":
			case "m":
				checkMov(game);
				break;
				
			case "shoot":
			case "s":
				game.shoot();
				break;
				
			case "shockwave":
			case "w":
				game.shockwave();
	
				break;
				
			case "none":
			case "n":
			case "":
			case " ":
				break;
	
			default: // error
				System.out.print(game.error());
				print = false;
			}
		}
	}

	private static void checkMov(Game game) {  //move <left|right><1|2>
		
		boolean ok = true;
		String dir;
		int step;
        dir = in.next();
        dir.toLowerCase();
    	step = Integer.parseInt(in.next());
        if(dir.equals("left") || dir.equals("l")){
        	if(step == 1)
        		game.moveUCM(-1);
        	else if(step == 2) 
        		game.moveUCM(-2);
        	else
        		ok = false;
        }
        else if(dir.equals("right") || dir.equals("r")){
        	if(step == 1)
        		game.moveUCM(1);
        	else if(step == 2) 
        		game.moveUCM(2);
        	else
        		ok = false;
        }
        else 
        	ok = false;
        if(!ok) System.out.print(game.error());
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