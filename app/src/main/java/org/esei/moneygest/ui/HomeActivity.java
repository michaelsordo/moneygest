package org.esei.moneygest.ui;

import static org.esei.moneygest.ui.FiltrosActivity.SIN_FILTROS;
import static org.esei.moneygest.ui.FiltrosActivity.FILTRO_FECHA;
import static org.esei.moneygest.ui.FiltrosActivity.FILTRO_CANTIDAD;
import static org.esei.moneygest.ui.FiltrosActivity.FILTRO_TIPO;
import static org.esei.moneygest.ui.FiltrosActivity.FILTRO_FECHA_CANTIDAD;
import static org.esei.moneygest.ui.FiltrosActivity.FILTRO_FECHA_TIPO;
import static org.esei.moneygest.ui.FiltrosActivity.FILTRO_CANTIDAD_TIPO;
import static org.esei.moneygest.ui.FiltrosActivity.FILTRO_FECHA_CANTIDAD_TIPO;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.esei.moneygest.R;
import org.esei.moneygest.core.Utilidades;
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
        new PieEntry(3,"");
        ArrayList <PieEntry> pieEntryArrayList = new ArrayList<>();
        pieEntryArrayList.add(gastosEntry);
        pieEntryArrayList.add(ingresosEntry);

        pieDS.setValues(pieEntryArrayList);
        //pieDS.setLabel("Gráfico Balance");
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




    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Utilidades utilidades = new Utilidades();
        boolean toret=utilidades.desplegarMenu(item, HomeActivity.this);
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
        Date minFecha, maxFecha;

        final int filtrosGastos = datosEnviados.getExtras().getInt( "filtrosGastos", -1 );
        final String minFechaGastos = datosEnviados.getExtras().getString( "minFechaGastos", "");
        final String maxFechaGastos = datosEnviados.getExtras().getString( "maxFechaGastos", "");
        final Double minCantidadGastos = datosEnviados.getExtras().getDouble( "minCantidadGastos", 0.00);
        final Double maxCantidadGastos = datosEnviados.getExtras().getDouble( "maxCantidadGastos", 0.00);
        final String tipoGastos = datosEnviados.getExtras().getString( "tipoGastos", "" );

        gastoMapper = new GastoMapper(this);

        switch (filtrosGastos){

            case SIN_FILTROS:
                toRet = gastoMapper.listarGastos(user);
                break;

            case FILTRO_FECHA:
                minFecha = null;
                maxFecha = null;

                try {
                    minFecha = dateFormat.parse(minFechaGastos);
                    maxFecha = dateFormat.parse(maxFechaGastos);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                toRet = gastoMapper.listarGastosFecha(user,minFecha,maxFecha);
                break;

            case FILTRO_CANTIDAD:
                toRet = gastoMapper.listarGastosCantidad(user,minCantidadGastos,maxCantidadGastos);
                break;

            case FILTRO_TIPO:
                toRet = gastoMapper.listarGastosTipo(user, tipoGastos);
                break;

            case FILTRO_FECHA_CANTIDAD:
                minFecha = null;
                maxFecha = null;

                try {
                    minFecha = dateFormat.parse(minFechaGastos);
                    maxFecha = dateFormat.parse(maxFechaGastos);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                toRet = gastoMapper.listarGastosFechaCantidad(user,minFecha,maxFecha, minCantidadGastos, maxCantidadGastos);
                break;

            case FILTRO_FECHA_TIPO:
                minFecha = null;
                maxFecha = null;

                try {
                    minFecha = dateFormat.parse(minFechaGastos);
                    maxFecha = dateFormat.parse(maxFechaGastos);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                toRet = gastoMapper.listarGastosFechaTipo(user,minFecha,maxFecha, tipoGastos);
                break;

            case FILTRO_CANTIDAD_TIPO:
                toRet = gastoMapper.listarGastosCantidadTipo(user,minCantidadGastos,maxCantidadGastos, tipoGastos);
                break;

            case FILTRO_FECHA_CANTIDAD_TIPO:
                minFecha = null;
                maxFecha = null;

                try {
                    minFecha = dateFormat.parse(minFechaGastos);
                    maxFecha = dateFormat.parse(maxFechaGastos);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                toRet = gastoMapper.listarGastosFechaCantidadTipo(user,minFecha,maxFecha, minCantidadGastos, maxCantidadGastos, tipoGastos);
                break;
        }
        return toRet;
    }

    public ArrayList<Ingreso> listarIngresos(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList <Ingreso> toRet = new ArrayList<>();
        final Intent datosEnviados = this.getIntent();
        Date minFecha, maxFecha;

        final int filtrosIngresos = datosEnviados.getExtras().getInt( "filtrosIngresos", -1 );
        final String minFechaIngresos = datosEnviados.getExtras().getString( "minFechaIngresos", new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()).toString());
        final String maxFechaIngresos = datosEnviados.getExtras().getString( "maxFechaIngresos", new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()).toString());
        final Double minCantidadIngresos = datosEnviados.getExtras().getDouble( "minCantidadIngresos", 0.00);
        final Double maxCantidadIngresos = datosEnviados.getExtras().getDouble( "maxCantidadIngresos", 0.00);
        final String tipoIngresos = datosEnviados.getExtras().getString( "tipoIngresos", "" );

        ingresoMapper = new IngresoMapper(this);

        switch (filtrosIngresos){

            case SIN_FILTROS:
                toRet = ingresoMapper.listarIngresos(user);
                break;

            case FILTRO_FECHA:
                minFecha = null;
                maxFecha = null;

                try {
                    minFecha = dateFormat.parse(minFechaIngresos);
                    maxFecha = dateFormat.parse(maxFechaIngresos);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                toRet = ingresoMapper.listarIngresosFecha(user,minFecha,maxFecha);
                break;

            case FILTRO_CANTIDAD:
                toRet = ingresoMapper.listarIngresosCantidad(user,minCantidadIngresos,maxCantidadIngresos);
                break;

            case FILTRO_TIPO:
                toRet = ingresoMapper.listarIngresosTipo(user, tipoIngresos);
                break;

            case FILTRO_FECHA_CANTIDAD:
                minFecha = null;
                maxFecha = null;

                try {
                    minFecha = dateFormat.parse(minFechaIngresos);
                    maxFecha = dateFormat.parse(maxFechaIngresos);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                toRet = ingresoMapper.listarIngresosFechaCantidad(user,minFecha,maxFecha, minCantidadIngresos, maxCantidadIngresos);
                break;

            case FILTRO_FECHA_TIPO:
                minFecha = null;
                maxFecha = null;

                try {
                    minFecha = dateFormat.parse(minFechaIngresos);
                    maxFecha = dateFormat.parse(maxFechaIngresos);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                toRet = ingresoMapper.listarIngresosFechaTipo(user,minFecha,maxFecha, tipoIngresos);
                break;

            case FILTRO_CANTIDAD_TIPO:
                toRet = ingresoMapper.listarIngresosCantidadTipo(user,minCantidadIngresos,maxCantidadIngresos, tipoIngresos);
                break;

            case FILTRO_FECHA_CANTIDAD_TIPO:
                minFecha = null;
                maxFecha = null;

                try {
                    minFecha = dateFormat.parse(minFechaIngresos);
                    maxFecha = dateFormat.parse(maxFechaIngresos);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                toRet = ingresoMapper.listarIngresosFechaCantidadTipo(user,minFecha,maxFecha, minCantidadIngresos, maxCantidadIngresos, tipoIngresos);
                break;
        }
        return toRet;
    }



}
