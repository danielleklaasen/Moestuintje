package com.danielleklaasen.moestuintje.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_FILE_NAME = "myGarden.db"; // setting db file name
    public static final int DB_VERSION = 1; // can only be initialized once (final)

    public DBHelper(Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // adding tables
        db.execSQL(SpecieTable.SQL_CREATE);
        db.execSQL(CultivatedPlantTable.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // just delete for now, later request json data file and reimport in new version.
        db.execSQL(SpecieTable.SQL_DELETE);
        db.execSQL(CultivatedPlantTable.SQL_DELETE);
        onCreate(db);
    }
}
