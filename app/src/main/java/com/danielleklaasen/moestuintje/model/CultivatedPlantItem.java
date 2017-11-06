package com.danielleklaasen.moestuintje.model;

import android.content.ContentValues;

import com.danielleklaasen.moestuintje.database.CultivatedPlantTable;

public class CultivatedPlantItem {

    private String itemId;
    private String createdAt;

    public CultivatedPlantItem(){
    }

    public CultivatedPlantItem(String itemId, String createdAt) {
        this.itemId = itemId;
        this.createdAt = createdAt;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ContentValues toValues(){
        ContentValues values = new ContentValues(2);

        values.put(CultivatedPlantTable.COLUMN_ID, itemId);
        values.put(CultivatedPlantTable.CREATED_AT, createdAt);

        return values;
    }

    @Override
    public String toString() {
        return "CultivatedPlantItem{" +
                "itemId='" + itemId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
