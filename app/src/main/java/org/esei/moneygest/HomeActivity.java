package org.esei.moneygest;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){

        boolean toret=false;

        switch (item.getItemId()){
            case R.id.item1:
                Intent intent = new Intent(getApplicationContext(), GastosActivity.class);
                startActivity(intent);
                toret=true;
                break;

            case R.id.item2:
                Intent intent2 = new Intent(getApplicationContext(),IngresosActivity.class);
                startActivity(intent2);
                toret=true;
                break;

            case R.id.item3:
                Intent intent3 = new Intent(getApplicationContext(),AreaPersonalActivity.class);
                startActivity(intent3);
                toret=true;
                break;
        }

        return toret;
    }

}
