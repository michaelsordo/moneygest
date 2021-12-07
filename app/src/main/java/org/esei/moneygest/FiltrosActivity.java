package org.esei.moneygest;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FiltrosActivity extends AppCompatActivity {

CheckBox cbCantidadGastos;
CheckBox cbCantidadIngresos;
EditText etCantidad;
EditText etCantidadIngresos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        cbCantidadGastos = findViewById(R.id.checkBox_cantidad_gastos);
        cbCantidadIngresos = findViewById(R.id.cb_cant_ingresos);
        etCantidad = findViewById(R.id.cantidad_gastos);
        etCantidadIngresos= findViewById(R.id.cantidad_ingresos);


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


    }
}