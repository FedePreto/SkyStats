package it.univpm.dummyClasses;

public class Main {
    private double temp;
    private double fells_like;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double humidity;

    public Main(double temp) {
        this.temp = temp;
    }
    public Main(){};

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFells_like() {
        return fells_like;
    }

    public void setFells_like(double fells_like) {
        this.fells_like = fells_like;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
