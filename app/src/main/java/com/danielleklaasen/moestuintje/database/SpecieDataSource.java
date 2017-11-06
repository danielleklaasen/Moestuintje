package com.danielleklaasen.moestuintje.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.danielleklaasen.moestuintje.model.SpecieItem;

import java.util.ArrayList;
import java.util.List;

public class SpecieDataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mDbHelper; // m = member of calss

    public SpecieDataSource(Context context) {
        this.mContext = context; // context can be activity
        mDbHelper = new DBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void open() {
        mDatabase = mDbHelper.getWritableDatabase(); // doesn't matter its double, you will close all, in close method
    }
    public void close() {
        mDbHelper.close();
    }

    public long getPlantItemsCount(){
        return DatabaseUtils.queryNumEntries(mDatabase, SpecieTable.TABLE_ITEMS);
    }

    // CREATE
    public SpecieItem createItem(SpecieItem item){
        ContentValues values = item.toValues();
        mDatabase.insert(SpecieTable.TABLE_ITEMS, null, values);
        return item;
    }

    public void seedDatabase(List<SpecieItem> PlantItemList){
        long numItems = getPlantItemsCount();
        if(numItems == 0){ // if database is empty, insert data
            for (SpecieItem plant:
                    PlantItemList) {
                try {
                    createItem(plant); // possible to give problems
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // READ
    public List<SpecieItem> getAllItems(String myGarden) {

        // Loading PlantItems from Database
        List<SpecieItem> PlantItems = new ArrayList<>();
        Cursor cursor;
        cursor = null;

        cursor = mDatabase.query(SpecieTable.TABLE_ITEMS, SpecieTable.ALL_COLUMNS,
                null, null, null, null, SpecieTable.COLUMN_NAME);

        while (cursor.moveToNext()) {
            SpecieItem item = new SpecieItem();
            item.setItemId(cursor.getString(cursor.getColumnIndex(SpecieTable.COLUMN_ID)));
            item.setItemName(cursor.getString(cursor.getColumnIndex(SpecieTable.COLUMN_NAME)));
            item.setImage(cursor.getInt(cursor.getColumnIndex(SpecieTable.COLUMN_IMAGE)));
            PlantItems.add(item);
        }
        cursor.close();
        return PlantItems;
    }

    // UPDATE
    public boolean setInGarden(String id, String name, int inGarden, String image, String category ) {
        ContentValues contentValues = new ContentValues();
        //  contentValues.put(SpecieTable.COLUMN_ID, id);
        //  contentValues.put(SpecieTable.COLUMN_NAME, name);
        //   contentValues.put(SpecieTable.COLUMN_IMAGE, image);
        mDatabase.update(SpecieTable.TABLE_ITEMS, contentValues, SpecieTable.COLUMN_ID + " = ?",new String[] { id });
        // if id matches, set COLUMN_IN_GARDEN to true
        return true;
    }

    // DELETE
    public int deleteItem(String itemId ) {
        return mDatabase.delete(SpecieTable.TABLE_ITEMS, SpecieTable.COLUMN_ID + " = ?",new String[] {itemId});
    }
}
