package com.univpm.oop.statistiche;
import java.util.ArrayList;

import com.univpm.oop.model.Citta;

public interface Filtro {

	public ArrayList<Citta> filtra(ArrayList<Citta> c, String filtro);
}
