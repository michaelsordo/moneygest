package org.esei.moneygest.model;

import android.content.Context;

import org.esei.moneygest.core.DatabaseHelper;

public class BaseMapper {

    protected DatabaseHelper databaseHelper;

    protected BaseMapper (Context context){
        this.databaseHelper = DatabaseHelper.getDatabaseHelper(context);
    }
}
