package com.univpm.oop.statistiche;

import java.util.ArrayList;

import com.univpm.oop.model.Citta;

public class ZoneGeografiche implements Filtro {
	
	public ArrayList<Citta> filtra(ArrayList<Citta> c, String filtro) {
		
		ArrayList<Citta> citta_geo = new ArrayList<Citta>();
		for (int i = 0; i < c.size(); i++) {
			if(c.get(i).getPosizione().equals(filtro))
				citta_geo.add(c.get(i));
		}
		
		return citta_geo;
	}
}
