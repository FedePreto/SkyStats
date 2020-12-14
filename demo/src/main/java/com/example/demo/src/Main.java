package com.example.demo.src;
import com.example.demo.*;
import com.example.demo.controller.CercaMeteo;
import com.example.demo.controller.Ricerca;
import com.example.demo.statistiche.Stat;
import com.example.demo.statistiche.Statistiche;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Main {
   
    public static void main(String[] args){
    	

        Convertitore conv = new Convertitore();
        int scelta;
        Scanner in = new Scanner(System.in);
        Ricerca r = new Ricerca();
        Stat s = new Stat();
        Citta c = new Citta();
        
     do {
    	     
        	System.out.println("\n=======================\n"+
        	                   "         Menu          \n"+
        			           "=======================\n"+
        	                   "1) Meteo Città\n"+
        			           "2) Statistiche di una specifica città\n"+
        	                   "3) Statistiche generali\n"+
        	                   "4) Crea storico di oggi\n"+
        			           "5) Aggiungi ai preferiti\n"+
        	                   "6) Rimuovi dai preferiti\n"+
        	                   "7) Stampa favoriti\n"+
        	                   "0) Esci\n");
        	
        	System.out.print("\nFai la tua scelta: ");
        	scelta = in.nextInt();
 
        	switch(scelta) {
        	
        	case 1:
        		cercaMeteo();
        		break;
        		
        	case 2:
        		menuStatistica(false);
        	    break;    
        	    
        	case 3:
        		menuStatistica(true);
        	    break;
        	case 4:
        	    r.salvataggioAutomatico();
        	    break;
        	case 5:
        		System.out.println("Inserisci il Nome della citta da aggiungere ai preferiti");
        		r.addFavoriti(in.next());
        		break;
        	case 6:
        		System.out.println("Inserisci la citta da rimuovere dai preferiti :");
        		r.removeFavoriti(in.next());
        	case 7:
        		r.stampaPreferiti();
        		break; 		
        	case 0: 
        		System.out.println("Alla prossima");
        		break;
        	}
        
        	
     }while(scelta!=0);

        
    
    }
    
    public static Date getData() {
    	 String s;
    	 Date d = null;
         do {
             //si procura la data sotto forma di una stringa nel formato SHORT
             
             Scanner in = new Scanner(System.in);
             s = in.nextLine();
             try{
                 //converte la stringa della data in un oggetto di classe Date
                 SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm");
                 d = formato.parse(s);
                 //crea un oggetto 'formattatore' fissando un pattern
                 formato = new SimpleDateFormat("'alle ore' HH:mm 'del' dd/MM/yyyy");
                 //visualizza la data formattata secondo il pattern fissato
                 System.out.println("OUTPUT: " + formato.format(d));
                 break; //esce dal ciclo       
             } catch (ParseException e) {
                 System.out.println("Formato data non valido.");
             }
         } while(true);
         return d;
    }
    
    
    public static void cercaMeteo() {
    	Scanner in = new Scanner(System.in);
    	Convertitore conv = new Convertitore();
    	String url;
		System.out.print("\nInserisci il nome o l'ID della città da cercare: ");   
		String city = in.next();
		Citta c = conv.findInJson(city);
		if(c==null) {
			try {
				int  ID = Integer.parseInt(city);
				url = "http://api.openweathermap.org/data/2.5/weather?id=" + ID + "&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
				
			} catch (NumberFormatException e) {
				
				url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + ",IT&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
				
			}
			System.out.println(conv.getClassFromCall(CercaMeteo.getMeteo(url),true));
		}else {
			System.out.println(c);
		}
    }
    public static void stampaStat(Date in,Date fin,String citta) {
    	Stat stat = new Stat();
	    System.out.println("La Media della pressione è: " + stat.getMedia(stat.getValues(in, fin, citta, true)));
	    System.out.println("La Varianza dell'pressione è: " + stat.getVarianza(stat.getValues(in, fin, citta, true)));
	    System.out.println("La Media dell'umidità è: " + stat.getMedia(stat.getValues(in, fin, citta, false)));
	    System.out.println("La Varianza della umidita è: " + stat.getVarianza(stat.getValues(in, fin, citta, false)));
    	
    
    }
    
    
	public static void menuStatistica(boolean isGeneral) {
		Scanner in = new Scanner(System.in);
		String citta = "";
		if(!isGeneral) {    	
    	System.out.println("Inserisci la citta da prendere in considerazione : ");
    	citta = in.next();
		}
    	Date inizio;
    	Date fine = new Date();
    	LocalDate l;
    	
    		System.out.println("Seleziona il range di tempo: \n"+
    							"1)Giornaliero\n"+
    							"2)Settimanale\n"+
    							"3)Mensile\n"+
    							"4)Annuale\n"+
    							"5)Custom\n");
    		switch(in.nextInt()) {
    		case 1:
    			
    			l =LocalDate.now().minusDays(1);
    			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
    			if(!isGeneral)
    				stampaStat(inizio,fine,citta);
    			else if (isGeneral) {
    				Stat s = new Stat();
    			    s.maxVal(inizio, fine);
    			}
    			break;
    		case 2:
    			
    			l =LocalDate.now().minusDays(7);
    			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
    			if(!isGeneral)
    				stampaStat(inizio,fine,citta);
    			else if (isGeneral) {
    				Stat s = new Stat();
    				s.maxVal(inizio, fine);
    			}
    			break;
    		case 3:
    			
    			l =LocalDate.now().minusDays(30);
    			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
    			if(!isGeneral)
    				stampaStat(inizio,fine,citta);
    			else if (isGeneral) {
    				Stat s = new Stat();
    				s.maxVal(inizio, fine);
    			}
    			break;
    		case 4:
    			
    			l =LocalDate.now().minusDays(365);
    			inizio = Date.from(l.atStartOfDay(ZoneId.systemDefault()).toInstant());
    			if(!isGeneral)
    				stampaStat(inizio,fine,citta);
    			else if (isGeneral) {
    				Stat s = new Stat();
    				s.maxVal(inizio, fine);
    			}
    			break;
    		case 5:
    			System.out.println("Inserisci la data di inizio [gg/mm/yy HH:mm] : ");
    			inizio = getData();
    			System.out.println("Inserisci la data di fine [gg/mm/yy HH:mm] : ");
    			fine = getData();
    			if(!isGeneral)
    				stampaStat(inizio,fine,citta);
    			else if (isGeneral) {
    				Stat s = new Stat();
    				s.maxVal(inizio, fine);
    			}
    		}
    	
    }
	
}