package org.esei.moneygest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

        //Para el menu contextual

        this.registerForContextMenu(listViewIngresos);

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
                utilidadesSP.logout(IngresosActivity.this);
                Toast.makeText(IngresosActivity.this, "Sesión cerrada correctamente", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                toret=true;
                break;
        }

        return toret;
    }

    //Para el menu contextual

    //Menu contextual

    public void onCreateContextMenu (ContextMenu menu , View v, ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);

        menu.setHeaderTitle ("Operación sobre gastos");
        menu.setHeaderIcon(R.drawable.ic_launcher_foreground);
        menu.add(1,1,1, "Borrar");
        menu.add(1,2,1, "Modificar");
        menu.add(1,3,1, "Cancelar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        int pos = ( (AdapterView.AdapterContextMenuInfo) item.getMenuInfo() ).position;
        switch(item.getItemId()){
            case 1:
                Toast.makeText(IngresosActivity.this, "Ha escogido borrar " + pos + listaIngresos.get(pos).getConcepto(),Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Borrado");
                builder.setMessage("¿Estás seguro de que deseas eliminar el ingreso?");
                builder.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ingresoMapper.deleteIngreso(listaIngresos.get(pos).getId());
                        Toast.makeText(IngresosActivity.this, "Ingreso eliminado correctamente.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),IngresosActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("NO", null);

                AlertDialog dialog = builder.create();
                dialog.show();

                break;
            case 2:
                Toast.makeText(IngresosActivity.this, "Ha escogido modificar " + pos + listaIngresos.get(pos).getConcepto(),Toast.LENGTH_SHORT).show();
                break;


            case 3:
                Toast.makeText(IngresosActivity.this, "Ha escogido cancelar " + pos + listaIngresos.get(pos).getConcepto(),Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onContextItemSelected(item);
    }


    public void onClick(View v) {
        //
    }



    public void addIngreso(View view){

        Intent i= new Intent(this, RegistroIngresosActivity.class);
        startActivity(i);

    }


}
