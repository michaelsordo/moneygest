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

    /**
     * INSERTS
     */

    public void insertarUsuario(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO USUARIO(login_usuario,email,password) VALUES(?,?,?)",
                    new String[]{user.getEmail(),user.getLogin(),user.getPassword()});
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

    public void updateUser( String username, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("UPDATE USUARIO SET pass_usuario=?, email_usuario=? WHERE login_usuario=?",
                    new String[]{password,email,username});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public String[] getUserData(String username) {
        String [] toRet = new String[2];
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.query("USUARIO",null, "login_usuario=?", new String[]{username}, null, null, null, null)) {
            if (cursor.moveToFirst())
            {
                toRet[0] = cursor.getString(cursor.getColumnIndex("email_usuario"));
                toRet[1] = cursor.getString(cursor.getColumnIndex("pass_usuario"));
            }
        }

        return toRet;
    }

    public Boolean insertGasto(String concepto, Double cantidad, Date fecha, String tipoGasto, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(fecha);

        contentValues.put("concepto_gasto", concepto);
        contentValues.put("cantidad_gasto", cantidad);
        contentValues.put("fecha_gasto", date);
        contentValues.put("tipo_gasto", tipoGasto);
        contentValues.put("autor_gasto", username);

        long result = MyDB.insert("GASTO", null, contentValues);

        if(result ==-1 ){
            return false;
        }

        return true;
    }

    public void updateGasto(int id, String concepto, Double cantidad, Date fecha, String tipoGasto) {
        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(fecha);

        db.beginTransaction();
        try {
            db.execSQL("UPDATE GASTO SET concepto_gasto=?, cantidad_gasto=?, fecha_gasto=?, tipo_gasto=? WHERE id_gasto=?",
                    new String[]{concepto,cantidad.toString(), date, tipoGasto, String.valueOf(id)});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteGasto(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM GASTO WHERE id_gasto=?",
                    new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /*
    public getGastosUser( String username) {
        String [] toRet = new String[2];
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.query("USUARIO",null, "login_usuario=?", new String[]{username}, null, null, null, null)) {
            if (cursor.moveToFirst())
            {
                toRet[0] = cursor.getString(cursor.getColumnIndex("email_usuario"));
                toRet[1] = cursor.getString(cursor.getColumnIndex("pass_usuario"));
            }
        }

        return toRet;
    }*/

    public Boolean insertIngreso(String concepto, Double cantidad, Date fecha,String tipoIngreso, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(fecha);

        contentValues.put("concepto_ingreso", concepto);
        contentValues.put("cantidad_ingreso", cantidad);
        contentValues.put("fecha_ingreso", date);
        contentValues.put("tipo_ingreso", tipoIngreso);
        contentValues.put("login_autor", username);

        long result = MyDB.insert("INGRESO", null, contentValues);

        if(result ==-1 ){
            return false;
        }

        return true;
    }

    public void updateIngreso(int id, String concepto, Double cantidad, Date fecha, String tipoIngreso) {
        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(fecha);

        db.beginTransaction();
        try {
            db.execSQL("UPDATE INGRESO SET concepto_ingreso=?, cantidad_ingreso=?, fecha_ingreso=?, tipo_ingreso=? WHERE id_ingreso=?",
                    new String[]{concepto,cantidad.toString(), date, tipoIngreso, String.valueOf(id)});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteIngreso(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM INGRESO WHERE id_ingreso=?",
                    new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
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