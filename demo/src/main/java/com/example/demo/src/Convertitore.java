package com.example.demo.src;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.apache.commons.io.input.ReversedLinesFileReader;

import com.example.demo.model.Citta;
import com.google.gson.*;

import log.Log;
/**
 * Classe contenente metodi per la conversione di Java to Json o viceversa
 * @author Nicolò
 * @author Federico
 *
 */
public class Convertitore {
	String nomeFile = "Storico.json";

	/**
	 * Legge tutto il database nomeFile e lo manda indietro in un ArrayList
	 * @author Nicolò
	 * @return ArrayList contenente tutti i valori nel DB
	 */
	public ArrayList<Citta> JsonToCitta() {
			ArrayList<Citta> c = new ArrayList<Citta>();
			Stream<String> file;
			int i = 0;
			Gson gson = new Gson();
			try {
				BufferedReader buf = new BufferedReader(new FileReader(nomeFile));
				file = buf.lines();
				while (true) {
					String prova = buf.readLine();
					if (prova.equals(""))
						return c;
	
					c.add(gson.fromJson(prova, Citta.class));
					i++;
				}
			} catch (FileNotFoundException e) {
				Log.report(new Date()+"-"+e.getMessage());
			} catch (IOException e) {
				Log.report(new Date()+"-"+e.getMessage());
			} catch (Exception e) {
				// System.out.println(i+" Exception");
				return c;
			}
			return c;
		}
	
    /**
	 * Override del metodo precedente che permette di filtrare in base ad un range di date dato in input
	 * @author Federico
	 * @author Nicolò
	 * @param inizio Data di inizio del range di tempo
	 * @param fine Data di fine del range di tempo
	 * @return ArrayList di Citta che sono del db e che si trovano tra la data di inizio e quella di fine
	 */
	public ArrayList<Citta> JsonToCitta(Date inizio, Date fine) {
		System.out.println("================================================================");
		ArrayList<Citta> c = new ArrayList<Citta>();
		Citta citta;
		String file="";
		int i = 0;
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
			Log.report(new Date()+"-"+e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println();
		}
		if(c.isEmpty())
			return null;
		return c;
}

	
	/*public void revert() {
		ArrayList<String> file = new ArrayList<String>();
		Scanner s;
		try {
			s = new Scanner(new BufferedReader(new FileReader(nomeFile)));
			while(s.hasNext()) {
				file.add(s.nextLine());
			}
			s.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(nomeFile));
			for(int i = file.size()-1;i>=0;i--) {
				buf.write(file.get(i)+"\n");
			}
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/****************************************
	public void revert() {
		ArrayList<Citta> c = JsonToCitta();
		Date last = new Date("01/01/00");
		
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(nomeFile));
			for (int i = 0; i < c.size(); i++) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	/**
	 * Metodo che salva le Citta contenute in c in nomeFile
	 * @author Federico
	 * @param c ArrayList contenente tutte le Citta da salvare
	 */
	public void salva(ArrayList<Citta> c) {
		Gson gson = new Gson();
		BufferedWriter buf;
		Scanner in;
		try {
			buf = new BufferedWriter(new FileWriter(nomeFile));
			for(int i = 0;i<c.size();i++) {
				buf.write(gson.toJson(c.get(i)));
			}
			buf.close();
		}catch(IOException e) {
			Log.report(new Date()+"-"+e.getMessage());
		}
		
	}
	/**
	 * Salva nel Database in append una sola riga
	 * @author Nicolò
	 * @param s Riga da scrivere
	 */
	public void salva(String s) {
		try {
			BufferedWriter buf = new BufferedWriter(new FileWriter(nomeFile,true));
			buf.write("\n"+s);
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.report(new Date()+"-"+e.getMessage());
		}
	}
	/**
	 * Data una stringa s contenente Json ritorna la classe corrispondente
	 * @author Nicolò
	 * @param s Stringa Json
	 * @return Classe Citta associata alla Stringa Json
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
				Log.report(new Date()+"-"+e.getMessage());
				System.out.println("ERRORE DI PARSING DELLA STRINGA");
				e.printStackTrace();
				System.out.println(e);
				return null;
				
			}catch(ClassCastException e){
				Log.report(new Date()+"-"+e.getMessage());
				System.out.println("ERRORE DI CASTING");
				e.printStackTrace();
				System.out.println(e);
				return null;
			
			}catch(Exception e) {
				Log.report(new Date()+"-"+e.getMessage());
				System.out.println("ERRORE GENERICO IN FASE DI CREAZIONE DELLA CLASSE");
				e.printStackTrace();
				System.out.println(e);
				return null;
				
			}
		return c;
}
	/**
	 * Stampa di Debug di una qualsiasi citta
	 * @author Nicolo
	 * @param c Citta da stampare
	 */
	public void stampaCitta(Citta c) {
		System.out.println(c); 
	}
	/**
	 * Controlla se <b>val</b> è contenuto nel DataBase e ritorna l 'ultimo valore
	 * @author Nicolò
	 * @param val nome della citta
	 * @return Citta trovata oppure null se non trovata
	 */
	public Citta findInJson(String val) {
		ArrayList<Citta> c;
		c = JsonToCitta();
		Citta city = new Citta();
		for(Citta x : c) {
			if(val.equals(x.getNome())) {
				return city;
			}
			
		}
		return null;
	}

}
