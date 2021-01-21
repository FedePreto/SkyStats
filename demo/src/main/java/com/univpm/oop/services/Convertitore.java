package com.univpm.oop.services;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.io.input.ReversedLinesFileReader;
import com.google.gson.*;
import com.univpm.oop.log.Log;
import com.univpm.oop.model.Citta;
/**
 * Classe contenente metodi per la lettura del DataBase, conversione da Java a Json o viceversa
 * @author Nicolò
 * @author Federico
 */
public class Convertitore {
	/**
	 * Path del file json contenente il DB
	 */
	String nomeFile = "Storico.json";
	
	
	/**
	 * Metodo che legge il DB completo  al contrario partendo dall'ultima riga del File fino alla prima
	 * grazie all'utilizzo di {@link ReversedLinesFileReader} 
	 * @author Federico 
	 * @return {@link ArrayList} contenente tutte le città memorizzate nel DB
	 */
	public ArrayList<Citta> JsonToCitta() {
			ArrayList<Citta> c = new ArrayList<Citta>();
			Gson gson = new Gson();
			try {
				ReversedLinesFileReader reader = new ReversedLinesFileReader(new File(nomeFile));
				String DB_line = reader.readLine();
				while(DB_line != null) {
					c.add(gson.fromJson(DB_line, Citta.class));
					DB_line = reader.readLine();
				}
			} catch (FileNotFoundException e) {
				Log.report("NESSUN FILE TROVATO",e.getMessage());
				return null;
			} catch (IOException e) {
				Log.report("ERRORE IN FASE DI LETTURA",e.getMessage());
				return null;
			} catch (Exception e) {
				Log.report("ERRORE GENERICO AVVENUTO NELLA CLASSE \"Convertitore\"",e.getMessage());
				return c;
			}
			
			return c;
		}
	
	//Questo metodo viene utilizzato dal package general GUI
	 /**
		 * Override del metodo precedente che permette di filtrare il DB in base ad un range di date dato in input
		 * @author Federico
		 * @author Nicolò
		 * @param inizio {@link Date} contenente la data di inizio del range di tempo
		 * @param fine {@link Date} contenente la data di fine del range di tempo
		 * @return {@link ArrayList} di Citta che sono del db e che si trovano tra la data di inizio e quella di fine
		 */
		public ArrayList<Citta> JsonToCitta(Date inizio, Date fine) {
			ArrayList<Citta> c = new ArrayList<Citta>();
			Citta citta;
			Gson gson = new Gson();
			try {
				ReversedLinesFileReader in = new ReversedLinesFileReader(new File(nomeFile));
				citta = gson.fromJson(in.readLine(), Citta.class);
				 do{
					citta = gson.fromJson(in.readLine(), Citta.class);
					if(citta!=null)
						if(citta.getData().before(fine)) {
							c.add(citta);
						}
				}while(citta.getData().after(inizio));
			}catch(IOException e) {
				Log.report("ERRORE IN FASE DI LETTURA",e.getMessage());
			}
			if(c.isEmpty())
				return null;
			return c;
	}
	/**
	 * Metodo che salva nel Database in append una città passata come stringa
	 * @author Nicolò
	 * @author Federico
	 * @param s {@link String} contenente la citta con i dati da scrivere nel DB
	 */
	public void salva(String s) {
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(nomeFile,true));
			buf.write("\n"+s);
			buf.close();
		} catch (IOException e) {
			Log.report("ERRORE SCRITTURA",e.getMessage());
		}
	}
	
	/**
	 * Data una stringa s contenente il Json ritornato dalla call a OpenWeather,
	 * preleva solo i dati di interesse ai fini del progetto
	 * @author Federico
	 * @author Nicolò 
	 * @param s {@link String} contenente il meteo sotto forma di Json
	 * @return {@link Citta} contenente i dati prelevati dalla stringa Json
	 */
	public Citta getClassFromCall(String s) {
		
		Citta c = new Citta();
		
		try {
			
			JsonObject city_weather = (JsonObject)JsonParser.parseString(s);
			c.setNome(city_weather.get("name").getAsString());
			c.setId(city_weather.get("id").getAsInt());
			JsonArray weather = city_weather.get("weather").getAsJsonArray();
			JsonObject main = city_weather.get("main").getAsJsonObject();
			JsonObject coord = city_weather.get("coord").getAsJsonObject();
			c.setPressione(main.get("pressure").getAsDouble());
			c.setUmidita(main.get("humidity").getAsDouble());
			c.setTemperatura(main.get("temp").getAsDouble());
			c.setMeteo(weather.get(0).getAsJsonObject().get("description").getAsString());
			c.setPosizione(c.getLocation(coord.get("lat").getAsDouble()));
			
			}catch(JsonParseException e) {
				Log.report("ERRORE DI PARSING DELLA STRINGA",e.getMessage());
				return null;
				
			}catch(ClassCastException e){
				Log.report("ERRORE DI CASTING",e.getMessage());
				return null;
			
			}catch(Exception e) {
				Log.report("ERRORE GENERICO IN FASE DI CREAZIONE DELL'OGGETTO",e.getMessage());
				return null;
				
			}
		return c;
	}
	
	
	/**
	 * Metodo che controlla se la stringa val è contenuta nel DataBase e ne ritorna i dati più aggiornati
	 * @author Nicolò
	 * @param name {@link String}  contenente il nome della citta
	 * @return {@link Citta} contenente i dati della città trovati, oppure <b>null</b> se non viene trovato nulla nel DB
	 */
	public Citta findInJson(String name) {
		ArrayList<Citta> city = JsonToCitta();
		try {
			int ID = Integer.parseInt(name);
			for(Citta x : city) {
				if(x.getId()==ID) {
					 return x;
				}				
			}			
		}catch(NumberFormatException e) {
			for(Citta x : city) {
				if(name.equals(x.getNome())) {
					return x;
				}				
			}			
		}
		return null;
	}
}
