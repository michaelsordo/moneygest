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

public class RegistroActivity extends AppCompatActivity {

    EditText username, password, email;
    Button btnlogin, btnregister;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        /*
        username = (EditText) findViewById(R.id.registro_nombre_usuario);
        email = (EditText) findViewById(R.id.registro_email_usuario);
        password = (EditText) findViewById(R.id.registro_pass_usuario);
        btnlogin = (Button) findViewById(R.id.boton_login_registro);

        btnregister = (Button) findViewById(R.id.boton_registro);
        */


        /*
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });*/

        /*
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String emailS = email.getText().toString();

                if(user.equals("") || pass.equals("") || email.equals("")){
                    Toast.makeText(registro.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT);
                }
                else{
                    Boolean checkuser = DB.checkUsernameExists(user);
                    Boolean checkemail = DB.checkEmailExists(emailS);

                    if(!checkuser){
                        if(!checkemail){
                            Boolean insert = DB.insertData(user, pass, emailS);
                            if(insert){
                                Toast.makeText(registro.this, "Usuario registrado correctamente", Toast.LENGTH_LONG);
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }

                            else{
                                Toast.makeText(registro.this, "Error en el registro", Toast.LENGTH_SHORT);
                            }
                        }

                        else{
                            Toast.makeText(registro.this, "El correo ya está en uso", Toast.LENGTH_SHORT);
                        }
                    }

                    else{
                        Toast.makeText(registro.this, "El nombre de usuario ya está en uso", Toast.LENGTH_SHORT);
                    }

                }
            }
        });*/
    }


    public void ejecutar_login(View view){

        Intent i= new Intent(this, LoginActivity.class);

        startActivity(i);

    }

    public void registrar(View view){
        DB = new DatabaseHelper(this);

        username = (EditText) findViewById(R.id.registro_nombre_usuario);
        email = (EditText) findViewById(R.id.registro_email_usuario);
        password = (EditText) findViewById(R.id.registro_pass_usuario);
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String emailS = email.getText().toString();

        if(user.equals("") || pass.equals("") || emailS.equals("")){
            Toast.makeText(RegistroActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
        }

        else{
            Boolean checkuser = DB.checkUsernameExists(user);
            Boolean checkemail = DB.checkEmailExists(emailS);

            if(!checkuser){
                if(!checkemail){
                    Boolean insert = DB.insertData(user, pass, emailS);
                    if(insert){
                        Toast.makeText(RegistroActivity.this, "Usuario registrado correctamente", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }

                    else{
                        Toast.makeText(RegistroActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                    }
                }

                else{
                    Toast.makeText(RegistroActivity.this, "El correo ya está en uso", Toast.LENGTH_SHORT).show();
                }
            }

            else{
                Toast.makeText(RegistroActivity.this, "El nombre de usuario ya está en uso", Toast.LENGTH_SHORT).show();
            }

        }

    }

}
