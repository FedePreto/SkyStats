package com.univpm.oop.statistiche;

import java.util.ArrayList;

import com.univpm.oop.model.Citta;
/**
 * Filtro che filtra le città in base alla loro posizione geografica (Nord, Centro o Sud)
 * @author Nicolò
 *
 */
public class ZoneGeografiche implements Filtro {
	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Citta> filtra(ArrayList<Citta> c, String filtro) {
		
		ArrayList<Citta> citta_geo = new ArrayList<Citta>();
		for (int i = 0; i < c.size(); i++) {
			if(c.get(i).getPosizione().equals(filtro))
				citta_geo.add(c.get(i));
		}
		
		return citta_geo;
	}
}
