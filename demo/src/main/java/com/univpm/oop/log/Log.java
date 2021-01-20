package com.univpm.oop.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Classe creata per poter salvare nel log di sistema gli eventuali errori avvenuti durante l'esecuzione
 * 
 * @author Nicolò
 * @author Federico
 *
 */
public class Log {
	/**
	 * Path del file di log
	 */
	public static String nome = "log.txt";
	
	/**
	 * Metodo che prende le due stringhe che sono state passate e le utilizza per creare un 
	 * messaggio di errore che sarà poi scritto nel file Log <b>nome</b>.
	 * 
	 * @author Nicolò
	 * @author Federico
	 * 
	 * @param customError parametro che contiene l'errore personalizzato scritto dal programmatore
	 * @param detail parametro che contiene i dettagli dell'errore
	 *   
	 */
	public static void report(String customError, String detail) {
		
		String error = "Errore avvenuto in data :" + new Date() +
			            "\n     Causa: " + customError +
			            "\n     Dettagli: " + detail + "\n";		
		
		try {
						       
			BufferedWriter buf = new BufferedWriter(new FileWriter(new File(nome),true));
			buf.write(error);
			buf.close();
			
		} catch (IOException e) {
			System.out.println("ERRORE IN FASE DI SCRITTURA");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
