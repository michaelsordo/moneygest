package org.esei.moneygest;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.esei.moneygest.core.Utilidades;
import org.esei.moneygest.core.UtilidadesSP;
import org.esei.moneygest.model.Ingreso;
import org.esei.moneygest.model.IngresoMapper;

import java.util.ArrayList;

public class IngresosActivity extends AppCompatActivity {

    ListView listViewIngresos;
    ArrayList<String> listaMostrar;
    ArrayList<Ingreso> listaIngresos;
    IngresoMapper ingresoMapper;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos_general);

        listViewIngresos = (ListView) findViewById(R.id.listViewIngresos);

        UtilidadesSP utilidadesSP = new UtilidadesSP();
        username = utilidadesSP.cargarUsername(IngresosActivity.this);

        ingresoMapper = new IngresoMapper(this);
        listaIngresos = ingresoMapper.listarIngresos(username);

        Utilidades utilidades = new Utilidades();
        listaMostrar = utilidades.obtenerListaIngresos(listaIngresos);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaMostrar);
        listViewIngresos.setAdapter(adapter);
    }

    //MENU

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
                UtilidadesSP utilidadesSP = new UtilidadesSP();
                utilidadesSP.logout(IngresosActivity.this);
                Toast.makeText(IngresosActivity.this, "Sesión cerrada correctamente", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                toret=true;
                break;
        }

        return toret;
    }


    public void addIngreso(View view){

        Intent i= new Intent(this, RegistroIngresosActivity.class);
        startActivity(i);

    }


}
