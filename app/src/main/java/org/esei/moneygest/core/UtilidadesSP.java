package org.esei.moneygest.core;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UtilidadesSP {

    public void guardarPreferencias(EditText username, EditText password, AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences preferences2 = activity.getSharedPreferences("session", Context.MODE_PRIVATE);

        String usuario = username.getText().toString();
        String pass = password.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", usuario);
        editor.putString("pass", pass);
        editor.commit();

        SharedPreferences.Editor editor2 = preferences2.edit();
        editor2.putString("user", usuario);
        editor2.putString("pass", pass);
        editor2.commit();
    }

    public void updatePreferencias(String usuario, EditText password, AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences preferences2 = activity.getSharedPreferences("session", Context.MODE_PRIVATE);

        String pass = password.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", usuario);
        editor.putString("pass", pass);
        editor.commit();

        SharedPreferences.Editor editor2 = preferences2.edit();
        editor2.putString("user", usuario);
        editor2.putString("pass", pass);
        editor2.commit();
    }

    public void cargarPreferencias(EditText username, EditText password, AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String usuario = preferences.getString("user", "");
        String pass = preferences.getString("pass", "");

        username.setText(usuario);
        password.setText(pass);
    }

    public void cargarInfoUser(TextView username, AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("session", Context.MODE_PRIVATE);

        String usuario = preferences.getString("user", "");
        username.setText(usuario);

    }

    public String cargarUsername(AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("session", Context.MODE_PRIVATE);

        return preferences.getString("user", "");

    }

    public  void logout(AppCompatActivity activity){
        SharedPreferences sharedpreferences = activity.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

    //metodo para guardar preferences tabla gasto

    public void guardarGastos(EditText registro_concepto_gasto , EditText registro_cantidad_gasto, EditText registro_fecha_gasto, AppCompatActivity activity){
        SharedPreferences preferences3 = activity.getSharedPreferences("datos_gasto", Context.MODE_PRIVATE);

        String concept = registro_concepto_gasto.getText().toString();
        Double cantidad=null;
        String cantidad_to_string = String.valueOf(cantidad);
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        String strDate = dateFormat.format(dateFormat);

        SharedPreferences.Editor editor = preferences3.edit();
        editor.putString("registro", concept);
        editor.putString("cantidad", cantidad_to_string);
        editor.putString("fecha",strDate);
        editor.commit();

    }


}
