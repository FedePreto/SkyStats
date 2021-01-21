package com.univpm.oop.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.univpm.oop.log.Log;
/**
 * Classe per fare le call all'API di OpenWeather, dove, dato un URL ritorna il meteo 
 * sotto forma di stringa
 * @author Federico
 */
public class CercaMeteo {
	/**
	 * Metodo che dato un URL personalizzato, richiede il meteo di una specifica città all'API di OpenWeather
	 * @author Federico
	 * @param url {@link String} contenente la richiesta di uno specifico meteo
	 * @return {@link String} contenete il meteo di una Citta sotto forma di Json 
	 */
	public static String getMeteo(String url) {

		String meteo_citta = "";

		try {

			URLConnection openConnection = (URLConnection) new URL(url).openConnection();
			Scanner in = new Scanner(new BufferedReader(new InputStreamReader(openConnection.getInputStream())));
			meteo_citta += in.nextLine();
			in.close();
			
		} catch (IOException e) {
			Log.report("ERRORE DURANTE LA RICHIESTA METEO",e.getMessage());
		} catch (Exception e) {
			Log.report("ERRORE GENERICO NELLA CLASSE \"CercaMeteo\"",e.getMessage());
		}		
		return meteo_citta;

	}

}
