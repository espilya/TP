package logic;

import java.util.Random;
import java.util.Scanner;

import commands.Command;
import commands.CommandGenerator;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import printer.BoardPrinter;
import printer.GamePrinter;
import printer.PrinterTypes;


public class Controller {
	private static String input;
	private static Scanner in = new Scanner(System.in);
	private static final String newLines = "\n\n\n\n\n\n\n\n";
	private final static String unknownCommandMsg = "Unknown command";
	private final static String PROMPT = ">";

	private static Game G;

	public static boolean continuar() {
		boolean answer;
		System.out.print(newLines);
		System.out.print("Desea continuar con el juego? (y/n)");
		System.out.print(PROMPT);
		String inp = in.next();
		inp = input.toLowerCase();
		if (inp.equals("y"))
			answer = true;
		else
			answer = false;
		return answer;
	}

	public void run(Level l, int seed) {
		Random rand = new Random(seed);

		G = new Game(l, rand);

		GamePrinter printer = PrinterTypes.BOARDPRINTER.getObject(G);

		System.out.println(G.infoToString());
		System.out.println(printer.toString(G));

		while (!G.isFinished()) {
			Command command = null;
			while (command == null) {
				try {
					System.out.print(PROMPT);
					String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
					command = CommandGenerator.parseCommand(words);
					if (command != null) {
						if (command.execute(G)) {
							System.out.println(G.infoToString());
							System.out.println(printer.toString(G));
						}
					} else {
						System.err.println(unknownCommandMsg);
						System.err.println("Exception: Comando invalido.");
					}
				} catch (CommandExecuteException ex) {
					System.err.println(ex.getMessage());
					Throwable cause = ex.getCause();
					if (cause != null) {
						System.err.println("Cause of Exception:");
						System.err.println("  " + ex.getCause());
					}
				}
			}
		}
		if (G.Lose())
			gameOverPrint();
		else if (G.Win())
			gameWinPrint();

		G.reset();
	}

	public void gameOverPrint() {
		String texto = "\n\n\n\n\n\n HAS PERDIDO \n\n\n\n\n\n";
		System.out.println(texto);
	}

	public void gameWinPrint() {
		String texto = "\n\n HAS GANADO \n\n";
		System.out.println(texto);
	}
}
