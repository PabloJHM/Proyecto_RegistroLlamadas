package com.example.pablo.proyecto_registrollamadas.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="llamadas.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql="create table "+ Tablas.TablaLlamadasEntrantes.TABLA+
                " ("+ Tablas.TablaLlamadasEntrantes._ID+
                " integer primary key autoincrement, "+
                Tablas.TablaLlamadasEntrantes.NUMERO+" integer, "+
                Tablas.TablaLlamadasEntrantes.FECHA+" text"+
                ")";
        db.execSQL(sql);

        sql="create table "+ Tablas.TablaLlamadasSalientes.TABLA+
                " ("+ Tablas.TablaLlamadasSalientes._ID+
                " integer primary key autoincrement, "+
                Tablas.TablaLlamadasSalientes.NUMERO+" integer, "+
                Tablas.TablaLlamadasSalientes.FECHA+" text"+
                ")";
        db.execSQL(sql);

        sql="create table "+ Tablas.TablaLlamadasPerdidas.TABLA+
                " ("+ Tablas.TablaLlamadasPerdidas._ID+
                " integer primary key autoincrement, "+
                Tablas.TablaLlamadasPerdidas.NUMERO+" integer, "+
                Tablas.TablaLlamadasPerdidas.FECHA+" text"+
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists "
                + Tablas.TablaLlamadasEntrantes.TABLA+","+
                Tablas.TablaLlamadasSalientes.TABLA+","+
                Tablas.TablaLlamadasPerdidas.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }
}
