package com.danielleklaasen.moestuintje.database;

public class CultivatedPlantTable {
    public static final String TABLE_ITEMS = "cultivatedPlant";
    public static final String COLUMN_ID = "specieId";
    public static final String CREATED_AT = "createdAt";

    public static final String[] ALL_COLUMNS = {
            COLUMN_ID, CREATED_AT};

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + " TEXT NOT NULL, " +
                    CREATED_AT + " TEXT NOT NULL, " +
                    "FOREIGN KEY(" + COLUMN_ID +
                    ") REFERENCES "+ SpecieTable.TABLE_ITEMS+"("+ SpecieTable.COLUMN_ID+"), "+
                    "PRIMARY KEY ("+ COLUMN_ID + ", " + CREATED_AT+ ")"+ ");";
    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ITEMS;
}
