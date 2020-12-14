package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.src.Citta;
import com.example.demo.src.Convertitore;
import com.sun.source.tree.NewClassTree;


@RestController
public class Controller {
	
	String url = "";//"C:\\Users\\OEM\\Downloads\\demo\\Esempio chiamata.txt";

	@GetMapping("/Weather")
	public Citta getMeteo(@RequestParam(name = "Citta", defaultValue = "") String city) {
		Convertitore conv = new Convertitore();
		try {
			int  ID = Integer.parseInt(city);
			url = "http://api.openweathermap.org/data/2.5/weather?id=" + ID + ",IT&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
					
		} catch (NumberFormatException e) {
			
			url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + ",IT&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
			
		}
		
		Citta meteo = conv.getClassFromCall(CercaMeteo.getMeteo(url), true); 
		return meteo;
		
	}
	
	//@GetMapping("/Stats")
}