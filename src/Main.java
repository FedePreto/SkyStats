import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static String nomeFile= "D:\\Documenti\\Programmazione\\Progetto\\src\\prova.json";
    public static void main(String[] args){
        ArrayList<Citta> c = new ArrayList<Citta>();
        /*c.add(new Citta(123,"fermo","bel tempo",0.3f,12f,new Date(2020,12,1)));
        c.add(new Citta(333,"Ancona","cattivo tempo",1.3f,2f,new Date(2020,12,3)));*/
        Convertitore conv = new Convertitore();
        //conv.salva(c,nomeFile);

        //c = conv.JsonToCitta(nomeFile);

        conv.stampaCitta(conv.getClassFromCall("{\"coord\":{\"lon\":13.72,\"lat\":43.16},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cielo sereno\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":8.54,\"feels_like\":7.14,\"temp_min\":6.67,\"temp_max\":10.56,\"pressure\":1009,\"humidity\":88},\"visibility\":10000,\"wind\":{\"speed\":0.89,\"deg\":296,\"gust\":1.79},\"clouds\":{\"all\":0},\"dt\":1607593456,\"sys\":{\"type\":3,\"id\":2001734,\"country\":\"IT\",\"sunrise\":1607581584,\"sunset\":1607614198},\"timezone\":3600,\"id\":3177099,\"name\":\"Fermo\",\"cod\":200}",true));
    }
}
