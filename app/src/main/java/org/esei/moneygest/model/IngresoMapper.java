package org.esei.moneygest.model;

import static org.esei.moneygest.core.DatabaseHelper.CAMPO_AUTOR_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_CANTIDAD_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_CONCEPTO_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_FECHA_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_ID_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_TIPO_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.TABLA_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.TABLA_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_ID_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_CONCEPTO_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_CANTIDAD_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_FECHA_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_TIPO_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_AUTOR_INGRESO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class IngresoMapper extends BaseMapper{

    public IngresoMapper(Context context) {
        super(context);
    }

    public void insertIngreso(Ingreso ingreso){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(ingreso.getFecha());

        contentValues.put(CAMPO_CONCEPTO_INGRESO, ingreso.getConcepto());
        contentValues.put(CAMPO_CANTIDAD_INGRESO, ingreso.getCantidad());
        contentValues.put(CAMPO_FECHA_INGRESO, date);
        contentValues.put(CAMPO_TIPO_INGRESO, ingreso.getTipo());
        contentValues.put(CAMPO_AUTOR_INGRESO, ingreso.getAutor());

        try {
            db.beginTransaction();
            db.insertOrThrow(
                    TABLA_INGRESO,
                    null,
                    contentValues
            );
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void updateIngreso(Ingreso ingreso) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(ingreso.getFecha());

        contentValues.put(CAMPO_CONCEPTO_INGRESO, ingreso.getConcepto());
        contentValues.put(CAMPO_CANTIDAD_INGRESO, ingreso.getCantidad());
        contentValues.put(CAMPO_FECHA_INGRESO, date);
        contentValues.put(CAMPO_TIPO_INGRESO, ingreso.getTipo());
        contentValues.put(CAMPO_AUTOR_INGRESO, ingreso.getAutor());

        try {
            db.beginTransaction();
            db.update(TABLA_INGRESO, contentValues, CAMPO_ID_INGRESO + "=?", new String[]{String.valueOf(ingreso.getId())});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteIngreso(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM " + TABLA_INGRESO + " WHERE "
                            + CAMPO_ID_INGRESO + "=?",
                    new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<Ingreso> listarIngresos(String username) {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Ingreso ingreso = null;

        ArrayList listaIngresos = new ArrayList<Gasto>();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLA_INGRESO
                        + " WHERE "
                        + CAMPO_AUTOR_INGRESO + "=?",
                new String[]{username});

        while (cursor.moveToNext()){
            ingreso=new Ingreso();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = null;

            try {
                fecha = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_FECHA_INGRESO)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            ingreso.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CAMPO_ID_INGRESO)));
            ingreso.setConcepto(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_CONCEPTO_INGRESO)));
            ingreso.setCantidad(cursor.getDouble(cursor.getColumnIndexOrThrow(CAMPO_CANTIDAD_INGRESO)));
            ingreso.setFecha(fecha);
            ingreso.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_TIPO_INGRESO)));

            listaIngresos.add(ingreso);
        }

        return listaIngresos;
    }

}
