package com.example.demo.exception;

import log.Log;

public class Data_Exception extends Exception{
	
	public Data_Exception() {
		super();
		Log.report("NON SONO DISPONIBILI DATI CON I FILTRI SPECIFICATI", getLocalizedMessage());
	}
	
	

}
