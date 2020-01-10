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
		if(commandWords.length == 1 && commandWords[0].equals(name) || commandWords[0].equals(shortCut))
			return new UpdateCommand();
		else
			return null;
	}
	
}