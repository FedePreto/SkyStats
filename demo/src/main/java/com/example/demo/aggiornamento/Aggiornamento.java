package com.example.demo.aggiornamento;

import java.util.TimerTask;

import com.example.demo.controller.Ricerca;

public class Aggiornamento extends TimerTask {

	Ricerca r = new Ricerca();

	public void run() {
		r.salvataggio();
	}

}
