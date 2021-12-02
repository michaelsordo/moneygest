package org.esei.moneygest;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class RegistroGastoActivity extends AppCompatActivity {

    EditText campoConcepto, campoCantidad, campoFecha;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_gastos);
    }

    public void registrarGasto(View view){

        //ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_gasto",null,1);

        campoConcepto = findViewById(R.id.registro_concepto_gasto);
        campoCantidad = findViewById(R.id.registro_cantidad_gasto);
        campoFecha = findViewById(R.id.registro_fecha_gasto);

        String concepto = campoConcepto.getText().toString();
        //aqui no estamos recogiendo los datos de los edittext claro
        Double cantidad = null;
        String cantidad_to_string = String.valueOf(cantidad);
        Date fecha = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        String strDate = dateFormat.format(fecha);

        if(concepto.equals("") || cantidad.equals("") || fecha.equals("")){
            Toast.makeText(RegistroGastoActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
        }

        else{

            //SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(utilidades.CAMPO_CONCEPTO,concepto);
            values.put(utilidades.CAMPO_CANTIDAD,cantidad);
            values.put(utilidades.CAMPO_FECHA, String.valueOf(fecha));

            Boolean insert_gasto = DB.insertGasto(concepto,cantidad,fecha);

            if(insert_gasto){
                UtilidadesSP utilidadesSP = new UtilidadesSP();
                utilidadesSP.guardar_gastos(campoConcepto,campoCantidad,campoFecha,RegistroGastoActivity.this);

                Toast.makeText(RegistroGastoActivity.this, "Gasto registrado correctamente", Toast.LENGTH_LONG).show();
            }

            else{
                Toast.makeText(RegistroGastoActivity.this, "Error en la inserción del gasto", Toast.LENGTH_SHORT).show();
            }
        }


    }


            //Long idResultante = db.insert(utilidades.TABLA_GASTO,utilidades.CAMPO_ID,values);

            //Toast.makeText(getApplicationContext(),"Id Registro" + idResultante,Toast.LENGTH_SHORT).show();
        }


