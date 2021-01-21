package com.univpm.oop.exception;

import com.univpm.oop.log.Log;
/**
 * Eccezione personalizzata per gestire l'errore dei dati non trovati nel Database
 * 
 * @author Nicolò
 * @author Federico
 *
 */

@SuppressWarnings("serial")
public class CityNotFoundException extends Exception{
	/**
	 * {@inheritDoc}
	 * @param city 
	 */
	public CityNotFoundException(String city) {
		super();
		Log.report("LA CITTA' "+city+" NON E' PRESENTE NELLO STORICO", "E' STATO RAGGIUNTO IL LIMITE MASSIMO DI 60 CALL PER MINUTO");
	}
}
