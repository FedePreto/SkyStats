package com.example.demo.aggiornamento;

import java.util.TimerTask;

import com.example.demo.DemoApplication;
import com.example.demo.services.CercaMeteo;
import com.example.demo.services.Favoriti;
import com.example.demo.src.Convertitore;
import com.google.gson.Gson;

/**
 * La classe estende la classe astratta {@link TimerTask}. Essa esegue l'aggiornamento del DB locale
 * salvando solamente il meteo delle città i cui nomi sono specificati nel file config.json
 * 
 * @author Federico
 *
 */

public class Aggiornamento extends TimerTask {
	
	/**
	 * {@inheritDoc}
	 * 
	 * @author Federico
	 */
	public void run() {
			String url = "";
			Favoriti r = new Favoriti();
			Convertitore conv = new Convertitore();
			Gson gson = new Gson();
			for (String x : r.getFavoriti()) {
				url = "http://api.openweathermap.org/data/2.5/weather?q=" + x + "&appid="+DemoApplication.key+"&units=metric&lang=it";						
				conv.salva(gson.toJson(conv.getClassFromCall(CercaMeteo.getMeteo(url))));
			}
	}

}
