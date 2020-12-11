

//Classe che serve per fare tutte le statistiche di una città in un determinato periodo

package com.example.demo.src;
import java.util.*;

public class Statistiche {
    String nomeFile;

    public Statistiche(String nomeFile){
        this.nomeFile = nomeFile;
    }

    public static double calcolaVarianzaUmidità(Date inizio,Date fine,int idCitta){

        Convertitore conv = new Convertitore();
        ArrayList<Citta> c = conv.JsonToCitta();
        //Da fare con le collection ma siccome non le so ancora usare bene lo faccio con gli arraylist
        ArrayList<Double[]> temp= new ArrayList<Double[]>();

        double media = calcolaMediaUmidita(inizio,fine,idCitta);//media umidita
        for(int i = 0; i<c.size();i++){//cicla tutti i risulati
            if(c.get(i).getId()==idCitta && c.get(i).getData().before(fine) && c.get(i).getData().after(inizio)){//se il risultato corrisponde con la citta e con il range di date...
                boolean trovato = false;
                for(int j = 0; j<temp.size();j++){
                    if(temp.get(j)[0]==c.get(i).getUmidita()) {//vede se quel dato si trova in temp
                        temp.get(j)[1]++;//se c'è incrementa il contatore
                        trovato = true;
                    }
                }
                if(!trovato){//se non c'è lo aggiunge
                    Double[] d = new Double[2];
                    d[0] = c.get(i).getUmidita();
                    d[1] = (double)1;
                    temp.add(d);
                }
            }
        }
        double varianza=0;
        //Calcolo varianza
        for(int i = 0; i<temp.size();i++){
            varianza += Math.pow(temp.get(i)[0]-media,2)*temp.get(i)[1];
        }

        return Math.sqrt(varianza/temp.size());
    }
    public static double calcolaMediaUmidita(Date inizio,Date fine,int idCitta){
        Convertitore conv = new Convertitore();
        ArrayList<Citta> c = conv.JsonToCitta();
        double media=0;
        int n=0;
        for(int i =0; i<c.size();i++){
            if(c.get(i).getId()==idCitta && c.get(i).getData().before(fine) && c.get(i).getData().after(inizio)){
                media += c.get(i).getUmidita();
                n++;
            }
        }
        return media/n;
    }

    public static double calcolaMediaPressione(Date inizio,Date fine,int idCitta){
        Convertitore conv = new Convertitore();
        ArrayList<Citta> c = conv.JsonToCitta();
        double media=0;
        int n=0;
        for(int i =0; i<c.size();i++){
            if(c.get(i).getId()==idCitta && c.get(i).getData().before(fine) && c.get(i).getData().after(inizio)){
                media += c.get(i).getPressione();
                n++;
            }
        }
        return media/(double)n;
    }

        public static double calcolaVarianzaPressione(Date inizio,Date fine,int idCitta){

        Convertitore conv = new Convertitore();
        ArrayList<Citta> c = conv.JsonToCitta();
        //Da fare con le collection ma siccome non le so ancora usare bene lo faccio con gli arraylist
        ArrayList<Double[]> temp= new ArrayList<Double[]>();

        double media = calcolaMediaPressione(inizio,fine,idCitta);//media pressione
        for(int i = 0; i<c.size();i++){//cicla tutti i risulati
            if(c.get(i).getId()==idCitta && c.get(i).getData().before(fine) && c.get(i).getData().after(inizio)){//se il risultato corrisponde con la citta e con il range di date...
                boolean trovato = false;
                for(int j = 0; j<temp.size();j++){
                    if(temp.get(i)[0]==c.get(i).getPressione()) {//vede se quel dato si trova in temp
                        temp.get(i)[1]++;//se c'è incrementa il contatore
                        trovato = true;
                    }
                }
                if(!trovato){//se non c'è lo aggiunge
                    Double[] d = new Double[2];
                    d[0] = c.get(i).getPressione();
                    d[1] = (double)1;
                    temp.add(d);
                }
            }
        }
        double varianza=0;
        //Calcolo varianza
        for(int i = 0; i<temp.size();i++){
            varianza += Math.pow(temp.get(i)[0]-media,2)*temp.get(i)[1];
        }

        return Math.sqrt(varianza/temp.size());
    }
}
