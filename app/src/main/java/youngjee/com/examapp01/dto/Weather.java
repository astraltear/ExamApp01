package youngjee.com.examapp01.dto;

public class Weather {

    int lat;
    int lon;
    int temperature;
    int coludy;
    String city;

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getColudy() {
        return coludy;
    }

    public void setColudy(int coludy) {
        this.coludy = coludy;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
