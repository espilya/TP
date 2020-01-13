package commands;

import files.FileSave;
import logic.Game;
import printer.Stringifier;

public class SaveCommand extends Command {

	private final static String help = "Save the hame";
	private final static String name = "save";
	private final static String details = "sa[V]e";
	private final static String shortCut = "v";
	Stringifier printer;

	public SaveCommand() {
		super(name, shortCut, details, help);
	}

	public boolean execute(Game game) {
		// "Game successfully saved in file <nombre_proporcionado_por_el_usuario>.dat.
		// Use the load command to reload it"
		String fileName = "" + ".dat";
		FileSave.Save(fileName, game);

		return false;
	}

	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && commandWords[0].equals(name) || commandWords[0].equals(shortCut))
			return new StringifyCommand();
		else
			return null;
	}

}