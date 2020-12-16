package com.example.demo.aggiornamento;

import java.util.TimerTask;

import com.example.*;

public class Aggiornamento extends TimerTask {

	Ricerca r = new Ricerca();
	Città città new Città();
	Convertitore conv= new Convertitore();

	public void aggiorna(){
		if (LocalTime.now()-città.data() >5 * 3600000)
			timer.schedule(agg, conv.differenzaDiDate(LocalTime.now(),), 5 * 3600000);
			else {
			r.salvataggio();
			timer.schedule(agg, 0, 5 * 3600000);
		}
		}
	}

}
