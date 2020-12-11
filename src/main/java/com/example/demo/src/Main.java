package com.example.demo.src;
import com.example.demo.*;
import com.example.demo.controller.CercaMeteo;
import com.example.demo.controller.Ricerca;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.util.StringBuilderFormattable;


public class Main {
   // public static String nomeFile= "D:\\Documenti\\Programmazione\\Progetto\\src\\prova.json";
    public static void main(String[] args){
        ArrayList<Citta> c = new ArrayList<Citta>(); 
        c.add(new Citta(123,"fermo","bel tempo",0.3,12.0,30,new Date()));
      /*  c.add(new Citta(333,"Ancona","cattivo tempo",1.3f,2f,new Date(2020,12,3)));*/
        Convertitore conv = new Convertitore();
        //conv.salva(c,nomeFile);

        //c = conv.JsonToCitta(nomeFile);
        int scelta;
        Scanner in = new Scanner(System.in);
        System.out.println(c.get(0).getNome());
        
     do {
    	     
        	System.out.println("\n=======================\n"+
        	                   "         Menu          \n"+
        			           "=======================\n"+
        	                   "1) Meteo Città\n"+
        			           "2) Statistiche\n"+
        	                   "3) Crea storico di oggi\n"+
        	                   "0) Esci\n");
        	
        	System.out.print("\nFai la tua scelta: ");
        	scelta = in.nextInt();
 
        	switch(scelta) {
        	
        	case 1:
        		String url;
        		System.out.print("\nInserisci il nome o l'ID della città da cercare: ");   
        		String city = in.next();
        		try {
        			int  ID = Integer.parseInt(city);
        			url = "http://api.openweathermap.org/data/2.5/weather?id=" + ID + "&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
        					
        		} catch (NumberFormatException e) {
        			
        			url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + ",IT&appid=907bf98c6e55b2f5321b46b5edb794de&units=metric&lang=it";
        			
        		}
        		conv.stampaCitta(conv.getClassFromCall(CercaMeteo.getMeteo(url),true));
        		break;
        		
        	case 2:
        		Date inizio = null;
        		Date fine = null;
        		//String start;
        		//String end;
        		int Id;
        		System.out.print("\nInserisci l'ID della città da cercare: ");
        		Id = in.nextInt();
        		System.out.println("Inserisci la data inizio [gg/mm/yy HH:mm]: ");
        		inizio = getData();
        		System.out.println("Inserisci la data di fine [gg/mm/yy HH:mm]: ");
        		fine = getData();
        	   /* System.out.println("\nInserisci la data di inizio e fine con la seguente formattazione [gg/mm/yyyy]: ");
        	    System.out.print("\nInizio: ");
        	    start = in.next();
        	    System.out.print("\nFine: ");
        	    end = in.next();
        	       try{
        	           DateFormat formatoData = DateFormat.getDateInstance(DateFormat.FULL, Locale.ITALY);    
        	           formatoData.setLenient(false);           
        	           inizio = formatoData.parse(start);
        	           fine = formatoData.parse(end);
        	       } catch (ParseException e) {
        	           System.out.println("Formato data non valido.");
        	       }*/
        	       
        	       System.out.println("La Media della pressione è: " + Statistiche.calcolaMediaPressione(inizio, fine, Id));
        	       System.out.println("La Varianza dell'umidità è: " + Statistiche.calcolaVarianzaUmidità(inizio, fine, Id));
        	       System.out.println("La Media dell'umidità è: " + Statistiche.calcolaMediaUmidita(inizio, fine, Id));
        	       System.out.println("La Varianza della pressione è: " + Statistiche.calcolaVarianzaPressione(inizio, fine, Id));
        	       break;
        	       
        	case 3:
        		
        		Ricerca r = new Ricerca();
        	    r.salvataggioAutomatico();
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
}
