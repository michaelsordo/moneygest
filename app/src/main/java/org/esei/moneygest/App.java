package org.esei.moneygest;

import android.app.Application;

public class App extends Application {
    private DatabaseHelper databasehelper;

    @Override
    public void onCreate() {
        super.onCreate();

        this.databasehelper = new DatabaseHelper( this.getApplicationContext() );
    }

    public DatabaseHelper getDb() {
        return this.databasehelper;
    }

}
