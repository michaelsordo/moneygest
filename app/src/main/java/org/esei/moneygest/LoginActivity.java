package org.esei.moneygest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.esei.moneygest.core.UtilidadesSP;
import org.esei.moneygest.model.UserMapper;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    UserMapper userMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.login_nombre_usuario);
        password = (EditText) findViewById(R.id.login_pass_usuario);

        UtilidadesSP utilidadesSP = new UtilidadesSP();
        utilidadesSP.cargarPreferencias(username, password, LoginActivity.this);

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


    public void ejecutar_registro(View view){

        Intent i= new Intent(this, RegistroActivity.class);

        startActivity(i);

    }

    public void loguear(View view){
        userMapper = new UserMapper(this);

        username = (EditText) findViewById(R.id.login_nombre_usuario);
        password = (EditText) findViewById(R.id.login_pass_usuario);
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if(user.equals("") || pass.equals("")){
            Toast.makeText(LoginActivity.this, "Por favor cubre todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean checkuserpass = userMapper.checkUsernamePass(user, pass);
            if(checkuserpass){
                UtilidadesSP utilidadesSP = new UtilidadesSP();
                utilidadesSP.guardarPreferencias(username, password, LoginActivity.this);
                Toast.makeText(LoginActivity.this, "Usuario logueado correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("filtrosGastos",-1);
                intent.putExtra("minFechaGastos","");
                intent.putExtra("maxFechaGastos","");
                intent.putExtra("minCantidadGastos",0.0);
                intent.putExtra("maxCantidadGastos",0.0);
                intent.putExtra("tipoGastos", "");

                intent.putExtra("filtrosIngresos",-1);
                intent.putExtra("minFechaIngresos","");
                intent.putExtra("maxFechaIngresos","");
                intent.putExtra("minCantidadIngresos",0.0);
                intent.putExtra("maxCantidadIngresos",0.0);
                intent.putExtra("tipoIngresos", "");
                startActivity(intent);
            }

            else{
                Toast.makeText(LoginActivity.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
            }
        }

    }

}