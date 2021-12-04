package org.esei.moneygest.model;

import static org.esei.moneygest.core.DatabaseHelper.TABLA_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_ID_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_CONCEPTO_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_CANTIDAD_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_FECHA_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_TIPO_INGRESO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_AUTOR_INGRESO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;

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

}
