package com.example.demo.statistiche;
import java.util.ArrayList;

import com.example.demo.model.Citta;
public class NomeId implements Filtro{
	
	public ArrayList<Citta> filtra(ArrayList<Citta> c, String filtro){
		
		ArrayList<Citta> tmp = new ArrayList<Citta>();
		for (int i = 0; i < c.size(); i++) {
			try {
				if(c.get(i).getId()==Integer.parseInt(filtro) ) {
					tmp.add(c.get(i));
				}
			}catch(NumberFormatException e) {
				if(c.get(i).getNome().equals(filtro)) {
					tmp.add(c.get(i));
				}
			}
		}
		return tmp;
	}
}
