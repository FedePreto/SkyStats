import java.util.Date;


public class Citta {
    private int id;
    private String nome;
    private String meteo;
    private double umidita;
    private double pressione;
    private Date data;

    public Citta(int id, String nome, String meteo, double umidita, double pressione, Date data) {
        this.id = id;
        this.nome = nome;
        this.meteo = meteo;
        this.umidita = umidita;
        this.pressione = pressione;
        this.data = data;

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

    public void setUmidita(float umidita) {
        this.umidita = umidita;
    }

    public double getPressione() {
        return pressione;
    }

    public void setPressione(float pressione) {
        this.pressione = pressione;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    public String toString(){
        return "Id = "+this.id+", Nome = "+this.nome+", Meteo = "+this.meteo+", Umidità = "+this.umidita+"% , Pressione = "+this.pressione+" , Data = "+this.data.toString();
    }
}
