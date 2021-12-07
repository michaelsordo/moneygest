package org.esei.moneygest;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class FiltrosActivity extends AppCompatActivity {

CheckBox cbCantidadGastos;
CheckBox cbCantidadIngresos;
EditText etCantidad;
EditText etCantidadIngresos;
CheckBox cbTipoGastos;
Spinner spTipoGastos;
CheckBox cbTipoIngresos;
Spinner spTipoIngresos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        cbCantidadGastos = findViewById(R.id.checkBox_cantidad_gastos);
        cbCantidadIngresos = findViewById(R.id.cb_cant_ingresos);
        etCantidad = findViewById(R.id.cantidad_gastos);
        etCantidadIngresos= findViewById(R.id.cantidad_ingresos);
        cbTipoGastos = findViewById(R.id.cb_tipo_gastos);
        spTipoGastos = findViewById(R.id.spinner_tipo_gastos);
        cbTipoIngresos = findViewById(R.id.cb_tipo_ingresos);
        spTipoIngresos = findViewById(R.id.spinner_tipo_ingresos);

        //Para spinner Gastos
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.categoria, android.R.layout.simple_spinner_item);
        spTipoGastos.setAdapter(adapter);

        //Para spinner Ingresos
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.categoria_ingresos, android.R.layout.simple_spinner_item);
        spTipoIngresos.setAdapter(adapter2);

        cbCantidadGastos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean esta_pulsado) {
                if(esta_pulsado){
                    etCantidad.setVisibility(View.VISIBLE);
                }else{
                    etCantidad.setVisibility(View.INVISIBLE);
                }
            }
        });


        cbCantidadIngresos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean esta_pulsado) {
                if(esta_pulsado){
                    etCantidadIngresos.setVisibility(View.VISIBLE);
                }else{
                    etCantidadIngresos.setVisibility(View.INVISIBLE);
                }
            }
        });

        cbTipoGastos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean esta_pulsado) {
                if(esta_pulsado){
                    spTipoGastos.setVisibility(View.VISIBLE);
                }else{
                    spTipoGastos.setVisibility(View.INVISIBLE);
                }
            }
        });

        cbTipoIngresos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean esta_pulsado) {
                if(esta_pulsado){
                    spTipoIngresos.setVisibility(View.VISIBLE);
                }else{
                    spTipoIngresos.setVisibility(View.INVISIBLE);
                }
            }
        });



    }
}