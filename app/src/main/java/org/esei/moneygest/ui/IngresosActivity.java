package org.esei.moneygest.ui;

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

import org.esei.moneygest.R;
import org.esei.moneygest.core.Utilidades;
import org.esei.moneygest.core.UtilidadesSP;
import org.esei.moneygest.model.Ingreso;
import org.esei.moneygest.model.IngresoMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

    }

    //Control ciclo de vida

    @Override
    public void onStart(){
        super.onStart();
    }
    @Override
    public void onResume(){
        super.onResume();

        listViewIngresos = (ListView) findViewById(R.id.listViewIngresos);

        UtilidadesSP utilidadesSP = new UtilidadesSP();
        username = utilidadesSP.cargarUsername(IngresosActivity.this);

        ingresoMapper = new IngresoMapper(this);
        listaIngresos = ingresoMapper.listarIngresos(username);

        Utilidades utilidades = new Utilidades();
        listaMostrar = utilidades.obtenerListaIngresos(listaIngresos);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaMostrar);
        listViewIngresos.setAdapter(adapter);

        this.registerForContextMenu(listViewIngresos);

    }

    @Override
    public void onPause(){
        super.onPause();
    }

    public void onDestroy(){
        super.onDestroy();
    }


    //Menu de opciones

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Utilidades utilidades = new Utilidades();
        boolean toret=utilidades.desplegarMenu(item, IngresosActivity.this);
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
                        IngresosActivity.this.finish();
                    }
                });
                builder.setNegativeButton("NO", null);

                AlertDialog dialog = builder.create();
                dialog.show();

                break;
            case 2:
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = sdf.format(listaIngresos.get(pos).getFecha());

                Intent intent = new Intent(getApplicationContext(), RegistroIngresoActivity.class);
                intent.putExtra("id",listaIngresos.get(pos).getId());
                intent.putExtra("concepto",listaIngresos.get(pos).getConcepto());
                intent.putExtra("cantidad €", listaIngresos.get(pos).getCantidad());
                intent.putExtra("fecha", fecha);
                intent.putExtra("tipo", listaIngresos.get(pos).getTipo());
                startActivity(intent);
                break;


            case 3:
                break;

        }
        return super.onContextItemSelected(item);
    }


    public void onClick(View v) {
        //
    }



    public void addIngreso(View view){

        Intent intent = new Intent(this, RegistroIngresoActivity.class);

        intent.putExtra("id",-1);
        intent.putExtra("concepto","");
        intent.putExtra("cantidad", 0.00);
        intent.putExtra("fecha", new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()).toString());
        intent.putExtra("tipo", "");

        startActivity(intent);

    }


}
