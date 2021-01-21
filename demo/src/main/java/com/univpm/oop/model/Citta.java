
//Classe che modella i parametri di una città

package com.univpm.oop.model;

import java.util.Date;
/**
 * Classe utilizzata per memorizzare i dati registrati di una città in un determinato istante
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
	 * Costruttore che si occupa di valorizzare gli attributi di un oggetto {@link Citta}
	 * @param id {@link int} contenente il codice ID della Citta
	 * @param nome {@link String} contenente il nome della Citta
	 * @param meteo {@link String} contenente il meteo della Citta
	 * @param umidita {@link double} contenente il tasso di umidità della città
	 * @param pressione {@link double} contenente il livello di pressione della città
	 * @param temperatura {@link double} contenente il valore della temperatura della città
	 * @param posizione  {@link String} contenente la posizione (Nord,Sud,Centro) della città 
	 * @param data {@link Date} contenente la data in cui sono stati registrati i precedenti dati della città
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
	 * Costruttore che si occupa di inizializzare la data quando viene creato un oggetto di tipo citta
	 */
	public Citta() {
		data = new Date();
	}
	/**
	 * Metodo getter che si occupa di ritornare la locazione della città
	 * @return posizione {@link String} contenente la locazione della città
	 */
	public String getPosizione() {
		return posizione;
	}

	
	/**
	 * Metodo setter che si occupa di memorizzare una nuova locazione della città
	 * @param posizione {@link String} contenente la locazione della città
	 */
	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}
	/**
	 * Metodo getter che si occupa di ritornare la temperatura della città
	 * @return temperatura {@link double} contenente la temperatura della città
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
	 * Metodo getter che si occupa di ritornare il codice ID della città
	 * @return id {@link int} contenente il codice ID della città
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
	 * Metodo getter che si occupa di ritornare il nome della città
	 * @return nome {@link String} contenente il nome della città
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
	 * Metodo getter che si occupa di ritornare il meteo della città
	 * @return meteo {@link String} contenente il meteo della città
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
	 * Metodo getter che si occupa di ritornare il tasso di umidità della città
	 * @return umidita {@link double} contenente il tasso di umidità della città
	 */
	public double getUmidita() {
		return umidita;
	}
	/**
	 * 
	 * @param umidita
	 */
	public void setUmidita(double umidita) {
		this.umidita = umidita;
	}
	/**
	 * Metodo getter che si occupa di ritornare la pressione della città
	 * @return pressione {@link double} contenente la pressione della città
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
	 * Metodo getter che si occupa di ritornare la data in cui sono stati registrati i dati della città
	 * @return id {@link Date} contenente la data in cui sono stati registrati i dati della città
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
	 * Metodo che data la latitudine di una Città associa alla citta una posizione geografica (Nord,Centro,Sud)
	 * @author Nicolò
	 * @param latitudine {@link double} contenente la latitudine della città
	 * @return {@link String} contenente la posizione geografica della città
	 */
	public String getLocation(double latitudine) {
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
