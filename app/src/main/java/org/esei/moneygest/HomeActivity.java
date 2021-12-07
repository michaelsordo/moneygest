package org.esei.moneygest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.esei.moneygest.core.UtilidadesSP;
import org.esei.moneygest.model.Gasto;
import org.esei.moneygest.model.GastoMapper;
import org.esei.moneygest.model.Ingreso;
import org.esei.moneygest.model.IngresoMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    TextView username;
    ListView listViewGastos;
    ArrayList<String> listaMostrar;
    ArrayList<Gasto> listaGastos;
    ArrayList<Ingreso> listaIngresos;
    GastoMapper gastoMapper;
    IngresoMapper ingresoMapper;
    String user;
    Double totalGastos = 0.0, totalIngresos=0.0;
    PieEntry gastosEntry, ingresosEntry;

    PieDataSet pieDS = new PieDataSet(null,null);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = (TextView) findViewById(R.id.info_user);
        UtilidadesSP utilidadesSP = new UtilidadesSP();
        utilidadesSP.cargarInfoUser(username, HomeActivity.this);
        String user = utilidadesSP.cargarUsername( HomeActivity.this);

        gastoMapper = new GastoMapper(this);
        listaGastos = gastoMapper.listarGastos(user);

        ingresoMapper = new IngresoMapper(this);
        listaIngresos = ingresoMapper.listarIngresos(user);

        for(int i=0; i< listaGastos.size(); i++){
            totalGastos += listaGastos.get(i).getCantidad();
        }

        for(int i=0; i< listaIngresos.size(); i++){
            totalIngresos += listaIngresos.get(i).getCantidad();
        }

        PieChart pieChart = findViewById(R.id.PieChart);


        //pieDS.setValues();
        gastosEntry = new PieEntry( totalGastos.floatValue(), "Gastos" );
        ingresosEntry = new PieEntry( totalIngresos.floatValue(), "Ingresos" );
        ArrayList <PieEntry> pieEntryArrayList = new ArrayList<>();
        pieEntryArrayList.add(gastosEntry);
        pieEntryArrayList.add(ingresosEntry);

        pieDS.setValues(pieEntryArrayList);
        pieDS.setLabel("Gráfico Balance");
        pieDS.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDS.setValueTextColor(Color.BLUE);
        pieDS.setValueTextSize(16f);

        PieData pieData = new PieData(pieDS);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Balance Gastos/Ingresos");
        pieChart.animate();

        pieDS.setFormLineWidth(4);

    }



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
                intent = new Intent(getApplicationContext(),ContactoActivity.class);
                startActivity(intent);
                toret=true;
                break;

            case R.id.item5:
                UtilidadesSP utilidadesSP = new UtilidadesSP();
                utilidadesSP.logout(HomeActivity.this);
                Toast.makeText(HomeActivity.this, "Sesión cerrada correctamente", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                toret=true;
                break;
        }

        return toret;
    }

    //Para ver los filtros

    public void verFiltros(View view){

        Intent intent = new Intent(this, FiltrosActivity.class);
        startActivity(intent);

    }



}
