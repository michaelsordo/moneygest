package org.esei.moneygest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.esei.moneygest.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user_db";
    public static int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {

            db.execSQL("CREATE TABLE IF NOT EXISTS USUARIO("
                    + "login_usuario VARCHAR(15) PRIMARY KEY,"
                    + "email_usuario VARCHAR(50) UNIQUE,"
                    + "pass_usuario VARCHAR(20) NOT NULL"
                    + ")");

            db.execSQL("CREATE TABLE IF NOT EXISTS GASTO("
                    + "id_gasto INT PRIMARY KEY, AUTO_INCREMENT"
                    + "concepto_gasto VARCHAR(100) NOT NULL,"
                    + "cantidad_gasto DOUBLE(7,2) NOT NULL,"
                    + "fecha_gasto DATE NOT NULL,"
                    + "login_autor VARCHAR(15) NOT NULL,"
                    + "FOREIGN KEY (login_autor) REFERENCES USUARIO(login_usuario) ON DELETE CASCADE"
                    + ")");

            db.execSQL("CREATE TABLE IF NOT EXISTS INGRESO("
                    + "id_ingreso INT PRIMARY KEY, AUTO_INCREMENT"
                    + "concepto_ingreso VARCHAR(100) NOT NULL,"
                    + "cantidad_ingreso DOUBLE(7,2) NOT NULL,"
                    + "fecha_ingreso DATE NOT NULL,"
                    + "login_autor VARCHAR(15) NOT NULL,"
                    + "FOREIGN KEY (login_autor) REFERENCES USUARIO(login_usuario) ON DELETE CASCADE"
                    + ")");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS USUARIO");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        this.onCreate(db);
    }

    public void burnData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM USUARIO");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /**
     * INSERTS
     */

    public void insertarUsuario(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO USUARIO(login_usuario,email,password) VALUES(?,?,?)",
                    new String[]{user.getEmail(),user.getlogin_user(),user.getpassword()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return;
    }

    public Boolean insertUser(String username, String password, String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login_usuario", username);
        contentValues.put("email_usuario", email);
        contentValues.put("pass_usuario", password);

        long result = MyDB.insert("USUARIO", null, contentValues);

        if(result ==-1 ){
            return false;
        }

        return true;
    }

    public Boolean insertGasto(String concepto, Double cantidad, Date fecha, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("concepto_gasto", concepto);
        contentValues.put("cantidad_gasto", cantidad);
        contentValues.put("fecha_gasto", String.valueOf(fecha));
        contentValues.put("login_autor", username);

        long result = MyDB.insert("GASTO", null, contentValues);

        if(result ==-1 ){
            return false;
        }

        return true;
    }

    public Boolean insertIngreso(String concepto, Double cantidad, Date fecha, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("concepto_ingreso", concepto);
        contentValues.put("cantidad_ingreso", cantidad);
        contentValues.put("fecha_ingreso", String.valueOf(fecha));
        contentValues.put("login_autor", username);

        long result = MyDB.insert("INGRESO", null, contentValues);

        if(result ==-1 ){
            return false;
        }

        return true;
    }

    public Boolean checkUsernameExists(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from USUARIO where login_usuario =?", new String[]{username} );

        if(cursor.getCount() > 0){
            return true;
        }

        return false;
    }

    public Boolean checkEmailExists(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from USUARIO where email_usuario =?", new String[]{email} );

        if(cursor.getCount() > 0){
            return true;
        }

        return false;
    }

    public Boolean checkUsernamePass(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from USUARIO where login_usuario =? and pass_usuario=?", new String[]{username, password});

        if(cursor.getCount() > 0){
            return true;
        }

        return false;
    }

}