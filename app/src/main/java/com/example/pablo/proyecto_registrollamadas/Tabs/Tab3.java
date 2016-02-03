package com.example.pablo.proyecto_registrollamadas.Tabs;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;

import com.example.pablo.proyecto_registrollamadas.Adaptadores.Adaptador;
import com.example.pablo.proyecto_registrollamadas.BD.Tablas;
import com.example.pablo.proyecto_registrollamadas.Llamada;
import com.example.pablo.proyecto_registrollamadas.R;
import com.example.pablo.proyecto_registrollamadas.SacaDatos;

import java.util.List;

public class Tab3 extends Fragment{
    private SacaDatos sd;
    private List<Llamada> lista;
    private int[] a;
    private WebView wv;
    private Adaptador ad;
    private ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sd=new SacaDatos(getContext());
        a=obtieneDatosPerdidas();
    }

    //Genero el listvew en el fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab3, container, false);
        lv=(ListView)v.findViewById(R.id.listView3);

        wv=(WebView)v.findViewById(R.id.webView4);
        WebSettings webSettings=wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.loadUrl("file:///android_asset/canvas/pruebagraficos.html"); //Url de la grafica
        wv.addJavascriptInterface(this, "InterfazAndroid");
        wv.setWebChromeClient(new WebChromeClient());

        lista=sd.sacaSalientesList();
        Cursor cu=sd.sacaPerdidasCursor();
        ad=new Adaptador(getActivity(),cu);
        lv.setAdapter(ad);
        return v;
    }

    //Metodo donde se envian los datos a la grafica
    @JavascriptInterface
    public int enviarDia(int pos){
        return a[pos];
    }

    //Con un cursor recorro la tabla correspondiente, y con varios contadores guardo el numero de llamadas
    //por dias de la semana. Estos se guardan en un array que después se enviara a la grafica
    public int[] obtieneDatosPerdidas(){
        int b[],l=0,m=0,x=0,j=0,v=0,s=0,d=0;
        String fecha;
        Cursor cu=getContext().getContentResolver().query(Tablas.TablaLlamadasPerdidas.CONTENT_URI, null, null, null, null, null);
        while(cu.moveToNext()){
            fecha=cu.getString(cu.getColumnIndex(cu.getColumnName(2)));
            if(fecha.toLowerCase().contains(getString(R.string.dia1).toLowerCase())){
                l++;
            } else if(fecha.toLowerCase().contains(getString(R.string.dia2).toLowerCase())){
                m++;
            } else if(fecha.toLowerCase().contains(getString(R.string.dia3).toLowerCase())){
                x++;
            } else if(fecha.toLowerCase().contains(getString(R.string.dia4).toLowerCase())){
                j++;
            } else if(fecha.toLowerCase().contains(getString(R.string.dia5).toLowerCase())){
                v++;
            } else if(fecha.toLowerCase().contains(getString(R.string.dia6).toLowerCase())){
                s++;
            } else if(fecha.toLowerCase().contains(getString(R.string.dia7).toLowerCase())){
                d++;
            }
        }
        b=new int[]{l,m,x,j,v,s,d};
        return b;
    }
}
