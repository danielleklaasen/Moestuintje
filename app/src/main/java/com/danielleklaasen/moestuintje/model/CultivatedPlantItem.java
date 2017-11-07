package com.danielleklaasen.moestuintje.model;

import android.content.ContentValues;

import com.danielleklaasen.moestuintje.database.CultivatedPlantTable;

import java.util.UUID;

public class CultivatedPlantItem {

    private String itemId;  // primary key, generate as uuid (universal unique id's)
    private String itemName;
    private int image;
    private String createdAt;


    public CultivatedPlantItem(){
    }

    public CultivatedPlantItem(String itemId, String itemName, int image, String createdAt) {
        if (itemId == null){ // assign random Id, always unique id.
            itemId = UUID.randomUUID().toString();
        }
        this.itemId = itemId;
        this.itemName = itemName;
        this.image = image;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ContentValues toValues(){
        ContentValues values = new ContentValues(4);

        values.put(CultivatedPlantTable.COLUMN_ID, itemId);
        values.put(CultivatedPlantTable.COLUMN_NAME, itemName);
        values.put(CultivatedPlantTable.COLUMN_IMAGE, image);
        values.put(CultivatedPlantTable.COLUMN_CREATED_AT, createdAt);

        return values;
    }

    @Override
    public String toString() {
        return "CultivatedPlantItem{" +
                "itemId='" + itemId + '\'' +
                "itemName='" + itemName + '\'' +
                "image='" + image + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
