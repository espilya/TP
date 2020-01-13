package files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import logic.Game;
import printer.Stringifier;

public class FileSave {
	public static void Save(String fileName, Game game) {

		Stringifier printer = new Stringifier(game);

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(printer.toString(game));
		} catch (IOException e) {

		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				// ...
			}
		}

	}

}
