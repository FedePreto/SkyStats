package com.univpm.oop.filtri;
import java.util.ArrayList;

import com.univpm.oop.model.Citta;
/**
 * Interfaccia che generalizza tutti i filtri
 * @author Federico
 * @author Nicolò
 */
public interface Filtro {
	/**
	 * Filtro da applicare ai dati
	 * @param c {@link ArrayList} contenente le città da filtrare
	 * @param filtro {@link String} contenente il filtro da applicare
	 * @return {@link ArrayList} contenente le città da filtrare
	 */
	public ArrayList<Citta> filtra(ArrayList<Citta> c, String filtro);
}
