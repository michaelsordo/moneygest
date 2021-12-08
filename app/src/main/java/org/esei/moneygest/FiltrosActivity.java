package org.esei.moneygest;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class FiltrosActivity extends AppCompatActivity {

CheckBox cbFechaGastos, cbFechaIngresos, cbCantidadGastos, cbCantidadIngresos, cbTipoGastos, cbTipoIngresos;
EditText editMinFechaGastos, editMaxFechaGastos, editMinFechaIngresos, editMaxFechaIngresos;
EditText editMinCantidadGastos, editMaxCantidadGastos, editMinCantidadIngresos, editMaxCantidadIngresos;
Spinner spTipoGastos, spTipoIngresos;
LinearLayout layoutFechaGastos, layoutFechaIngresos;
LinearLayout layoutCantidadGastos, layoutCantidadIngresos;
LinearLayout layoutTipoGastos, layoutTipoIngresos;

DatePickerDialog.OnDateSetListener setListener;
DatePickerDialog.OnDateSetListener setListener2;
DatePickerDialog.OnDateSetListener setListener3;
DatePickerDialog.OnDateSetListener setListener4;

public static final int SIN_FILTROS = -1;
public static final int FILTRO_FECHA = 0;
public static final int FILTRO_CANTIDAD = 1;
public static final int FILTRO_TIPO = 2;
public static final int FILTRO_FECHA_CANTIDAD = 3;
public static final int FILTRO_FECHA_TIPO = 4;
public static final int FILTRO_CANTIDAD_TIPO = 5;
public static final int FILTRO_FECHA_CANTIDAD_TIPO = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        //Añadiendo funcionalidad modo calendario

        Calendar calendar = Calendar.getInstance();
        final int anho = calendar.get(Calendar.YEAR);
        final int mes = calendar.get(Calendar.MONTH);
        final int dia = calendar.get(Calendar.DAY_OF_MONTH);


        layoutFechaGastos = findViewById(R.id.layout_fecha_gastos);
        layoutFechaIngresos = findViewById(R.id.layout_fecha_ingresos);
        layoutCantidadGastos = findViewById(R.id.layout_cantidad_gastos);
        layoutCantidadIngresos = findViewById(R.id.layout_cantidad_ingresos);
        layoutTipoGastos = findViewById(R.id.layout_tipo_gastos);
        layoutTipoIngresos = findViewById(R.id.layout_tipo_ingresos);

        cbFechaGastos = findViewById(R.id.cb_fecha_gasto);
        cbFechaIngresos = findViewById(R.id.cb_fecha_ingreso);
        cbCantidadGastos = findViewById(R.id.cb_cantidad_gasto);
        cbCantidadIngresos = findViewById(R.id.cb_cantidad_ingreso);
        cbTipoGastos = findViewById(R.id.cb_tipo_gasto);
        cbTipoIngresos = findViewById(R.id.cb_tipo_ingreso);

        editMinFechaGastos = findViewById(R.id.fecha_gastos_min);
        editMaxFechaGastos = findViewById(R.id.fecha_gastos_max);
        editMinCantidadGastos = findViewById(R.id.cantidad_gastos_min);
        editMaxCantidadGastos = findViewById(R.id.cantidad_gastos_max);
        editMinFechaIngresos = findViewById(R.id.fecha_ingresos_min);
        editMaxFechaIngresos = findViewById(R.id.fecha_ingresos_max);
        editMinCantidadIngresos = findViewById(R.id.cantidad_ingresos_min);
        editMaxCantidadIngresos = findViewById(R.id.cantidad_ingresos_max);

        spTipoGastos = findViewById(R.id.spinner_tipo_gasto);
        spTipoIngresos = findViewById(R.id.spinner_tipo_ingreso);

        //FECHA MIN GASTOS

        editMinFechaGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        FiltrosActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
                editMinFechaGastos.setText(date);

            }
        };

        //FECHA MAX GASTOS

        editMaxFechaGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        FiltrosActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener2,anho,mes,dia);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener2 = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int anho, int mes, int dia) {
                mes=mes+1;
                String date = dia+"/"+mes+"/"+anho;
                editMaxFechaGastos.setText(date);

            }
        };

        //FECHA MIN INGRESOS

        editMinFechaIngresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        FiltrosActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener3,anho,mes,dia);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener3 = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int anho, int mes, int dia) {
                mes=mes+1;
                String date = dia+"/"+mes+"/"+anho;
                editMinFechaIngresos.setText(date);

            }
        };

        //FECHA MAX INGRESOS

        editMaxFechaIngresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        FiltrosActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener4,anho,mes,dia);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener4 = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int anho, int mes, int dia) {
                mes=mes+1;
                String date = dia+"/"+mes+"/"+anho;
                editMaxFechaIngresos.setText(date);

            }
        };




        //Para spinner Gastos
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.categoria, android.R.layout.simple_spinner_item);
        spTipoGastos.setAdapter(adapter);

        //Para spinner Ingresos
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.categoria_ingresos, android.R.layout.simple_spinner_item);
        spTipoIngresos.setAdapter(adapter2);

        cbFechaGastos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean esta_pulsado) {
                if(esta_pulsado){
                    layoutFechaGastos.setVisibility(View.VISIBLE);
                    editMinFechaGastos.setVisibility(View.VISIBLE);
                    editMaxFechaGastos.setVisibility(View.VISIBLE);
                }else{
                    layoutFechaGastos.setVisibility(View.GONE);
                    editMinFechaGastos.setVisibility(View.GONE);
                    editMaxFechaGastos.setVisibility(View.GONE);
                }
            }
        });

        cbCantidadGastos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean esta_pulsado) {
                if(esta_pulsado){
                    layoutCantidadGastos.setVisibility(View.VISIBLE);
                    editMinCantidadGastos.setVisibility(View.VISIBLE);
                    editMaxCantidadGastos.setVisibility(View.VISIBLE);
                }else{
                    layoutCantidadGastos.setVisibility(View.GONE);
                    editMinCantidadGastos.setVisibility(View.GONE);
                    editMaxCantidadGastos.setVisibility(View.GONE);
                }
            }
        });

        cbTipoGastos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean esta_pulsado) {
                if(esta_pulsado){
                    layoutTipoGastos.setVisibility(View.VISIBLE);
                    spTipoGastos.setVisibility(View.VISIBLE);
                }else{
                    layoutTipoGastos.setVisibility(View.GONE);
                    spTipoGastos.setVisibility(View.GONE);
                }
            }
        });

        cbFechaIngresos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean esta_pulsado) {
                if(esta_pulsado){
                    layoutFechaIngresos.setVisibility(View.VISIBLE);
                    editMinFechaIngresos.setVisibility(View.VISIBLE);
                    editMaxFechaIngresos.setVisibility(View.VISIBLE);
                }else{
                    layoutFechaIngresos.setVisibility(View.GONE);
                    editMinFechaIngresos.setVisibility(View.GONE);
                    editMaxFechaIngresos.setVisibility(View.GONE);
                }
            }
        });

        cbCantidadIngresos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean esta_pulsado) {
                if(esta_pulsado){
                    layoutCantidadIngresos.setVisibility(View.VISIBLE);
                    editMinCantidadIngresos.setVisibility(View.VISIBLE);
                    editMaxCantidadIngresos.setVisibility(View.VISIBLE);
                }else{
                    layoutCantidadIngresos.setVisibility(View.GONE);
                    editMinCantidadIngresos.setVisibility(View.GONE);
                    editMaxCantidadIngresos.setVisibility(View.GONE);
                }
            }
        });

        cbTipoIngresos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean esta_pulsado) {
                if(esta_pulsado){
                    layoutTipoIngresos.setVisibility(View.VISIBLE);
                    spTipoIngresos.setVisibility(View.VISIBLE);
                }else{
                    layoutTipoIngresos.setVisibility(View.GONE);
                    spTipoIngresos.setVisibility(View.GONE);
                }
            }
        });
    }


    public void aplicarFiltros(View view){
        Intent intent = new Intent(this, HomeActivity.class);

        Date minFechaGastos = null, maxFechaGastos = null;
        Double minCantidadGastos = 0.0, maxCantidadGastos = 0.0;
        String tipoGastos = "";
        Date minFechaIngresos = null, maxFechaIngresos = null;
        Double minCantidadIngresos = 0.0, maxCantidadIngresos = 0.0;
        String tipoIngresos = "";
        Boolean camposVacios[] = new Boolean[6];
        Arrays.fill(camposVacios, Boolean.FALSE);
        Boolean valoresInvalidos[] = new Boolean[4];
        Arrays.fill(valoresInvalidos, Boolean.FALSE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String minFechaG="", maxFechaG="", minFechaI="", maxFechaI="";

        int filtrosGastos = -1;
        int filtrosIngresos= -1;

        if(cbFechaGastos.isChecked()){
            if(editMinFechaGastos.getText().toString().equals("") || editMaxFechaGastos.getText().toString().equals("")){
                camposVacios[0] = true;
            }

            else{
                camposVacios[0] = false;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    minFechaGastos = dateFormat.parse(editMinFechaGastos.getText().toString());
                    maxFechaGastos = dateFormat.parse(editMaxFechaGastos.getText().toString());
                    minFechaG = sdf.format(minFechaGastos);
                    maxFechaG = sdf.format(maxFechaGastos);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(minFechaGastos.after(maxFechaGastos)){
                    valoresInvalidos[0] = true;
                }

                else{
                    valoresInvalidos[0] = false;
                    filtrosGastos = FILTRO_FECHA;
                }
            }
        }

        if(cbCantidadGastos.isChecked()){

            if(editMinCantidadGastos.getText().toString().equals("") || editMaxCantidadGastos.getText().toString().equals("")){
                camposVacios[1] = true;
            }

            else{
                camposVacios[1] = false;
                minCantidadGastos = Double.parseDouble(editMinCantidadGastos.getText().toString());
                maxCantidadGastos = Double.parseDouble(editMaxCantidadGastos.getText().toString());

                if(minCantidadGastos > maxCantidadGastos){
                    valoresInvalidos[1] = true;
                }

                else{
                    valoresInvalidos[1] = false;

                    if(filtrosGastos == SIN_FILTROS){
                        filtrosGastos = FILTRO_CANTIDAD;
                    }

                    else if(filtrosGastos == FILTRO_FECHA){
                        filtrosGastos = FILTRO_FECHA_CANTIDAD;
                    }
                }
            }

        }

        if(cbTipoGastos.isChecked()){
            tipoGastos = spTipoGastos.getSelectedItem().toString();

            if(tipoGastos.equals("")){
                camposVacios[2] = true;
            }

            else{
                camposVacios[2] = false;

                if(filtrosGastos == SIN_FILTROS){
                    filtrosGastos = FILTRO_TIPO;
                }

                else if(filtrosGastos == FILTRO_FECHA){
                    filtrosGastos = FILTRO_FECHA_TIPO;
                }

                else if(filtrosGastos == FILTRO_CANTIDAD){
                    filtrosGastos = FILTRO_CANTIDAD_TIPO;
                }

                else if(filtrosGastos == FILTRO_FECHA_CANTIDAD){
                    filtrosGastos = FILTRO_FECHA_CANTIDAD_TIPO;
                }

            }
        }

        if(cbFechaIngresos.isChecked()){
            if(editMinFechaIngresos.getText().toString().equals("") || editMaxFechaIngresos.getText().toString().equals("")){
                camposVacios[3] = true;
            }

            else{
                camposVacios[3] = false;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    minFechaIngresos = dateFormat.parse(editMinFechaIngresos.getText().toString());
                    maxFechaIngresos = dateFormat.parse(editMaxFechaIngresos.getText().toString());
                    minFechaI = sdf.format(minFechaIngresos);
                    maxFechaI = sdf.format(maxFechaIngresos);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(minFechaIngresos.after(maxFechaIngresos)){
                    valoresInvalidos[2] = true;
                }

                else{
                    valoresInvalidos[2] = false;
                    filtrosIngresos = FILTRO_FECHA;
                }
            }

        }

        if(cbCantidadIngresos.isChecked()){
            if(editMinCantidadIngresos.getText().toString().equals("") || editMaxCantidadIngresos.getText().toString().equals("")){
                camposVacios[4] = true;
            }

            else{
                camposVacios[4] = false;
                minCantidadIngresos = Double.parseDouble(editMinCantidadIngresos.getText().toString());
                maxCantidadIngresos = Double.parseDouble(editMaxCantidadIngresos.getText().toString());

                if(minCantidadIngresos > maxCantidadIngresos){
                    valoresInvalidos[3] = true;
                }

                else{
                    valoresInvalidos[3] = false;
                    if(filtrosIngresos == SIN_FILTROS){
                        filtrosIngresos = FILTRO_CANTIDAD;
                    }

                    else if(filtrosIngresos == FILTRO_FECHA){
                        filtrosIngresos = FILTRO_FECHA_CANTIDAD;
                    }
                }
            }
        }

        if(cbTipoIngresos.isChecked()){
            tipoIngresos = spTipoIngresos.getSelectedItem().toString();

            if(tipoIngresos.equals("")){
                camposVacios[5] = true;
            }

            else{
                camposVacios[5] = false;

                if(filtrosIngresos == SIN_FILTROS){
                    filtrosIngresos = FILTRO_TIPO;
                }

                else if(filtrosIngresos == FILTRO_FECHA){
                    filtrosIngresos = FILTRO_FECHA_TIPO;
                }

                else if(filtrosIngresos == FILTRO_CANTIDAD){
                    filtrosIngresos = FILTRO_CANTIDAD_TIPO;
                }

                else if(filtrosIngresos == FILTRO_FECHA_CANTIDAD){
                    filtrosIngresos = FILTRO_FECHA_CANTIDAD_TIPO;
                }

            }

        }

        Boolean vacio = false, invalido = false;

        for (int i = 0; i<camposVacios.length;i++){
            if(camposVacios[i]){
                vacio = true;
            }
        }

        for (int i = 0; i<valoresInvalidos.length;i++){
            if(valoresInvalidos[i]){
                invalido = true;
            }
        }

        if(vacio){
            Toast.makeText(FiltrosActivity.this, "Por favor cubre todos los campos seleccionados", Toast.LENGTH_SHORT).show();
        }

        else if (invalido) {
            Toast.makeText(FiltrosActivity.this, "Un valor mínimo no puede ser mayor que un valor máximo", Toast.LENGTH_SHORT).show();
        }

        else{

            intent.putExtra("filtrosGastos",filtrosGastos);
            intent.putExtra("minFechaGastos",minFechaG);
            intent.putExtra("maxFechaGastos",maxFechaG);
            intent.putExtra("minCantidadGastos",minCantidadGastos);
            intent.putExtra("maxCantidadGastos",maxCantidadGastos);
            intent.putExtra("tipoGastos", tipoGastos);

            intent.putExtra("filtrosIngresos",filtrosIngresos);
            intent.putExtra("minFechaIngresos",minFechaI);
            intent.putExtra("maxFechaIngresos",maxFechaI);
            intent.putExtra("minCantidadIngresos",minCantidadIngresos);
            intent.putExtra("maxCantidadIngresos",maxCantidadIngresos);
            intent.putExtra("tipoIngresos", tipoIngresos);

            startActivity(intent);
        }

    }


}