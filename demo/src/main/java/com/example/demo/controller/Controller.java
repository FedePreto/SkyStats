package com.example.demo.controller;

//Classe non utilizzata momentaneamente (Serve per gestire le call da Postaman alla nostra API)

/*
 * import org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * 
 * @RestController public class Controller {
 * 
 * String url = "";//"C:\\Users\\OEM\\Downloads\\demo\\Esempio chiamata.txt";
 * 
 * @GetMapping("/Weather") public String getSaluto(@RequestParam(name = "Citta",
 * defaultValue = "") String city) {
 * 
 * try { int ID = Integer.parseInt(city); url =
 * "http://api.openweathermap.org/data/2.5/weather?id=" + ID +
 * "&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
 * 
 * } catch (NumberFormatException e) {
 * 
 * url = "http://api.openweathermap.org/data/2.5/weather?q=" + city +
 * ",IT&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
 * 
 * }
 * 
 * String meteo = CercaMeteo.getMeteo(url); return meteo;
 * 
 * 
 * 
 * } }
 */