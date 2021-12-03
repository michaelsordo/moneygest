package org.esei.moneygest;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class RegistroGastoActivity extends AppCompatActivity {

    EditText editConcepto, editCantidad, editFecha;
    Spinner tipoGasto;
    DatabaseHelper DB;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_gastos);

        editFecha = (EditText) findViewById(R.id.registro_fecha_gasto);

        //Añadiendo funcionalidad modo calendario

        Calendar calendar = Calendar.getInstance();
        final int anho = calendar.get(Calendar.YEAR);
        final int mes = calendar.get(Calendar.MONTH);
        final int dia = calendar.get(Calendar.DAY_OF_MONTH);

        editFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegistroGastoActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,anho,mes,dia);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int anho, int mes, int dia) {
                mes=mes+1;
                String date = dia+"/"+mes+"/"+anho;
                editFecha.setText(date);

            }
        };

        //Spinner para seleccion de categoría

        tipoGasto = (Spinner) findViewById(R.id.idspinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.categoria, android.R.layout.simple_spinner_item);
        tipoGasto.setAdapter(adapter);
    }

    public void registrarGasto(View view){

        DB = new DatabaseHelper(this);

        editConcepto = (EditText) findViewById(R.id.registro_concepto_gasto);
        editCantidad = (EditText) findViewById(R.id.registro_cantidad_gasto);
        tipoGasto = (Spinner) findViewById(R.id.idspinner);
        //editFecha = (EditText) findViewById(R.id.registro_fecha_gasto);

        String concepto = editConcepto.getText().toString();

        String cantidadString = editCantidad.getText().toString();

        Double cantidad = Double.parseDouble(cantidadString);

        String stringFecha = editFecha.getText().toString();

        String tipoGastoString = tipoGasto.getSelectedItem().toString();


        //aqui no estamos recogiendo los datos de los edittext claro
        //pero si los recogemos y cambiamos el tipo de dato da error
        //Double cantidad = null;
        //String cantidad_to_string = String.valueOf(cantidad);
        //Date fecha = Calendar.getInstance().getTime();
        //DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        //String strDate = dateFormat.format(fecha);

        if(concepto.equals("") || cantidad.equals("") || stringFecha.equals("") || tipoGastoString.equals("")){
            Toast.makeText(RegistroGastoActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
        }

        else{

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = null;

            try {
                fecha = dateFormat.parse(stringFecha);
                Toast.makeText(RegistroGastoActivity.this, stringFecha + String.valueOf(fecha), Toast.LENGTH_SHORT).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //SQLiteDatabase db = conn.getWritableDatabase();
            UtilidadesSP utilidadesSP = new UtilidadesSP();
            String username = utilidadesSP.cargar_username(RegistroGastoActivity.this);

            Toast.makeText(RegistroGastoActivity.this, concepto + " " + cantidad + " " + fecha + " " + username, Toast.LENGTH_SHORT).show();

            Boolean insert_gasto = DB.insertGasto(concepto,cantidad,fecha,tipoGastoString,username);

            if(insert_gasto){

                Toast.makeText(RegistroGastoActivity.this, "Gasto registrado correctamente", Toast.LENGTH_LONG).show();
            }

            else{
                Toast.makeText(RegistroGastoActivity.this, "Error en la inserción del gasto", Toast.LENGTH_SHORT).show();
            }


            Toast.makeText(RegistroGastoActivity.this, "Gasto registrado correctamente", Toast.LENGTH_LONG).show();

        }


    }


            //Long idResultante = db.insert(utilidades.TABLA_GASTO,utilidades.CAMPO_ID,values);

            //Toast.makeText(getApplicationContext(),"Id Registro" + idResultante,Toast.LENGTH_SHORT).show();
        }


