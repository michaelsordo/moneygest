package org.esei.moneygest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.esei.moneygest.core.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin, btnregister;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.login_nombre_usuario);
        password = (EditText) findViewById(R.id.login_pass_usuario);

        UtilidadesSP utilidadesSP = new UtilidadesSP();
        utilidadesSP.cargar_preferencias(username, password, LoginActivity.this);

    }

    public void ejecutar_registro(View view){

        Intent i= new Intent(this, RegistroActivity.class);

        startActivity(i);

    }

    public void loguear(View view){
        DB = new DatabaseHelper(this);

        username = (EditText) findViewById(R.id.login_nombre_usuario);
        password = (EditText) findViewById(R.id.login_pass_usuario);
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if(user.equals("") || pass.equals("")){
            Toast.makeText(LoginActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean checkuserpass = DB.checkUsernamePass(user, pass);
            if(checkuserpass){
                UtilidadesSP utilidadesSP = new UtilidadesSP();
                utilidadesSP.guardar_preferencias(username, password, LoginActivity.this);
                Toast.makeText(LoginActivity.this, "Usuario logueado correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }

            else{
                Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
            }
        }

    }

}