package com.example.demo.statistiche;
import com.example.demo.controller.Ricerca;
import com.example.demo.src.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	public double getVarianza(Double[] val) {
		double sommaScarti = 0;
		double media = getMedia(val);
		for(int i=0; i<val.length; i++)
			sommaScarti += (val[i]-media)*(val[i]-media);
		return sommaScarti/val.length;
	}
	
	public void maxVal(Date inizio, Date fine) {
		Convertitore conv = new Convertitore();
		Ricerca r = new Ricerca();
	    ArrayList<Citta> c = conv.JsonToCitta();
	    ArrayList<String> nomiCitta = r.aggiornaArray();
	    int indiceCittaU = 0;
	    int indiceCittaP = 0;
	    int indiceCittaVU = 0;
	    int indiceCittaVP = 0;
	    
	    double valMaxPressione = 0;
	    double valMaxUmidita = 0;
	    double valMaxVarUm = 0;
	    double valMaxVarPre =0;
	    for (int i=0; i<c.size(); i++) {
	    	if(c.get(i).getUmidita()>=valMaxUmidita && c.get(i).getData().before(fine) && c.get(i).getData().after(inizio)) {
	    		indiceCittaU = i;
	    		valMaxUmidita = c.get(i).getUmidita();
	    	}
	    	if (c.get(i).getPressione()>=valMaxPressione && c.get(i).getData().before(fine) && c.get(i).getData().after(inizio)) {
	    		indiceCittaP = i;
	    		valMaxPressione = c.get(i).getPressione();
	    	}
	    }
	    
	    for (int j=0; j<nomiCitta.size(); j++) {
	    	for (int i=0; i<c.size(); i++) {
	    		if(nomiCitta.get(j).equals(c.get(i).getNome()) && getVarianza(getValues(inizio,fine,c.get(i).getNome(),true))>=valMaxVarPre){
	    		indiceCittaVP = i;
	    		valMaxVarPre = getVarianza(getValues(inizio,fine,c.get(i).getNome(),true));
	    	}
	    	if(nomiCitta.get(j).equals(c.get(i).getNome()) && getVarianza(getValues(inizio,fine,c.get(i).getNome(),false))>=valMaxVarUm){
	    		indiceCittaVU = i;
	    		valMaxVarUm = getVarianza(getValues(inizio,fine,c.get(i).getNome(),false));
	    	}
	      }
	    }
	    
	 	System.out.println("La città con umidita maggiore è: " + c.get(indiceCittaU).getNome() + " con " + valMaxUmidita + "%");
    	System.out.println("La città con pressione maggiore è: " + c.get(indiceCittaP).getNome() + " con " + valMaxPressione + " hPa");
    	System.out.println("La città con varianza di umidità maggiore è: " + c.get(indiceCittaVU).getNome() + " con " + valMaxVarUm);
    	System.out.println("La citta con varianza di pressione maggiore è: " + c.get(indiceCittaVP).getNome() + " con " + valMaxVarPre);
	    	
	}
	 
	public void stampaArray(ArrayList<Double[]> a ) {
		for(int i = 0;i<a.size();i++) {
			System.out.println(a.get(i)[0]+" "+a.get(i)[1]);
		}
	}
}
