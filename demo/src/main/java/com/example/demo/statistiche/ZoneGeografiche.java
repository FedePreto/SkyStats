package com.example.demo.statistiche;

import java.util.ArrayList;

import com.example.demo.model.Citta;

public class ZoneGeografiche implements Filtro {
	
	public ArrayList<Citta> filtra(ArrayList<Citta> c, String filtro) {
		
		ArrayList<Citta> tmp = new ArrayList<Citta>();
		for (int i = 0; i < c.size(); i++) {
			if(c.get(i).getPosizione().equals(filtro))
				tmp.add(c.get(i));
		}
		return tmp;
	}
}
