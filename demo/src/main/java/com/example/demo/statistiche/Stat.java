package com.example.demo.statistiche;

import com.example.demo.model.Citta;
import com.example.demo.services.Favoriti;
import com.example.demo.src.*;
import java.util.Date;
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
			Double[] d = new Double[val.size()];
			return val.toArray(d);
		}
	}

	public double getMedia(Double[] val) {
		double media = 0;
		for (int i = 0; i < val.length; i++) {
			media += val[i];
		}
		return media / (double) val.length;
	}

	/*
	 * public double getVarianza(Double[] val, double media) { Convertitore conv =
	 * new Convertitore(); //Da fare con le collection ma siccome non le so ancora
	 * usare bene lo faccio con gli arraylist ArrayList<Double[]> temp= new
	 * ArrayList<Double[]>();
	 * 
	 * for(int i = 0; i<val.length;i++){//cicla tutti i risulati boolean trovato =
	 * false; for(int j = 0; j<temp.size();j++){ if(temp.get(j)[0]==val[i]) {//vede
	 * se quel dato si trova in temp temp.get(j)[1]++;//se c'è incrementa il
	 * contatore trovato = true; } } if(!trovato){//se non c'è lo aggiunge Double[]
	 * d = new Double[2]; d[0] = val[i]; d[1] = (double)1; temp.add(d); } }
	 * 
	 * double varianza=0; //stampaArray(temp); //Calcolo varianza for(int i = 0;
	 * i<temp.size();i++){ varianza +=
	 * Math.pow(temp.get(i)[0]-media,2)*temp.get(i)[1]; } return
	 * Math.sqrt(varianza/temp.size()); }
	 */

	public double getVarianza(Double[] val, double media) {
		double var = 0;
		for (int i = 0; i < val.length; i++) {
			var += Math.pow((val[i] - media), 2);
		}
		return var / val.length;
	}

	public void stampaArray(ArrayList<Double[]> a) {
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i)[0] + " " + a.get(i)[1]);
		}
	}
	/*
	 * public void maxVal(Date inizio, Date fine) { Convertitore conv = new
	 * Convertitore(); Ricerca r = new Ricerca(); ArrayList<Citta> c =
	 * conv.JsonToCitta(); ArrayList<String> nomiCitta = r.getFavoriti(); int
	 * indiceCittaU = 0; int indiceCittaP = 0; int indiceCittaVU = 0; int
	 * indiceCittaVP = 0;
	 * 
	 * double valMaxPressione = 0; double valMaxUmidita = 0; double valMaxVarUm = 0;
	 * double valMaxVarPre =0; for (int i=0; i<c.size(); i++) {
	 * if(c.get(i).getUmidita()>=valMaxUmidita && c.get(i).getData().before(fine) &&
	 * c.get(i).getData().after(inizio)) { indiceCittaU = i; valMaxUmidita =
	 * c.get(i).getUmidita(); } if (c.get(i).getPressione()>=valMaxPressione &&
	 * c.get(i).getData().before(fine) && c.get(i).getData().after(inizio)) {
	 * indiceCittaP = i; valMaxPressione = c.get(i).getPressione(); } }
	 * 
	 * for (int j=0; j<nomiCitta.size(); j++) { for (int i=0; i<c.size(); i++) {
	 * if(nomiCitta.get(j).equals(c.get(i).getNome()) &&
	 * getVarianza(getValues(inizio,fine,c.get(i).getNome(),true))>=valMaxVarPre){
	 * indiceCittaVP = i; valMaxVarPre =
	 * getVarianza(getValues(inizio,fine,c.get(i).getNome(),true)); }
	 * if(nomiCitta.get(j).equals(c.get(i).getNome()) &&
	 * getVarianza(getValues(inizio,fine,c.get(i).getNome(),false))>=valMaxVarUm){
	 * indiceCittaVU = i; valMaxVarUm =
	 * getVarianza(getValues(inizio,fine,c.get(i).getNome(),false)); } } }
	 * 
	 * System.out.println("La città con umidita maggiore è: " +
	 * c.get(indiceCittaU).getNome() + " con " + valMaxUmidita + "%");
	 * System.out.println("La città con pressione maggiore è: " +
	 * c.get(indiceCittaP).getNome() + " con " + valMaxPressione + " hPa");
	 * System.out.println("La città con varianza di umidità maggiore è: " +
	 * c.get(indiceCittaVU).getNome() + " con " + valMaxVarUm);
	 * System.out.println("La citta con varianza di pressione maggiore è: " +
	 * c.get(indiceCittaVP).getNome() + " con " + valMaxVarPre);
	 * 
	 * }
	 */

	public String printMaxValues(Date inizio, Date fine) {
		Convertitore conv = new Convertitore();
		ArrayList<Citta> citta = conv.JsonToCitta();

		double min[] = new double[4];
		double max[] = new double[4];

		int maxU = 0;
		int maxP = 0;
		int maxVU = 0;
		int maxVP = 0;

		int minU = 0;
		int minP = 0;
		int minVU = 0;
		int minVP = 0;

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
				if ( Hum > max[0]) {
					max[0] = Hum;
					maxU = i;
				}
				
				if (Pres > max[1]) {
					max[1] = Pres;
					maxP = i;
				}
				
				if (Hum < min[0]) {
				min[0] = Hum;
				minU = i;
				}
				
				if ( Pres < min[1]) {
				min[1] = Pres;
				minP = i;
				}
			}
			
			if (getVarF > max[2]) {
				max[2] = getVarF;
				maxVU = i;
			}
			
			if (getVarF < min[2]) {
				min[2] = getVarF;
				minVU = i;
			}
			
			if (getVarT > max[3]) {
				max[3] = getVarT;
				maxVP = i;
			}
			
			if (getVarT < min[3]) {
				min[3] = getVarT;
				minVP = i;
			}
			
		}
		/*
		System.out.println("La città con umidita maggiore è: " + citta.get(maxU).getNome() + " con " + max[0] + "%");
		System.out
				.println("La città con pressione maggiore è: " + citta.get(maxP).getNome() + " con " + max[1] + " hPa");
		System.out.println(
				"La città con varianza di umidità maggiore è: " + citta.get(maxVU).getNome() + " con " + max[2]);
		System.out.println(
				"La citta con varianza di pressione maggiore è: " + citta.get(maxVP).getNome() + " con " + max[3]);
		System.out.println();
		System.out.println("La città con umidita minore è: " + citta.get(minU).getNome() + " con " + min[0] + "%");
		System.out.println("La città con pressione minore è: " + citta.get(minP).getNome() + " con " + min[1] + " hPa");
		System.out
				.println("La città con varianza di umidità minore è: " + citta.get(minVU).getNome() + " con " + min[2]);
		System.out.println(
				"La citta con varianza di pressione minore è: " + citta.get(minVP).getNome() + " con " + min[3]);
		*/
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
		Double[] d = new Double[temp.size()];
		return temp.toArray(d);

	}
}
