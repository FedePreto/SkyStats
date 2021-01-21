package com.univpm.oop.src;
import java.text.DecimalFormat;
import java.util.ArrayList;
//Classe non utilizzata momentaneamente (Serve per gestire le call da Postaman alla nostra API)

 import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam; 
 import org.springframework.web.bind.annotation.RestController;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.univpm.oop.exception.*;
import com.univpm.oop.model.Citta;
import com.univpm.oop.services.CercaMeteo;
import com.univpm.oop.services.Convertitore;
import com.univpm.oop.services.Favoriti;
import com.univpm.oop.statistiche.*;

 /**
  * Classe che gestisce le chiamate al nostro Server SpringBoot
  * @author Federico
  * @author Nicolò
  *
  */
 
 @RestController public class Controller {

 /**
  * Call che restituisce il meteo di una citta prelevandolo dal DB oppure direttamente da OpenWeather(dipende dalla richiesta dell'utente)
  * 
  * @author Federico
  * 
  * @param body JsonObject contenente i filtri
  *  
  * @return c  parametro che contiene il meteo della città scelta
  */
 @PostMapping("/Weather")
 public ArrayList<Citta> getWeather(@RequestBody JsonObject body)throws CityNotFoundException {
	 JsonArray citta = body.get("citta").getAsJsonArray();
	 ArrayList<Citta> city = new ArrayList<Citta>();
	 Convertitore conv = new Convertitore(); 
	 Citta c = new Citta();
	 String url="";
	 for (int i = 0; i < citta.size(); i++) {
		 if(i<=60) {
			 try { 
				 int ID = Integer.parseInt(citta.get(i).getAsString());
				
				 url = "http://api.openweathermap.org/data/2.5/weather?id=" + ID + "&appid="+DemoApplication.key+"&units=metric&lang=it";
					 
			 	}catch (NumberFormatException e) {
				 url = "http://api.openweathermap.org/data/2.5/weather?q=" + citta.get(i).getAsString() + ",IT&appid="+DemoApplication.key+"&units=metric&lang=it"; 
			 	}	 
				String meteo = CercaMeteo.getMeteo(url);
				c = conv.getClassFromCall(meteo);
				
		 }else {
			 
			 c = conv.findInJson(citta.get(i).getAsString());
			 if(c==null) {
				 throw new CityNotFoundException(citta.get(i).getAsString());
			 }
			 
		 }
		 
		 city.add(c);
		
	}
	return city;
 }
 
/**
 * Call che restituisce le statistiche delle città filtrate dai filtri passati per Post
 * 
 * @author Federico
 * @author Nicolò
 * 
 * @param body JsonObject contenente i filtri da applicare alle città da usare per calcolare le statistiche
 
 * @return JsonObject contenente tutte le informazioni riguardanti le statistiche
 * 
 */

@PostMapping("/Stat")
 public JsonObject getStat(@RequestBody JsonObject body)throws DataException {
	
	Convertitore conv = new Convertitore(); 
	Stat s = new Stat();
	ArrayList<Citta> citta = conv.JsonToCitta(); //Legge tutto lo storico e lo memorizza nell'ArrayList
	JsonObject error = new JsonObject();
	try {
		citta = Filtra(body, citta);
	} catch (MalformedException e) {
		error.addProperty("Error","Sintassi del body errata");
		return error;	
	}
	Double[][] dati = s.getValues(citta);
	if(dati==null)
		throw new DataException();
	double mediaP = s.getMedia(dati[0]);
	double mediaU = s.getMedia(dati[1]);
	double mediaT = s.getMedia(dati[2]);
	double varianzaP = s.getVarianza(dati[0]);
	double varianzaU = s.getVarianza(dati[1]);
	double varianzaT = s.getVarianza(dati[2]);
	JsonObject JsonReturn = new JsonObject();
	JsonReturn.addProperty("Nome", citta.get(0).getNome());
	JsonReturn.addProperty("Media Umidità", new DecimalFormat("#.##").format(mediaU));
	JsonReturn.addProperty("Varianza Umidità", new DecimalFormat("#.##").format(varianzaU));
	JsonReturn.addProperty("Media Pressione",new DecimalFormat("#.##").format(mediaP));
	JsonReturn.addProperty("Varianza Pressione", new DecimalFormat("#.##").format(varianzaP));
	JsonReturn.addProperty("Media Temperatura",  new DecimalFormat("#.##").format(mediaT));
	JsonReturn.addProperty("Varianza Temperatura",  new DecimalFormat("#.##").format(varianzaT));
	return JsonReturn;
	}

/**
 * In base al periodo specificato dall'attributo <b>Periodo</b> la call restituisce le città che assumono i massimi valori in questo range di tempo
 * @author Federico  
 * 
 * @param period Tipo di range di tempo(Giornaliero, Settimanale, Mensile, Annuale o Customizzato) 
 *
 * @return JsonObject contenente tutti le citta con i valori massimi nel Database
 */
 @GetMapping("/Max")
 public JsonObject getMax(@RequestParam(name = "Periodo", defaultValue = "Giornaliero") String period) {
	 
	 Convertitore conv = new Convertitore(); 
	 Stat s = new Stat();
	 ArrayList<Citta> citta = conv.JsonToCitta(); //Legge tutto lo storico e lo memorizza nell'ArrayList
	 Tempo t = new Tempo();
	 return s.getMax(t.filtra(citta, period));
	 
 }
 
 
 /**
  * In base al periodo specificato dall'attributo <b>Periodo</b> la call restituisce le città che assumono i minimi valori in questo range di tempo
  * @author Federico
  * 
  * 
  * @param period Range di tempo(Giornaliero, Settimanale, Mensile, Annuale o Customizzato)
  * @return JsonObject contenente tutte le citta con i valori minimi di pressione, umidità e temperatura nel Database
  */
 @GetMapping("/Min")
 public JsonObject getMin(@RequestParam(name = "Periodo", defaultValue = "Giornaliero") String period) {
     Convertitore conv = new Convertitore(); 
	 Stat s = new Stat();
	 ArrayList<Citta> citta = conv.JsonToCitta(); //Legge tutto lo storico e lo memorizza nell'ArrayList
	 Tempo t = new Tempo();
	 t.filtra(citta, period);
	 return s.getMin(citta);
 }
 
/**
 * Call per gestire i favoriti
 * @author Federico
 * 
 * @param action 	<br>Se "Aggiungi" : aggiunge ai favoriti <b>name</b>
 * 					<br>Se "Rimuovi" : rimuove dai favoriti <b>name</b>
 * 					<br>Se "Stampa" : ritorna i favoriti
 * @param name Oggetto di action
 * @return ritorna JsonObject con i log di alcune azioni
 */
 @GetMapping("/Fav")
 public JsonObject Favoriti(@RequestParam(name = "Action")String action,@RequestParam(name = "Name", defaultValue = "")String name) {
	 Favoriti fav = new Favoriti();
	 switch(action) {
	 case "Aggiungi":
		 fav.addFavoriti(name);
		 return fav.stampaFavoriti();
	 case "Rimuovi":
		 fav.removeFavoriti(name);
		 return fav.stampaFavoriti();
	 case "Stampa":
		return fav.stampaFavoriti();
	default:
		JsonObject errObj = new JsonObject();
		errObj.addProperty("La scelta selezionata non è consentita","");
		return errObj;		 
	 }

 }
 


 /**
  *Metodo che dato un Array di Citta e un Array di filtri restituisce in Array di citta che rispettano quei filtri
  * 
  * @author Nicolò
  * @author Federico
  * @param body {@link JsonObject} contenente il Body per la gestione dei filtri da applicare
  * 
  * @return {@link ArrayList} di oggeti di tipo {@link Filtro} contenente i filtri da applicare alle statistiche
  */
 public static ArrayList<Citta> Filtra(JsonObject body,ArrayList<Citta>citta)throws MalformedException{
	 String s = "";
	 try{
		 JsonObject jo = body.get("filtri").getAsJsonObject();
		 JsonObject jobject = jo.get("tempo").getAsJsonObject();
		 if(jobject.get("attivo").getAsBoolean()) {
			 s = "tempo";
			 Tempo t = new Tempo();
			 citta = t.filtra(citta, jobject.get("filtro").getAsString());
			 citta.get(0).setNome(jobject.get("filtro").getAsString());// Serve a far si che ci sia un nome appropiato quando si stampano i risulotati		 
		}	 
		 jobject = jo.get("nome").getAsJsonObject();
		 if(jobject.get("attivo").getAsBoolean()) {
			 s = "nome";
			 NomeId n = new NomeId();
			 citta = n.filtra(citta, jobject.get("filtro").getAsString());
			 return citta; //Se viene effettuato il filtraggio per nome non può essere effettuato anche il filtraggio per ZoneGeo
			 }
		 jobject = jo.get("ZoneGeografiche").getAsJsonObject();
		 if(jobject.get("attivo").getAsBoolean()) {
			 s = "ZoneGeografiche";
			 ZoneGeografiche z = new ZoneGeografiche();
			 citta = z.filtra(citta, jobject.get("filtro").getAsString());
			 citta.get(0).setNome(jobject.get("filtro").getAsString()); // Serve a far si che ci sia un nome appropiato quando si stampano i risulotati
			 }
		 }catch(NullPointerException e) {
			 throw new MalformedException(s);
		 }
	 return citta; 
	}
 
 
 }
 
 