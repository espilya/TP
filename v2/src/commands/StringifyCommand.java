package commands;

import logic.Game;
import printer.Stringifier;

public class StringifyCommand extends Command {

	private final static String help = "Serialization";
	private final static String name = "stringify";
	private final static String details = "s[T]ringify";
	private final static String shortCut = "t";
	Stringifier printer;

	public StringifyCommand() {
		super(name, shortCut, details, help);
	}

	public boolean execute(Game game) {
		printer = new Stringifier(game);
		System.out.println(printer.toString(game));
		return false;
	}

	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
//			return new StringifyCommand();
			return this;
		else
			return null;
	}

}