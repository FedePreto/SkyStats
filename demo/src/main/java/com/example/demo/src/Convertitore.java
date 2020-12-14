package com.example.demo.src;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.google.gson.*;




public class Convertitore {
  String nomeFile = "prova.json";

   public Convertitore(String nomeFile){
        this.nomeFile = nomeFile;
   }

    public Convertitore(){
       nomeFile = ".\\src\\main\\java\\com\\example\\demo\\src/"+"prova.json";    	
    }

    public ArrayList<Citta> JsonToCitta(){
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



    public void salva(ArrayList<Citta> c){
        Gson gson = new Gson();
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(nomeFile));
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

    public void salva(Citta c ){
        Gson gson = new Gson();
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(new File(nomeFile),true));

            //buf.write("{\"Citta\":");
            buf.write(gson.toJson(c));
            buf.write("\n");
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Citta getClassFromCall(String s,boolean salva){
        Citta c = new Citta();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonTree = jsonParser.parse(s);

        if(jsonTree.isJsonObject()){
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            //System.out.println(jsonTree.toString());
            //JsonObject main = jsonObject.getAsJsonObject("main");
            //JsonObject weather = jsonObject.getAsJsonObject("weather");


            c.setNome(jsonObject.get("name").getAsString());
            c.setId(jsonObject.get("id").getAsInt());

            JsonArray weather = jsonObject.get("weather").getAsJsonArray();
            JsonObject main = jsonObject.get("main").getAsJsonObject();
            JsonObject coord = jsonObject.get("coord").getAsJsonObject();
            c.setPressione(main.get("pressure").getAsDouble());
            c.setUmidita(main.get("humidity").getAsDouble());
            c.setTemperatura(main.get("temp").getAsDouble());
            c.setMeteo(weather.get(0).getAsJsonObject().get("description").getAsString());
            c.setPosizione(c.getLocation(coord.get("lat").getAsDouble()));
        }
        if(salva)
            salva(c);
        return c;
    }
    public void stampaCitta(Citta c){
       // System.out.println(c.toString());
    	System.out.println(c); //Il metodo toString viene richiamato in automatico quando si fa il SYSO
    }


    public Citta findInJson(String val) {
    	ArrayList<Citta> c ;
    	c = JsonToCitta();
    	Citta citta  = null;
    	Date now = new Date();
    	int maxRecent=0;
    	try {
    		int id = Integer.parseInt(val);
    		for(int i = 0;i<c.size();i++) {
    			//System.out.println(c.get(i).getNome()+"  Data = "+c.get(i).getData().toString()+" difference by now = "+ differenzaDiDate(c.get(i).getData(), now, TimeUnit.MINUTES)+" minuti");
    			if(c.get(i).getId()==id && differenzaDiDate(c.get(i).getData(), now, TimeUnit.MINUTES)<300) {
    				if(differenzaDiDate(c.get(maxRecent).getData(), now, TimeUnit.HOURS)>differenzaDiDate(c.get(i).getData(), now, TimeUnit.HOURS)) {
    					citta = c.get(i);
    					maxRecent=i;
    				}
    			}
    		}
    	}
    	catch(Exception e){
    		for(int i = 0;i<c.size();i++) {
    			//System.out.println(c.get(i).getNome()+"  Data = "+c.get(i).getData().toString()+" difference by now = "+ differenzaDiDate(c.get(i).getData(), now, TimeUnit.MINUTES)+" minuti");
    			if(c.get(i).getNome().equals(val) && differenzaDiDate(c.get(i).getData(),now, TimeUnit.MINUTES)<300) {
    				if(differenzaDiDate(c.get(maxRecent).getData(), now, TimeUnit.HOURS)>differenzaDiDate(c.get(i).getData(), now, TimeUnit.HOURS)) {
    					citta=c.get(i);
    					maxRecent=i;
    				}
    			}
    		}
    	}
    	return citta;
    }
    
    public double differenzaDiDate(Date date1,Date date2,TimeUnit timeUnit) {
    	long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }





}
