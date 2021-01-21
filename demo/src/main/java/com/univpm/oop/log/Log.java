package com.univpm.oop.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Classe creata per poter salvare nel file <b>log.txt</b> eventuali errori avvenuti durante l'esecuzione. 
 * @author Nicolò
 * @author Federico
 */
public class Log {
	
	/**
	 * Path del file <b>log.txt</b>
	 */
	public static String nome = "log.txt";
	
	/**
	 * Metodo che prende le due stringhe che sono state passate e le utilizza per creare un 
	 * messaggio di errore personalizzato che sarà poi scritto nel file <b>log.txt</b>. 
	 * @author Nicolò
	 * @author Federico 
	 * @param customError {@link String} contenente l'errore personalizzato scritto dal programmatore
	 * @param detail {@link String} contenente i dettagli dell'errore   
	 */
	public static void report(String customError, String detail) {
		
		String error = "\nErrore avvenuto in data :" + new Date() +
			            "\n     Causa: " + customError +
			            "\n     Dettagli: " + detail + "\n";		
		
		try {		
			
			BufferedWriter buf = new BufferedWriter(new FileWriter(new File(nome),true));
			buf.write(error);
			buf.close();	
			
		} catch (IOException e) {
			System.out.println("ERRORE IN FASE DI SCRITTURA NEL FILE LOG");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
