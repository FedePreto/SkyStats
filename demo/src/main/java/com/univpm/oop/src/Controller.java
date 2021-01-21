package com.univpm.oop.src;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.univpm.oop.exception.*;
import com.univpm.oop.model.Citta;
import com.univpm.oop.services.*;
import com.univpm.oop.statistiche.*;

 /**
  * Classe che gestisce le chiamate al nostro Server SpringBoot
  * @author Federico
  *
  */
 
 @RestController public class Controller {
	 
/**
 * Oggetto Convertitore creato per poter accedere ai sui metodi
 */	 
   Convertitore conv = new Convertitore();
 /**
  * Oggetto Stat creato per poter accedere ai suoi metodi  
  */
   Stat s = new Stat();
  
 /**
  * Call che restituisce il meteo di una citta richiesta dall'utente prelevando i dati in tempo reale da OpenWeather.
  * Se vengono richieste più di 60 città contemporaneamente, visto che OpenWeather impone un limite di 60 chiamate al 
  * minuto dalla 61° citta in poi, i dati saranno prelevatai dal DB.
  * 
  * @author Federico 
  * @param body {@link JsonObject} contenente un array di citta delle quali si vuole conoscere il meteo 
  * @return c  {@link ArrayList} di {@link Citta} contenente il meteo della città richieste
  * @throws CityNotFoundException eccezione personalizzata che viene lanciata nel caso in cui la città cercata non è presente nel DB
  */
 @PostMapping("/Weather")
 public ArrayList<Citta> getWeather(@RequestBody JsonObject body)throws CityNotFoundException {
	 JsonArray citta = body.get("citta").getAsJsonArray();
	 ArrayList<Citta> city = new ArrayList<Citta>();                              
	 Citta c = new Citta();
	 String url="";
	 for (int i = 0; i < citta.size(); i++) {
		 if(i<=60) {
			 try { 
				 int ID = Integer.parseInt(citta.get(i).getAsString());
				    // Stringa nel caso in cui venga fatta una richiesta per ID
				 url = "http://api.openweathermap.org/data/2.5/weather?id=" + ID + "&appid=" + DemoApplication.key + "&units=metric&lang=it";
					 
			 	}catch (NumberFormatException e) {
			 		// Stringa nel caso in cui venga fatta una richiesta per nome
				 url = "http://api.openweathermap.org/data/2.5/weather?q=" + citta.get(i).getAsString() + ",IT&appid=" + DemoApplication.key +"&units=metric&lang=it"; 
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
 * Call che restituisce le statistiche in base a dei filtri che vengo applicati nel body 
 * passato durante la chiamata.
 * @author Federico
 * @author Nicolò 
 * @param body {@link JsonObject} contenente i filtri da applicare al DB in maniera da
 *        prelevare solamente le città che corrispondono alle richieste dei filtri. 
 * @return Jsonreturn {@link JsonObject} contenente tutte le informazioni riguardanti le statistiche
 * @throws DataException eccezione personalizzata che viene lanciata quando non è possibile trovare
 *         dati con i filtri specificati 
 */
@PostMapping("/Stat")
 public JsonObject getStat(@RequestBody JsonObject body)throws DataException {	
	ArrayList<Citta> citta = conv.JsonToCitta();
	try {
		citta = Filtra(body, citta);
	} catch (MalformedException e) {
		JsonObject error = new JsonObject();
		error.addProperty("Error","Sintassi del body errata");
		return error;	
	}
	Double[][] dati = s.getValues(citta);
	if(dati==null) {
		throw new DataException();
	}
	JsonObject JsonReturn = new JsonObject();
	JsonReturn.addProperty("Nome", citta.get(0).getNome());
	JsonReturn.addProperty("Media Umidità", new DecimalFormat("#.##").format(s.getMedia(dati[1])));
	JsonReturn.addProperty("Varianza Umidità", new DecimalFormat("#.##").format(s.getVarianza(dati[1])));
	JsonReturn.addProperty("Media Pressione",new DecimalFormat("#.##").format(s.getMedia(dati[0])));
	JsonReturn.addProperty("Varianza Pressione", new DecimalFormat("#.##").format(s.getVarianza(dati[0])));
	JsonReturn.addProperty("Media Temperatura",  new DecimalFormat("#.##").format( s.getMedia(dati[2])));
	JsonReturn.addProperty("Varianza Temperatura",  new DecimalFormat("#.##").format(s.getVarianza(dati[2])));
	return JsonReturn;
	}

/**
 * Call che in base al periodo specificato dal parametro <b>period</b> restituisce le città e i loro valori assunti,
 * che sono i maggiori nel range di tempo specificato.
 * @author Federico 
 * @param period {@link String} contenente il range di tempo(Giornaliero, Settimanale, Mensile, Annuale o Customizzato)
 * con cui andare a filtrare il DB
 * @return {@link JsonObject} contenente tutte le citta con i valori minimi di pressione, umidità e temperatura nel range di tempo specificato
 */
 @GetMapping("/Max")
 public JsonObject getMax(@RequestParam(name = "Periodo", defaultValue = "Giornaliero") String period) {
	 ArrayList<Citta> citta = conv.JsonToCitta(); 
	 Tempo t = new Tempo();
	 return s.getMax(t.filtra(citta, period));
 }
  
 /**
 * Call che in base al periodo specificato dal parametro <b>period</b> restituisce le città e i loro valori assunti,
 * che sono i minori nel range di tempo specificato.
 * @author Federico
 * @param period {@link String} contenente il range di tempo(Giornaliero, Settimanale, Mensile, Annuale o Customizzato)
 * con cui andare a filtrare il DB
 * @return {@link JsonObject} contenente tutte le citta con i valori minimi di pressione, umidità e temperatura nel range di tempo specificato
 */
 @GetMapping("/Min")
 public JsonObject getMin(@RequestParam(name = "Periodo", defaultValue = "Giornaliero") String period) {
	 ArrayList<Citta> citta = conv.JsonToCitta();
	 Tempo t = new Tempo();
	 t.filtra(citta, period);
	 return s.getMin(citta);
 }
 
/**
 * Call che si occupa della gestione del file <b>config.json</b>
 * @author Federico * 
 * @param action {@link String} contenente l'azione da performare che può essere:
 *               	<br>"Aggiungi" : aggiunge ai favoriti <b>name</b>
 * 					<br>"Rimuovi" : rimuove dai favoriti <b>name</b>
 * 					<br>"Stampa" : ritorna i favoriti
 * @param name {@link String} contenente il nome di una città nel caso i cui si voglia eliminare o aggiungere al file <b>config.json</b>
 * @return {@link JsonObject} contenente le città presenti nel file <b>config.json</b>
 * @throws IllegalRequestException eccezione personalizzata che viene lanciata nel caso in cui l'azione da performare non è consentita
 */
 @GetMapping("/Fav")
 public JsonObject Favoriti(@RequestParam(name = "Action")String action,@RequestParam(name = "Name")String name)throws IllegalRequestException{
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
		throw new IllegalRequestException(action);
				 
	 }

 }
 


 /**
  * Metodo che dato un {@link ArrayList} di {@link Citta} filtra tutto l' {@link ArrayList}
  * in base alle specifiche di filtraggio passate nel parametro <b>body</b> 
  * @author Federico
  * @author Nicolò
  * @param body {@link JsonObject} contenente i filtri da applicare  * 
  * @return {@link ArrayList} di tipo {@link Citta} contenente le città filtrate
  * @throws MalformedException eccezione personalizzata lanciata nel caso in cui il filtro non è formattato correttamente
  */
 public static ArrayList<Citta> Filtra(JsonObject body,ArrayList<Citta>citta)throws MalformedException{
	 //Stringa che serve in caso di errore a poter stampare nel file log.txt su quale filtro è il problema
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
 
 