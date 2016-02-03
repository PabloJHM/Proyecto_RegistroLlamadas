package com.example.pablo.proyecto_registrollamadas.BD;

import android.net.Uri;
import android.provider.BaseColumns;


public class Tablas {
    private Tablas(){
    }

    public static abstract class TablaLlamadasEntrantes implements BaseColumns {
        public static final String TABLA = "entrantes";
        public static final String NUMERO = "numero";
        public static final String FECHA = "fecha";
        public final static String AUTHORITY = "com.example.pablo.proyecto_registrollamadas";
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLA);
    }

    public static abstract class TablaLlamadasSalientes implements BaseColumns {
        public static final String TABLA = "salientes";
        public static final String NUMERO = "numero";
        public static final String FECHA = "fecha";
        public final static String AUTHORITY = "com.example.pablo.proyecto_registrollamadas";
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLA);
    }

    public static abstract class TablaLlamadasPerdidas implements BaseColumns {
        public static final String TABLA = "perdidas";
        public static final String NUMERO = "numero";
        public static final String FECHA = "fecha";
        public final static String AUTHORITY = "com.example.pablo.proyecto_registrollamadas";
        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLA);
    }
}
