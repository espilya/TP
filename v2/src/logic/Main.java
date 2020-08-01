package logic;

import java.util.Random;

import exceptions.ProgramExecuteException;

public class Main {
	int semilla;

	static Random rand = new Random();
	static Controller controller = new Controller();

	public static void main(String[] args) throws ProgramExecuteException {
		Level L;
		try {
			switch (args.length) {
			case 1:
				args[0].toLowerCase();
				L = Level.parse(args[0]);
				controller.run(L, rand.nextInt());
				break;
			case 2:
				args[0].toLowerCase();
				L = Level.parse(args[0]);
				controller.run(L, Integer.parseInt(args[1]));
				break;
			default:
				throw new ProgramExecuteException();
			}
		} catch (ProgramExecuteException e) {
			System.err.println("Exception: " + e + ":= must be just 1 or 2 arguments");
		} catch (NumberFormatException e) {
			System.err.println("Exception: " + e + ":= It's not a number. The seed must be a number");
			System.err.println("Usage ==> Main <EASY|HARD|INSANE> [seed] ");
		}
	}

}