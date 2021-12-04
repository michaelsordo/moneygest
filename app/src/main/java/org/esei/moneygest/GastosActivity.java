package org.esei.moneygest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.esei.moneygest.core.DatabaseHelper;
import org.esei.moneygest.core.UtilidadesSP;
import org.esei.moneygest.model.Gasto;

import java.util.ArrayList;

public class GastosActivity extends AppCompatActivity {

    //Para generar el listView

    ListView listViewGastos;
    ArrayList<String> listaInformación;
    ArrayList<Gasto> listaGastos;
    DatabaseHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_general);

        listViewGastos= (ListView) findViewById(R.id.listViewGastos);
        conn= new DatabaseHelper(this);

        consultarGastos();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformación);
        listViewGastos.setAdapter(adapter);

    }

    //metodo para consulta de gastos

    public void consultarGastos() {

        SQLiteDatabase db = conn.getReadableDatabase();
        Gasto gasto = null;

        listaGastos = new ArrayList<Gasto>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLA_GASTO,null);

        while (cursor.moveToNext()){
            gasto=new Gasto();
            gasto.setId(cursor.getInt(0));
            gasto.setConcepto(cursor.getString(1));
            gasto.setCantidad(cursor.getDouble(2));
            gasto.setTipo(cursor.getString(4));

            //faltaria la fecha pero no sé recuperarla
            //faltaria también el tipo de gasto

            listaGastos.add(gasto);

            //Se encarga de recorrer la lista de usuarios
            obtenerLista();


        }

    }

    private void obtenerLista() {
        listaInformación=new ArrayList<String>();

        for(int i=0;i<listaGastos.size();i++){
            listaInformación.add("Gasto número: " + listaGastos.get(i).getId()+ "\n"+ "Concepto: "+listaGastos.get(i).getConcepto()
                    +"\n"+ "Cantidad: "+ listaGastos.get(i).getCantidad() + "\n" +"Tipo Gasto "+listaGastos.get(i).getTipo());
        }
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
                utilidadesSP.logout(GastosActivity.this);
                Toast.makeText(GastosActivity.this, "Sesión cerrada correctamente", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                toret=true;
                break;
        }

        return toret;
    }


    public void add_gasto(View view){

        Intent i= new Intent(this, RegistroGastoActivity.class);
        startActivity(i);

    }

}
