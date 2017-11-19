package com.example.ricardo.enucompsala;

/**
 * Created by ricardo on 17/11/17.
 */

public class Ponto {

    double latitude, longitude;
    String nome;

    public Ponto(double latitude, double longitude, String nome) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
