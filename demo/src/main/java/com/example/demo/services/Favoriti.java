
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

	public void salvataggio() {
		String url = "";
		ArrayList<Citta> city = new ArrayList<Citta>();
		Convertitore conv = new Convertitore();
		for (String x : favoriti) {
			url = "http://api.openweathermap.org/data/2.5/weather?q=" + x
					+ "&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
						
			city.add(conv.getClassFromCall(CercaMeteo.getMeteo(url)));
		}
		
		conv.salva(city);

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
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Scrive sul file <b>config</> i favoriti in modo da salvarli
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
			e.printStackTrace();
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
			e.printStackTrace();
		}

	}

	public void addFavoriti(String fav) {
		favoriti.add(fav);
		salvaArray();
	}
	
	//Metodo creato per l'utilizzo con la classe controller utile a visualizzare il contenuto del file fav
	public JsonObject stampaFavoriti() {
		JsonObject jo = new JsonObject();
		try {
			BufferedReader buf = new BufferedReader(new FileReader(new File(config)));
			jo = JsonParser.parseReader(buf).getAsJsonObject(); 		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * @param val Valore dal eliminare
	 */
	public void removeFavoriti(String val) {
		favoriti.remove(val);
		salvaArray();
	}
	/**
	 * Ritorna i favoriti
	 * @return ArrayList dei favoriti
	 */
	public ArrayList<String> getFavoriti() {
		return favoriti;
	}
}
