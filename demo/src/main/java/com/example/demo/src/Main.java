package com.example.demo.src;

import com.example.demo.*;
import com.example.demo.aggiornamento.Aggiornamento;
import com.example.demo.controller.CercaMeteo;
import com.example.demo.controller.Ricerca;
import com.example.demo.statistiche.Stat;
import com.example.demo.statistiche.Statistiche;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.util.StringBuilderFormattable;

public class Main {

	public static void main(String[] args) {

		Convertitore conv = new Convertitore();
		int scelta;
		Scanner in = new Scanner(System.in);
		Ricerca r = new Ricerca();
		Timer timer = new Timer();
		Aggiornamento agg = new Aggiornamento();
		timer.schedule(agg, 0, 5 * 3600000);

		do {

			System.out.println("\n=======================\n" +
			                   "         Menu            \n" + 
					           "=========================\n" +
					           "1) Meteo Città\n" +
					           "2) Statistiche di una città\n" +
					           "3) Crea storico di oggi\n"+
					           "4) Aggiungi ai preferiti\n" + 
					           "5) Rimuovi dai preferiti\n" + 
					           "6) Stampa favoriti\n"+
					           "7) Stat per zone geografice\n" + 
					           "8) Stampa le citta con i valori massimi e minimi nel DB\n" +
					           "0) Esci\n");

			System.out.print("\nFai la tua scelta: ");
			scelta = in.nextInt();

			switch (scelta) {

			case 1:
				cercaMeteo();
				break;

			case 2:
				System.out.println("Inserisci il nome della citta : ");
				String citta = in.next();
				Date[] date = menuDate();
				stampaStat(date[0], date[1], citta);
				break;
			case 3:
				r.salvataggio();
				break;
			case 4:
				System.out.println("Inserisci il Nome della citta da aggiungere ai preferiti");
				r.addFavoriti(in.next());
				r.stampaPreferiti();
				break;
			case 5:
				System.out.println("Inserisci la citta da rimuovere dai preferiti :");
				r.removeFavoriti(in.next());
			case 6:
				r.stampaPreferiti();
				break;
			case 8:
				Date[] d = menuDate();
				Stat s = new Stat();
				s.printMaxValues(d[0], d[1]);
			case 0:
				System.out.println("Alla prossima");
				break;
			}

		} while (scelta != 0);

	}

	public static Date getData() {
		String s;
		Date d = null;
		do {
			// si procura la data sotto forma di una stringa nel formato SHORT

			Scanner in = new Scanner(System.in);
			s = in.nextLine();
			try {
				// converte la stringa della data in un oggetto di classe Date
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm");
				d = formato.parse(s);
				// crea un oggetto 'formattatore' fissando un pattern
				formato = new SimpleDateFormat("'alle ore' HH:mm 'del' dd/MM/yyyy");
				// visualizza la data formattata secondo il pattern fissato
				System.out.println("OUTPUT: " + formato.format(d));
				break; // esce dal ciclo
			} catch (ParseException e) {
				System.out.println("Formato data non valido.");
			}
		} while (true);
		return d;
	}

	public static void cercaMeteo() {
		Scanner in = new Scanner(System.in);
		Convertitore conv = new Convertitore();
		String url;
		System.out.print("\nInserisci il nome o l'ID della città da cercare: ");
		String city = in.next();
		
		Citta c = conv.findInJson(city);
	}
	

	public static void stampaStat(Date in, Date fin, String citta) {
		Stat stat = new Stat();
		double mediaP = stat.getMedia(stat.getValues(in, fin, citta, true));
		double mediaU = stat.getMedia(stat.getValues(in, fin, citta, false));
		System.out.println("La Media della pressione è: " + new DecimalFormat("#.##").format(mediaP));
		System.out.println("La Varianza dell'pressione è: "
				+ new DecimalFormat("#.##").format(stat.getVarianza(stat.getValues(in, fin, citta, true), mediaP)));
		System.out.println("La Media dell'umidità è: " + new DecimalFormat("#.##").format(mediaU));
		System.out.println("La Varianza della umidita è: "
				+ new DecimalFormat("#.##").format(stat.getVarianza(stat.getValues(in, fin, citta, false), mediaU)));
	}

	public static Date[] menuDate() {
		Scanner in = new Scanner(System.in);
		// System.out.println("Inserisci la citta da prendere in considerazione : ");
		// String citta = in.next();
		Date inizio = new Date();
		Date fine = new Date();
		LocalDate l;

		System.out.println("Seleziona il range di tempo: \n" + 
		                   "1)Giornaliero\n" + 
				           "2)Settimanale\n" + 
		                   "3)Mensile\n"+
				           "4)Annuale\n"+
		                   "5)Custom\n");
		switch (in.nextInt()) {
		case 1:
			fine = new Date();
			l = LocalDate.now().minusDays(1);
			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
			// stampaStat(inizio,fine,citta);
			break;
		case 2:
			fine = new Date();
			l = LocalDate.now().minusDays(7);
			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
			// stampaStat(inizio,fine,citta);
			break;
		case 3:
			fine = new Date();
			l = LocalDate.now().minusDays(30);
			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
			// stampaStat(inizio,fine,citta);
			break;
		case 4:
			fine = new Date();
			l = LocalDate.now().minusDays(365);
			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
			// stampaStat(inizio,fine,citta);
			break;
		case 5:
			System.out.println("Inserisci la data di inizio [gg/mm/yy HH:mm] : ");
			inizio = getData();
			System.out.println("Inserisci la data di fine [gg/mm/yy HH:mm] : ");
			fine = getData();
			// stampaStat(inizio,fine,citta);
		}
		Date[] date = { inizio, fine };
		return date;
	}

	// Da finire
	public void zoneGeo() {
		Scanner in = new Scanner(System.in);
		int scelta;

		System.out.println("1)Nord\n" + "2)Centro\n" + "3)Sud");
		scelta = in.nextInt();
		Date[] date = menuDate();
		switch (scelta) {
		case 1:
		}

	}
}
