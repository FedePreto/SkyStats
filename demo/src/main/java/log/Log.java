package log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Classe creata per poter salvare nel log di sistema gli eventuali errori avvenuti durante l'esecuzione
 * @author Nicolò
 *
 */
public class Log {
	public static String nome = "log.txt";
	
	/**
	 * Scrive la stringa s nel file Log.<b>nome</b>
	 * @author Nicolò
	 * @param s
	 */
	public static void report(String s) {
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(new File(nome)));
			buf.write(s);
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
