package org.esei.moneygest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GastosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_general);
    }


    public void add_gasto(View view){

        Intent i= new Intent(this, RegistroGastoActivity.class);
        startActivity(i);

    }

}
