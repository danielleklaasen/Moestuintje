package com.danielleklaasen.moestuintje.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.danielleklaasen.moestuintje.model.CultivatedPlantItem;
import com.danielleklaasen.moestuintje.model.SpecieItem;

import java.util.ArrayList;
import java.util.List;

public class CultivatedPlantDataSource {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public CultivatedPlantDataSource(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void open(){
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    // CREATE SINGLE ITEM
    public CultivatedPlantItem createItem(CultivatedPlantItem item) {
        ContentValues values = item.toValues();
        mDatabase.insert(CultivatedPlantTable.TABLE_ITEMS, null, values);
        return item;
    }

    public long getDataItemsCount(){
        return DatabaseUtils.queryNumEntries(mDatabase, CultivatedPlantTable.TABLE_ITEMS);
    }

    // READ
    public List<CultivatedPlantItem> getAllItems(String myGarden) {

        // Loading CultivatedPlantItems from Database
        List<CultivatedPlantItem> CultivatedPlantItems = new ArrayList<>();
        Cursor cursor;
        cursor = null;

        cursor = mDatabase.query(CultivatedPlantTable.TABLE_ITEMS, CultivatedPlantTable.ALL_COLUMNS,
                null, null, null, null, CultivatedPlantTable.CREATED_AT);

        while (cursor.moveToNext()) {
            CultivatedPlantItem item = new CultivatedPlantItem();
            item.setItemId(cursor.getString(cursor.getColumnIndex(CultivatedPlantTable.COLUMN_ID)));
            item.setCreatedAt(cursor.getString(cursor.getColumnIndex(CultivatedPlantTable.CREATED_AT)));

            // need to add values accessed through foreign key.
            CultivatedPlantItems.add(item);
        }
        cursor.close();



        return CultivatedPlantItems;
    }

    //

}
