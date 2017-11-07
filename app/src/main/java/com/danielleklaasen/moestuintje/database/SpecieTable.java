package com.danielleklaasen.moestuintje.database;

public class SpecieTable {
    public static final String TABLE_ITEMS = "specie";
    public static final String COLUMN_ID = "specieId";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE = "image";

    public static final String[] ALL_COLUMNS = {
            COLUMN_ID, COLUMN_NAME,COLUMN_IMAGE};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_IMAGE + " TEXT" + ");";
    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ITEMS;
}