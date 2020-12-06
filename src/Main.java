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

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Citta.class,new CittaAdapter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String json = "";
        try {
            BufferedReader buf = new BufferedReader(new FileReader("D:\\Documenti\\Programmazione\\Progetto\\src\\prova2.json"));
            json = buf.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        c.add(gson.fromJson(json,Citta.class));

        for(int i = 0; i<c.size();i++){
            System.out.println(c.get(i).toString());
        }
    }
}
