package com.example.demo.statistiche;

import com.example.demo.model.Citta;
import com.example.demo.services.BarraProgresso;
import com.example.demo.src.*;
import javax.swing.*;  
import java.util.Date;

import javax.swing.JProgressBar;

import org.json.simple.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Stat {
	public Double[] getValues(Date inizio, Date fine, String citta, boolean isPressione) {
		Convertitore conv = new Convertitore();
		ArrayList<Citta> c = conv.JsonToCitta();
		ArrayList<Double> val = new ArrayList<Double>();
		try {
			int idCitta = Integer.parseInt(citta);
			for (int i = 0; i < c.size(); i++) {
				// if(c.get(i).getId()==idCitta && c.get(i).getData().before(fine) &&
				// c.get(i).getData().after(inizio)){
				if (c.get(i).getData().after(inizio) && c.get(i).getId() == idCitta
						&& c.get(i).getData().before(fine)) {
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
				// if(c.get(i).getNome().equals(citta) &&
				// c.get(i).getData().before(fine)&&c.get(i).getData().after(inizio) ) {
				if (c.get(i).getData().after(inizio) && c.get(i).getNome().equals(citta)
						&& c.get(i).getData().before(fine)) {
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

	

	public double getVarianza(Double[] val, double media) {
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
	@SuppressWarnings("unchecked")
	public JSONObject getMax(Date inizio, Date fine) {
		Convertitore conv = new Convertitore();
		ArrayList<Citta> citta = conv.JsonToCitta();
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
		
		//Crea una barra che permette all'utente di visualizzare lo stato di avanzamento
		BarraProgresso m=new BarraProgresso(0,citta.size());  
	    m.setVisible(true);  
	    m.setTitle("Stato di avanzamento");  //Titolo della barra
		
		//Ciclo for che permette di analizzare tutte le citta presenti nell'arrayList
		for (int i = 0; i < citta.size(); i++) {
		    
			//Aggiorna lo stato della barra e imposta il nuovo valore
			 m.paint(m.getGraphics()); 
			 m.jb.setValue(i);
			 System.out.println(i);
			/*
			   L'istanziamento delle seguenti variabili è volto alla memorizzazione
			   dei valori permettendo così di non dover svolgere gli stessi calcoli due volte 
			   con il conseguente risparmio notevole di tempo in fase di esecuzione			 
			 */
			
			//getVarU memorizza la varianza dell'umidità per una determinata citta
			double getVarU= getVarianza(getValues(inizio, fine, citta.get(i).getNome(), false),
					getMedia(getValues(inizio, fine, citta.get(i).getNome(), false)));
			//getVarP memorizza la varianza della pressione per una determinata citta
			double getVarP=getVarianza(getValues(inizio, fine, citta.get(i).getNome(), true),
					getMedia(getValues(inizio, fine, citta.get(i).getNome(), true)));
			//Hum memorizza l'umidità della città momentanemante processata
			double Hum = citta.get(i).getUmidita();
			//Pres calcola la pressione su singolo valore
			double Pres = citta.get(i).getPressione();
			
			//Questo if permette di selezionare solo le città nel range di tempo personalizzato
			if( citta.get(i).getData().after(inizio) && citta.get(i).getData().before(fine)) {
				//Permette di memorizzare il valore massimo di umidità e l'indice della città che lo contiene
				if ( Hum > max_val[0]) {
					max_val[0] = Hum;
					max_index[0] = i;
				}
				
				//Permette di memorizzare il valore massimo di pressione e l'indice della citta che lo contiene
				if (Pres > max_val[1]) {
					max_val[1] = Pres;
					max_index[1] = i;
				}
				
				//Permette di memorizzare il valore massimo di varianza di Umidita e l'indice della città su cui è stato calcolato
				if (getVarU > max_val[2]) {
					System.out.println(getVarU);
					max_val[2] = getVarU;
					max_index[2] = i;
				}
				
				//Permette di memorizzare il valore massimo di varianza di pressione e l'indice della città su cui è stato calcolato
				if (getVarP > max_val[3]) {
					System.out.println(getVarP);
					max_val[3] = getVarP;
					max_index[3] = i;
				}
				
				
			}
		
	    }
		m.dispose();
		JSONObject JsonReturn = new JSONObject();
		if(max_val[0] == 0) {
			JsonReturn.put("Nessun valore trovato nel range di tempo specficato","");
			return JsonReturn;
		}
		JSONObject Max_Umidity = new JSONObject();
		JSONObject Max_Pression = new JSONObject();
		JSONObject Max_Var_Um = new JSONObject();
		JSONObject Max_Var_Pr = new JSONObject();
		Max_Umidity.put("Nome", citta.get(max_index[0]).getNome());
		Max_Umidity.put("Valore", new DecimalFormat("#.##").format(max_val[0])+"%");
		Max_Pression.put("Nome",citta.get(max_index [1]).getNome());
		Max_Pression.put("Valore", new DecimalFormat("#.##").format(max_val[1])+" hPa");
		Max_Var_Um.put("Nome", citta.get(max_index [2]).getNome());
		Max_Var_Um.put("Valore", new DecimalFormat("#.##").format(max_val[2]));
		Max_Var_Pr.put("Nome", citta.get(max_index [3]).getNome());
		Max_Var_Pr.put("Valore", new DecimalFormat("#.##").format(max_val[3]));
		JsonReturn.put("Umidità Massima", Max_Umidity);
		JsonReturn.put("Pressione Massima", Max_Pression);
		JsonReturn.put("Varianza di Umidità Massima", Max_Var_Um);
		JsonReturn.put("Varianza di Pressione Massima", Max_Var_Pr);
		return JsonReturn;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getMin(Date inizio, Date fine) {
		Convertitore conv = new Convertitore();
		ArrayList<Citta> citta = conv.JsonToCitta();
	/*
	  Array volto alla memorizzazione dei vari valori massimi con la seguente logica di indici:
		0-Umidità massima
		1-Pressione massima
		2-Varianza di umidità massima
		3-Varianza di pressione massima
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
	     min_val[2] = getVarianza(getValues(inizio, fine, citta.get(0).getNome(), false),
					getMedia(getValues(inizio, fine, citta.get(0).getNome(), false)));
		 min_val[3] = getVarianza(getValues(inizio, fine, citta.get(0).getNome(), true),
					getMedia(getValues(inizio, fine, citta.get(0).getNome(), true)));
		
		//Crea una barra che permette all'utente di visualizzare lo stato di avanzamento
		BarraProgresso m = new BarraProgresso(0,citta.size());  
	    m.setVisible(true);  
	    m.setTitle("Stato di avanzamento");  //Titolo della barra
		
		//Ciclo for che permette di analizzare tutte le citta presenti nell'arrayList
		for (int i = 1; i < citta.size(); i++) {
		    
			//Aggiorna lo stato della barra e imposta il nuovo valore
			 m.paint(m.getGraphics()); 
			 m.jb.setValue(i);
			/*
			   L'istanziamento delle seguenti variabili è volto alla memorizzazione
			   dei valori permettendo così di non dover svolgere gli stessi calcoli due volte 
			   con il conseguente risparmio notevole di tempo in fase di esecuzione			 
			 */
			
			
			//getVarU memorizza la varianza dell'umidità per una determinata citta
			double getVarU= getVarianza(getValues(inizio, fine, citta.get(i).getNome(), false),
					getMedia(getValues(inizio, fine, citta.get(i).getNome(), false)));
			//getVarP memorizza la varianza della pressione per una determinata citta
			double getVarP=getVarianza(getValues(inizio, fine, citta.get(i).getNome(), true),
					getMedia(getValues(inizio, fine, citta.get(i).getNome(), true)));
			//Hum memorizza l'umidità della città momentanemante processata
			double Hum = citta.get(i).getUmidita();
			//Pres calcola la pressione su singolo valore
			double Pres = citta.get(i).getPressione();
			
			//Questo if permette di selezionare solo le città nel range di tempo personalizzato
			if( citta.get(i).getData().after(inizio) && citta.get(i).getData().before(fine)) {
				//Permette di memorizzare il valore massimo di umidità e l'indice della città che lo contiene
				if ( Hum < min_val[0]) {
					min_val[0] = Hum;
					min_index[0] = i;
				}
				
				//Permette di memorizzare il valore massimo di pressione e l'indice della citta che lo contiene
				if (Pres < min_val[1]) {
					min_val[1] = Pres;
					min_index[1] = i;
				}
				
				//Permette di memorizzare il valore massimo di varianza di Umidita e l'indice della città su cui è stato calcolato
				if (getVarU < min_val[2]) {
					min_val[2] = getVarU;
					min_index[2] = i;
				}
				
				//Permette di memorizzare il valore massimo di varianza di pressione e l'indice della città su cui è stato calcolato
				if (getVarP > min_val[3]) {
					System.out.println(getVarP);
					min_val[3] = getVarP;
					min_index[3] = i;
				}
				
				
			}
		
	    }
		m.dispose();
		JSONObject JsonReturn = new JSONObject();
		if(min_val[0] == 0 && min_val[1] == 0 && min_val[2] == 0 && min_val[3]==0) {
			JsonReturn.put("Nessun valore trovato nel range di tempo specficato","");
			return JsonReturn;
		}
		JSONObject Min_Umidity = new JSONObject();
		JSONObject Min_Pression = new JSONObject();
		JSONObject Min_Var_Um = new JSONObject();
		JSONObject Min_Var_Pr = new JSONObject();
		Min_Umidity.put("Nome", citta.get(min_index[0]).getNome());
		Min_Umidity.put("Valore", new DecimalFormat("#.##").format(min_val[0])+"%");
		Min_Pression.put("Nome",citta.get(min_index [1]).getNome());
		Min_Pression.put("Valore", new DecimalFormat("#.##").format(min_val[1])+" hPa");
		Min_Var_Um.put("Nome", citta.get(min_index [2]).getNome());
		Min_Var_Um.put("Valore", new DecimalFormat("#.##").format(min_val[2]));
		Min_Var_Pr.put("Nome", citta.get(min_index [3]).getNome());
		Min_Var_Pr.put("Valore", new DecimalFormat("#.##").format(min_val[3]));
		JsonReturn.put("Umidità Minima", Min_Umidity);
		JsonReturn.put("Pressione Minia", Min_Pression);
		JsonReturn.put("Varianza di Umidità Minima", Min_Var_Um);
		JsonReturn.put("Varianza di Pressione Minima", Min_Var_Pr);
		return JsonReturn;
	}
//Metodo utilizzato nella classe Main
	public String printMaxValues(Date inizio, Date fine) {
		Convertitore conv = new Convertitore();
		ArrayList<Citta> citta = conv.JsonToCitta();
		
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
		min[2] = getVarianza(getValues(inizio, fine, citta.get(0).getNome(), false),
				getMedia(getValues(inizio, fine, citta.get(0).getNome(), false)));
		min[3] = getVarianza(getValues(inizio, fine, citta.get(0).getNome(), true),
				getMedia(getValues(inizio, fine, citta.get(0).getNome(), true)));
		for (int i = 0; i < citta.size(); i++) {
			// Max
			//getVarF prende la varianza dell'umidità
			double getVarF= getVarianza(getValues(inizio, fine, citta.get(i).getNome(), false),
					getMedia(getValues(inizio, fine, citta.get(i).getNome(), false)));
			//getVarT prende la varianza della pressione
			double getVarT=getVarianza(getValues(inizio, fine, citta.get(i).getNome(), true),
					getMedia(getValues(inizio, fine, citta.get(i).getNome(), true)));
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
