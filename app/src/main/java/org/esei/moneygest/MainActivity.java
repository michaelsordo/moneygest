package org.esei.moneygest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin, btnregister;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


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

        Intent i= new Intent(this, registro.class);

        startActivity(i);

    }

    public void loguear(View view){
        DB = new DatabaseHelper(this);

        username = (EditText) findViewById(R.id.login_nombre_usuario);
        password = (EditText) findViewById(R.id.login_pass_usuario);
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if(user.equals("") || pass.equals("")){
            Toast.makeText(MainActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean checkuserpass = DB.checkUsernamePass(user, pass);
            if(checkuserpass){
                Toast.makeText(MainActivity.this, "Usuario logueado correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }

            else{
                Toast.makeText(MainActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
            }
        }

    }

}