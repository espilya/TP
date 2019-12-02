package commands;
import logic.Game;


public class MoveCommand extends Command{
	
	private final static String help = "Moves UCM-Ship to the indicated direction.\\n";
	private final static String name = "move";
	private final static String details = "[M]ove <left|right><1|2>";
	private final static String shortCut = "m";
	
	private int dir;
	private int step;

 	public MoveCommand() {
			super(name, shortCut, details, help);	
		}


	private boolean checkMov(String[] commandWords) {  //move <left|right><1|2>
		
		boolean ok = false;

		if(commandWords[2].equalsIgnoreCase("1"))
		{
	    	step = 1;
	    	ok = true;
		}
	    else if(commandWords[2].equalsIgnoreCase("2")) 
	    {
	    	step = 2;
	    	ok = true;
	    }

	    if(commandWords[1].equals("left") || commandWords[1].equals("l"))
	    {
	       	dir = -1;
	       	ok = true;
	    }
	    else if(commandWords[1].equals("right") || commandWords[1].equals("r"))
	    {
	       	dir = 1;
	       	ok = true;
	    }
        return ok;
	}


	@Override
	public boolean execute(Game game) {
		game.move(dir * step);
		return true;
	}


	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length == 3 && (commandWords[0].equals(name) || commandWords[0].equals(shortCut)) && checkMov(commandWords))
			return this;
		else
			return null;
	}
	
	public String helpText()
	{
		return details + " : " + help + "\n";
	}
}