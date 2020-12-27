package com.example.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.demo.aggiornamento.Aggiornamento;
import com.example.demo.model.Citta;
import com.example.demo.src.Convertitore;
import com.google.gson.Gson;

@SpringBootApplication
public class DemoApplication {

	
	public static void main(String[] args) {
		//Per usare le interfacce grafiche si è dovuto utilizzare questo builder di avviamento
		 SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
		    builder.headless(false).run(args);
		    
		    Timer timer = new Timer();
			Aggiornamento agg = new Aggiornamento();
			timer.schedule(agg, getDelay(), 5 * 3600000);
		
	}
	
	public static long getDelay() {
		Date now = new Date();
		Gson gson = new Gson();
		Citta c = null;
		        	try {
	    		Convertitore conv = new Convertitore();
				Scanner in = new Scanner(new BufferedReader(new FileReader("Storico.json")));
				c = gson.fromJson(in.nextLine(), Citta.class);							
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		    long differenza = now.getTime() - c.getData().getTime();
	    	if(differenza>5*3600000) {
				return 0;
			}
			else return (5*3600000-differenza);
	}


}

