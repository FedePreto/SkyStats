package com.univpm.oop.statistiche;
import java.util.ArrayList;

import com.univpm.oop.model.Citta;
/**
 * Interfaccia che generalizza tutti i filtri
 * @author Nicolò
 * @author Federico
 *
 */
public interface Filtro {
	/**
	 * Filtro da applicare ai dati
	 * @param c Dati da filtrare
	 * @param filtro Filtro da applicare
	 * @return Dati filtrati
	 */
	public ArrayList<Citta> filtra(ArrayList<Citta> c, String filtro);
}
