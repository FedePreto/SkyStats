package com.example.demo.statistiche;

import com.example.demo.GUI.BarraProgresso;
import com.example.demo.model.Citta;
import com.example.demo.services.Favoriti;
import com.example.demo.src.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
	 * 
	 * @return
	 */
	
	public Double[][] getValues(ArrayList<Citta>c){
		
		Double[][] dati = new Double[3][c.size()];
		
		for (int i = 0; i < c.size(); i++) {			
			dati[0][i] = c.get(i).getPressione();
			dati[1][i] = c.get(i).getUmidita();
			dati[2][i] = c.get(i).getTemperatura();
		}	
				
		return dati;
	}
	/**
	 * Prende i dati dal Database e li ritorna sottoforma di Array di Double in modo da poter essere processati da funzioni statistiche
	 * @author Nicolò
	 * @param c Lista delle citta dalle quali prelevare i dati
	 * @param citta nome della citta da selezionare
	 * @return array di valori di temperatura delle citta <b>citta</b> nell array <b>c</b>
	 */
	public Double[] getValues(ArrayList<Citta>c , String citta) {
		Convertitore conv = new Convertitore();
		ArrayList<Double> val = new ArrayList<Double>();
		try {
			int idCitta = Integer.parseInt(citta);
			for (int i = 0; i < c.size(); i++) {
				if (c.get(i).getId() == idCitta) {
					val.add(c.get(i).getTemperatura());
				}
			}
			Double[] d = new Double[val.size()];
			return val.toArray(d);
		} catch (NumberFormatException e) {
			for (int i = 0; i < c.size(); i++) {
				if (c.get(i).getNome().equals(citta)) {
					val.add(c.get(i).getTemperatura());
				}
			}
			if(val.isEmpty()) return null;
			Double[] d = new Double[val.size()];
				return val.toArray(d);
		}
	}
	/**
	 * Metodo che manda indietro i dati utili alla ricerca sottoforma di Array Double
	 * @author Federico
	 * @param inizio Data di inizio del range di tempo
	 * @param fine	Data di fine del range di tempo
	 * @param citta	Città soggetta a statistiche
	 * @param isPressione	Flag utilizzato per decidere di avere indietro o l'umidità oppure la pressione
	 * @return Array di Double contenente i valori richiesti
	 */
	public Double[] getValues(ArrayList<Citta> c, String citta, boolean isPressione) {
		Convertitore conv = new Convertitore();
		ArrayList<Double> val = new ArrayList<Double>();
		try {
			int idCitta = Integer.parseInt(citta);
			for (int i = 0; i < c.size(); i++) {
				if (c.get(i).getId() == idCitta) {
					if (isPressione) {
						val.add(c.get(i).getPressione());
					} else {
						val.add(c.get(i).getUmidita());
					}
				}
			}
			Double[] d = new Double[val.size()];
			return val.toArray(d);
		} catch (NumberFormatException e) {
			for (int i = 0; i < c.size(); i++) {
				if (c.get(i).getNome().equals(citta)) {
					if (isPressione) {
						val.add(c.get(i).getPressione());
					} else {
						val.add(c.get(i).getUmidita());
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
		if (val == null) return 0;
		else {
			double media = 0;
			for (int i = 0; i < val.length; i++) {
			media += val[i];
			}
			return media / (double) val.length;
			}
		}	

	/**
	 * Metodo che dati in input i valori e la media di questi calcola la varianza
	 * @author Federico	
	 * @param val	Valori usati per calcolare la varianza
	 * @param media	Media dei valori	
	 * @return	Varianza
	 */
	public double getVarianza(ArrayList<Citta> c, boolean isPressione) {
		Double[] val = getValues(c,c.get(0).getNome(),isPressione);
		double media = getMedia(val);
		
		if(val==null) return 0;
		else {
			double var = 0;
			for (int i = 0; i < val.length; i++) {
				var += Math.pow((val[i] - media), 2);
		}
			return var / val.length;
			}
		
	}
	/**
	 * Metodo che calcola la varianza dati i valori e la loro media
	 * @author Nicolò
	 * @param val valori da calcolarne la varianza
	 * @return varianza dei valori
	 */
	public double getVarianza(Double[] val) {
		double media = getMedia(val);
		if(val==null) return 0;
		else {
			double var = 0;
			for (int i = 0; i < val.length; i++) {
				var += Math.pow((val[i] - media), 2);
		}
			return var / val.length;
			}
		}

	public void stampaArray(ArrayList<Double[]> a) {
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i)[0] + " " + a.get(i)[1]);
		}
	}
	/**
	 * Date in input 2 date il metodo cerca nel database le citta con i valori massimi per ogni parametro
	 * @author Federico 
	 * @param inizio Data di inizio del range di tempo
	 * @param fine	Data di fine del range di tempo
	 * @return JsonObject che contiene tutte le con i valori massimi nel database nel range di tempo indicato
	 */
	public JsonObject getMax(Date inizio, Date fine) {
		Convertitore conv = new Convertitore();
		ArrayList<Citta> citta = conv.JsonToCitta(inizio,fine);
		
	/*
	  Array volto alla memorizzazione dei vari valori massimi con la seguente logica di indici:
		0-Umidità massima
		1-Pressione massima
		2-Varianza di umidità massima
		3-Varianza di pressione massima
	*/
		double max_val[] = new double[4];
		
	/*
	   Array volto alla memorizzazione dei vari indici dell'array citta contenenti le citta cercate
	   (Sfrutta la stessa logica di posizionamento dell'arrey precedente)	
	 */
		int max_index[] = new int[4];
		
		//Ciclo for che permette di analizzare tutte le citta presenti nell'arrayList
		for (int i = 0; i < citta.size(); i++) {	
			/*
			   L'istanziamento delle seguenti variabili è volto alla memorizzazione
			   dei valori permettendo così di non dover svolgere gli stessi calcoli due volte 
			   con il conseguente risparmio notevole di tempo in fase di esecuzione			 
			 */
			
			//Permette di memorizzare il valore massimo di umidità e l'indice della città che lo contiene
				
			if ( citta.get(i).getUmidita() > max_val[0]) {
				max_val[0] = citta.get(i).getUmidita();
				System.out.println(citta.get(i).getData());
				System.out.println(i);
				max_index[0] = i;
				}
			//Permette di memorizzare il valore massimo di pressione e l'indice della citta che lo contiene
			if (citta.get(i).getPressione()> max_val[1]) {				
				max_val[1] = citta.get(i).getPressione();
				max_index[1] = i;
				}
		}		
		Favoriti fav = new Favoriti();
		ArrayList<String> favoriti = fav.getFavoriti();
		double getVarU=0;
		double getVarP=0;
		//Crea una barra che permette all'utente di visualizzare lo stato di avanzamento
			BarraProgresso m=new BarraProgresso(0,favoriti.size());  
			m.setVisible(true);  
			m.setTitle("Calcolo della varianza");  //Titolo della barra
		for(int i=0; i<favoriti.size(); i++) {
			 m.paint(m.getGraphics()); 
			 m.jb.setValue(i);
			for(int j=0; j<citta.size(); j++) {
				if(favoriti.get(i).equals(citta.get(j).getNome())) {
					getVarU= getVarianza(citta,true);
					//getVarP memorizza la varianza della pressione per una determinata citta
					getVarP=getVarianza(citta,false);
					break;
				}
			}
					//Permette di memorizzare il valore massimo di varianza di Umidita e l'indice della città su cui è stato calcolato
				if (getVarU > max_val[2]) {
					max_val[2] = getVarU;
					max_index[2] = i;
					}
				
				//Permette di memorizzare il valore massimo di varianza di pressione e l'indice della città su cui è stato calcolato
				if (getVarP > max_val[3]) {
					max_val[3] = getVarP;
					max_index[3] = i;
					}
		}
		m.dispose();
		JsonObject JsonReturn = new JsonObject();
		if(max_val[0] == 0) {
			JsonReturn.addProperty("Nessun valore trovato nel range di tempo specficato","");
			return JsonReturn;
		}
		JsonObject Max_Umidity = new JsonObject();
		JsonObject Max_Pression = new JsonObject();
		JsonObject Max_Var_Um = new JsonObject();
		JsonObject Max_Var_Pr = new JsonObject();
		Max_Umidity.addProperty("Nome", citta.get(max_index[0]).getNome());
		Max_Umidity.addProperty("Valore", new DecimalFormat("#.##").format(max_val[0])+"%");
		Max_Pression.addProperty("Nome",citta.get(max_index [1]).getNome());
		Max_Pression.addProperty("Valore", new DecimalFormat("#.##").format(max_val[1])+" hPa");
		Max_Var_Um.addProperty("Nome", citta.get(max_index [2]).getNome());
		Max_Var_Um.addProperty("Valore", new DecimalFormat("#.##").format(max_val[2]));
		Max_Var_Pr.addProperty("Nome", citta.get(max_index [3]).getNome());
		Max_Var_Pr.addProperty("Valore", new DecimalFormat("#.##").format(max_val[3]));
		JsonReturn.add("Umidità Massima", Max_Umidity);
		JsonReturn.add("Pressione Massima", Max_Pression);
		JsonReturn.add("Varianza di Umidità Massima", Max_Var_Um);
		JsonReturn.add("Varianza di Pressione Massima", Max_Var_Pr);
		return JsonReturn;
	}
	/**
	 * Metodo che manda indietro un JsonObject di tutte le città con valori minimi nel range di tempo definito da inizio e fine
	 * @author Federico
	 * @param inizio Data di inizio del range
	 * @param fine Data di fine del range
	 * @return JsonObject che contiente tutte le citta con i valori minimi nel database contenute nel range di tempo definito da inizio e fine
	 */
	public JsonObject getMin(Date inizio, Date fine) {
		Convertitore conv = new Convertitore();
		ArrayList<Citta> citta = conv.JsonToCitta(inizio,fine);
	/*
	  Array volto alla memorizzazione dei vari valori minimi con la seguente logica di indici:
		0-Umidità minima
		1-Pressione minima
		2-Varianza di umidità minima
		3-Varianza di pressione minima
	*/
		double min_val[] = new double[4];
		
	/*
	   Array volto alla memorizzazione dei vari indici dell'array citta contenenti le citta cercate
	   (Sfrutta la stessa logica di posizionamento dell'arrey precedente)	
	 */
		int min_index[] = new int[4];
 //Inizializza i minimi al primo valore letto nell'array poi procederà con i confronti
		 min_val[0] = citta.get(0).getUmidita();
	     min_val[1] = citta.get(0).getPressione();
	     min_val[2] = getVarianza(getValues(citta, citta.get(0).getNome()));
		 min_val[3] = getVarianza(getValues(citta, citta.get(0).getNome()));
		 //Ciclo for che permette di analizzare tutte le citta presenti nell'arrayList
		for (int i = 1; i < citta.size(); i++) {
		   /*
			   L'istanziamento delle seguenti variabili è volto alla memorizzazione
			   dei valori permettendo così di non dover svolgere gli stessi calcoli due volte 
			   con il conseguente risparmio notevole di tempo in fase di esecuzione			 
			 */				
				//Permette di memorizzare il valore minimo di umidità e l'indice della città che lo contiene
				if ( citta.get(i).getUmidita() < min_val[0]) {
					min_val[0] = citta.get(i).getUmidita();
					min_index[0] = i;
				}
				
				//Permette di memorizzare il valore minimo di pressione e l'indice della citta che lo contiene
				if (citta.get(i).getPressione() < min_val[1]) {
					min_val[1] = citta.get(i).getPressione();
					min_index[1] = i;
				}
		}
		Favoriti fav = new Favoriti();
		ArrayList<String> favoriti = fav.getFavoriti();
		double getVarU=0;
		double getVarP=0;
		//Crea una barra che permette all'utente di visualizzare lo stato di avanzamento
			BarraProgresso m=new BarraProgresso(0,favoriti.size());  
			m.setVisible(true);  
		    m.setTitle("Calcolo dell varianza");  //Titolo della barra
		for(int i=1; i<favoriti.size(); i++) {
			m.paint(m.getGraphics()); 
			m.jb.setValue(i);
			for(int j=1; j<citta.size(); j++) {
				if(favoriti.get(i).equals(citta.get(j).getNome())) {					
					getVarU= getVarianza(getValues(citta, citta.get(j).getNome(), false));
					//getVarP memorizza la varianza della pressione per una determinata citta
					getVarP=getVarianza(getValues(citta, citta.get(j).getNome(), true));
					break;
			   }
			}
				//Permette di memorizzare il valore minimo di varianza di Umidita e l'indice della città su cui è stato calcolato
				if (getVarU < min_val[2]) {					
					min_val[2] = getVarU;
					min_index[2] = i;
				}
				
				//Permette di memorizzare il valore minimo di varianza di pressione e l'indice della città su cui è stato calcolato
				if (getVarP < min_val[3]) {
					min_val[3] = getVarP;
					min_index[3] = i;
				}				
			}
		m.dispose();
		JsonObject JsonReturn = new JsonObject();
		if(min_val[0] == 0 && min_val[1] == 0 && min_val[2] == 0 && min_val[3]==0) {
			JsonReturn.addProperty("Nessun valore trovato nel range di tempo specficato","");
			return JsonReturn;
		}
		JsonObject Min_Umidity = new JsonObject();
		JsonObject Min_Pression = new JsonObject();
		JsonObject Min_Var_Um = new JsonObject();
		JsonObject Min_Var_Pr = new JsonObject();
		Min_Umidity.addProperty("Nome", citta.get(min_index[0]).getNome());
		Min_Umidity.addProperty("Valore", new DecimalFormat("#.##").format(min_val[0])+"%");
		Min_Pression.addProperty("Nome",citta.get(min_index [1]).getNome());
		Min_Pression.addProperty("Valore", new DecimalFormat("#.##").format(min_val[1])+" hPa");
		Min_Var_Um.addProperty("Nome", citta.get(min_index [2]).getNome());
		Min_Var_Um.addProperty("Valore", new DecimalFormat("#.##").format(min_val[2]));
		Min_Var_Pr.addProperty("Nome", citta.get(min_index [3]).getNome());
		Min_Var_Pr.addProperty("Valore", new DecimalFormat("#.##").format(min_val[3]));
		JsonReturn.add("Umidità Minima", Min_Umidity);
		JsonReturn.add("Pressione Minima", Min_Pression);
		JsonReturn.add("Varianza di Umidità Minima", Min_Var_Um);
		JsonReturn.add("Varianza di Pressione Minima", Min_Var_Pr);
		return JsonReturn;
	}
	
//Metodo utilizzato nella classe Main
	/**
	 * Stampa le citta con valori di umidita e pressione massimi
	 * @author Nicolò
	 * 
	 * @param inizio data di inizio del range di tempo
	 * @param fine data di fine del range di tempo
	 * @return stringa di output
	 */
	public String printMaxValues(Date inizio, Date fine) {
		Convertitore conv = new Convertitore();
		ArrayList<Citta> citta = conv.JsonToCitta(inizio,fine);
		
		//Array per la memorizzazione dei valori minimi e massimi

		double min[] = new double[4];
		double max[] = new double[4];
		
		
        //Variabili per la memorizzazione degli indici delle citta massimi
		int maxU = 0;
		int maxP = 0;
		int maxVU = 0;
		int maxVP = 0;

		//Variabile per la memorizzazione dell'indice della Citta minimi
		int minU = 0;
		int minP = 0;
		int minVU = 0;
		int minVP = 0;
        //Inizializza i minimi al primo valore letto nell'array poi procederà con i confronti
		min[0] = citta.get(0).getUmidita();
		min[1] = citta.get(0).getPressione();
		min[2] = getVarianza(getValues(citta, citta.get(0).getNome(), false));
		min[3] = getVarianza(getValues(citta, citta.get(0).getNome(), true));
		for (int i = 0; i < citta.size(); i++) {
			// Max
			//getVarF prende la varianza dell'umidità
			double getVarF= getVarianza(getValues(citta, citta.get(i).getNome(), false));
			//getVarT prende la varianza della pressione
			double getVarT=getVarianza(getValues(citta, citta.get(i).getNome(), true));
			//Hum calcola l'umidità sul singolo valore
			double Hum = citta.get(i).getUmidita();
			//Pres calcola la pressione su singolo valore
			double Pres = citta.get(i).getPressione();
			
			//Il metodo Max è stato Ottimizzato riducendo il numero di call, innestando i vari if
			if( citta.get(i).getData().after(inizio) && citta.get(i).getData().before(fine)) {
				//check se l'umidità è il valore massimo
				if ( Hum > max[0]) {
					max[0] = Hum;
					maxU = i;
				}
				//check se l'umidità è il valore minimo
				if (Hum < min[0]) {
				min[0] = Hum;
				minU = i;
				}
				//check se la pressione è il valore massimo
				if (Pres > max[1]) {
					max[1] = Pres;
					maxP = i;
				}
				//check se la pressione è il valore minimo
				if ( Pres < min[1]) {
				min[1] = Pres;
				minP = i;
				}
			}
			//check se la varianza dell'umidità è il valore massimo
			if (getVarF > max[2]) {
				max[2] = getVarF;
				maxVU = i;
			}
			//check se la varianza dell'umidità è il valore minimo
			if (getVarF < min[2]) {
				min[2] = getVarF;
				minVU = i;
			}
			//check se la varianza della pressione è il valore massimo
			if (getVarT > max[3]) {
				max[3] = getVarT;
				maxVP = i;
			}
			//check se la varianza della pressione è il valore minimo
			if (getVarT < min[3]) {
				min[3] = getVarT;
				minVP = i;
			}
			
		}
		
		return	"La città con umidita maggiore è: " + citta.get(maxU).getNome() + " con " + max[0] + "%<br>"+
				"La città con pressione maggiore è: " + citta.get(maxP).getNome() + " con " + max[1] + " hPa<br>"+
				"La citta con varianza di pressione maggiore è: " + citta.get(maxVP).getNome() + " con " + max[3]+"<br>"+
				"La città con umidita minore è: " + citta.get(minU).getNome() + " con " + min[0] + "%<br>"+
				"La città con pressione minore è: " + citta.get(minP).getNome() + " con " + min[1] + " hPa<br>"+
				"La citta con varianza di pressione minore è: " + citta.get(minVP).getNome() + " con " + min[3];
				
	}
	
	/**
	 * Date le città e la posizione geografica da filtrare ritorna un array di dati in base a <b>flag</b>
	 * @author Nicolò
	 * @param c citta da filtrare
	 * @param location Posizione geografica
	 * @param flag <br>1- Umidità<br>2- Pressione<br>3- Temperatura
	 * @return dati filtrati
	 */
	public Double[] getDataByLocation(ArrayList<Citta> c, String location,int flag) {
		ArrayList<Double> val = new ArrayList<Double>();
		for (int i = 0; i < c.size(); i++) {
			if(c.get(i).getPosizione().equals(location)) {
				if(flag==0) {
					val.add(c.get(i).getPressione());
				}else if(flag==1) {
					val.add(c.get(i).getUmidita());
				}else if(flag==2) {
					val.add(c.get(i).getTemperatura());
				}
			}
		}
		Double[] tmp = new Double[val.size()];
		tmp = (Double[]) val.toArray();
		return tmp;
	}
	/**
	 * Metodo utilizzato per prendere dati dal database in base al range di tempo definito tra inizio e fine e alla posizione geografica (location) 
	 * @param inizio Data di inizio del range di tempo
	 * @param fine Data di fine del range di tempo
	 * @param location zona geografica ( Nord, Centro o Sud)
	 * @param isPressione Flag utilizzato per decidere se prendere i dati riguardanti la pressione oppure l'umidità
	 * @return Array di Double contentente tutti i valori
	 */
	public Double[] getDataByLocation(Date inizio, Date fine, String location, boolean isPressione) {
		Convertitore conv = new Convertitore();
		ArrayList<Citta> file = conv.JsonToCitta();
		ArrayList<Double> temp = new ArrayList<Double>();

		for (int i = 0; i < file.size(); i++) {
			if (file.get(i).getPosizione().equals(location) && file.get(i).getData().after(inizio)
					&& file.get(i).getData().before(fine)) {
				if (isPressione) {
					temp.add(file.get(i).getPressione());
				} else {
					temp.add(file.get(i).getUmidita());
				}
			}
		}
		if(temp.isEmpty())
			return null;
		Double[] d = new Double[temp.size()];
		return temp.toArray(d);

	}
}
