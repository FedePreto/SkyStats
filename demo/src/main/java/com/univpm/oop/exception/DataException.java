package com.univpm.oop.exception;

import com.univpm.oop.log.Log;
/**
 * Classe che gestisce un errore personalizzato nel caso in cui non sono disponibili i dati nel le caratteristiche richieste.
 * @author Federico
 *
 */
@SuppressWarnings("serial")
public class DataException extends Exception{
	/**
	 * {@inheritDoc}
	 */
	public DataException() {
		super();
		Log.report("NON SONO DISPONIBILI DATI CON I FILTRI SPECIFICATI", getLocalizedMessage());
	}
	
	

}
