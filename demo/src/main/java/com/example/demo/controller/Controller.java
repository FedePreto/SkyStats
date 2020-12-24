package com.example.demo.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONObject;

//Classe non utilizzata momentaneamente (Serve per gestire le call da Postaman alla nostra API)

 import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam; 
 import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Citta;
import com.example.demo.services.CercaMeteo;
import com.example.demo.src.*;
import com.example.demo.statistiche.Stat;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


 
 
 @RestController public class Controller {
 
 String url = "";//"C:\\Users\\OEM\\Downloads\\demo\\Esempio chiamata.txt";

 
 @GetMapping("/Weather")
 public String getWeather(@RequestParam(name = "Citta", defaultValue = "Rome") String city,@RequestParam(name = "Aggiornamento", defaultValue = "Si")String agg) {
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
				 System.out.println(meteo);
				 c = conv.getClassFromCall(meteo);
				 System.out.println(c);
				 return c.toString();
	 }
	 else if(agg.equals("No")){	
	 c = conv.findInJson(city);
	 return c.toString();
	 }
	 else return null;
 }
 
 @SuppressWarnings("unchecked")
@PostMapping("/Stat")
 public JSONObject getStat(@RequestBody JSONObject JsonBody) {
	 JsonObject body = (JsonObject)JsonParser.parseString(JsonBody.toString());
	 System.out.println(body);
	 String city =  body.get("city").getAsString();
     String type = body.get("type").getAsString();
     String inizio = "";
     String fine = "";
	 try {
		 JsonArray range = body.getAsJsonArray("range");
		 inizio = range.get(0).getAsString();
		 fine = range.get(1).getAsString();
		 }catch(NullPointerException e) {};
	
	 Date[] range = menuDate(type,inizio,fine);
	 System.out.println(range[0] + " " + range[1]);
	 Stat s = new Stat();
	 Double[] valP =  s.getValues(range[0], range[1], city, true);
	 Double[] valU = s.getValues(range[0], range[1], city, false);
	 double mediaU = s.getMedia(valU);
	 double mediaP = s.getMedia(valP);
	 double varianzaU = s.getVarianza(valU, mediaU);
	 double varianzaP = s.getVarianza(valP, mediaP);
	 JSONObject JsonReturn = new JSONObject();
	 if(valP == null && valU == null) {
		 JsonReturn.put("Nessun valore trovato nel range di tempo specificato","");
		 return JsonReturn;
	 }
	 else {
		 JsonReturn.put("Nome", city);
		 JsonReturn.put("Media Umidità", new DecimalFormat("#.##").format(mediaU));
		 JsonReturn.put("Varianza Umidità", new DecimalFormat("#.##").format(varianzaU));
		 JsonReturn.put("Media Pressione",new DecimalFormat("#.##").format(mediaP));
		 JsonReturn.put("Varianza Pressione", new DecimalFormat("#.##").format(varianzaP));
		 return JsonReturn;
		 }
 }

 @PostMapping("/Max")
 public JSONObject getMax(@RequestBody JSONObject JsonBody) {
	 JsonObject body = (JsonObject)JsonParser.parseString(JsonBody.toString());
     String type = body.get("type").getAsString();
     System.out.println(type);
     String inizio = "";
     String fine = "";
     try {
		 JsonArray range = body.getAsJsonArray("range");
		 inizio = range.get(0).getAsString();
		 fine = range.get(1).getAsString();
		 }catch(NullPointerException e) {};
	 Date[] range = menuDate(type,inizio,fine);
	 Stat s = new Stat();
	 JSONObject JsonReturn = s.getMax(range[0],range[1]);
	 return JsonReturn;
 }
 
 @PostMapping("/Min")
 public JSONObject getMin(@RequestBody JSONObject JsonBody) {
	 JsonObject body = (JsonObject)JsonParser.parseString(JsonBody.toString());
     String type = body.get("type").getAsString();
     String inizio = "";
     String fine = "";
     try {
		 JsonArray range = body.getAsJsonArray("range");
		 inizio = range.get(0).getAsString();
		 fine = range.get(1).getAsString();
		 }catch(NullPointerException e) {};
	 Date[] range = menuDate(type,inizio,fine);
	 Stat s = new Stat();
	 JSONObject JsonReturn = s.getMin(range[0],range[1]);
	 return JsonReturn;
 }
 
 @SuppressWarnings("unchecked")
@PostMapping("/ZoneGeo")
 public JSONObject getZoneGeo(@RequestBody JSONObject JsonBody) {
	 JsonObject body = (JsonObject)JsonParser.parseString(JsonBody.toString());
	 String zone = body.get("zone").getAsString();
     String type = body.get("type").getAsString();
     String inizio = "";
     String fine = "";
     try {
		 JsonArray range = body.getAsJsonArray("range");
		 inizio = range.get(0).getAsString();
		 fine = range.get(1).getAsString();
		 }catch(NullPointerException e) {};
	 Date[] range = menuDate(type,inizio,fine);	 
	 Stat stat = new Stat();
	 Double[] valP =  stat.getDataByLocation(range[0], range[1], zone, true);
	 Double[] valU = stat.getDataByLocation(range[0], range[1], zone, false);
	 double mediaP = stat.getMedia(valP);
	 double mediaU = stat.getMedia(valU);
	 double varianzaU = stat.getVarianza(valU, mediaU);
	 double varianzaP = stat.getVarianza(valP, mediaP);
	 JSONObject JsonReturn = new JSONObject();
	 if(valP == null && valU == null) {
		 JsonReturn.put("Nessun valore trovato nel range di tempo specificato","");
		 return JsonReturn;
	 }
	 else {
		 JsonReturn.put("Zona", zone);
		 JsonReturn.put("Media Umidità", new DecimalFormat("#.##").format(mediaU));
		 JsonReturn.put("Varianza Umidità", new DecimalFormat("#.##").format(varianzaU));
		 JsonReturn.put("Media Pressione",new DecimalFormat("#.##").format(mediaP));
		 JsonReturn.put("Varianza Pressione", new DecimalFormat("#.##").format(varianzaP));
		 return JsonReturn;
		 }		
 }
 
		
 public static Date[] menuDate(String time, String start, String end) {
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
		case "Customizzato":
				try {
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm");
					inizio = formato.parse(start);
					fine = formato.parse(end);
					break;
				}catch (ParseException e1) {
					try {
						SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
						inizio = formato.parse(start);
						fine = formato.parse(end);					
						}catch (ParseException e2) {
							e2.printStackTrace();
						}
				}
		}
		Date[] date = {inizio, fine};
		return date;
	}
 
  }
 