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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.esei.moneygest.core.UtilidadesSP;
import org.esei.moneygest.model.Ingreso;
import org.esei.moneygest.model.IngresoMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegistroIngresoActivity extends AppCompatActivity {

    TextView textViewTitulo;
    EditText editConcepto, editCantidad, editFecha;
    Spinner tipoIngreso;
    IngresoMapper ingresoMapper;
    Ingreso ingreso;
    DatePickerDialog.OnDateSetListener setListener;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ingresos);

        editFecha = (EditText) findViewById(R.id.registro_fecha_ingreso);

        //Añadiendo funcionalidad modo calendario

        Calendar calendar = Calendar.getInstance();
        final int anho = calendar.get(Calendar.YEAR);
        final int mes = calendar.get(Calendar.MONTH);
        final int dia = calendar.get(Calendar.DAY_OF_MONTH);

        textViewTitulo = (TextView) findViewById(R.id.registro_titulo_ingreso);
        editConcepto = (EditText) findViewById(R.id.registro_concepto_ingreso);
        editCantidad = (EditText) findViewById(R.id.registro_cantidad_ingreso);
        tipoIngreso = (Spinner) findViewById(R.id.registro_tipo_ingreso);

        final Intent datosEnviados = this.getIntent();
        id = datosEnviados.getExtras().getInt( "id", -1 );
        final String concepto = datosEnviados.getExtras().getString( "concepto", "" );
        final Double cantidad = datosEnviados.getExtras().getDouble( "cantidad", 0.00);
        final String fecha = datosEnviados.getExtras().getString( "fecha", new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()).toString());
        final String tipo = datosEnviados.getExtras().getString( "tipo", "" );

        if(id == -1){
            textViewTitulo.setText(getString(R.string.registrar_ingreso));
        }

        else{
            textViewTitulo.setText(getString(R.string.modificar_ingreso));
        }

        editConcepto.setText(concepto);
        editCantidad.setText(cantidad.toString());
        editFecha.setText(fecha);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.categoria_ingresos, android.R.layout.simple_spinner_item);
        tipoIngreso.setAdapter(adapter);

        for(int i= 0; i < tipoIngreso.getAdapter().getCount(); i++)
        {
            if(tipoIngreso.getAdapter().getItem(i).toString().contains(tipo))
            {
                tipoIngreso.setSelection(i);
            }
        }

        editFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegistroIngresoActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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

    }

    public void registrarIngreso(View view){

        if(this.id == -1){
            ingresoMapper = new IngresoMapper(this);

            editConcepto = (EditText) findViewById(R.id.registro_concepto_ingreso);
            editCantidad = (EditText) findViewById(R.id.registro_cantidad_ingreso);


            String concepto = editConcepto.getText().toString();
            String cantidadString = editCantidad.getText().toString();
            String stringFecha = editFecha.getText().toString();
            String tipoIngresoString = tipoIngreso.getSelectedItem().toString();

            if(concepto.equals("") || cantidadString.equals("") || stringFecha.equals("") || tipoIngresoString.equals("")){
                Toast.makeText(RegistroIngresoActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
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
                String username = utilidadesSP.cargarUsername(RegistroIngresoActivity.this);

                ingreso = new Ingreso(concepto, cantidad, fecha, tipoIngresoString, username);
                ingresoMapper.insertIngreso(ingreso);

                Toast.makeText(RegistroIngresoActivity.this, "Ingreso registrado correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),IngresosActivity.class);
                startActivity(intent);

            }
        }

        else{
            ingresoMapper = new IngresoMapper(this);

            editConcepto = (EditText) findViewById(R.id.registro_concepto_ingreso);
            editCantidad = (EditText) findViewById(R.id.registro_cantidad_ingreso);


            String concepto = editConcepto.getText().toString();
            String cantidadString = editCantidad.getText().toString();
            String stringFecha = editFecha.getText().toString();
            String tipoIngresoString = tipoIngreso.getSelectedItem().toString();

            if(concepto.equals("") || cantidadString.equals("") || stringFecha.equals("") || tipoIngresoString.equals("")){
                Toast.makeText(RegistroIngresoActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
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
                String username = utilidadesSP.cargarUsername(RegistroIngresoActivity.this);

                ingreso = new Ingreso(id, concepto, cantidad, fecha, tipoIngresoString, username);
                Toast.makeText(RegistroIngresoActivity.this, fecha.toString(), Toast.LENGTH_LONG).show();
                ingresoMapper.updateIngreso(ingreso);

                Toast.makeText(RegistroIngresoActivity.this, "Ingreso actualizado correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),IngresosActivity.class);
                startActivity(intent);

            }
        }



    }

}



