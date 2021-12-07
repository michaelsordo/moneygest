package org.esei.moneygest;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FiltrosActivity extends AppCompatActivity {

CheckBox cbFechaGastos, cbFechaIngresos, cbCantidadGastos, cbCantidadIngresos, cbTipoGastos, cbTipoIngresos;
EditText editMinFechaGastos, editMaxFechaGastos, editMinFechaIngresos, editMaxFechaIngresos;
EditText editMinCantidadGastos, editMaxCantidadGastos, editMinCantidadIngresos, editMaxCantidadIngresos;
Spinner spTipoGastos, spTipoIngresos;
LinearLayout layoutFechaGastos, layoutFechaIngresos;
LinearLayout layoutCantidadGastos, layoutCantidadIngresos;
TextView tvCantidadGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        layoutFechaGastos = findViewById(R.id.layout_fecha_gastos);
        layoutFechaIngresos = findViewById(R.id.layout_fecha_ingresos);
        layoutCantidadGastos = findViewById(R.id.layout_cantidad_gastos);
        layoutCantidadIngresos = findViewById(R.id.layout_cantidad_ingresos);

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
                    spTipoGastos.setVisibility(View.VISIBLE);
                }else{
                    spTipoGastos.setVisibility(View.INVISIBLE);
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
                    spTipoIngresos.setVisibility(View.VISIBLE);
                }else{
                    spTipoIngresos.setVisibility(View.INVISIBLE);
                }
            }
        });



    }
}