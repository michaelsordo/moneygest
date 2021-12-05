package org.esei.moneygest;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.esei.moneygest.core.Utilidades;
import org.esei.moneygest.core.UtilidadesSP;
import org.esei.moneygest.model.Gasto;
import org.esei.moneygest.model.GastoMapper;

import java.util.ArrayList;

public class GastosActivity extends AppCompatActivity {

    ListView listViewGastos;
    ArrayList<String> listaMostrar;
    ArrayList<Gasto> listaGastos;
    GastoMapper gastoMapper;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_general);

        listViewGastos= (ListView) findViewById(R.id.listViewGastos);


        UtilidadesSP utilidadesSP = new UtilidadesSP();
        username = utilidadesSP.cargarUsername(GastosActivity.this);

        gastoMapper = new GastoMapper(this);
        listaGastos = gastoMapper.listarGastos(username);

        Utilidades utilidades = new Utilidades();
        listaMostrar = utilidades.obtenerListaGastos(listaGastos);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaMostrar);
        listViewGastos.setAdapter(adapter);

        //Para el menu contextual

        this.registerForContextMenu(listViewGastos);

    }

    private void obtenerLista() {
        listaMostrar =new ArrayList<String>();

        for(int i=0;i<listaGastos.size();i++){
            listaMostrar.add("Gasto número: " + listaGastos.get(i).getId()+ "\n"+ "Concepto: "+listaGastos.get(i).getConcepto()
                    +"\n"+ "Cantidad: "+ listaGastos.get(i).getCantidad() + "\n" +"Tipo Gasto "+listaGastos.get(i).getTipo());
        }
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

    //Menu contextual

    public void onCreateContextMenu (ContextMenu menu ,View v, ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);

        menu.setHeaderTitle ("Operación sobre gastos");
        menu.setHeaderIcon(R.drawable.ic_launcher_foreground);
        menu.add(1,1,1, "Borrar");
        menu.add(1,2,1, "Modificar");
        menu.add(1,3,1, "Cancelar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case 1:
                Toast.makeText(GastosActivity.this, "Ha escogido borrar",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(GastosActivity.this, "Ha escogido modificar",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(GastosActivity.this, "Ha escogido cancelar",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onContextItemSelected(item);
    }

    /*private ArrayList<String> getData(){
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<list.size();i++){
            list.add("Gasto" + (i + 1));
        }
        return list;
    }*/



    public void addGasto(View view){

        Intent i= new Intent(this, RegistroGastoActivity.class);
        startActivity(i);

    }

}
