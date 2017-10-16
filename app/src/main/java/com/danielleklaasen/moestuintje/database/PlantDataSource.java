package com.danielleklaasen.moestuintje.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.danielleklaasen.moestuintje.model.PlantItem;

import java.util.ArrayList;
import java.util.List;

public class PlantDataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper; // m = member of calss

    public PlantDataSource(Context context) {
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
        return DatabaseUtils.queryNumEntries(mDatabase, PlantTable.TABLE_ITEMS);
    }

    // CREATE
    public PlantItem createItem(PlantItem item){
        ContentValues values = item.toValues();
        mDatabase.insert(PlantTable.TABLE_ITEMS, null, values);
        return item;
    }

    public void seedDatabase(List<PlantItem> PlantItemList){
        long numItems = getPlantItemsCount();
        if(numItems == 0){ // if database is empty, insert data
            for (PlantItem plant:
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
    public List<PlantItem> getAllItems(String myGarden) {

        // Loading PlantItems from Database
        List<PlantItem> PlantItems = new ArrayList<>();
        Cursor cursor;
        cursor = null;

        if (myGarden == null){
            // display all
            cursor = mDatabase.query(PlantTable.TABLE_ITEMS, PlantTable.ALL_COLUMNS,
                    null, null, null, null, PlantTable.COLUMN_NAME);
        }else{
            // display only in garden

            // select * from items where inGarden = 'true';
            cursor = mDatabase.query(PlantTable.TABLE_ITEMS, PlantTable.ALL_COLUMNS,
                    PlantTable.COLUMN_IN_GARDEN + "=?", new String[] {"true"}, null, null, PlantTable.COLUMN_NAME);
        }

        while (cursor.moveToNext()) {
            PlantItem item = new PlantItem();
            item.setItemId(cursor.getString(cursor.getColumnIndex(PlantTable.COLUMN_ID)));
            item.setItemName(cursor.getString(cursor.getColumnIndex(PlantTable.COLUMN_NAME)));
            item.setInGarden(cursor.getInt(cursor.getColumnIndex(PlantTable.COLUMN_IN_GARDEN)));
            item.setImage(cursor.getString(cursor.getColumnIndex(PlantTable.COLUMN_IMAGE)));
            item.setCategory(cursor.getString(cursor.getColumnIndex(PlantTable.COLUMN_CATEGORY))); // added column
            PlantItems.add(item);
        }
        cursor.close();
        return PlantItems;
    }

    // UPDATE
    public boolean setInGarden(String id, String name, int inGarden, String image, String category ) {
        ContentValues contentValues = new ContentValues();
        //  contentValues.put(PlantTable.COLUMN_ID, id);
        //  contentValues.put(PlantTable.COLUMN_NAME, name);
        contentValues.put(PlantTable.COLUMN_IN_GARDEN,"true");
        //   contentValues.put(PlantTable.COLUMN_IMAGE, image);
        //   contentValues.put(PlantTable.COLUMN_CATEGORY, category);
        mDatabase.update(PlantTable.TABLE_ITEMS, contentValues, PlantTable.COLUMN_ID + " = ?",new String[] { id });
        // if id matches, set COLUMN_IN_GARDEN to true
        return true;
    }



    // DELETE
    public int deleteItem(String itemId ) {
        return mDatabase.delete(PlantTable.TABLE_ITEMS, PlantTable.COLUMN_ID + " = ?",new String[] {itemId});
    }
}
