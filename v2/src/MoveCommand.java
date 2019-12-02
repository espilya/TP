

public class MoveCommand extends Command{
	
	private final static String help = "[M]ove <left|right><1|2>: Moves UCM-Ship to the indicated direction.\\n";
	private final static String name = "move";
	private final static String details = "";
	private final static String shortCut = "";

		public MoveCommand() {
			super(name, shortCut, details, help);	
			// TODO Auto-generated constructor stub
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


	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		//Arreglar 
		if(commandWords[0] == name)
			return this;
		else
			return null;
	}
	
	public static String Help()
	{
		return help;
	}
}