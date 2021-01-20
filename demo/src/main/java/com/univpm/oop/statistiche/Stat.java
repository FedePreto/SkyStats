package com.univpm.oop.statistiche;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.univpm.oop.model.Citta;
import com.univpm.oop.services.Convertitore;
import com.univpm.oop.services.Favoriti;
import com.univpm.oop.src.*;

import javax.swing.*;  
import java.util.Date;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * Classe che contiene tutti i metodi riguardanti le statistiche.
 * 	@author Nicolò
 *	@author Federico
 */
public class Stat {
	
	/**
	 * 
	 * Prende in input un ArrayList di Citta e ritorna una matrice che su ogni riga 
	 * contiene Pressione, Umidità e Temperatura per ogni città dell'Arraylist
	 * 
	 * @author Federico
	 * @author Nicolò
	 * 
	 * @return
	 */
	
	public static Double[][] getValues(ArrayList<Citta>c){
		
		Double[][] dati = new Double[3][c.size()];
		
		for (int i = 0; i < c.size(); i++) {			
			dati[0][i] = c.get(i).getPressione();
			dati[1][i] = c.get(i).getUmidita();
			dati[2][i] = c.get(i).getTemperatura();
		}	
				
		return dati;
	}
	
	/**
	 * Metodo che manda indietro i dati di una città che possono essere temperatura, umidità o pressione 
	 * in base alla scelta effettuata
	 * @author Federico
	 * @param c {@link ArrayList} di città contenente i dati da cercare 
	 * @param citta {@link String} contenente il nome o l'Id della città da cercare 
	 * @param scelta parametro che serve a selezionare il tipo di dato cercato
	 *              (0 = pressione,
	 *               1 = umidità,
	 *               2 = temperatura) 
	 * @return Array di tipo Double contenente i valori cercati
	 */
	public Double[] getValues(ArrayList<Citta> c, String citta, int scelta) {
		ArrayList<Double> val = new ArrayList<Double>();
		try {
			int idCitta = Integer.parseInt(citta);
			for (int i = 0; i < c.size(); i++) {
				if (c.get(i).getId() == idCitta) {
					switch(scelta) {
					case 0: val.add(c.get(i).getPressione());
					        break;
					case 1: val.add(c.get(i).getUmidita());
					        break;
					case 2: val.add(c.get(i).getTemperatura());
					        break;
					}
					
				}
			}
			Double[] d = new Double[val.size()];
			return val.toArray(d);
		} catch (NumberFormatException e) {
			for (int i = 0; i < c.size(); i++) {
				if (c.get(i).getNome().equals(citta)) {
					switch(scelta) {
					case 0: val.add(c.get(i).getPressione());
					        break;
					case 1: val.add(c.get(i).getUmidita());
					        break;
					case 2: val.add(c.get(i).getTemperatura());
					        break;
					}
				}
			}
			if(val.isEmpty()) return null;
			Double[] d = new Double[val.size()];
				return val.toArray(d);
		}
	}
	
	/**
	 * Dati i valori ritorna la media
	 * @author Nicolò
	 * @param val	Valori per i quali calcolare la media
	 * @return	Ritorna la media
	 */
	public double getMedia(Double[] val) {
		if (val == null) {
			return 0;
		}else {
				double media = 0;
				for (int i = 0; i < val.length; i++) {
					media += val[i];
				}
				return media / (double) val.length;
			}
		}	

	/**
	 * Metodo che, dati in input i valori e la media di questi, calcola la varianza
	 * @author Federico	
	 * @param val	Valori usati per calcolare la varianza
	 * @param media	Media dei valori	
	 * @return	Varianza
	 */
	public double getVarianza(ArrayList<Citta> city, String c,int  scelta) {
		Double[] val = getValues(city,c,scelta);
		double media = getMedia(val);		
		if(val==null) {
			return 0;
		}else{
			double var = 0;
			for (int i = 0; i < val.length; i++) {
				var += Math.pow((val[i] - media), 2);
			}
			return var / val.length;
		}
	}
	/**
	 * Metodo che calcola la varianza dati i valori e la loro media
	 * @author Federico
	 * @author Nicolò
	 * @param val valori da calcolarne la varianza
	 * @return varianza dei valori
	 */
	public double getVarianza(Double[] val) {
		double media = getMedia(val);
		if(val==null) {
			return 0;
		}else{
			double var = 0;
			for (int i = 0; i < val.length; i++) {
				var += Math.pow((val[i] - media), 2);
			}
			return var / val.length;
		}
	}

	
	/**
	 * Date in input un Array di Citta,il metodo cerca nel database le citta con i valori massimi per ogni parametro e le ritorna sotto forma di JsonObject
	 * @author Federico 
	 * @param  citta {@link ArrayList} di tipo {@link Citta} contenente tutte le citta filtrate su cui fare statistiche
	 * @return JsonObject che contiene tutte le con i valori massimi nel database nel range di tempo indicato
	 */
	public JsonObject getMax(ArrayList<Citta> citta) {	
	/*
	  Array volto alla memorizzazione dei vari valori massimi con la seguente logica di indici:
		0- Pressione massima
		1- Umidità massima
		2- Temperatura massima 
		3- Varianza di pressione massima
		4- Varianza di umidità massima
		5- Varianza di temperatura massima
	*/
		double max_val[] = new double[6];
		
	/*
	   Array volto alla memorizzazione dei vari indici dell'array citta contenenti le citta cercate
	   (Sfrutta la stessa logica di posizionamento dell'arrey precedente)	
	 */
		int max_index[] = new int[6];
		
		//Ciclo for che permette di analizzare tutte le citta presenti nell'arrayList
		for (int i = 0; i < citta.size(); i++) {				
			
			//Permette di memorizzare il valore massimo di pressione e l'indice della citta che lo contiene
			if (citta.get(i).getPressione() > max_val[0]) {				
				max_val[0] = citta.get(i).getPressione();
				max_index[0] = i;
				}
			//Permette di memorizzare il valore massimo di umidità e l'indice della città che lo contiene
			if ( citta.get(i).getUmidita() > max_val[1]) {
				max_val[1] = citta.get(i).getUmidita();
				max_index[1] = i;
				}
			//Permette di memorizzare il valore massimo di temeperatura e l'indice della citta che lo contiene
			if (citta.get(i).getTemperatura() > max_val[2]) {				
				max_val[2] = citta.get(i).getTemperatura();
				max_index[2] = i;
				}
		}	
		/* 
		   Creo un oggetto favoriti per prendere i nomi di tutte le citta della lista in maniera da non dover
		   calcolare la varianza più volte della stessa città nella lista(questo mi permette di risparmiare un sacco di tempo)		    
		*/
		 
		ArrayList<String> favoriti = Favoriti.favoriti;
		double getVarP=0;
		double getVarU=0;
		double getVarT=0;
		for(int i=0; i<favoriti.size(); i++) {
			 getVarP = getVarianza(citta, favoriti.get(i),0);
			 getVarU = getVarianza(citta,favoriti.get(i) ,1);
			 getVarT = getVarianza(citta, favoriti.get(i) ,2);
			//Permette di memorizzare il valore massimo di varianza di pressione e l'indice della città su cui è stato calcolato
			if (getVarP > max_val[3]) {				
				max_val[3] = getVarP;
				max_index[3] = i;
				}
			//Permette di memorizzare il valore massimo di varianza di Umidita e l'indice della città su cui è stato calcolato
			if (getVarU > max_val[4]) {
				max_val[4] = getVarU;
				max_index[4] = i;
			}
			//Permette di memorizzare il valore massimo di varianza di temperatura e l'indice della città su cui è stato calcolato
			if (getVarT > max_val[5]) {
				max_val[5] = getVarT;
				max_index[5] = i;
				}
				
				
		}
		JsonObject JsonReturn = new JsonObject();
		if(max_val[0] == 0) {
			JsonReturn.addProperty("Nessun valore trovato nel range di tempo specficato","");
			return JsonReturn;
		}
		JsonObject Max_Pression = new JsonObject();
		JsonObject Max_Umidity = new JsonObject();
		JsonObject Max_Temperature = new JsonObject();
		JsonObject Max_Var_Pr = new JsonObject();
		JsonObject Max_Var_Um = new JsonObject();
		JsonObject Max_Var_Temp = new JsonObject();
		Max_Pression.addProperty("Nome",citta.get(max_index [0]).getNome());
		Max_Pression.addProperty("Valore", new DecimalFormat("#.##").format(max_val[0])+" hPa");
		Max_Umidity.addProperty("Nome", citta.get(max_index[1]).getNome());
		Max_Umidity.addProperty("Valore", new DecimalFormat("#.##").format(max_val[1])+"%");
		Max_Temperature.addProperty("Nome",citta.get(max_index [2]).getNome());
		Max_Temperature.addProperty("Valore", new DecimalFormat("#.##").format(max_val[2])+"°C");	
		Max_Var_Pr.addProperty("Nome", favoriti.get(max_index [3]));
		Max_Var_Pr.addProperty("Valore", new DecimalFormat("#.##").format(max_val[3]));
		Max_Var_Um.addProperty("Nome", favoriti.get(max_index [4]));
		Max_Var_Um.addProperty("Valore", new DecimalFormat("#.##").format(max_val[4]));
		Max_Var_Temp.addProperty("Nome", favoriti.get(max_index [5]));
		Max_Var_Temp.addProperty("Valore", new DecimalFormat("#.##").format(max_val[5]));
		JsonReturn.add("Pressione Massima", Max_Pression);
		JsonReturn.add("Umidità Massima", Max_Umidity);
		JsonReturn.add("Temperatura Massima", Max_Temperature);
		JsonReturn.add("Varianza di Pressione Massima", Max_Var_Pr);
		JsonReturn.add("Varianza di Umidità Massima", Max_Var_Um);
		JsonReturn.add("Varianza di Temperatura Massima", Max_Var_Temp);
		return JsonReturn;
	}
	
	
	/**
	 * Date in input un Array di Citta,il metodo cerca nel database le citta con i valori minimi per ogni parametro e le ritorna sotto forma di JsonObject
	 * @author Federico
	 * @param inizio Data di inizio del range
	 * @param fine Data di fine del range
	 * @return JsonObject che contiente tutte le citta con i valori minimi nel database contenute nel range di tempo definito da inizio e fine
	 */
	public JsonObject getMin(ArrayList<Citta>citta) {
		/*
		  Array volto alla memorizzazione dei vari valori massimi con la seguente logica di indici:
			0- Pressione minima
			1- Umidità minima
			2- Temperatura minima 
			3- Varianza di pressione minima
			4- Varianza di umidità minima
			5- Varianza di temperatura minima
		*/
		double min_val[] = new double[6];
		
	/*
	   Array volto alla memorizzazione dei vari indici dell'array citta contenenti le citta cercate
	   (Sfrutta la stessa logica di posizionamento dell'arrey precedente)	
	 */
		int min_index[] = new int[6];
 //Inizializza i minimi al primo valore letto nell'array poi procederà con i confronti
		 min_val[0] = citta.get(0).getPressione();
	     min_val[1] = citta.get(0).getUmidita();
	     min_val[2] = citta.get(0).getTemperatura();
	     min_val[3] = getVarianza(citta,citta.get(0).getNome(),0);
		 min_val[4] = getVarianza(citta,citta.get(0).getNome(),1);
		 min_val[5] = getVarianza(citta,citta.get(0).getNome(),2);
		 System.out.println(min_val[3] + "  " + min_val[4] + "  " + min_val[5]);
		 //Ciclo for che permette di analizzare tutte le citta presenti nell'arrayList
		for (int i = 1; i < citta.size(); i++) {
			//Permette di memorizzare il valore minimo di pressione e l'indice della citta che lo contiene
			if (citta.get(i).getPressione() < min_val[0]) {
				min_val[0] = citta.get(i).getPressione();
				min_index[0] = i;
			}	
			//Permette di memorizzare il valore minimo di umidità e l'indice della città che lo contiene
			if ( citta.get(i).getUmidita() < min_val[1]) {
				min_val[1] = citta.get(i).getUmidita();
				min_index[1] = i;
			}
			//Permette di memorizzare il valore minimo di temperatura e l'indice della città che lo contiene
			if ( citta.get(i).getTemperatura() < min_val[2]) {
				min_val[2] = citta.get(i).getTemperatura();
				min_index[2] = i;
			}
				
		}
		
		ArrayList<String> favoriti = Favoriti.favoriti;
		double getVarP=0;
		double getVarU=0;
		double getVarT=0;
		for(int i=0; i<favoriti.size(); i++) {
			System.out.println(favoriti.get(i));
		    //getVarP memorizza la varianza della pressione per una determinata citta
			getVarP = getVarianza(citta,favoriti.get(i),0);
			System.out.println(getVarP);
			getVarU = getVarianza(citta,favoriti.get(i),1);
			System.out.println(getVarU);
			getVarT = getVarianza(citta,favoriti.get(i),2);	
			System.out.println(getVarT);
			//Permette di memorizzare il valore minimo di varianza di pressione e l'indice della città su cui è stato calcolato
			if (getVarP <= min_val[3]) {
				System.out.println(min_val[3] + " " + getVarP);
				min_val[3] = getVarP;
				min_index[3] = i;
				System.out.println(favoriti.get(min_index[3]));
			}
				//Permette di memorizzare il valore minimo di varianza di Umidita e l'indice della città su cui è stato calcolato
			if (getVarU <= min_val[4]) {	
				System.out.println(min_val[4] + " " + getVarU);
				min_val[4] = getVarU;
				min_index[4] = i;
				System.out.println(favoriti.get(min_index[4]));
				}
				//Permette di memorizzare il valore minimo di varianza di pressione e l'indice della città su cui è stato calcolato
			if (getVarT <= min_val[5]) {
				System.out.println(min_val[5] + " " + getVarT);
				min_val[5] = getVarT;
				min_index[5] = i;
				System.out.println(favoriti.get(min_index[5]));
			}				
		}
		
		JsonObject JsonReturn = new JsonObject();
		if(min_val[0] == 0 && min_val[1] == 0 && min_val[2] == 0 && min_val[3]==0) {
			JsonReturn.addProperty("Nessun valore trovato nel range di tempo specficato","");
			return JsonReturn;
		}
		JsonObject Min_Pression = new JsonObject();
		JsonObject Min_Umidity = new JsonObject();
		JsonObject Min_Temperature = new JsonObject();
		JsonObject Min_Var_Pr = new JsonObject();
		JsonObject Min_Var_Um = new JsonObject();
		JsonObject Min_Var_Temp = new JsonObject();
		Min_Pression.addProperty("Nome",citta.get(min_index [0]).getNome());
		Min_Pression.addProperty("Valore", new DecimalFormat("#.##").format(min_val[0])+" hPa");
		Min_Umidity.addProperty("Nome", citta.get(min_index[1]).getNome());
		Min_Umidity.addProperty("Valore", new DecimalFormat("#.##").format(min_val[1])+"%");
		Min_Temperature.addProperty("Nome", citta.get(min_index[2]).getNome());
		Min_Temperature.addProperty("Valore", new DecimalFormat("#.##").format(min_val[2])+"°C");
		Min_Var_Pr.addProperty("Nome", favoriti.get(min_index[3]));
		Min_Var_Pr.addProperty("Valore", new DecimalFormat("#.##").format(min_val[3]));
		Min_Var_Um.addProperty("Nome", favoriti.get(min_index[4]));
		Min_Var_Um.addProperty("Valore", new DecimalFormat("#.##").format(min_val[4]));
		Min_Var_Temp.addProperty("Nome", favoriti.get(min_index[5]));
		Min_Var_Temp.addProperty("Valore", new DecimalFormat("#.##").format(min_val[5]));
		JsonReturn.add("Pressione Minima", Min_Pression);
		JsonReturn.add("Umidità Minima", Min_Umidity);
		JsonReturn.add("Temperatura Minima", Min_Temperature);
		JsonReturn.add("Varianza di Pressione Minima", Min_Var_Pr);
		JsonReturn.add("Varianza di Umidità Minima", Min_Var_Um);
		JsonReturn.add("Varianza di Temperatura Minima", Min_Var_Temp);
		return JsonReturn;
	}
	
}
