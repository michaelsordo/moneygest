package org.esei.moneygest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.esei.moneygest.User;

import java.util.ArrayList;
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
            db.execSQL("CREATE TABLE IF NOT EXISTS usuario("
                    + "login_usuario VARCHAR(15) PRIMARY KEY,"
                    + "email_usuario VARCHAR(50) UNIQUE,"
                    + "pass_usuario VARCHAR(20) NOT NULL"
                    + ")");
            // solucionar tema tabla gastos
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS usuario");
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
            db.execSQL("DELETE FROM usuario");
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
            db.execSQL("INSERT INTO usuario(login_usuario,email,password) VALUES(?,?,?)",
                    new String[]{user.getEmail(),user.getlogin_user(),user.getpassword()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return;
    }

    public Boolean insertData(String username, String password, String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login_usuario", username);
        contentValues.put("email_usuario", email);
        contentValues.put("pass_usuario", password);

        long result = MyDB.insert("usuario", null, contentValues);

        if(result ==-1 ){
            return false;
        }

        return true;
    }

    public Boolean checkUsernameExists(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from usuario where login_usuario =?", new String[]{username} );

        if(cursor.getCount() > 0){
            return true;
        }

        return false;
    }

    public Boolean checkEmailExists(String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from usuario where email_usuario =?", new String[]{email} );

        if(cursor.getCount() > 0){
            return true;
        }

        return false;
    }

    public Boolean checkUsernamePass(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from usuario where login_usuario =? and pass_usuario=?", new String[]{username, password});

        if(cursor.getCount() > 0){
            return true;
        }

        return false;
    }

}