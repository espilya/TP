package commands;

import logic.Game;

public class FalseCommand extends Command {

	private final static String help = "Command user for incorrect input.\n" ;
	private final static String name = "False Command";
	private final static String details = "false";
	private final static String shortCut = "";

	public FalseCommand() {
			super(name, shortCut, details, help);	
			// TODO Auto-generated constructor stub
		}

	@Override
	public boolean execute(Game game) {
//		System.err.print("\t\t  +-------------------------------------+\n");
//		System.err.print("\t\t  |\t  <|> Incorrect Input <|>       |\n");
//		System.err.print("\t\t  |        \tTry again.              |\n");
//		System.err.print("\t\t  +-------------------------------------+\n");
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length == 1 && commandWords[0].equals(name) || commandWords[0].equals(shortCut))
			return this;
		else
			return null;
	}
	
	public String helpText()
	{
		return details + " : " + help + "\n";
	}

}
