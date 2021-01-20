
//Classe che modella i parametri di una città

package com.univpm.oop.model;

import java.util.Date;
/**
 * Classe rappresentativa della citta
 *
 */
public class Citta {
	/**
	 * Id della citta
	 */
	private int id;
	/**
	 * Nome della citta
	 */
	private String nome;
	/**
	 * Meteo della citta
	 */
	private String meteo;
	/**
	 * Umidità della citta
	 */
	private double umidita;
	/**
	 * Pressione della citta
	 */
	private double pressione;
	/**
	 * Temperaturad della citta
	 */
	private double temperatura;
	/**
	 * Posizione della citta
	 */
	private String posizione;
	/**
	 * Momento in cui i dati sono stati raccolti
	 */
	private Date data;
	/**
	 * Costruttore
	 * @param id
	 * @param nome
	 * @param meteo
	 * @param umidita
	 * @param pressione
	 * @param temperatura
	 * @param posizione
	 * @param data
	 */
	public Citta(int id, String nome, String meteo, double umidita, double pressione, double temperatura, String posizione, Date data) {
		this.id = id;
		this.nome = nome;
		this.meteo = meteo;
		this.umidita = umidita;
		this.pressione = pressione;
		this.temperatura = temperatura;
		this.posizione = posizione;
		this.data = data;
	}
	/**
	 * Costruttore
	 */
	public Citta() {
		data = new Date();
	}
	/**
	 * Getter
	 * @return
	 */
	public String getPosizione() {
		return posizione;
	}

	
	/**
	 * Setter
	 * @param posizione
	 */
	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}
	/**
	 * Getter
	 * @return
	 */
	public double getTemperatura() {
		return temperatura;
	}
	/**
	 * Setter
	 * @param temperatura
	 */
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	/**
	 * Getter
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * Setter
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Getter
	 * @return
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Setter
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Getter
	 * @return
	 */
	public String getMeteo() {
		return meteo;
	}
	/**
	 * Setter
	 * @param meteo
	 */
	public void setMeteo(String meteo) {
		this.meteo = meteo;
	}
	/**
	 * Getter
	 * @return
	 */
	public double getUmidita() {
		return umidita;
	}
	/**
	 * Setter
	 * @param umidita
	 */
	public void setUmidita(double umidita) {
		this.umidita = umidita;
	}
	/**
	 * Getter
	 * @return
	 */
	public double getPressione() {
		return pressione;
	}
	/**
	 * Setter
	 * @param pressione
	 */
	public void setPressione(double pressione) {
		this.pressione = pressione;
	}
	/**
	 * Getter
	 * @return
	 */
	public Date getData() {
		return data;
	}
	/**
	 * Setter
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}
	/**
	 * To string
	 */
	public String toString() {
		return "\nId = " + this.id + "\nNome = " + this.nome + "\nPosizione: " + this.posizione + "\nMeteo = "
				+ this.meteo + "\nUmidità = " + this.umidita + "%\nPressione = " + this.pressione + "\nData = "
				+ this.data.toString();
	}
	/**
	 * Data la latitudine associa la citta ad una posizione geografica (Nord, Centro o Sud)
	 * @author Nicolò
	 * @param latitudine
	 * @return posizione geografica
	 */
	public String getLocation(double latitudine) {
		// System.out.println(latitudine);
		if (latitudine > 43.77) {
			return "Nord";
		} else {
			if (latitudine >= 41.89) {
				return "Centro";
			} else {
				return "Sud";
			}
		}
	}
}
