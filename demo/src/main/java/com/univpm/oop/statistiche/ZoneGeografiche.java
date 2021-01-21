package com.univpm.oop.statistiche;

import java.util.ArrayList;

import com.univpm.oop.model.Citta;
/**
 * La classe di occupa del filtraggio delle città in base alla loro posizione geografica (Nord, Centro o Sud)
 * @author Nicolò
 * @author Federico
 */
public class ZoneGeografiche implements Filtro {
	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Citta> filtra(ArrayList<Citta> c, String filtro) {
		
		ArrayList<Citta> cittaGeo = new ArrayList<Citta>();
		for (int i = 0; i < c.size(); i++) {
			if(c.get(i).getPosizione().equals(filtro))
				cittaGeo.add(c.get(i));
		}		
		return cittaGeo;
	}
}
