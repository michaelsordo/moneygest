package org.esei.moneygest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class registro extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
    }

    public void ejecutar_login(View view){

        Intent i= new Intent(this, MainActivity.class);

        startActivity(i);

    }
}
