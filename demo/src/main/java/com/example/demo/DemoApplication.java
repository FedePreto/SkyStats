package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.demo.aggiornamento.Aggiornamento;
import com.example.demo.model.Citta;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import log.Log;

/**
 * La seguente classe si occupa di gestire ed avviare tutti i componenti dell'applicazione
 * 
 * @author Federico
 *
 */
@SpringBootApplication
public class DemoApplication {
	public static String key;
	/**
	 * Avvia SpringBoot inizializzando Headles a false in modo da poter instanziare AWT permettendo così la
	 * visualizzazione delle icone, inoltre fa anche partire l'aggiornamento automatico ogni 5h dello storico
	 * controllando però prima la data e l'ora dell'ultimo aggiornamento
	 * 
	 * @author Federico
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * Solitamente SpringBoot setta l'Headless a true, in questo modo l'applicazione non consente l'utilizzo di interfacce 
		 * con le seguenti 2 righe di codice l'Headless viene settato a false prima di far partire l'applicazione
		*/	
		 SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
		 builder.headless(false).run(args);
		 Scanner s = new Scanner(System.in);
		 System.out.println("Inserisci la key di OpenWeather: ");
		 DemoApplication.key = s.next();
		 Timer timer = new Timer();
		 Aggiornamento agg = new Aggiornamento();
		 timer.schedule(agg, getDelay(), 5 * 3600000);
	}
	
	/**
	 * Metodo statico che si occupa di calcolare e restituire il tempo di attesa 
	 * prima di eseguire l'aggiornamento automatico per la prima volta  
	 *   
	 * @author Federico
	 * 
	 * @return delay espresso in millisecondi
	 */	
	private static long getDelay() {
		
		Date now = new Date();     
		Gson gson = new Gson();    
		Citta c = null;         		

		try {
			ReversedLinesFileReader in = new ReversedLinesFileReader(new File("Storico.json"));
			c = gson.fromJson(in.readLine(), Citta.class);
			} catch (FileNotFoundException e) {
				Log.report("NESSUN FILE TROVATO",e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				Log.report("ERRORE IN FASE DI LETTURA",e.getMessage());
				e.printStackTrace();
			} catch (JsonSyntaxException e) {
				Log.report("ERRORE JSON LETTO NON FORMATTATO CORRETTAMENTE",e.getMessage());
				e.printStackTrace();
			}
		
		long differenza = now.getTime() - c.getData().getTime();  
		    
	    	if(differenza > 5*3600000)
	    		return 0;	
	    	else return (5*3600000 - differenza);
	}


}

