package org.esei.moneygest.core;


import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.esei.moneygest.ui.AreaPersonalActivity;
import org.esei.moneygest.ui.ContactoActivity;
import org.esei.moneygest.ui.GastosActivity;
import org.esei.moneygest.ui.HomeActivity;
import org.esei.moneygest.ui.IngresosActivity;
import org.esei.moneygest.ui.LoginActivity;
import org.esei.moneygest.R;
import org.esei.moneygest.model.Gasto;
import org.esei.moneygest.model.Ingreso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Utilidades {

    public ArrayList <String> obtenerListaGastos(ArrayList<Gasto> listaGastos) {
        ArrayList listaInformacion=new ArrayList<String>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date;

        for(int i=0;i<listaGastos.size();i++){
            date = sdf.format(listaGastos.get(i).getFecha());

            listaInformacion.add("Gasto número: " + (i+1) + "\n"+ "Concepto: "+listaGastos.get(i).getConcepto()
                    +"\n"+ "Cantidad: "+ listaGastos.get(i).getCantidad() + "\n"+ "Fecha: "+ date
                    + "\n" +"Tipo: "+listaGastos.get(i).getTipo());
        }

        return listaInformacion;
    }

    public ArrayList <String> obtenerListaIngresos(ArrayList<Ingreso> listaIngresos) {
        ArrayList listaInformacion=new ArrayList<String>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date;

        for(int i=0;i<listaIngresos.size();i++){
            date = sdf.format(listaIngresos.get(i).getFecha());

            listaInformacion.add("Ingreso número: " + (i+1) + "\n"+ "Concepto: "+listaIngresos.get(i).getConcepto()
                    +"\n"+ "Cantidad: "+ listaIngresos.get(i).getCantidad() + "\n"+ "Fecha: "+ date
                    + "\n" +"Tipo: "+listaIngresos.get(i).getTipo());
        }

        return listaInformacion;
    }

    public boolean desplegarMenu(MenuItem item, AppCompatActivity activity){

        boolean toret=false;
        Intent intent;

        switch (item.getItemId()){

            case R.id.item0:
                intent = new Intent(activity.getApplicationContext(), HomeActivity.class);
                intent.putExtra("filtrosGastos",-1);
                intent.putExtra("minFechaGastos","");
                intent.putExtra("maxFechaGastos","");
                intent.putExtra("minCantidadGastos",0.0);
                intent.putExtra("maxCantidadGastos",0.0);
                intent.putExtra("tipoGastos", "");

                intent.putExtra("filtrosIngresos",-1);
                intent.putExtra("minFechaIngresos","");
                intent.putExtra("maxFechaIngresos","");
                intent.putExtra("minCantidadIngresos",0.0);
                intent.putExtra("maxCantidadIngresos",0.0);
                intent.putExtra("tipoIngresos", "");

                activity.startActivity(intent);
                if(activity instanceof HomeActivity){
                }
                else{
                    activity.finish();
                }
                toret=true;
                break;

            case R.id.item1:
                intent = new Intent(activity.getApplicationContext(), GastosActivity.class);
                activity.startActivity(intent);
                if(activity instanceof HomeActivity){
                }
                else{
                    activity.finish();
                }
                toret=true;
                break;

            case R.id.item2:
                intent = new Intent(activity.getApplicationContext(), IngresosActivity.class);
                activity.startActivity(intent);
                if(activity instanceof HomeActivity){
                }
                else{
                    activity.finish();
                }
                toret=true;
                break;

            case R.id.item3:
                intent = new Intent(activity.getApplicationContext(), AreaPersonalActivity.class);
                activity.startActivity(intent);
                if(activity instanceof HomeActivity){
                }
                else{
                    activity.finish();
                }
                toret=true;
                break;

            case R.id.item4:
                intent = new Intent(activity.getApplicationContext(), ContactoActivity.class);
                activity.startActivity(intent);
                if(activity instanceof HomeActivity){
                }
                else{
                    activity.finish();
                }
                toret=true;
                break;

            case R.id.item5:
                UtilidadesSP utilidadesSP = new UtilidadesSP();
                utilidadesSP.logout(activity);
                Toast.makeText(activity, "Sesión cerrada correctamente", Toast.LENGTH_LONG).show();
                intent = new Intent(activity.getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);
                activity.finish();
                toret=true;
                break;
        }

        return toret;
    }


}
