package com.example.demo.statistiche;
import com.example.demo.model.Citta;
import java.util.ArrayList;

public abstract class Filtro {
	public String filtro;
	public Filtro(String filtro) {
		this.filtro= filtro;
	}
	public abstract ArrayList<Citta> filtra(ArrayList<Citta> c);
}
