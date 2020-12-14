
//Classe che modella i parametri di una città

package com.example.demo.src;
import java.util.Date;


public class Citta {
    private int id;
    private String nome;
    private String meteo;
    private double umidita;
    private double pressione;
    private double temperatura;
    private String posizione;
    private Date data;

    public Citta(int id, String nome, String meteo, double umidita, double pressione,double temperatura, Date data) {
        this.id = id;
        this.nome = nome;
        this.meteo = meteo;
        this.umidita = umidita;
        this.pressione = pressione;
        this.temperatura=temperatura;
        this.data = data;

    }
    public Citta(){
        data = new Date();
    }
    
    public String getPosizione() {
		return posizione;
	}
	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}
	public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMeteo() {
        return meteo;
    }

    public void setMeteo(String meteo) {
        this.meteo = meteo;
    }

    public double getUmidita() {
        return umidita;
    }

    public void setUmidita(double umidita) {
        this.umidita = umidita;
    }

    public double getPressione() {
        return pressione;
    }

    public void setPressione(double pressione) {
        this.pressione = pressione;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    public String toString(){
        return "\nId = "+this.id+"\nNome = "+this.nome+"\nPosizione: "+this.posizione+"\nMeteo = "+this.meteo+"\nUmidità = "+this.umidita+"%\nPressione = "+this.pressione+"\nData = "+this.data.toString();
    }
    public String getLocation(double latitudine) {
    	//System.out.println(latitudine);
    	if(latitudine>43.77) {
    		return "Nord";
    	}else {
    		if(latitudine>=41.89) {
    			return "Centro";
    		}else {
    			return "Sud";
    		}
    	}
    }
}
