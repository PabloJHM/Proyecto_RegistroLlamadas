package com.example.pablo.proyecto_registrollamadas;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.pablo.proyecto_registrollamadas.BD.Ayudante;
import com.example.pablo.proyecto_registrollamadas.BD.Tablas;



public class Provider extends ContentProvider {
    private Ayudante a;
    private Context c;
    public Provider(Context c){
        this.c=c;
        a=new Ayudante(c);
    }

    public Provider(){}

    public static final UriMatcher convierteUri2Int;
    private static int SALIENTE=0,ENTRANTE=1, PERDIDA=2;
    static {
        convierteUri2Int = new UriMatcher(UriMatcher.NO_MATCH);
        /********Perdidas********/
        convierteUri2Int.addURI(Tablas.TablaLlamadasPerdidas.AUTHORITY,
                Tablas.TablaLlamadasPerdidas.TABLA, PERDIDA);
        /********Entrantes********/
        convierteUri2Int.addURI(Tablas.TablaLlamadasEntrantes.AUTHORITY,
                Tablas.TablaLlamadasEntrantes.TABLA, ENTRANTE);
        /********Interprete********/
        convierteUri2Int.addURI(Tablas.TablaLlamadasSalientes.AUTHORITY,
                Tablas.TablaLlamadasSalientes.TABLA, SALIENTE);
    }
    private Ayudante abd;
    private SQLiteDatabase bd;
    @Override
    public boolean onCreate() {
        Ayudante a = new Ayudante(getContext());
        this.abd=a;
        bd=abd.getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c=null;
        if(uri.toString().contains(Tablas.TablaLlamadasPerdidas.TABLA)){//Si contiene "perdidas"
            c = bd.query(Tablas.TablaLlamadasPerdidas.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
        }else if(uri.toString().contains(Tablas.TablaLlamadasEntrantes.TABLA)) {//Si contiene "entrantes"
            c = bd.query(Tablas.TablaLlamadasEntrantes.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
        }else if(uri.toString().contains(Tablas.TablaLlamadasSalientes.TABLA)){//Si contiene "salientes"
            c = bd.query(Tablas.TablaLlamadasSalientes.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
        }
        c.setNotificationUri(getContext().getContentResolver(), Tablas.TablaLlamadasPerdidas.CONTENT_URI);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int conver=convierteUri2Int.match(uri);
        long rowId;
        if (conver != SALIENTE && conver != ENTRANTE &&
                conver != PERDIDA) {
            throw new IllegalArgumentException(c.getString(R.string.ukUri)+ uri);
        }
        if(uri.toString().contains(Tablas.TablaLlamadasPerdidas.TABLA)){//Si contiene "perdidas"
            rowId = bd.insert(Tablas.TablaLlamadasPerdidas.TABLA, null, values);
            if (rowId > 0) {
                Uri uri_actividad = ContentUris.withAppendedId(Tablas.TablaLlamadasPerdidas.CONTENT_URI, rowId);
                getContext().getContentResolver().notifyChange(uri_actividad, null);
                return uri_actividad;
            }
        }else if(uri.toString().contains(Tablas.TablaLlamadasSalientes.TABLA)){// Si contiene "salientes"
            rowId = bd.insert(Tablas.TablaLlamadasSalientes.TABLA, null, values);
            if (rowId > 0) {
                Uri uri_actividad = ContentUris.withAppendedId(Tablas.TablaLlamadasSalientes.CONTENT_URI, rowId);
                getContext().getContentResolver().notifyChange(uri_actividad, null);
                return uri_actividad;
            }
        }else if(uri.toString().contains(Tablas.TablaLlamadasEntrantes.TABLA)){//Si contiene "entrantes"
            rowId = bd.insert(Tablas.TablaLlamadasEntrantes.TABLA, null, values);
            if (rowId > 0) {
                Uri uri_actividad = ContentUris.withAppendedId(Tablas.TablaLlamadasEntrantes.CONTENT_URI, rowId);
                getContext().getContentResolver().notifyChange(uri_actividad, null);
                return uri_actividad;
            }
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}