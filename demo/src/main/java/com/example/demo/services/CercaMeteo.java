package com.example.demo.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Scanner;

import log.Log;
/**
 * Classe per fare le call alle api di OpenWeather
 * @author Federico
 *
 */
public class CercaMeteo {
	/**
	 * Metodo che dato un URL personalizzato, richiede il meteo di una specifica città all'Api OpenWeather
	 * @author Federico
	 * @param url Stringa personalizzata per la richiesta di uno specifico meteo
	 * @return Stringa contenete il meteo di una Citta sotto forma di JsonObject 
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
