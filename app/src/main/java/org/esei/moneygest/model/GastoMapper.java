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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GastoMapper extends BaseMapper{

    public GastoMapper(Context context) {
        super(context);
    }

    public void insertGasto(Gasto gasto){
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
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

    public ArrayList<Gasto> listarGastos(String username) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Gasto gasto = null;

        ArrayList listaGastos = new ArrayList<Gasto>();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                                + TABLA_GASTO
                                + " WHERE "
                                + CAMPO_AUTOR_GASTO + "=?",
                                new String[]{username});

        while (cursor.moveToNext()){
            gasto=new Gasto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = null;

            try {
                fecha = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_FECHA_GASTO)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            gasto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CAMPO_ID_GASTO)));
            gasto.setConcepto(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_CONCEPTO_GASTO)));
            gasto.setCantidad(cursor.getDouble(cursor.getColumnIndexOrThrow(CAMPO_CANTIDAD_GASTO)));
            gasto.setFecha(fecha);
            gasto.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_TIPO_GASTO)));

            listaGastos.add(gasto);
        }

        return listaGastos;
    }

    public ArrayList<Gasto> listarGastosFecha(String username, Date fechaInicio, Date fechaFin) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Gasto gasto = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateInicio = sdf.format(fechaInicio);
        String dateFin = sdf.format(fechaFin);

        ArrayList listaGastos = new ArrayList<Gasto>();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLA_GASTO
                        + " WHERE "
                        + CAMPO_AUTOR_GASTO + "=?"
                        + " AND " + CAMPO_FECHA_GASTO
                        + " BETWEEN " + "?" + " AND " + "?",
                new String[]{username, dateInicio, dateFin});

        while (cursor.moveToNext()){
            gasto=new Gasto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = null;

            try {
                fecha = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_FECHA_GASTO)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            gasto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CAMPO_ID_GASTO)));
            gasto.setConcepto(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_CONCEPTO_GASTO)));
            gasto.setCantidad(cursor.getDouble(cursor.getColumnIndexOrThrow(CAMPO_CANTIDAD_GASTO)));
            gasto.setFecha(fecha);
            gasto.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_TIPO_GASTO)));

            listaGastos.add(gasto);
        }

        return listaGastos;
    }

    public ArrayList<Gasto> listarGastosCantidad(String username, Double cantidadMin, Double cantidadMax) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Gasto gasto = null;

        ArrayList listaGastos = new ArrayList<Gasto>();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLA_GASTO
                        + " WHERE "
                        + CAMPO_AUTOR_GASTO + "=?"
                        + " AND " + CAMPO_CANTIDAD_GASTO
                        + " BETWEEN " + "?" + " AND " + "?",
                new String[]{username, cantidadMin.toString(), cantidadMax.toString()});

        while (cursor.moveToNext()){
            gasto=new Gasto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = null;

            try {
                fecha = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_FECHA_GASTO)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            gasto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CAMPO_ID_GASTO)));
            gasto.setConcepto(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_CONCEPTO_GASTO)));
            gasto.setCantidad(cursor.getDouble(cursor.getColumnIndexOrThrow(CAMPO_CANTIDAD_GASTO)));
            gasto.setFecha(fecha);
            gasto.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_TIPO_GASTO)));

            listaGastos.add(gasto);
        }

        return listaGastos;
    }

    public ArrayList<Gasto> listarGastosTipo(String username, String tipo) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Gasto gasto = null;

        ArrayList listaGastos = new ArrayList<Gasto>();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLA_GASTO
                        + " WHERE "
                        + CAMPO_AUTOR_GASTO + "=?"
                        + " AND " + CAMPO_TIPO_GASTO + "=?",
                new String[]{username, tipo});

        while (cursor.moveToNext()){
            gasto=new Gasto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = null;

            try {
                fecha = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_FECHA_GASTO)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            gasto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CAMPO_ID_GASTO)));
            gasto.setConcepto(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_CONCEPTO_GASTO)));
            gasto.setCantidad(cursor.getDouble(cursor.getColumnIndexOrThrow(CAMPO_CANTIDAD_GASTO)));
            gasto.setFecha(fecha);
            gasto.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_TIPO_GASTO)));

            listaGastos.add(gasto);
        }

        return listaGastos;
    }

    public ArrayList<Gasto> listarGastosFechaCantidad(String username, Date fechaInicio, Date fechaFin, Double cantidadMin, Double cantidadMax) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Gasto gasto = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateInicio = sdf.format(fechaInicio);
        String dateFin = sdf.format(fechaFin);

        ArrayList listaGastos = new ArrayList<Gasto>();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLA_GASTO
                        + " WHERE "
                        + CAMPO_AUTOR_GASTO + "=?"
                        + " AND " + CAMPO_FECHA_GASTO
                        + " BETWEEN " + "?" + " AND " + "?"
                        + " AND " + CAMPO_CANTIDAD_GASTO
                        + " BETWEEN " + "?" + " AND " + "?",
                new String[]{username, dateInicio, dateFin, cantidadMin.toString(), cantidadMax.toString()});

        while (cursor.moveToNext()){
            gasto=new Gasto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = null;

            try {
                fecha = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_FECHA_GASTO)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            gasto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CAMPO_ID_GASTO)));
            gasto.setConcepto(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_CONCEPTO_GASTO)));
            gasto.setCantidad(cursor.getDouble(cursor.getColumnIndexOrThrow(CAMPO_CANTIDAD_GASTO)));
            gasto.setFecha(fecha);
            gasto.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_TIPO_GASTO)));

            listaGastos.add(gasto);
        }

        return listaGastos;
    }

    public ArrayList<Gasto> listarGastosFechaTipo(String username, Date fechaInicio, Date fechaFin, String tipo) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Gasto gasto = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateInicio = sdf.format(fechaInicio);
        String dateFin = sdf.format(fechaFin);

        ArrayList listaGastos = new ArrayList<Gasto>();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLA_GASTO
                        + " WHERE "
                        + CAMPO_AUTOR_GASTO + "=?"
                        + " AND " + CAMPO_FECHA_GASTO
                        + " BETWEEN " + "?" + " AND " + "?"
                        + " AND " + CAMPO_TIPO_GASTO + "=?",
                new String[]{username, dateInicio, dateFin, tipo});

        while (cursor.moveToNext()){
            gasto=new Gasto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = null;

            try {
                fecha = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_FECHA_GASTO)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            gasto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CAMPO_ID_GASTO)));
            gasto.setConcepto(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_CONCEPTO_GASTO)));
            gasto.setCantidad(cursor.getDouble(cursor.getColumnIndexOrThrow(CAMPO_CANTIDAD_GASTO)));
            gasto.setFecha(fecha);
            gasto.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_TIPO_GASTO)));

            listaGastos.add(gasto);
        }

        return listaGastos;
    }

    public ArrayList<Gasto> listarGastosCantidadTipo(String username, Double cantidadMin, Double cantidadMax, String tipo) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Gasto gasto = null;

        ArrayList listaGastos = new ArrayList<Gasto>();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLA_GASTO
                        + " WHERE "
                        + CAMPO_AUTOR_GASTO + "=?"
                        + " AND " + CAMPO_CANTIDAD_GASTO
                        + " BETWEEN " + "?" + " AND " + "?"
                        + " AND " + CAMPO_TIPO_GASTO + "=?",
                new String[]{username, cantidadMin.toString(), cantidadMax.toString(), tipo});

        while (cursor.moveToNext()){
            gasto=new Gasto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = null;

            try {
                fecha = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_FECHA_GASTO)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            gasto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CAMPO_ID_GASTO)));
            gasto.setConcepto(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_CONCEPTO_GASTO)));
            gasto.setCantidad(cursor.getDouble(cursor.getColumnIndexOrThrow(CAMPO_CANTIDAD_GASTO)));
            gasto.setFecha(fecha);
            gasto.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_TIPO_GASTO)));

            listaGastos.add(gasto);
        }

        return listaGastos;
    }

    public ArrayList<Gasto> listarGastosFechaCantidadTipo(String username, Date fechaInicio, Date fechaFin, Double cantidadMin, Double cantidadMax, String tipo) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Gasto gasto = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateInicio = sdf.format(fechaInicio);
        String dateFin = sdf.format(fechaFin);

        ArrayList listaGastos = new ArrayList<Gasto>();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLA_GASTO
                        + " WHERE "
                        + CAMPO_AUTOR_GASTO + "=?"
                        + " AND " + CAMPO_FECHA_GASTO
                        + " BETWEEN " + "?" + " AND " + "?"
                        + " AND " + CAMPO_CANTIDAD_GASTO
                        + " BETWEEN " + "?" + " AND " + "?"
                        + " AND " + CAMPO_TIPO_GASTO + "=?",
                new String[]{username, dateInicio, dateFin, cantidadMin.toString(), cantidadMax.toString(), tipo});

        while (cursor.moveToNext()){
            gasto=new Gasto();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date fecha = null;

            try {
                fecha = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_FECHA_GASTO)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            gasto.setId(cursor.getInt(cursor.getColumnIndexOrThrow(CAMPO_ID_GASTO)));
            gasto.setConcepto(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_CONCEPTO_GASTO)));
            gasto.setCantidad(cursor.getDouble(cursor.getColumnIndexOrThrow(CAMPO_CANTIDAD_GASTO)));
            gasto.setFecha(fecha);
            gasto.setTipo(cursor.getString(cursor.getColumnIndexOrThrow(CAMPO_TIPO_GASTO)));

            listaGastos.add(gasto);
        }

        return listaGastos;
    }

}
