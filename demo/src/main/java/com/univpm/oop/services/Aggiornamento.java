package com.univpm.oop.services;

import java.util.TimerTask;

import com.google.gson.Gson;
import com.univpm.oop.src.DemoApplication;

/**
 * La classe estende la classe astratta {@link TimerTask}. Essa esegue l'aggiornamento del DB locale
 * salvando solamente il meteo delle città i cui nomi sono specificati nel file <b>config.json</b> 
 * @author Federico
 */

public class Aggiornamento extends TimerTask {	
	/**
	 * Il metodo si occupa di effettuare le chiamate a OpenWeather e salvare i dati nel DB 
	 * @author Federico
	 */
	public void run() {
			String url = "";
			Convertitore conv = new Convertitore();
			Gson gson = new Gson();
			for (String x : Favoriti.favoriti) {
				url = "http://api.openweathermap.org/data/2.5/weather?q=" + x + "&appid="+DemoApplication.key+"&units=metric&lang=it";						
				conv.salva(gson.toJson(conv.getClassFromCall(CercaMeteo.getMeteo(url))));
			}
	}

}
