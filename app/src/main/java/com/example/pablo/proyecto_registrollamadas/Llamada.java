package com.example.pablo.proyecto_registrollamadas;

import android.content.ContentValues;

import com.example.pablo.proyecto_registrollamadas.BD.Tablas;

public class Llamada {
    private String tlf;
    private String fecha;

    public Llamada(){}
    public Llamada(String tlf, String fecha) {
        this.tlf = tlf;
        this.fecha = fecha;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ContentValues getContentValuesEntrantes(){
        ContentValues cv = new ContentValues();
        cv.put(Tablas.TablaLlamadasEntrantes.NUMERO,this.tlf);
        cv.put(Tablas.TablaLlamadasEntrantes.FECHA,this.fecha);
        return cv;
    }

    public ContentValues getContentValuesSalientes(){
        ContentValues cv = new ContentValues();
        cv.put(Tablas.TablaLlamadasSalientes.NUMERO,this.tlf);
        cv.put(Tablas.TablaLlamadasSalientes.FECHA,this.fecha);
        return cv;
    }

    public ContentValues getContentValuesPerdidas(){
        ContentValues cv = new ContentValues();
        cv.put(Tablas.TablaLlamadasPerdidas.NUMERO,this.tlf);
        cv.put(Tablas.TablaLlamadasPerdidas.FECHA,this.fecha);
        return cv;
    }

    @Override
    public String toString() {
        return "Llamada{" +
                "tlf='" + tlf + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
