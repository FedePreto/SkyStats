

//Classe che serve per fare il salvataggio automatico del meteo dei capoluoghi di regione
//(Il nome della classe è completamente fuori luogo da cambiare) 

package com.example.demo.controller;

import com.example.demo.src.Citta;
import com.example.demo.src.Convertitore;


public class Ricerca {
	
public static void salvataggioAutomatico() {
	String[] capoluoghi = {"L'Aquila","Potenza","Catanzaro", "Napoli","Bologna","Trieste","Roma","Genova","Milano","Ancona","Campobasso","Torino","Bari","Cagliari","Palermo","Firenze","Trento","Perugia","Aosta","Venezia"};
	String url = "";
	for(String x : capoluoghi) {
		url = "http://api.openweathermap.org/data/2.5/weather?q=" + x
				+ "&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
		
		Convertitore c = new Convertitore();
		c.stampaCitta(c.getClassFromCall(CercaMeteo.getMeteo(url),true));
	}
	
}

}
