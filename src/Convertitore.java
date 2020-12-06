import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import com.google.gson.Gson;

public class Convertitore {

    public ArrayList<Citta> JsonToCitta(String nomeFile){
        ArrayList<Citta> c = new ArrayList<Citta>();
        Stream<String> file;
        int i =0;
        Gson gson = new Gson();
        try {
            BufferedReader buf = new BufferedReader(new FileReader(nomeFile));
            file = buf.lines();
            while(true){
                String prova = buf.readLine();
                if(prova.equals(""))
                    return c;

                c.add(gson.fromJson(prova,Citta.class));
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            //System.out.println(i+" Exception");
            return c;
        }
        return c;
    }



    public void salva(ArrayList<Citta> c,String nome){
        Gson gson = new Gson();
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(nome));
            for(int i = 0; i<c.size();i++){
                //buf.write("{\"Citta\":");
                buf.write(gson.toJson(c.get(i)));
                buf.write("\n");

            }

            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }












}
