package org.esei.moneygest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.esei.moneygest.core.DatabaseHelper;
import org.esei.moneygest.core.UtilidadesSP;
import org.esei.moneygest.model.User;
import org.esei.moneygest.model.UserMapper;

public class RegistroActivity extends AppCompatActivity {

    EditText username, password, email;
    Button btnlogin, btnregister;
    DatabaseHelper DB;
    User user;
    UserMapper userMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

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



    public void ejecutar_login(View view){

        Intent i= new Intent(this, LoginActivity.class);

        startActivity(i);

    }

    public void registrar(View view){
        userMapper = new UserMapper(this);

        username = (EditText) findViewById(R.id.registro_nombre_usuario);
        email = (EditText) findViewById(R.id.registro_email_usuario);
        password = (EditText) findViewById(R.id.registro_pass_usuario);
        String usuario = username.getText().toString();
        String pass = password.getText().toString();
        String emailS = email.getText().toString();

        if(usuario.equals("") || pass.equals("") || emailS.equals("")){
            Toast.makeText(RegistroActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
        }

        else{
            Boolean checkuser = userMapper.checkUsernameExists(usuario);
            Boolean checkemail = userMapper.checkEmailExists(emailS);

            if(!checkuser){
                if(!checkemail){
                    user = new User(usuario, emailS, pass);

                    userMapper.insertUser(user);

                    UtilidadesSP utilidadesSP = new UtilidadesSP();
                    utilidadesSP.guardarPreferencias(username, password, RegistroActivity.this);

                    Toast.makeText(RegistroActivity.this, "Usuario registrado correctamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
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
