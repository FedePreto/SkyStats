package com.univpm.oop.exception;

import com.univpm.oop.log.Log;
/**
 * Eccezione personalizzata per gestire l'errore dei dati non trovati nel Database 
 * @author Nicolò
 * @author Federico
 *
 */

@SuppressWarnings("serial")
public class CityNotFoundException extends Exception{
	/**
	 * Costruttore dell'eccezione che si occupa di aggiornare il file <b>log.txt</b>
	 * @param city {@link String} contenente il nome della città che non è stato possibile trovare nel DB
	 */
	public CityNotFoundException(String city) {
		super();
		Log.report("LA CITTA' "+city+" NON E' PRESENTE NELLO STORICO", "E' STATO RAGGIUNTO IL LIMITE MASSIMO DI 60 CALL PER MINUTO");
	}
}
