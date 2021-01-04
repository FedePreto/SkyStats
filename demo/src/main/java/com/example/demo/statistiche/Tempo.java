package com.example.demo.statistiche;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
	@SuppressWarnings("deprecation")
	public static Date[] getDateFromString(String time) {
		 Date inizio = new Date();
		 Date fine = new Date();
		 LocalDate l;
			switch (time) {
			case "Giornaliero":
				fine = new Date();
				l = LocalDate.now().minusDays(1);
				inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
				// stampaStat(inizio,fine,citta);
				break;
			case "Settimanale":
				fine = new Date();
				l = LocalDate.now().minusDays(7);
				inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
				// stampaStat(inizio,fine,citta);
				break;
			case "Mensile":
				fine = new Date();
				l = LocalDate.now().minusDays(30);
				inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
				// stampaStat(inizio,fine,citta);
				break;
			case "Annuale":
				fine = new Date();
				l = LocalDate.now().minusDays(365);
				inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
				// stampaStat(inizio,fine,citta);
				break;
			default:
				try {
				String[] d = time.split(",");
				inizio = new Date(d[0]);
				fine =new Date(d[1]);
				}catch(PatternSyntaxException e) {
					System.out.println("Errore : Separatore date non trovato!");
				}
				
			}
					
			Date[] date = {inizio, fine};
			return date;
		}
}
