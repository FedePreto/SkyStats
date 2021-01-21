package com.univpm.oop.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.univpm.oop.log.Log;

/**
 * Classe che contiene tutti i metodi per la gestione del Favoriti ({@link ArrayList} di tipo {@link String} contenente i nomi delle citta preferite dall'utente)
 * @author Federico
 * @author Nicolò
 */
public class Favoriti {
	/**
	 * {@link ArrayList} in cui verranno memorizzati i nomi delle città contenute nel file <b>config.json</b>
	 */
	public static ArrayList<String> favoriti;
	/**
	 * Path del file contenente il DB
	 */
	public static String config = "config.json";
	
	/**
	 * Costruttore che inizializza la classe Favoriti aggiornando favoriti dal file <b>config</b>
	 */
	public Favoriti() {
		favoriti = new ArrayList<String>();
		aggiornaArray();
	}
	

	/**
	 * Metodo che va a leggere i nomi delle città scritti nel file <b>config.json</b> e li memorizza
	 * nell' {@link ArrayList} dei favoriti
	 * @author Nicolò 
	 */
	public void aggiornaArray() {
		favoriti.clear();
		try {
			BufferedReader buf = new BufferedReader(new FileReader(new File(config)));
			JsonObject jo = JsonParser.parseReader(buf).getAsJsonObject(); 
			JsonArray ar = jo.get("favoriti").getAsJsonArray();
			for (int i = 0; i < ar.size(); i++) 
				favoriti.add(ar.get(i).getAsString());
			buf.close();

		} catch (FileNotFoundException e) {
			Log.report("FILE "+config+" NON TROVATO", e.getMessage());
		} catch (JsonSyntaxException e) {
			Log.report("SINTASSI JSON ERRATA", e.getMessage());
		} catch (IOException e) {
			Log.report("ERRORE LETTURA FILE", e.getMessage());
		}

	}
	/**
	 * Scrive sul file <b>config</b> i favoriti in modo da salvarli
	 * @author Nicolò
	 */
	public void salvaArray() {
		ArrayList<String> file = new ArrayList<String>();
		try {
			Scanner buf = new Scanner(new BufferedReader(new FileReader(new File(config))));
			while (buf.hasNext()) {
				file.add(buf.nextLine());
			}
			buf.close();

		} catch (FileNotFoundException e) {
			Log.report("FILE "+config+" NON TROVATO", e.getMessage());
		}
		Gson gson = new Gson();
		file.set(0, "{\"favoriti\":" + gson.toJson(favoriti, new TypeToken<ArrayList<String>>(){}.getType()) + "}");
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(new File(config)));
			for (int i = 0; i < file.size(); i++) {
				buf.write(file.get(i));
			}
			buf.close();

		} catch (IOException e) {
			Log.report("ERRORE SCRITTURA FILE", e.getMessage());
		}

	}
	/**
	 * Aggiunge fav alla lista dei favoriti
	 * @param fav {@link String} nome della città da aggiungere ai favoriti
	*/
	public void addFavoriti(String fav) {
		favoriti.add(fav);
		salvaArray();
	}
	
	/**
	 * Ritorna un JsonObject contentente tutte le citta nei favoriti
	 * @author Federico
	 * @return {@link JsonObject} contenente i nomi dei favoriti
	 */
	public JsonObject stampaFavoriti() {
		JsonObject jo = new JsonObject();
		try {
			BufferedReader buf = new BufferedReader(new FileReader(new File(config)));
			jo = JsonParser.parseReader(buf).getAsJsonObject(); 		
		}catch (FileNotFoundException e) {
			Log.report("FILE "+config+" NON TROVATO", e.getMessage());
		}
		return jo;
	}
	

	/**
	 * Cancella il favorito <b>val</b> dai favoriti
	 * @author Nicolò
	 * @param name {@link String} nome della citta da eliminare
	 */
	public void removeFavoriti(String name) {
		favoriti.remove(name);
		salvaArray();
	}
}
