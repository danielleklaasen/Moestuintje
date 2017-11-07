package com.danielleklaasen.moestuintje.database;

public class CultivatedPlantTable {
    public static final String TABLE_ITEMS = "cultivatedPlant";
    public static final String COLUMN_ID = "cultivatedPlantId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_CREATED_AT = "createdAt";

    public static final String[] ALL_COLUMNS = {
            COLUMN_ID, COLUMN_NAME, COLUMN_IMAGE, COLUMN_CREATED_AT};


    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_IMAGE + " TEXT, " +
                    COLUMN_CREATED_AT + " TEXT" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ITEMS;
}

