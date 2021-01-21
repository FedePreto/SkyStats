package com.univpm.oop.filtri;
import java.util.ArrayList;

import com.univpm.oop.model.Citta;

/**
 * Filtro che seleziona le città in base al loro nome o ID. La classe estende l'interfaccia {@link Filtro}
 * @author Nicolò
 * @author Federico
 */
public class NomeId implements Filtro{
	/**
	 * {@inheritDoc}
	 */
	public ArrayList<Citta> filtra(ArrayList<Citta> c, String filtro){
		
		ArrayList<Citta> cittaNome = new ArrayList<Citta>();
		for (int i = 0; i < c.size(); i++) {
			try {
				if(c.get(i).getId()==Integer.parseInt(filtro) ) {
					cittaNome.add(c.get(i));
				}
			}catch(NumberFormatException e) {
				if(c.get(i).getNome().equals(filtro)) {
					cittaNome.add(c.get(i));
				}
			}
		}
		return cittaNome;
	}
}
