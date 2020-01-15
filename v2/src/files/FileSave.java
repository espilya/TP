package files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import logic.Game;
import printer.Stringifier;

public class FileSave {
	public static void Save(String fileName, String text) {

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(text);
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
