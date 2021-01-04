package com.example.demo.statistiche;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;

import com.example.demo.model.Citta;

public class Tempo extends Filtro{
	public Tempo(String filtro) {
		super(filtro);
	}
	
	public ArrayList<Citta> filtra(ArrayList<Citta> c){
		String filtro = super.filtro;
		ArrayList<Citta> tmp = new ArrayList<Citta>();
		Date[] date = new Date[2];
		date = getDateFromString(filtro);
		for (int i = 0; i < c.size(); i++) {
			if(c.get(i).getData().before(date[1]) && c.get(i).getData().after(date[0]))
				tmp.add(c.get(i));
		}
		return tmp;
	}
	
	public static Date[] getDateFromString(String time) {
		 Date inizio = new Date();
		 LocalDate l = LocalDate.now().minusDays(0);
		 Date fine = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 
			switch (time) {
			
			case "Giornaliero":     			
				l = LocalDate.now().minusDays(1);
				inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
				break;
				
			case "Settimanale":
				l = LocalDate.now().minusDays(7);
				inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
				break;
				
			case "Mensile":
				l = LocalDate.now().minusDays(30);
				inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
				break;
				
			case "Annuale":
				l = LocalDate.now().minusDays(365);
				inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
				break;
				
			default:
				try {
				String[] d = time.split(",");
				DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
				inizio = df.parse(d[0]);
				fine = df.parse(d[1]);
				}catch(PatternSyntaxException e) {
					System.out.println("Errore : Separatore date non trovato!");
				} catch (ParseException e) {
					System.out.println("Errore : Formattazione delle date non corretto [dd/MM/yy]]");
					e.printStackTrace();
				}
				
			}
					
			Date[] date = {inizio, fine};
			return date;
		}
}
