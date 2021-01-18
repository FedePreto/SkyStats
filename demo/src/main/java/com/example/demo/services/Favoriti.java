
//Classe che serve per fare il salvataggio automatico del meteo dei capoluoghi di regione
//(Il nome della classe è completamente fuori luogo da cambiare) 

package com.example.demo.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import com.example.demo.model.Citta;
import com.example.demo.src.Convertitore;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import log.Log;

/**
 * Classe che contiene tutti i metodi per la gestione del Favoriti ( Array di Stringhe che contengono i nomi delle citta Preferite dall'utente
 * @author Federico
 * @author Nicolò
 *
 */
public class Favoriti {
	private ArrayList<String> favoriti;
	String config = ".\\src\\main\\java\\com\\example\\demo\\config/" + "config.json";
	
	/**
	 * Costruttore che inizializza la classe Favoriti aggiornando favoriti dal file <b>config</b>
	 */
	public Favoriti() {
		favoriti = new ArrayList<String>();
		aggiornaArray();
	}
	

	/**
	 * Metodo che va ad aggiornare favoriti con i favoriti scirtti sul file <b>config</>
	 * @author Nicolò
	 * 
	 * 
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
			// TODO Auto-generated catch block
			Log.report(new Date()+"-"+e.getMessage());
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			Log.report(new Date()+"-"+e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.report(new Date()+"-"+e.getMessage());
		}

	}
	/**
	 * Scrive sul file <b>config</> i favoriti in modo da salvarli
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
			// TODO Auto-generated catch block
			Log.report(new Date()+"-"+e.getMessage());
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
			// TODO Auto-generated catch block
			Log.report(new Date()+"-"+e.getMessage());
		}

	}
	/**
	 * Aggiunge fav alla lista dei favoriti
	 * @param fav elemento da aggiungere
	 */
	public void addFavoriti(String fav) {
		favoriti.add(fav);
		salvaArray();
	}
	
	/**
	 * Ritorna JsonObject contentente tutte le citta nei favoriti
	 * @author Federico
	 * 
	 * @return
	 */
	public JsonObject stampaFavoriti() {
		JsonObject jo = new JsonObject();
		try {
			BufferedReader buf = new BufferedReader(new FileReader(new File(config)));
			jo = JsonParser.parseReader(buf).getAsJsonObject(); 		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.report(new Date()+"-"+e.getMessage());
		}
		return jo;
	}
	/**
	 * Stampa di debug dei favoriti
	 * @author Nicolò
	 */
	public void stampaPreferiti() {
		for (int i = 0; i < favoriti.size(); i++) {
			System.out.println(favoriti.get(i));
		}
	}

	/**
	 * Cancella il favorito <b>val</> dai favoriti
	 * @author Nicolò
	 * @param val Valore dal eliminare
	 */
	public void removeFavoriti(String val) {
		favoriti.remove(val);
		salvaArray();
	}
	/**
	 * Ritorna i favoriti
	 * @author Nicolò
	 * @return ArrayList dei favoriti
	 */
	public ArrayList<String> getFavoriti() {
		return favoriti;
	}
}
