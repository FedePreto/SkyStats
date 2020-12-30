package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.demo.aggiornamento.Aggiornamento;
import com.example.demo.model.Citta;
import com.example.demo.src.Convertitore;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@SpringBootApplication
public class DemoApplication {

	/**
	 * @author Federico
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//Per usare le interfacce grafiche si è dovuto utilizzare questo builder di avviamento
		 SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
		    builder.headless(false).run(args);
		    Convertitore conv = new Convertitore();
		    //conv.revert();
		    Timer timer = new Timer();
			Aggiornamento agg = new Aggiornamento();
			timer.schedule(agg, getDelay(), 5 * 3600000);
		
	}
	
	/**
	 * 
	 * @author Federico
	 * 
	 * @return
	 */
	public static long getDelay() {
		Date now = new Date();
		Gson gson = new Gson();
		Citta c = null;
		    try {
	    		Convertitore conv = new Convertitore();
				//Scanner in = new Scanner(new BufferedReader(new FileReader("Storico.json")));
	    		ReversedLinesFileReader in = new ReversedLinesFileReader(new File("Storico.json"));
				c = gson.fromJson(in.readLine(), Citta.class);							
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (JsonSyntaxException e) {
				System.out.println("Errore sintassi Json");
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    long differenza = now.getTime() - c.getData().getTime();
	    	if(differenza>5*3600000) {
				return 0;
			}
			else return (5*3600000-differenza);
	}


}

