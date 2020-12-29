package com.example.demo.aggiornamento;

import java.util.TimerTask;

import com.example.demo.services.Favoriti;

/**
 * 
 * @author Federico
 *
 */

public class Aggiornamento extends TimerTask {

	Favoriti r = new Favoriti();

	public void run() {
		r.salvataggio();
	}

}
