package com.example.demo.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

//Classe non utilizzata momentaneamente (Serve per gestire le call da Postaman alla nostra API)

 import org.springframework.web.bind.annotation.GetMapping; 
 import org.springframework.web.bind.annotation.RequestParam; 
 import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Citta;
import com.example.demo.services.CercaMeteo;
import com.example.demo.src.*;
import com.example.demo.statistiche.Stat;
import com.google.gson.JsonObject;

 
 
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
 
 @GetMapping("/Stat")
 public String getStat(@RequestParam(name = "time",defaultValue = "Settimanale")String time,@RequestParam(name = "citta",defaultValue = "Rome")String city) {
	 Date[] range = menuDate(time);
	 Stat s = new Stat();
	 double mediaU = s.getMedia(s.getValues(range[0], range[1], city, false));
	 double mediaP = s.getMedia(s.getValues(range[0], range[1], city, true));
	  return city+"<br>"+"Media Umidita = " + mediaU + "<br>Varianza Umidita = "+s.getVarianza(s.getValues(range[0], range[1], city, false), mediaU)+"<br>Media Umidita = " + mediaP + "<br>Varianza Umidita = "+s.getVarianza(s.getValues(range[0], range[1], city, true), mediaP);
	
	 
 }
 
 @GetMapping("/StatCustom")
 public String getStat(@RequestParam(name="inizio",defaultValue = "01/01/2000 00:00")Date inizio,@RequestParam(name = "fine", defaultValue = "01/01/2100 00:00")Date fine,@RequestParam(name = "citta", defaultValue = "Rome")String city) {
	 Stat s = new Stat();
	 double mediaU = s.getMedia(s.getValues(inizio, fine, city, false));
	 double mediaP = s.getMedia(s.getValues(inizio, fine, city, true));
	 return city+"<br>"+"Media Umidita = " + mediaU + "<br>Varianza Umidita = "+s.getVarianza(s.getValues(inizio, fine, city, false), mediaU)+"<br>Media Umidita = " + mediaP + "<br>Varianza Umidita = "+s.getVarianza(s.getValues(inizio, fine, city, true), mediaP);

 }
 
 @GetMapping("/Max")
 public String getMax(@RequestParam(name = "timeRange", defaultValue = "Settimanale")String timeRange,@RequestParam(name = "citta",defaultValue = "Rome")String city) {
	 Date[] range = menuDate(timeRange);
	 Stat s = new Stat();
	 return s.printMaxValues(range[0], range[1]);
 }
 
 @GetMapping("/ZoneGeo")
 public String getZoneGeo(@RequestParam( name = "zona", defaultValue = "Centro")String zona,@RequestParam( name = "periodo",defaultValue = "Settimanale")String periodo) {
	 Date[] range = menuDate(periodo);
	 Stat stat = new Stat();
	 double mediaP = stat.getMedia(stat.getDataByLocation(range[0], range[1], zona, true));
	 double mediaU = stat.getMedia(stat.getDataByLocation(range[0], range[1], zona, false));
	 
	 return "Media della pressione al "+zona+": "+new DecimalFormat("#.##").format(mediaP)+"<br>Varianza della pressione al "+zona+" : "+new DecimalFormat("#.##").format(stat.getVarianza(stat.getDataByLocation(range[0], range[1], zona, true), mediaP))+
			 "<br><br>Media dell'umidità al "+zona+" : "+mediaU+"<br>Varianza dell'umidità al "+zona+" : "+stat.getVarianza(stat.getDataByLocation(range[0], range[1], zona, true), mediaU);
		
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
		Date[] date = { inizio, fine };
		return date;
	}
 
  }
 