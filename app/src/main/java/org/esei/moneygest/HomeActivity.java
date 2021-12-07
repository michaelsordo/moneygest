package org.esei.moneygest;

import static org.esei.moneygest.FiltrosActivity.SIN_FILTROS;
import static org.esei.moneygest.FiltrosActivity.FILTRO_FECHA;
import static org.esei.moneygest.FiltrosActivity.FILTRO_CANTIDAD;
import static org.esei.moneygest.FiltrosActivity.FILTRO_TIPO;
import static org.esei.moneygest.FiltrosActivity.FILTRO_FECHA_CANTIDAD;
import static org.esei.moneygest.FiltrosActivity.FILTRO_FECHA_TIPO;
import static org.esei.moneygest.FiltrosActivity.FILTRO_CANTIDAD_TIPO;
import static org.esei.moneygest.FiltrosActivity.FILTRO_FECHA_CANTIDAD_TIPO;

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

import java.text.ParseException;
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
        user = utilidadesSP.cargarUsername( HomeActivity.this);

        listaGastos = listarGastos();
        listaIngresos = listarIngresos();

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

    public ArrayList<Gasto> listarGastos(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Gasto> toRet = new ArrayList<>();
        final Intent datosEnviados = this.getIntent();

        final int filtrosGastos = datosEnviados.getExtras().getInt( "filtrosGastos", -1 );
        final String minFechaGastos = datosEnviados.getExtras().getString( "minFechaGastos", new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()).toString());
        final String maxFechaGastos = datosEnviados.getExtras().getString( "maxFechaGastos", new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()).toString());
        final Double minCantidadGastos = datosEnviados.getExtras().getDouble( "minCantidadGastos", 0.00);
        final Double maxCantidadGastos = datosEnviados.getExtras().getDouble( "minCantidadGastos", 0.00);
        final String tipoGastos = datosEnviados.getExtras().getString( "tipoGastos", "" );

        gastoMapper = new GastoMapper(this);
        ingresoMapper = new IngresoMapper(this);

        if(filtrosGastos == SIN_FILTROS){
            toRet = gastoMapper.listarGastos(user);
        }
        else if(filtrosGastos == FILTRO_FECHA){
            Date minFecha = null;
            Date maxFecha = null;

            try {
                minFecha = dateFormat.parse(minFechaGastos);
                maxFecha = dateFormat.parse(maxFechaGastos);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            toRet = gastoMapper.listarGastosFecha(user,minFecha,maxFecha);
        }
        else if(filtrosGastos == FILTRO_CANTIDAD){
            toRet = gastoMapper.listarGastosCantidad(user,minCantidadGastos,maxCantidadGastos);
        }
        else if(filtrosGastos == FILTRO_TIPO){
            toRet = gastoMapper.listarGastosTipo(user, tipoGastos);
        }
        else if(filtrosGastos == FILTRO_FECHA_CANTIDAD){
            Date minFecha = null;
            Date maxFecha = null;

            try {
                minFecha = dateFormat.parse(minFechaGastos);
                maxFecha = dateFormat.parse(maxFechaGastos);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            toRet = gastoMapper.listarGastosFechaCantidad(user,minFecha,maxFecha, minCantidadGastos, maxCantidadGastos);
        }
        else if(filtrosGastos == FILTRO_FECHA_TIPO){
            Date minFecha = null;
            Date maxFecha = null;

            try {
                minFecha = dateFormat.parse(minFechaGastos);
                maxFecha = dateFormat.parse(maxFechaGastos);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            toRet = gastoMapper.listarGastosFechaTipo(user,minFecha,maxFecha, tipoGastos);
        }
        else if(filtrosGastos == FILTRO_CANTIDAD_TIPO){
            toRet = gastoMapper.listarGastosCantidadTipo(user,minCantidadGastos,maxCantidadGastos, tipoGastos);
        }
        else{
            Date minFecha = null;
            Date maxFecha = null;

            try {
                minFecha = dateFormat.parse(minFechaGastos);
                maxFecha = dateFormat.parse(maxFechaGastos);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            toRet = gastoMapper.listarGastosFechaCantidadTipo(user,minFecha,maxFecha, minCantidadGastos, maxCantidadGastos, tipoGastos);
        }

        return toRet;
    }

    public ArrayList<Ingreso> listarIngresos(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList <Ingreso> toRet = new ArrayList<>();
        final Intent datosEnviados = this.getIntent();

        final int filtrosIngresos = datosEnviados.getExtras().getInt( "filtrosIngresos", -1 );
        final String minFechaIngresos = datosEnviados.getExtras().getString( "minFechaIngresos", new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()).toString());
        final String maxFechaIngresos = datosEnviados.getExtras().getString( "maxFechaIngresos", new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()).toString());
        final Double minCantidadIngresos = datosEnviados.getExtras().getDouble( "minCantidadIngresos", 0.00);
        final Double maxCantidadIngresos = datosEnviados.getExtras().getDouble( "minCantidadIngresos", 0.00);
        final String tipoIngresos = datosEnviados.getExtras().getString( "tipoIngresos", "" );

        ingresoMapper = new IngresoMapper(this);

        if(filtrosIngresos == SIN_FILTROS){
            toRet = ingresoMapper.listarIngresos(user);
        }
        else if(filtrosIngresos == FILTRO_FECHA){
            Date minFecha = null;
            Date maxFecha = null;

            try {
                minFecha = dateFormat.parse(minFechaIngresos);
                maxFecha = dateFormat.parse(maxFechaIngresos);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            toRet = ingresoMapper.listarIngresosFecha(user,minFecha,maxFecha);
        }
        else if(filtrosIngresos == FILTRO_CANTIDAD){
            toRet = ingresoMapper.listarIngresosCantidad(user,minCantidadIngresos,maxCantidadIngresos);
        }
        else if(filtrosIngresos == FILTRO_TIPO){
            toRet = ingresoMapper.listarIngresosTipo(user, tipoIngresos);
        }
        else if(filtrosIngresos == FILTRO_FECHA_CANTIDAD){
            Date minFecha = null;
            Date maxFecha = null;

            try {
                minFecha = dateFormat.parse(minFechaIngresos);
                maxFecha = dateFormat.parse(maxFechaIngresos);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            toRet = ingresoMapper.listarIngresosFechaCantidad(user,minFecha,maxFecha, minCantidadIngresos, maxCantidadIngresos);
        }
        else if(filtrosIngresos == FILTRO_FECHA_TIPO){
            Date minFecha = null;
            Date maxFecha = null;

            try {
                minFecha = dateFormat.parse(minFechaIngresos);
                maxFecha = dateFormat.parse(maxFechaIngresos);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            toRet = ingresoMapper.listarIngresosFechaTipo(user,minFecha,maxFecha, tipoIngresos);
        }
        else if(filtrosIngresos == FILTRO_CANTIDAD_TIPO){
            toRet = ingresoMapper.listarIngresosCantidadTipo(user,minCantidadIngresos,maxCantidadIngresos, tipoIngresos);
        }
        else{
            Date minFecha = null;
            Date maxFecha = null;

            try {
                minFecha = dateFormat.parse(minFechaIngresos);
                maxFecha = dateFormat.parse(maxFechaIngresos);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            toRet = ingresoMapper.listarIngresosFechaCantidadTipo(user,minFecha,maxFecha, minCantidadIngresos, maxCantidadIngresos, tipoIngresos);
        }

        return toRet;
    }



}
