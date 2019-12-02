package commands;
import logic.Game;


public class UpdateCommand extends Command{

	private final static String help = ""; //falta
	private final static String name = "update";
	private final static String details = "[U]pdate";
	private final static String shortCut = "u";

		public UpdateCommand() {
			super(name, shortCut, details, help);	
		}


	//falta
	public boolean execute(Game game) {
		//No se que tiene que hacer
		return false;
	}


	public Command parse(String[] commandWords) {
		if(commandWords[0].equals(name) || commandWords[0].equals(shortCut))
			return this;
		else
			return null;
	}
	
	public String helpText()
	{
		return details + " : " + help + "\n";
	}
}