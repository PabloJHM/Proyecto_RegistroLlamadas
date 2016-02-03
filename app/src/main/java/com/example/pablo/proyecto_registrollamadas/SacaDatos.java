package com.example.pablo.proyecto_registrollamadas;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;

import com.example.pablo.proyecto_registrollamadas.BD.Tablas;

import java.util.ArrayList;
import java.util.List;

public class SacaDatos extends Application{
    private List<Llamada> lp,ls,le;
    private Context c;
    public SacaDatos(Context c){
        this.c=c;
    }
    public Cursor sacaPerdidasCursor(){
        Cursor cur = c.getContentResolver().query(Tablas.TablaLlamadasPerdidas.CONTENT_URI, null, null, null, null);
        return cur;
    }

    public Cursor sacaSalientesCursor(){
        Cursor cur = c.getContentResolver().query(Tablas.TablaLlamadasSalientes.CONTENT_URI, null, null, null, null);
        return cur;
    }

    public Cursor sacaEntrantesCursor(){
        Cursor cur = c.getContentResolver().query(Tablas.TablaLlamadasEntrantes.CONTENT_URI, null, null, null, null);
        return cur;
    }


    public List sacaPerdidasList() {
        lp = new ArrayList();
        String tlf, fecha;
        Cursor cur;
        Llamada aux;
        cur=sacaPerdidasCursor();
        String[] prueba=cur.getColumnNames();
        for(int i=0;i<prueba.length;i++){
            System.out.println(prueba[i]);
        }
        while (cur.moveToNext()) {
            tlf = cur.getString(cur.getColumnIndex(cur.getColumnName(1)));
            fecha = cur.getString(cur.getColumnIndex(cur.getColumnName(2)));
            aux = new Llamada(tlf, fecha);
            lp.add(aux);
        }
        cur.close();
        return lp;
    }

    public List sacaSalientesList() {
        ls = new ArrayList();
        Cursor cur;
        String tlf,fecha;
        Llamada aux;
        cur=sacaSalientesCursor();
        while (cur.moveToNext()) {
            tlf = cur.getString(cur.getColumnIndex(cur.getColumnName(1)));
            fecha = cur.getString(cur.getColumnIndex(cur.getColumnName(2)));
            aux = new Llamada(tlf, fecha);
            ls.add(aux);
        }
        cur.close();
        return ls;
    }

    public List sacaEntrantesList() {
        le = new ArrayList();
        String tlf,fecha;
        Cursor cur;
        Llamada aux;
        cur=sacaEntrantesCursor();
        while (cur.moveToNext()) {
            tlf = cur.getString(cur.getColumnIndex(cur.getColumnName(1)));
            fecha = cur.getString(cur.getColumnIndex(cur.getColumnName(2)));
            aux = new Llamada(tlf, fecha);
            le.add(aux);
        }
        cur.close();
        return le;
    }
}
