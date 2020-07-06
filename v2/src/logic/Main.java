package logic;

import java.util.Random;

import exceptions.ProgramExecuteException;

public class Main {
	int semilla;

	static Random rand = new Random();
	static Controller controller = new Controller();

	public static void main(String[] args) throws ProgramExecuteException {
		try {
			switch (args.length) {
			case 1:
				args[0].toLowerCase();
				controller.run(args[0], rand.nextInt());
				break;
			case 2:
				args[0].toLowerCase();
				controller.run(args[0], Integer.parseInt(args[1]));
				break;
			default:
				throw new ProgramExecuteException();
			}
		} catch (Error | ProgramExecuteException e) {
			System.err.println("Exception: " + e + "level must be one of: 'EASY, HARD, INSANE'");
		} catch (NumberFormatException e) {
			System.err.println("Exception: " + e + ":= It's not a number. The seed must be a number");
			System.err.println("Usage ==> Main <EASY|HARD|INSANE> [seed]");
		}
	}

}