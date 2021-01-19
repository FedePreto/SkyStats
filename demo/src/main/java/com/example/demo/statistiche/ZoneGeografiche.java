package com.example.demo.statistiche;

import java.util.ArrayList;

import com.example.demo.model.Citta;

public class ZoneGeografiche implements Filtro {
	
	public ArrayList<Citta> filtra(ArrayList<Citta> c, String filtro) {
		
		ArrayList<Citta> citta_geo = new ArrayList<Citta>();
		for (int i = 0; i < c.size(); i++) {
			if(c.get(i).getPosizione().equals(filtro))
				citta_geo.add(c.get(i));
		}
		citta_geo.get(0).setNome(filtro);
		return citta_geo;
	}
}
