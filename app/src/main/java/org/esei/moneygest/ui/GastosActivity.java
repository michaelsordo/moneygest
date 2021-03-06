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
import org.esei.moneygest.model.Gasto;
import org.esei.moneygest.model.GastoMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

    }

    //Control ciclo de vida

    @Override
    public void onStart(){
        super.onStart();
    }
    @Override
    public void onResume(){
        super.onResume();

        listViewGastos= (ListView) findViewById(R.id.listViewGastos);

        UtilidadesSP utilidadesSP = new UtilidadesSP();
        username = utilidadesSP.cargarUsername(GastosActivity.this);

        gastoMapper = new GastoMapper(this);
        listaGastos = gastoMapper.listarGastos(username);

        Utilidades utilidades = new Utilidades();
        listaMostrar = utilidades.obtenerListaGastos(listaGastos);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaMostrar);
        listViewGastos.setAdapter(adapter);

        this.registerForContextMenu(listViewGastos);

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
        boolean toret=utilidades.desplegarMenu(item, GastosActivity.this);
        return toret;
    }

    //Menu contextual

    public void onCreateContextMenu (ContextMenu menu ,View v, ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);

        menu.setHeaderTitle ("Operaci??n sobre gastos");
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
                builder.setMessage("??Est??s seguro de que deseas eliminar el gasto?");
                builder.setPositiveButton("S??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gastoMapper.deleteGasto(listaGastos.get(pos).getId());
                        Toast.makeText(GastosActivity.this, "Gasto eliminado correctamente.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),GastosActivity.class);
                        startActivity(intent);
                        GastosActivity.this.finish();
                    }
                });
                builder.setNegativeButton("NO", null);

                AlertDialog dialog = builder.create();
                dialog.show();

                break;
            case 2:
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = sdf.format(listaGastos.get(pos).getFecha());

                Intent intent = new Intent(getApplicationContext(), RegistroGastoActivity.class);
                intent.putExtra("id",listaGastos.get(pos).getId());
                intent.putExtra("concepto",listaGastos.get(pos).getConcepto());
                intent.putExtra("cantidad", listaGastos.get(pos).getCantidad());
                intent.putExtra("fecha", fecha);
                intent.putExtra("tipo", listaGastos.get(pos).getTipo());
                startActivity(intent);

                break;

            case 3:
                break;

        }
        return super.onContextItemSelected(item);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    public void addGasto(View view){

        Intent intent = new Intent(this, RegistroGastoActivity.class);

        intent.putExtra("id",-1);
        intent.putExtra("concepto","");
        intent.putExtra("cantidad", 0.00);
        intent.putExtra("fecha", new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()).toString());
        intent.putExtra("tipo", "");

        startActivity(intent);


    }

}
