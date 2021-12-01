package org.esei.moneygest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.TextView;

public class UtilidadesSP {

    public void guardar_preferencias(EditText username, EditText password, AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String usuario = username.getText().toString();
        String pass = password.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", usuario);
        editor.putString("pass", pass);

        editor.commit();
    }

    public void cargar_preferencias(EditText username, EditText password, AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String usuario = preferences.getString("user", "");
        String pass = preferences.getString("pass", "");

        username.setText(usuario);
        password.setText(pass);
    }

    public void cargar_info_user(TextView username, AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String usuario = preferences.getString("user", "");
        username.setText(usuario);

    }

    public  void logout(AppCompatActivity activity){
        SharedPreferences sharedpreferences = activity.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

}
