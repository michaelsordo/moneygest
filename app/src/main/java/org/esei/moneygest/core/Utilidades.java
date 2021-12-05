package org.esei.moneygest.core;


import org.esei.moneygest.model.Gasto;
import org.esei.moneygest.model.Ingreso;

import java.lang.reflect.Array;
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


}
