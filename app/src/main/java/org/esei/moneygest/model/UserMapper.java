package org.esei.moneygest.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static org.esei.moneygest.core.DatabaseHelper.TABLA_USUARIO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_LOGIN_USUARIO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_EMAIL_USUARIO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_PASS_USUARIO;

public class UserMapper extends BaseMapper {

    public UserMapper(Context context) {
        super(context);
    }

    public void insertarUsuario(User user) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO " + TABLA_USUARIO
                    +"(" + CAMPO_LOGIN_USUARIO
                    +"," + CAMPO_EMAIL_USUARIO
                    +"," + CAMPO_PASS_USUARIO
                    +")" + " VALUES(?,?,?)",
                    new String[]{user.getLogin(), user.getEmail(),user.getPassword()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return;
    }

    public Boolean insertUser(String username, String password, String email){
        SQLiteDatabase MyDB = databaseHelper.getWritableDatabase();
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
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("UPDATE " + TABLA_USUARIO + " SET "
                    + CAMPO_PASS_USUARIO + "=?, "
                    + CAMPO_EMAIL_USUARIO + "=?"
                    + " WHERE "
                    + CAMPO_LOGIN_USUARIO + "=?",
                    new String[]{password,email,username});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public String[] getUserData(String username) {
        String [] toRet = new String[2];
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        try (Cursor cursor = db.query(TABLA_USUARIO,null, CAMPO_LOGIN_USUARIO+ "=?", new String[]{username}, null, null, null, null)) {
            if (cursor.moveToFirst())
            {
                toRet[0] = cursor.getString(cursor.getColumnIndex(CAMPO_EMAIL_USUARIO));
                toRet[1] = cursor.getString(cursor.getColumnIndex(CAMPO_PASS_USUARIO));
            }
        }

        return toRet;
    }

    public Boolean checkUsernameExists(String username){
        SQLiteDatabase MyDB = databaseHelper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM "
                                    + TABLA_USUARIO
                                    + " WHERE "
                                    + CAMPO_LOGIN_USUARIO + "=?",
                                    new String[]{username} );

        if(cursor.getCount() > 0){
            return true;
        }

        return false;
    }

    public Boolean checkEmailExists(String email){
        SQLiteDatabase MyDB = databaseHelper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM "
                        + TABLA_USUARIO
                        + " WHERE "
                        + CAMPO_EMAIL_USUARIO + "=?",
                        new String[]{email} );

        if(cursor.getCount() > 0){
            return true;
        }

        return false;
    }

    public Boolean checkUsernamePass(String username, String password){
        SQLiteDatabase MyDB = databaseHelper.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM "
                        + TABLA_USUARIO
                        + " WHERE "
                        + CAMPO_LOGIN_USUARIO + "=?"
                        + " AND "
                        + CAMPO_PASS_USUARIO + "=?",
                        new String[]{username, password} );

        if(cursor.getCount() > 0){
            return true;
        }

        return false;
    }

}
