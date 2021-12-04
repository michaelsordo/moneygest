package org.esei.moneygest.model;

import static org.esei.moneygest.core.DatabaseHelper.TABLA_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_ID_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_CONCEPTO_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_CANTIDAD_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_FECHA_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_TIPO_GASTO;
import static org.esei.moneygest.core.DatabaseHelper.CAMPO_AUTOR_GASTO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GastoMapper extends BaseMapper{

    public GastoMapper(Context context) {
        super(context);
    }

    public void insertGasto(Gasto gasto){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(gasto.getFecha());

        contentValues.put(CAMPO_CONCEPTO_GASTO, gasto.getConcepto());
        contentValues.put(CAMPO_CANTIDAD_GASTO, gasto.getCantidad());
        contentValues.put(CAMPO_FECHA_GASTO, date);
        contentValues.put(CAMPO_TIPO_GASTO, gasto.getTipo());
        contentValues.put(CAMPO_AUTOR_GASTO, gasto.getAutor());

        try {
            db.beginTransaction();
            db.insertOrThrow(
                    TABLA_GASTO,
                    null,
                    contentValues
            );
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void updateGasto(Gasto gasto) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(gasto.getFecha());

        contentValues.put(CAMPO_CONCEPTO_GASTO, gasto.getConcepto());
        contentValues.put(CAMPO_CANTIDAD_GASTO, gasto.getCantidad());
        contentValues.put(CAMPO_FECHA_GASTO, date);
        contentValues.put(CAMPO_TIPO_GASTO, gasto.getTipo());
        contentValues.put(CAMPO_AUTOR_GASTO, gasto.getAutor());

        try {
            db.beginTransaction();
            db.update(TABLA_GASTO, contentValues, CAMPO_ID_GASTO + "=?", new String[]{String.valueOf(gasto.getId())});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteGasto(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM " + TABLA_GASTO + " WHERE "
                    + CAMPO_ID_GASTO + "=?",
                    new String[]{String.valueOf(id)});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

}
