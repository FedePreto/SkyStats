package com.example.demo.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



//Classe non utilizzata momentaneamente (Serve per gestire le call da Postaman alla nostra API)

 import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam; 
 import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Citta;
import com.example.demo.services.*;
import com.example.demo.src.Convertitore;
import com.example.demo.statistiche.Stat;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

 
 
 @RestController public class Controller {
 
 String url = "";//"C:\\Users\\OEM\\Downloads\\demo\\Esempio chiamata.txt";

 /**
  * 
  * @author Federico
  * 
  * @param city
  * @param agg
  * @return
  */
 @GetMapping("/Weather")
 public Citta getWeather(@RequestParam(name = "Citta", defaultValue = "Rome") String city,@RequestParam(name = "Aggiornamento", defaultValue = "Si")String agg) {
	 Citta c = new Citta();
	 Convertitore conv = new Convertitore(); 
	 if(agg.equals("Si")) {
		 try { int ID = Integer.parseInt(city); url =
				 "http://api.openweathermap.org/data/2.5/weather?id=" + ID +
				 "&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
				 
				  } catch (NumberFormatException e) {
				 
				 url = "http://api.openweathermap.org/data/2.5/weather?q=" + city +
				 ",IT&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
				 
				 }
				 
				 String meteo = CercaMeteo.getMeteo(url);
				 c = conv.getClassFromCall(meteo);
				 return c;
	 }
	 else if(agg.equals("No")){	
	 c = conv.findInJson(city);
	 return c;
	 }
	 else return null;
 }

/**
 * 
 * @author Federico
 * 
 * @param body
 * @return
 */

@PostMapping("/Stat")
 public JsonObject getStat(@RequestBody JsonObject body) {
	 String city =  body.get("city").getAsString();
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
	 Convertitore c = new Convertitore();
	 ArrayList<Citta> citta = c.JsonToCitta(date[0], date[1]);
	 Double[] valP =  s.getValues(citta, city, true);
	 Double[] valU = s.getValues(citta, city, false);
	 double mediaU = s.getMedia(valU);
	 double mediaP = s.getMedia(valP);
	 double varianzaU = s.getVarianza(valU, mediaU);
	 double varianzaP = s.getVarianza(valP, mediaP);
	 JsonObject JsonReturn = new JsonObject();
	 if(valP == null && valU == null) {
		 JsonReturn.addProperty("Nessun valore trovato nel range di tempo specificato","");
		 return JsonReturn;
	 }
	 else {
		 JsonReturn.addProperty("Nome", city);
		 JsonReturn.addProperty("Media Umidità", new DecimalFormat("#.##").format(mediaU));
		 JsonReturn.addProperty("Varianza Umidità", new DecimalFormat("#.##").format(varianzaU));
		 JsonReturn.addProperty("Media Pressione",new DecimalFormat("#.##").format(mediaP));
		 JsonReturn.addProperty("Varianza Pressione", new DecimalFormat("#.##").format(varianzaP));
		 return JsonReturn;
		 }
 }

/**
 * 
 * @author Federico 
 *
 * @param body
 * @return
 */
 @PostMapping("/Max")
 public JsonObject getMax(@RequestBody JsonObject body) {
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
	 System.out.println(date[0] + " " + date[1]);
	 return s.getMax(date[0],date[1]);
 }
 /**
  * 
  * @author Federico
  * 
  * @param body
  * @return
  */
 @PostMapping("/Min")
 public JsonObject getMin(@RequestBody JsonObject body) {
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
 * 
 * @author Federico
 *  
 * @param body
 * @return
 */
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
	 double varianzaU = stat.getVarianza(valU, mediaU);
	 double varianzaP = stat.getVarianza(valP, mediaP);
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
 
/**
 * 
 * @author Federico
 * 
 * @param action
 * @param name
 * @return
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
  * 
  * @author Federico
  * 
  * @param start
  * @param end
  * @return
  */
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
					e2.printStackTrace();
				}
		}
	 Date[] date = {inizio, fine};
	 return date;
		}
 }
 
 