package org.esei.moneygest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.esei.moneygest.core.DatabaseHelper;
import org.esei.moneygest.core.UtilidadesSP;
import org.esei.moneygest.model.Gasto;
import org.esei.moneygest.model.GastoMapper;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    TextView username;

    ListView listViewGastos;
    ArrayList<String> listaMostrar;
    ArrayList<Gasto> listaGastos;
    GastoMapper gastoMapper;

    PieDataSet pieDS = new PieDataSet(null,null);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = (TextView) findViewById(R.id.info_user);
        UtilidadesSP utilidadesSP = new UtilidadesSP();
        utilidadesSP.cargarInfoUser(username, HomeActivity.this);

        PieChart pieChart = findViewById(R.id.PieChart);
        datosDB = new DatabaseHelper(this);
        sqlDB = DatabaseHelper.databaseHelper.getWritableDatabase();

        pieDS.setValues(getDataValues());
        pieDS.setLabel("Hola");
        pieDS.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDS.setValueTextColor(Color.BLUE);
        pieDS.setValueTextSize(16f);

        PieData pieData = new PieData(pieDS);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("desc Pie");
        pieChart.animate();

        pieDS.setFormLineWidth(4);



    }

    private ArrayList<PieEntry> getDataValues(){

        ArrayList<PieEntry> dataValues = new ArrayList<>();
        Cursor cursor = datosDB.getValues();

        for (int i=0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            dataValues.add(new PieEntry(cursor.getFloat(0),String.valueOf(cursor.getString(1))));
        }

        return dataValues;
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

}
