package org.esei.moneygest;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.esei.moneygest.model.Gasto;
import org.esei.moneygest.model.GastoMapper;

import java.util.ArrayList;

public class FiltrosActivity extends AppCompatActivity {

CheckBox cbCantidadGastos;
EditText etCantidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        cbCantidadGastos = findViewById(R.id.checkBox_cantidad_gastos);
        etCantidad = findViewById(R.id.cantidad);

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

    }
}