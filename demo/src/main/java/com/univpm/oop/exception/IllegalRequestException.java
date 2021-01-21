package com.univpm.oop.exception;

import com.univpm.oop.log.Log;

/**
 * Classe che gestisce un errore personalizzato nel caso in cui l'azione da performare non è consentita.
 * @author Federico *
 */
@SuppressWarnings("serial")
public class IllegalRequestException extends Exception{
	/**
	 * {@inheritDoc}
	 */
	public IllegalRequestException(String s) {
		super();
		Log.report("LA SCELTA EFFETTUATA NON E'CONSENTITA", s + " NON E' UN'AZIONE CHE PUO ESSERE ESEGUITA");
	}
	
	

}
