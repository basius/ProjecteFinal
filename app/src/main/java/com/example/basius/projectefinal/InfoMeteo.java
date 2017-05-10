package com.example.basius.projectefinal;

/**
 * Created by basius on 9/05/17.
 */

public class InfoMeteo {
    String humidity;
    String pressure;
    String temperatura;

    public InfoMeteo() {
    }

    public InfoMeteo(String humidity, String pressure, String temperatura) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperatura = temperatura;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
