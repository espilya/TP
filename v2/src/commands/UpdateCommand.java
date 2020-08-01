package commands;
import logic.Game;


public class UpdateCommand extends Command{

	private final static String help = "Avanza un ciclo sin realizar ninguna accion"; 
	private final static String name = "update";
	private final static String details = "[U]pdate";
	private final static String shortCut = "u";

		public UpdateCommand() {
			super(name, shortCut, details, help);	
		}



	public boolean execute(Game game) {	
		game.update();
		return true;
	}


	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
			//return new UpdateCommand();
			return this;
		else
			return null;
	}
	
}