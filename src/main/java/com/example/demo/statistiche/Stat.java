package com.example.demo.statistiche;
import com.example.demo.src.*;
import java.util.Date;
import java.util.ArrayList;

public class Stat {
	public Double[] getValues(Date inizio,Date fine,String citta,boolean isPressione) {
		 Convertitore conv = new Convertitore();
	        ArrayList<Citta> c = conv.JsonToCitta();
	        ArrayList<Double> val = new ArrayList<Double>();
	        try {
	        	int idCitta = Integer.parseInt(citta);
	        	for(int i =0; i<c.size();i++){
	        		if(c.get(i).getId()==idCitta && c.get(i).getData().before(fine) && c.get(i).getData().after(inizio)){
	        			if(isPressione) {
	        				val.add(c.get(i).getPressione());	            		
	        			}else {
	        				val.add(c.get(i).getUmidita());
	        			}
	        		}
	        	}
	        	Double[] d = new Double[val.size()];
	        	return val.toArray(d);
	        }catch(NumberFormatException e) {
	        	for(int i =0; i<c.size();i++){
	        		if(c.get(i).getNome().equals(citta) && c.get(i).getData().before(fine) && c.get(i).getData().after(inizio)){
	        			if(isPressione) {
	        				val.add(c.get(i).getPressione());	            		
	        			}else {
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
		for(int i = 0; i<val.length;i++) {
			media += val[i];
		}
		return media/(double)val.length;
	}
	
	public double getVarianza(Double[] val, double media) {
		Convertitore conv = new Convertitore();
        //Da fare con le collection ma siccome non le so ancora usare bene lo faccio con gli arraylist
        ArrayList<Double[]> temp= new ArrayList<Double[]>();
        
        for(int i = 0; i<val.length;i++){//cicla tutti i risulati
            boolean trovato = false;
            for(int j = 0; j<temp.size();j++){
            	if(temp.get(j)[0]==val[i]) {//vede se quel dato si trova in temp
            		temp.get(j)[1]++;//se c'è incrementa il contatore
                    trovato = true;
                }
            }
            if(!trovato){//se non c'è lo aggiunge
            	Double[] d = new Double[2];
                d[0] = val[i];
                d[1] = (double)1;
                temp.add(d);
            }
        }
        
        double varianza=0;
        //stampaArray(temp);
        //Calcolo varianza
        for(int i = 0; i<temp.size();i++){
            varianza += Math.pow(temp.get(i)[0]-media,2)*temp.get(i)[1];
        }
        return Math.sqrt(varianza/temp.size());
	}
	public void stampaArray(ArrayList<Double[]> a ) {
		for(int i = 0;i<a.size();i++) {
			System.out.println(a.get(i)[0]+" "+a.get(i)[1]);
		}
	}
}
