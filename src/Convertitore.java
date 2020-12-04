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
        JSONObject jo = new JSONObject();
       /* for(int i = 0; i< c.size();i++){

            jo.put("id",c.get(i).getId());
            jo.put("nome",c.get(i).getNome());
            jo.put("meteo",c.get(i).getMeteo());
            jo.put("umidita",c.get(i).getUmidita());
            jo.put("pressione",c.get(i).getPressione());
            jo.put("data",c.get(i).getData());
        }*/

        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(nome));
            for(int i = 0; i<c.size();i++){
                buf.write(gson.toJson(c.get(i)));
                buf.write("\n");

            }

            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
