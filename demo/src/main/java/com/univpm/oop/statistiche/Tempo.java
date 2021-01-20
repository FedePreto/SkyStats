package com.univpm.oop.statistiche;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;

import com.univpm.oop.log.Log;
import com.univpm.oop.model.Citta;

/**
 * La classe si occupa della gestione del filtraggio del DataBase in base ad un
 * periodo temporale che può essere: Giornaliero,Settimanale,Mensile,Annuale oppure
 * Customizzato, cioè un perido selezionabile liberamente dall'utente. La classe estende 
 * la classe madre astratta {@link Filtro}
 * 
 * 
 * @author Federico
 * @author Nicolò
 *
 */

public class Tempo implements Filtro{

	public ArrayList<Citta> filtra(ArrayList<Citta> c,String filtro){
		ArrayList<Citta> citta_tempo = new ArrayList<Citta>();
		Date[] date = new Date[2];
		date = getDateFromString(filtro);
		System.out.println(date[0] + " " + date[1]);
		System.out.println(c.get(0).getNome());
		System.out.println(c.get(c.size()-1));
		for (int i = 0; i < c.size(); i++) {
			if(c.get(i).getData().before(date[1]) && c.get(i).getData().after(date[0]))
				citta_tempo.add(c.get(i));
		}
		citta_tempo.get(0).setNome(filtro);
		return citta_tempo;
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
				l = LocalDate.now().minusDays(31);
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
					Log.report("SEPARATORE DATE NON TROVATO",e.getMessage());
				} catch (ParseException e) {
					Log.report("FORMATTAZIONE DATE NON CORRETTO, INSERIRE NELLA SEGUENTE MANIERA [dd/MM/yy]",e.getMessage());
				}
				
			}
					
			Date[] date = {inizio, fine};
			return date;
		}
}
