package commands;

import logic.Game;

public class NoneCommand extends Command {

	private final static String help = "None command. When you decided to wait.\n" ;
	private final static String name = "none";
	private final static String details = "[N]one";
	private final static String shortCut = "n";

	public NoneCommand() {
			super(name, shortCut, details, help);	
			// TODO Auto-generated constructor stub
		}

	
	public boolean execute(Game game) {
		System.out.print("You've decided to wait..\n\n\n\n");
		game.update();
		return true;
	}


	public Command parse(String[] commandWords) {
		if(commandWords.length == 1 && (commandWords[0].equals(name) || commandWords[0].equals(shortCut)))
			return this;
		else
			return null;
	}
	
	public String helpText()
	{
		return details + " : " + help + "\n";
	}
}
