package com.example.demo.controller;

//Classe non utilizzata momentaneamente (Serve per gestire le call da Postaman alla nostra API)

 import org.springframework.web.bind.annotation.GetMapping; import
 org.springframework.web.bind.annotation.RequestParam; import
 org.springframework.web.bind.annotation.RestController;

import com.example.demo.src.Citta;
import com.example.demo.src.Convertitore;
import com.example.demo.src.Main;

 
 
 @RestController public class Controller {
 
 String url = "";//"C:\\Users\\OEM\\Downloads\\demo\\Esempio chiamata.txt";
 
 @GetMapping("/Weather") public Citta getSaluto(@RequestParam(name = "Citta", defaultValue = "Rome") String city,@RequestParam(name = "Aggiornamento", defaultValue = "Si")String agg) {
	 Citta c = new Citta();
	 Convertitore conv = new Convertitore(); 
	 if(agg.equals("Si")) {
		 System.out.println("Sono nel si");
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
				 return c;
	 }
	 else if(agg.equals("No")){	
		 System.out.println("Sono nel no");
	 c = conv.findInJson(city);
	 return c;
	 }
	 else return null;
//@Getmapping()
 /*
  try { int ID = Integer.parseInt(city); url =
 "http://api.openweathermap.org/data/2.5/weather?id=" + ID +
 "&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
 
  } catch (NumberFormatException e) {
 
 url = "http://api.openweathermap.org/data/2.5/weather?q=" + city +
 ",IT&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
 
 }
 
 String meteo = CercaMeteo.getMeteo(url); return meteo;
 
 */
 
 } }
 