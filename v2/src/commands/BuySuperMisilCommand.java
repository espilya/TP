package commands;

import logic.Game;

public class BuySuperMisilCommand extends Command {

	private final static String help = "Buy a supermisil.\n";
	private final static String name = "buysupermisil";
	private final static String details = "[B]uy[S]upermisil";
	private final static String shortCut = "bs";

	public BuySuperMisilCommand() {
		super(name, shortCut, details, help);
	}

	public boolean execute(Game game) {
		if (game.buySuperMisil())
			System.out.println("SuperMisil comprado con exito \n");
		else
			System.out.println("No se ha podido comprar SuperMisil con exito, no dispone de los puntos suficientes \n");
		return false;
	}

	public Command parse(String[] commandWords) {
		if (commandWords.length == 1 && (commandWords[0].equals(name) || commandWords[0].equals(shortCut)))
			return new BuySuperMisilCommand();
		else
			return null;
	}

}