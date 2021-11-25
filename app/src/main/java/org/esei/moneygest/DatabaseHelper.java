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
    public static String DATABASE_NAME = "user_db";
    public static int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS usuario("
                    + "email_usuario string(45) PRIMARY KEY,"
                    + "login_usuario string(45) NOT NULL,"
                    + "pass_usuario string(45) NOT NULL"
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

}