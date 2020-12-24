package com.example.demo.src;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.example.demo.model.Citta;
import com.google.gson.*;

public class Convertitore {
	String nomeFile = "Storico.json";

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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// System.out.println(i+" Exception");
			return c;
		}
		return c;
	}public Convertitore() {
		// TODO Auto-generated constructor stub
	}

public ArrayList<Citta> JsonToCitta(Date inizio, Date fine) {
		ArrayList<Citta> c = new ArrayList<Citta>();
		String file="";
		int i = -1;
		Gson gson = new Gson();
		try {
			Scanner in = new Scanner(new BufferedReader(new FileReader(nomeFile)));
			do {
				
				c.add(gson.fromJson(in.nextLine(), Citta.class));
				i++;
			}
			while((c.get(i).getData().after(inizio) && c.get(i).getData().before(fine)) && in.hasNext());
		}catch(IOException e) {
			e.printStackTrace();
		}
		if(c.isEmpty())return null;
		return c;
}
	
	public void salva(ArrayList<Citta> c) {
		Gson gson = new Gson();
		BufferedWriter buf;
		Scanner in;
		try {
			buf = new BufferedWriter(new FileWriter("Storico2.json"));
			in = new Scanner(new BufferedReader(new FileReader(nomeFile)));
			for(Citta x : c) {
				buf.write(gson.toJson(x));
				buf.write("\n");
			}
			while(in.hasNext()) {
				buf.write(in.nextLine());
				buf.write("\n");
			}
			in.close();
			buf.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		try {
			buf = new BufferedWriter(new FileWriter(nomeFile));
			in = new Scanner(new BufferedReader(new FileReader("Storico2.json"))); 
			while(in.hasNext()) {
				buf.write(in.nextLine());
				buf.write("\n");
			}
			in.close();
			buf.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
/*
	public void salva(ArrayList<Citta> c) {
		Gson gson = new Gson();
		ArrayList<String> database = new ArrayList<String>();
		try {
			Scanner in =new Scanner(new BufferedReader(new FileReader(nomeFile)));
			while(in.hasNext()) {
				database.add(in.nextLine());					
			}
			in.close();
			BufferedWriter buf = new BufferedWriter(new FileWriter(new File(nomeFile)));
			for(Citta x : c) {
			buf.write(gson.toJson(x));
			buf.write("\n");}
			for(int i=0; i<database.size(); i++) {
				buf.write(database.get(i));
				buf.write("\n");
			}
			buf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
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
				
				System.out.println("ERRORE DI PARSING DELLA STRINGA");
				e.printStackTrace();
				System.out.println(e);
				return null;
				
			}catch(ClassCastException e){
				System.out.println("ERRORE DI CASTING");
				e.printStackTrace();
				System.out.println(e);
				return null;
			
			}catch(Exception e) {
				System.out.println("ERRORE GENERICO IN FASE DI CREAZIONE DELLA CLASSE");
				e.printStackTrace();
				System.out.println(e);
				return null;
				
			}
		return c;
}

	public void stampaCitta(Citta c) {
		System.out.println(c); 
	}

	public Citta findInJson(String val) {
		ArrayList<Citta> c;
		c = JsonToCitta();
		System.out.println(c.get(6));
		boolean trovato=false;
		Citta city = new Citta();
		for(Citta x : c) {
			if(val.equals(x.getNome()) && !trovato) {
				city = x;
				trovato=true;
			}
			
		}
		return city;
	}/*
		Citta citta = null;
		Date now = new Date();
		int maxRecent = 0;
		try {
			int id = Integer.parseInt(val);
			for (int i = 0; i < c.size(); i++) {
				// System.out.println(c.get(i).getNome()+" Data =
				// "+c.get(i).getData().toString()+" difference by now = "+
				// differenzaDiDate(c.get(i).getData(), now, TimeUnit.MINUTES)+" minuti");
				if (c.get(i).getId() == id && differenzaDiDate(c.get(i).getData(), now, TimeUnit.MINUTES) < 300) {
					if (differenzaDiDate(c.get(maxRecent).getData(), now,
							TimeUnit.HOURS) > differenzaDiDate(c.get(i).getData(), now, TimeUnit.HOURS)) {
						citta = c.get(i);
						maxRecent = i;
					}
				}
			}
		} catch (Exception e) {
			for (int i = 0; i < c.size(); i++) {
				// System.out.println(c.get(i).getNome()+" Data =
				// "+c.get(i).getData().toString()+" difference by now = "+
				// differenzaDiDate(c.get(i).getData(), now, TimeUnit.MINUTES)+" minuti");
				if (c.get(i).getNome().equals(val)
						&& differenzaDiDate(c.get(i).getData(), now, TimeUnit.MINUTES) < 300) {
					if (differenzaDiDate(c.get(maxRecent).getData(), now,
							TimeUnit.HOURS) > differenzaDiDate(c.get(i).getData(), now, TimeUnit.HOURS)) {
						citta = c.get(i);
						maxRecent = i;
					}
				}
			}
		}
		return citta;
	}*/

	public double differenzaDiDate(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

}
