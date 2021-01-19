package com.example.demo.statistiche;
import com.example.demo.model.Citta;
import java.util.ArrayList;

public interface Filtro {

	public ArrayList<Citta> filtra(ArrayList<Citta> c, String filtro);
}
