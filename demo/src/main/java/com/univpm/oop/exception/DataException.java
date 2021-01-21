package com.univpm.oop.exception;

import com.univpm.oop.log.Log;
/**
 * Classe che gestisce un errore personalizzato nel caso in cui non siano disponibili i dati con le caratteristiche richieste.
 * @author Federico
 */
@SuppressWarnings("serial")
public class DataException extends Exception{
	/**
	 * Costruttore dell'eccezione che si occupa di aggiornare il file <b>log.txt</b>
	 */
	public DataException() {
		super();
		Log.report("NON SONO DISPONIBILI DATI CON I FILTRI SPECIFICATI", getLocalizedMessage());
	}
	
	

}
