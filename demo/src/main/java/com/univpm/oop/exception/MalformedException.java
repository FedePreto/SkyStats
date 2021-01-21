package com.univpm.oop.exception;

import com.univpm.oop.log.Log;

@SuppressWarnings("serial")
public class MalformedException extends Exception {
	
	public MalformedException(String s){
		super();
		Log.report("JSON NON FORMATTATO CORRETTAMENTE","FILTRO " + s + " ERRATO");
	}

}
