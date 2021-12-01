package org.esei.moneygest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin, btnregister;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cargar_preferencias();


        /*
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT);
                }
                else{
                    Boolean checuserpass = DB.checkUsernamePass(user, pass);
                    if(checuserpass){
                        Toast.makeText(MainActivity.this, "Usuario logueado correctamente", Toast.LENGTH_LONG);
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }

                    else{
                        Toast.makeText(MainActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT);
                    }
                }

            }
        });*/

        /*
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), registro.class);
                startActivity(intent);
            }
        });*/
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
                guardar_preferencias();
                Toast.makeText(LoginActivity.this, "Usuario logueado correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }

            else{
                Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void guardar_preferencias(){
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        username = (EditText) findViewById(R.id.login_nombre_usuario);
        password = (EditText) findViewById(R.id.login_pass_usuario);

        String usuario = username.getText().toString();
        String pass = password.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", usuario);
        editor.putString("pass", pass);

        editor.commit();
    }

    private void cargar_preferencias(){
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        username = (EditText) findViewById(R.id.login_nombre_usuario);
        password = (EditText) findViewById(R.id.login_pass_usuario);

        String usuario = preferences.getString("user", "");
        String pass = preferences.getString("pass", "");

        username.setText(usuario);
        password.setText(pass);
    }



}