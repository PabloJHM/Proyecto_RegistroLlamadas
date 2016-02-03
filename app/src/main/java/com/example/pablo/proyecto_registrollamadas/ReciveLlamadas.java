package com.example.pablo.proyecto_registrollamadas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.example.pablo.proyecto_registrollamadas.BD.Tablas;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReciveLlamadas extends BroadcastReceiver {

    private static int ultEstado = TelephonyManager.CALL_STATE_IDLE;
    private static Date fecha;
    private static boolean entrante;
    private static String num;
    private Context c;
    private GregorianCalendar gc;

    @Override
    public void onReceive(Context context, Intent intent) {
        c=context;
        /** Con esto deberia guardar el telefono al cual llamamos al hacer una llamada, pero no lo hace **/
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            num = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
        }
        /** **/
        else{
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            int state = 0;
            if(stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                state = TelephonyManager.CALL_STATE_IDLE;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                state = TelephonyManager.CALL_STATE_RINGING;
            }
            onCallStateChanged(context, state, number);
        }
    }

    public void onCallStateChanged(Context context, int state, String number) {
        c=context;
        num = number;
        //Si el ultimo estado es igual al estado actual significa que no hay accion, por lo que no hace nada
        if(ultEstado == state)
            return;
        switch (state) {
            //Si la accion es que suena el telefono es porque recibimos una llamada, guardamos los datos y ponemos entrante en true
            //debido a que es una posible llamada entrante
            case TelephonyManager.CALL_STATE_RINGING:
                entrante = true;
                fecha = new Date();
                num = number;
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                //Con la accion offhook sabemos que se descuelga el telefono, asi que en este momento no hacemos nada ya que ya
                //guardamos antermiormente que era una llamada entrante.
                // No obstante, si no se descuelga el telefono, al poner entrante en false especificamos que es una llamada perdida
                if(ultEstado != TelephonyManager.CALL_STATE_RINGING){
                    entrante = false;
                    fecha = new Date();
                }
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                //El movil ha vuelto a su estado de espera, dependiendo del estado anterior realizaremos una accion u otra
                //Si el ultimo estado era que el telefono estaba sonando, entonces fue una llamada perdida
                if(ultEstado == TelephonyManager.CALL_STATE_RINGING){
                    llamadaPerdida(context, num, fecha);
                }else if(entrante){ //Si se respondió a la llamada fue una entrante
                    llamadaEntrante(context, num, fecha);
                }else{ //Si no fue ninguno de las dos anteriores entonces fue una saliente
                    llamadaSaliente(context, num, fecha);
                }
                break;
        }
        ultEstado = state;
    }

    //Insert para llamadas entrantes
    protected void llamadaEntrante(Context ctx, String number, Date start){
        gc=new GregorianCalendar();
        gc.setTime(start);
        String fecha=devuelveFechas();
        Llamada aux=new Llamada(number,fecha);
        ctx.getContentResolver().insert(Tablas.TablaLlamadasEntrantes.CONTENT_URI,aux.getContentValuesEntrantes());
    }

    //Insert para llamadas salientes
    protected void llamadaSaliente(Context ctx, String number, Date start){
        gc=new GregorianCalendar();
        gc.setTime(start);
        String fecha=devuelveFechas();
        Llamada aux=new Llamada(number,fecha);
        ctx.getContentResolver().insert(Tablas.TablaLlamadasSalientes.CONTENT_URI, aux.getContentValuesSalientes());
    }

    //Insert para llamadas perdidas
    protected void llamadaPerdida(Context ctx, String number, Date start){
        gc=new GregorianCalendar();
        gc.setTime(start);
        String fecha=devuelveFechas();
        Llamada aux=new Llamada(number,fecha);
        ctx.getContentResolver().insert(Tablas.TablaLlamadasPerdidas.CONTENT_URI, aux.getContentValuesPerdidas());
    }

    //Metodo que devuelve un string con la fecha formateada
    public String devuelveFechas(){
        int diasem=gc.get(Calendar.DAY_OF_WEEK);
        int dia=gc.get(Calendar.DAY_OF_MONTH);
        int mes=gc.get(Calendar.MONTH);
        String s,diaSemana, nombremes="";
        if(diasem == 2){
            diaSemana = c.getResources().getString(R.string.dia1);
        } else if (diasem==3){
            diaSemana = c.getResources().getString(R.string.dia2);
        } else if (diasem==4){
            diaSemana = c.getResources().getString(R.string.dia3);
        } else if (diasem==5){
            diaSemana = c.getResources().getString(R.string.dia4);
        } else if (diasem==6){
            diaSemana = c.getResources().getString(R.string.dia5);
        } else if (diasem==7){
            diaSemana = c.getResources().getString(R.string.dia6);
        } else {
            diaSemana = c.getResources().getString(R.string.dia7);
        }

        switch (mes){
            case 0:
                nombremes=c.getResources().getString(R.string.mes1);
                break;
            case 1:
                nombremes=c.getResources().getString(R.string.mes2);
                break;
            case 2:
                nombremes=c.getResources().getString(R.string.mes3);
                break;
            case 3:
                nombremes=c.getResources().getString(R.string.mes4);
                break;
            case 4:
                nombremes=c.getResources().getString(R.string.mes5);
                break;
            case 5:
                nombremes=c.getResources().getString(R.string.mes6);
                break;
            case 6:
                nombremes=c.getResources().getString(R.string.mes7);
                break;
            case 7:
                nombremes=c.getResources().getString(R.string.mes8);
                break;
            case 8:
                nombremes=c.getResources().getString(R.string.mes9);
                break;
            case 9:
                nombremes=c.getResources().getString(R.string.mes10);
                break;
            case 10:
                nombremes=c.getResources().getString(R.string.mes11);
                break;
            case 11:
                nombremes=c.getResources().getString(R.string.mes12);
                break;
        }
        s=diaSemana+" "+dia+c.getResources().getString(R.string.de)+nombremes;
        return s;
    }
}

