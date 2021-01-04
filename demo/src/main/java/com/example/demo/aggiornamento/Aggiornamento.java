package com.example.demo.aggiornamento;

import java.util.TimerTask;

import com.example.demo.services.Favoriti;

/**
 * Classe per l'aggiornamento automatico
 * @author Federico
 *
 */

public class Aggiornamento extends TimerTask {

	Favoriti r = new Favoriti();
	
	public void run() {
		r.salvataggio();
	}

}
