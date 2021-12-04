package org.esei.moneygest;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.esei.moneygest.core.DatabaseHelper;

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

        String concepto = editConcepto.getText().toString();
        String cantidadString = editCantidad.getText().toString();
        String stringFecha = editFecha.getText().toString();
        String tipoGastoString = tipoGasto.getSelectedItem().toString();

        if(concepto.equals("") || cantidadString.equals("") || stringFecha.equals("") || tipoGastoString.equals("")){
            Toast.makeText(RegistroGastoActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
        }

        else{

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = null;

            try {
                fecha = dateFormat.parse(stringFecha);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Double cantidad = Double.parseDouble(cantidadString);

            UtilidadesSP utilidadesSP = new UtilidadesSP();
            String username = utilidadesSP.cargar_username(RegistroGastoActivity.this);

            Boolean insert_gasto = DB.insertGasto(concepto,cantidad,fecha,tipoGastoString,username);

            if(insert_gasto){

                Toast.makeText(RegistroGastoActivity.this, "Gasto registrado correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),GastosActivity.class);
                startActivity(intent);
                
            }

            else{
                Toast.makeText(RegistroGastoActivity.this, "Error en la inserción del gasto", Toast.LENGTH_SHORT).show();
            }

        }


    }

}


