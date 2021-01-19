package com.example.demo.controller;
import com.example.demo.statistiche.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.example.demo.DemoApplication;
import com.example.demo.exception.*;



//Classe non utilizzata momentaneamente (Serve per gestire le call da Postaman alla nostra API)

 import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam; 
 import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.Data_Exception;
import com.example.demo.model.Citta;
import com.example.demo.services.*;
import com.example.demo.src.Convertitore;
import com.example.demo.statistiche.Stat;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import log.Log;

 /**
  * Classe che gestisce le chiamate al nostro Server
  * @author Federico
  * @author Nicolò
  *
  */
 
 @RestController public class Controller {
	 
 /**
  * Call che restituisce il meteo di una citta prelevandolo dal DB oppure direttamente da OpenWeather(dipende dalla richiesta dell'utente)
  * 
  * @author Federico
  * 
  * @param city	 parametro che permette di scegliere il nome della citta della quale si vuole conoscere il meteo
  * @param agg  parametro che permette di scegliere se prelevare il meteo dal DB oppure da OpenWeather
  *  
  * @return c  parametro che contiene il meteo della città scelta
  */

 public JsonElement getWeather(String city,int i) {
	 String url = "";
	 Citta c = new Citta();
	 Convertitore conv = new Convertitore(); 
	 if(i<=60) {
		 try { 
			 int ID = Integer.parseInt(city);
			 url = "http://api.openweathermap.org/data/2.5/weather?id=" + ID + "&appid="+DemoApplication.key+"&units=metric&lang=it";
				 
		 	}catch (NumberFormatException e) {
			 url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + ",IT&appid="+DemoApplication.key+"&units=metric&lang=it"; 
		 	}	 
			String meteo = CercaMeteo.getMeteo(url);
			c = conv.getClassFromCall(meteo);
			
	 }else {
		 
		 c = conv.findInJson(city);
		 
	 }
	 Gson gson = new Gson();
	 //String json = gson.toJson(c,Citta.class);
	 
	 return gson.toJsonTree(c);
	 
 }

 @PostMapping("/Weather")
 public JsonObject getWeather(@RequestBody JsonObject body)throws Data_Exception {
	 JsonArray citta = body.get("citta").getAsJsonArray();
	 JsonArray ret = new JsonArray();
	 for (int i = 0; i < citta.size(); i++) {
		
		ret.add(getWeather(citta.get(i).getAsString(),i));
	}
	JsonObject jo = new JsonObject();
	jo.add("meteo", ret);
	return jo;
 }
 
/**
 * Call che restituisce le statistiche di una citta 
 * 
 * @author Federico
 * @author Nicolò
 * 
 * @param body JsonObject contenente le specifiche della statistica che si vuole eseguire
 
 * @return JsonObject contenente tutte le informazioni riguardanti le statistiche
 * 
 */

@PostMapping("/Stat")
 public JsonObject getStat(@RequestBody JsonObject body)throws Data_Exception {
	
	Convertitore conv = new Convertitore(); 
	Stat s = new Stat();
	ArrayList<Citta> citta = conv.JsonToCitta(); //Legge tutto lo storico e lo memorizza nell'ArrayList
	citta = Filtra(body, citta);	
	Double[][] dati = s.getValues(citta);
	if(dati==null)
		throw new Data_Exception();
	double mediaP = s.getMedia(dati[0]);
	double mediaU = s.getMedia(dati[1]);
	double mediaT = s.getMedia(dati[2]);
	double varianzaP = s.getVarianza(dati[0]);
	double varianzaU = s.getVarianza(dati[1]);
	double varianzaT = s.getVarianza(dati[2]);
	JsonObject JsonReturn = new JsonObject();
	JsonReturn.addProperty("Nome", citta.get(0).getNome());
	JsonReturn.addProperty("Media Umidità", new DecimalFormat("#.##").format(mediaU));
	JsonReturn.addProperty("Varianza Umidità", new DecimalFormat("#.##").format(varianzaU));
	JsonReturn.addProperty("Media Pressione",new DecimalFormat("#.##").format(mediaP));
	JsonReturn.addProperty("Varianza Pressione", new DecimalFormat("#.##").format(varianzaP));
	JsonReturn.addProperty("Media Temperatura",  new DecimalFormat("#.##").format(mediaT));
	JsonReturn.addProperty("Varianza Temperatura",  new DecimalFormat("#.##").format(varianzaT));
	return JsonReturn;
	}

/**
 * Dato un body in JsonObject, "/Max" è una call in Post che restituisce un JsonObject contentente le citta con i valori massimi di temperatura, umidita e pressione nel database
 * @author Federico  * 
 * @param body
 * @param type Tipo di range di tempo(Giornaliero, Settimanale, Mensile, Annuale o Customizzato) 
 *
 * @return JsonObject contenente tutti le citta con i valori massimi nel Database
 */
 @GetMapping("/Max")
 public JsonObject getMax(@RequestParam(name = "Periodo", defaultValue = "Giornaliero") String period) {
	 
	 Convertitore conv = new Convertitore(); 
	 Stat s = new Stat();
	 ArrayList<Citta> citta = conv.JsonToCitta(); //Legge tutto lo storico e lo memorizza nell'ArrayList
	 Tempo t = new Tempo();
	 t.filtra(citta, period);
	 return s.getMax(citta);
	 
	 
    /* String type = body.get("type").getAsString();
     Date[] date = new Date[2];
     if(type.equals("Customizzato")) {
		 JsonArray range = body.getAsJsonArray("range");
		 String inizio = range.get(0).getAsString();
		 String fine = range.get(1).getAsString();
		 date = menuDate(inizio,fine);
		 }
     else {
    	 date = menuDate(type);
    	 };
	 Stat s = new Stat();
	 System.out.println(date[0] + " " + date[1]);
	 return s.getMax(date[0],date[1]);*/
 }
 /**
  * Dato un body in JsonObject, "/Min" è una call in Post che restituisce tutte le citta con i valori minimi di pressione, umidità e Temperatura nel database
  * @author Federico
  * 
  * 
  * @param body
  * @param type Range di tempo(Giornaliero, Settimanale, Mensile, Annuale o Customizzato)
  * @param range Nel caso in cui type=Customizzato allora range contiente la data di inizio e di fine del range di tempo
  * @return JsonObject contenente tutte le citta con i valori minimi di pressione, umidità e temperatura nel Database
  */
 @GetMapping("/Min")
 public JsonObject getMin(@RequestParam(name = "Periodo", defaultValue = "Giornaliero") String period) {
     Convertitore conv = new Convertitore(); 
	 Stat s = new Stat();
	 ArrayList<Citta> citta = conv.JsonToCitta(); //Legge tutto lo storico e lo memorizza nell'ArrayList
	 Tempo t = new Tempo();
	 t.filtra(citta, period);
	 return s.getMax(citta);
 }
 /*
     String type = body.get("type").getAsString();
     Date[] date = new Date[2];
     if(type.equals("Customizzato")) {
		 JsonArray range = body.getAsJsonArray("range");
		 String inizio = range.get(0).getAsString();
		 String fine = range.get(1).getAsString();
		 date = menuDate(inizio,fine);
		 }
     else {
    	 date = menuDate(type);
    	 };
	 Stat s = new Stat();
	 return s.getMin(date[0],date[1]);
}

 
/**
 * Call che dato un JsonBody in post restituisce un altro JsonBody contenente tutte le informazioni riguardanti le citta con valori minimi di umidita, pressione e temperatura
 * @author Federico
 *  
 * @param body
 * @param zone zona geografica
 * @param type Range di tempo (Giornaliero, Settimanale, Mensile, Annuale o Customizzato)
 * @param range Nel caso in cui type = Customizzato allora range conterrà il range di date 
 * @return JsonObject contenente le città con i valori minimi di umidità, pressione e temperatura
 */
 /*
@PostMapping("/ZoneGeo")
 public JsonObject getZoneGeo(@RequestBody JsonObject body) {
	 String zone = body.get("zone").getAsString();
     String type = body.get("type").getAsString();
     Date[] date = new Date[2];
     if(type.equals("Customizzato")) {
		 JsonArray range = body.getAsJsonArray("range");
		 String inizio = range.get(0).getAsString();
		 String fine = range.get(1).getAsString();
		 date = menuDate(inizio,fine);
		 }
     else {
    	 date = menuDate(type);
    	 };
	 Stat stat = new Stat();
	 Double[] valP =  stat.getDataByLocation(date[0], date[1], zone, true);
	 Double[] valU = stat.getDataByLocation(date[0], date[1], zone, false);
	 double mediaP = stat.getMedia(valP);
	 double mediaU = stat.getMedia(valU);
	 double varianzaU = stat.getVarianza(valU);
	 double varianzaP = stat.getVarianza(valP);
	 JsonObject JsonReturn = new JsonObject();
	 if(valP == null && valU == null) {
		 JsonReturn.addProperty("Nessun valore trovato nel range di tempo specificato","");
		 return JsonReturn;
	 }
	 else {
		 JsonReturn.addProperty("Zona", zone);
		 JsonReturn.addProperty("Media Umidità", new DecimalFormat("#.##").format(mediaU));
		 JsonReturn.addProperty("Varianza Umidità", new DecimalFormat("#.##").format(varianzaU));
		 JsonReturn.addProperty("Media Pressione",new DecimalFormat("#.##").format(mediaP));
		 JsonReturn.addProperty("Varianza Pressione", new DecimalFormat("#.##").format(varianzaP));
		 return JsonReturn;
		 }		
 }
 */
/**
 * Call per gestire i favoriti
 * @author Federico
 * 
 * @param action 	<br>Se "Aggiungi" : aggiunge ai favoriti <b>name</b>
 * 					<br>Se "Rimuovi" : rimuove dai favoriti <b>name</b>
 * 					<br>Se "Stampa" : ritorna i favoriti
 * @param name Oggetto di action
 * @return ritorna JsonObject con i log di alcune azioni
 */
 @GetMapping("/Fav")
 public JsonObject Favoriti(@RequestParam(name = "Action")String action,@RequestParam(name = "Name", defaultValue = "")String name) {
	 Favoriti fav = new Favoriti();
	 switch(action) {
	 case "Aggiungi":
		 fav.addFavoriti(name);
		 return fav.stampaFavoriti();
	 case "Rimuovi":
		 fav.removeFavoriti(name);
		 return fav.stampaFavoriti();
	 case "Stampa":
		return fav.stampaFavoriti();
	default:
		JsonObject errObj = new JsonObject();
		errObj.addProperty("La scelta selezionata non è consentita","");
		return errObj;		 
	 }

 }
 
/**
 * @author Nicolò		
 * @param time
 * @return
 *//*
 public static Date[] menuDate(String time) {
	 Date inizio = new Date();
	 Date fine = new Date();
	 LocalDate l;
		switch (time) {
		case "Giornaliero":
			fine = new Date();
			l = LocalDate.now().minusDays(1);
			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
			// stampaStat(inizio,fine,citta);
			break;
		case "Settimanale":
			fine = new Date();
			l = LocalDate.now().minusDays(7);
			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
			// stampaStat(inizio,fine,citta);
			break;
		case "Mensile":
			fine = new Date();
			l = LocalDate.now().minusDays(30);
			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
			// stampaStat(inizio,fine,citta);
			break;
		case "Annuale":
			fine = new Date();
			l = LocalDate.now().minusDays(365);
			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
			// stampaStat(inizio,fine,citta);
			break;
		}
				
		Date[] date = {inizio, fine};
		return date;
	}

 /**
  * Converte un range di date da String a Date
  * @author Federico
  * 
  * @param start Inizio range
  * @param end Fine range
  * @return Array di Date contenente le date di inizio e fine
  *//*
 public static  Date[] menuDate(String start,String end) {
	 Date inizio = new Date();
	 Date fine = new Date();
	 try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm");
			inizio = formato.parse(start);
			fine = formato.parse(end);
		}catch (ParseException e1) {
			try {
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
				inizio = formato.parse(start);
				fine = formato.parse(end);					
				}catch (ParseException e2) {
					Log.report(new Date()+"-"+e2.getMessage());
				}
		}
	 Date[] date = {inizio, fine};
	 return date;
		}
 
 */
 /**
  * Metodo utile per la lettura mediante JsonObject dei filtri da applicare al DataBase locale,
  * il filtraggio può essere effettuato per città, periodo temporale e zone geografiche
  * 
  * @author Nicolò
  * 
  * @param body {@link JsonObject} contenente il Body per la gestione dei filtri da applicare
  * 
  * @return {@link ArrayList} di oggeti di tipo {@link Filtro} contenente i filtri da applicare alle statistiche
  */
 public static ArrayList<Citta> Filtra(JsonObject body,ArrayList<Citta>citta){
	 JsonObject jo = body.get("filtri").getAsJsonObject();
	 JsonObject jobject = jo.get("tempo").getAsJsonObject();
	 if(jobject.get("attivo").getAsBoolean()) {
		  Tempo t = new Tempo();
		  citta = t.filtra(citta, jobject.get("filtro").getAsString());
		}	 
	 jobject = jo.get("nome").getAsJsonObject();
	 if(jobject.get("attivo").getAsBoolean()) {
		NomeId n = new NomeId();
		citta = n.filtra(citta, jobject.get("filtro").getAsString());
		return citta; //Se viene effettuato il filtraggio per nome non può essere effettuato anche il filtraggio per ZoneGeo
		}
	 jobject = jo.get("ZoneGeografiche").getAsJsonObject();
	 if(jobject.get("attivo").getAsBoolean()) {
		 ZoneGeografiche z = new ZoneGeografiche();
		 citta = z.filtra(citta, jobject.get("filtro").getAsString());
		 }
	 return citta;
	}
 
 
 }
 
 