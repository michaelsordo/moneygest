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




public class RegistroGastoActivity extends Activity {

    EditText campoConcepto, campoCantidad, campoFecha;

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
        String cantidad = campoCantidad.getText().toString();
        String fecha = campoFecha.getText().toString();

        if(concepto.equals("") || cantidad.equals("") || fecha.equals("")){
            //Toast.makeText(RegistroGastoActvity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
        }

        else{

            //SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(utilidades.CAMPO_CONCEPTO,concepto);
            values.put(utilidades.CAMPO_CANTIDAD,cantidad);
            values.put(utilidades.CAMPO_FECHA,fecha);

            //Long idResultante = db.insert(utilidades.TABLA_GASTO,utilidades.CAMPO_ID,values);

            //Toast.makeText(getApplicationContext(),"Id Registro" + idResultante,Toast.LENGTH_SHORT).show();
        }

    }
}