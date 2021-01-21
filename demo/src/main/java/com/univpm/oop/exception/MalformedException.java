package com.univpm.oop.exception;

import com.univpm.oop.log.Log;
/**
 * Eccezione personalizzata lanciata nel caso in cui il Json letto non è formattato correttamente
 * Inoltre si occupa di aggiornare il file <b>log.txt</b> con un errore personalizzato 
 * @author Federico
 */
@SuppressWarnings("serial")
public class MalformedException extends Exception {
	/**
	 * Costruttore dell'eccezione che si occupa di aggiornare il file <b>log.txt</b>
	 * @param s {@link String} contenente il nome del filtro che non è stato letto correttamente
	 */
	public MalformedException(String s){
		super();
		Log.report("JSON NON FORMATTATO CORRETTAMENTE","FILTRO " + s + " ERRATO");
	}

}
