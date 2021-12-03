package org.esei.moneygest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AreaPersonalActivity extends AppCompatActivity {
    EditText editPass, editEmail;
    TextView username;
    DatabaseHelper DB;
    String[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_personal);

        DB = new DatabaseHelper(this);

        username = (TextView) findViewById(R.id.area_nombre_usuario);
        editPass = (EditText) findViewById(R.id.area_pass_usuario);
        editEmail = (EditText) findViewById(R.id.area_correo_usuario);

        UtilidadesSP utilidadesSP = new UtilidadesSP();
        utilidadesSP.cargar_info_user(username, AreaPersonalActivity.this);

        username.setText("diego");

        datos = DB.getUserData(username.getText().toString());

        editEmail.setText(datos[0]);
        editPass.setText(datos[1]);

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
                utilidadesSP.logout(AreaPersonalActivity.this);
                Toast.makeText(AreaPersonalActivity.this, "Sesión cerrada correctamente", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                toret=true;
                break;
        }

        return toret;
    }

    public void modificarCuenta(View view){

        DB = new DatabaseHelper(this);

        username = (TextView)  findViewById(R.id.area_nombre_usuario);
        editPass = (EditText) findViewById(R.id.area_pass_usuario);
        editEmail = (EditText) findViewById(R.id.area_correo_usuario);
        String user = username.getText().toString();
        String pass = editPass.getText().toString();
        String email = editEmail.getText().toString();
        
        EditText editUsername = null;
        editUsername.setText(user);

        UtilidadesSP utilidadesSP = new UtilidadesSP();
        utilidadesSP.guardar_preferencias(editUsername, editPass, AreaPersonalActivity.this);

        if(user.equals("") || pass.equals("") || email.equals("")){
            Toast.makeText(AreaPersonalActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{
                UtilidadesSP utilidadesSP2 = new UtilidadesSP();
                utilidadesSP2.guardar_preferencias(editUsername, editPass, AreaPersonalActivity.this);

                //Realizar Update aqui

                Toast.makeText(AreaPersonalActivity.this, "Usuario actualizado correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
        }

    }

}
