package com.example.pablo.proyecto_registrollamadas.Adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.pablo.proyecto_registrollamadas.R;


public class Adaptador extends CursorAdapter {
    private TextView tv1,tv2;
    private Cursor cur;
    public Adaptador(Context context, Cursor cursor){
        super(context, cursor,true);
        this.cur=cursor;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.item_list, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        tv1 =(TextView)view.findViewById(R.id.tvTlf);
        tv2 =(TextView)view.findViewById(R.id.tvFec);
        tv1.setText(cur.getString(cur.getColumnIndex(cur.getColumnName(1))));//Telefono
        tv2.setText(cur.getString(cur.getColumnIndex(cur.getColumnName(2))));//Fecha
    }
}