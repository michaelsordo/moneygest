package org.esei.moneygest.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.esei.moneygest.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "moneygest_db";
    public static int DATABASE_VERSION = 1;
    public static DatabaseHelper databaseHelper;

    public static final String TABLA_USUARIO = "USUARIO";
    public static final String CAMPO_LOGIN_USUARIO = "login_usuario";
    public static final String CAMPO_EMAIL_USUARIO = "email_usuario";
    public static final String CAMPO_PASS_USUARIO = "pass_usuario";

    public static final String TABLA_GASTO = "GASTO";
    public static final String CAMPO_ID_GASTO = "id_gasto";
    public static final String CAMPO_CONCEPTO_GASTO = "concepto_gasto";
    public static final String CAMPO_CANTIDAD_GASTO = "cantidad_gasto";
    public static final String CAMPO_FECHA_GASTO = "fecha_gasto";
    public static final String CAMPO_TIPO_GASTO = "tipo_gasto";
    public static final String CAMPO_AUTOR_GASTO = "autor_gasto";

    public static final String TABLA_INGRESO = "INGRESO";
    public static final String CAMPO_ID_INGRESO = "id_ingreso";
    public static final String CAMPO_CONCEPTO_INGRESO = "concepto_ingreso";
    public static final String CAMPO_CANTIDAD_INGRESO = "cantidad_ingreso";
    public static final String CAMPO_FECHA_INGRESO = "fecha_ingreso";
    public static final String CAMPO_TIPO_INGRESO = "tipo_ingreso";
    public static final String CAMPO_AUTOR_INGRESO = "autor_ingreso";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getDatabaseHelper(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_USUARIO
                    +"("
                    + CAMPO_LOGIN_USUARIO + " VARCHAR(15) PRIMARY KEY,"
                    + CAMPO_EMAIL_USUARIO + " VARCHAR(50) UNIQUE,"
                    + CAMPO_PASS_USUARIO + " VARCHAR(20) NOT NULL"
                    + ")");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_GASTO
                    +"("
                    + CAMPO_ID_GASTO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + CAMPO_CONCEPTO_GASTO + " VARCHAR(100) NOT NULL,"
                    + CAMPO_CANTIDAD_GASTO + " DOUBLE(7,2) NOT NULL,"
                    + CAMPO_FECHA_GASTO + " DATE NOT NULL,"
                    + CAMPO_TIPO_GASTO + " VARCHAR(30) NOT NULL,"
                    + CAMPO_AUTOR_GASTO + " VARCHAR(15) NOT NULL,"
                    + "FOREIGN KEY " + "(" + CAMPO_AUTOR_GASTO + ")" + " REFERENCES " + TABLA_USUARIO + "(" + CAMPO_LOGIN_USUARIO + ")" + " ON DELETE CASCADE"
                    + ")");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLA_INGRESO
                    +"("
                    + CAMPO_ID_INGRESO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + CAMPO_CONCEPTO_INGRESO + " VARCHAR(100) NOT NULL,"
                    + CAMPO_CANTIDAD_INGRESO + " DOUBLE(7,2) NOT NULL,"
                    + CAMPO_FECHA_INGRESO + " DATE NOT NULL,"
                    + CAMPO_TIPO_INGRESO + " VARCHAR(30) NOT NULL,"
                    + CAMPO_AUTOR_INGRESO + " VARCHAR(15) NOT NULL,"
                    + "FOREIGN KEY " + "(" + CAMPO_AUTOR_INGRESO + ")" + " REFERENCES " + TABLA_USUARIO + "(" + CAMPO_LOGIN_USUARIO + ")" + " ON DELETE CASCADE"
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
            db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO);
            db.execSQL("DROP TABLE IF EXISTS " + TABLA_GASTO);
            db.execSQL("DROP TABLE IF EXISTS " + TABLA_INGRESO);
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

}