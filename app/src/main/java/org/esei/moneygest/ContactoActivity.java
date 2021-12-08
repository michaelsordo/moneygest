package org.esei.moneygest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.esei.moneygest.core.UtilidadesSP;
import org.esei.moneygest.model.Gasto;
import org.esei.moneygest.model.GastoMapper;
import java.util.ArrayList;

public class ContactoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacto);


    }
    //Menu de opciones

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){

        boolean toret=false;
        Intent intent;

        switch (item.getItemId()){

            case R.id.item0:
                intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("filtrosGastos",-1);
                intent.putExtra("minFechaGastos","");
                intent.putExtra("maxFechaGastos","");
                intent.putExtra("minCantidadGastos",0.0);
                intent.putExtra("maxCantidadGastos",0.0);
                intent.putExtra("tipoGastos", "");

                intent.putExtra("filtrosIngresos",-1);
                intent.putExtra("minFechaIngresos","");
                intent.putExtra("maxFechaIngresos","");
                intent.putExtra("minCantidadIngresos",0.0);
                intent.putExtra("maxCantidadIngresos",0.0);
                intent.putExtra("tipoIngresos", "");
                startActivity(intent);
                toret=true;
                break;

            case R.id.item1:
                intent = new Intent(getApplicationContext(), GastosActivity.class);
                startActivity(intent);
                toret=true;
                break;

            case R.id.item2:
                intent = new Intent(getApplicationContext(),IngresosActivity.class);
                startActivity(intent);
                toret=true;
                break;

            case R.id.item3:
                intent = new Intent(getApplicationContext(),AreaPersonalActivity.class);
                startActivity(intent);
                toret=true;
                break;

            case R.id.item4:
                intent = new Intent(getApplicationContext(),ContactoActivity.class);
                startActivity(intent);
                toret=true;
                break;

            case R.id.item5:
                UtilidadesSP utilidadesSP = new UtilidadesSP();
                utilidadesSP.logout(ContactoActivity.this);
                Toast.makeText(ContactoActivity.this, "Sesión cerrada correctamente", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                toret=true;
                break;
        }

        return toret;
    }

    //Control ciclo de vida

    @Override
    public void onStart(){
        super.onStart();
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    public void onDestroy(){
        super.onDestroy();
    }


}