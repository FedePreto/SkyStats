import java.util.*;

public class Statistiche {

    public float calcolaVarianzaUmidità(Date inizio,Date fine,int idCitta,String nomeFile){

        Convertitore conv = new Convertitore();
        ArrayList<Citta> c = conv.JsonToCitta(nomeFile);
        HashMap<Double, Integer> var = new HashMap<Double, Integer>();
        Iterator it = var.entrySet().iterator();
        double media = calcolaMediaUmidita(inizio,fine,idCitta,nomeFile);
        for(int i = 0; i<c.size();i++){
            if(c.get(i).getId()==idCitta && c.get(i).getData().before(fine) && c.get(i).getData().after(inizio)){
                while(it.hasNext()){
                    HashMap<Double,Integer> pair = (HashMap<Double,Integer>)it.next();
                    if(c.get(i).getUmidita() == pair.){

                    }
                }
            }
        }
    }
    public double calcolaMediaUmidita(Date inizio,Date fine,int idCitta,String nomeFile){
        Convertitore conv = new Convertitore();
        ArrayList<Citta> c = conv.JsonToCitta(nomeFile);
        double media=0;
        int n=0;
        for(int i =0; i<c.size();i++){
            if(c.get(i).getId()==idCitta && c.get(i).getData().before(fine) && c.get(i).getData().after(inizio)){
                media += c.get(i).getUmidita();
                n++;
            }
        }
        return media/(double)n;
    }


}
