package com.example.demo.statistiche;

import java.util.ArrayList;

import com.example.demo.model.Citta;

public class ZoneGeografiche extends Filtro {
	
	public ZoneGeografiche(String filtro) {
		super(filtro);
	}
	public ArrayList<Citta> filtra(ArrayList<Citta> c) {
		String filtro = super.filtro;
		ArrayList<Citta> tmp = new ArrayList<Citta>();
		for (int i = 0; i < c.size(); i++) {
			if(c.get(i).getPosizione().equals(filtro))
				tmp.add(c.get(i));
		}
		return tmp;
	}
}
