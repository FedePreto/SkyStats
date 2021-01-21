package com.univpm.oop.exception;

import com.univpm.oop.log.Log;

/**
 * Classe che gestisce un errore personalizzato nel caso in cui l'azione da performare non sia consentita.
 * @author Federico 
 */
@SuppressWarnings("serial")
public class IllegalRequestException extends Exception{
	
	/**
	 * Costruttore dell'eccezione che si occupa di aggiornare il file <b>log.txt</b>
	 * @param s {@link String} contenente il nome dell'azione richiesta che non è possibile eseguire
	 */
	public IllegalRequestException(String s) {
		super();
		Log.report("LA SCELTA EFFETTUATA NON E'CONSENTITA", s + " NON E' UN'AZIONE CHE PUO ESSERE ESEGUITA");
	}
	
	

}
