package com.danielleklaasen.moestuintje.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.danielleklaasen.moestuintje.database.PlantTable;

import java.util.UUID;

public class PlantItem implements Parcelable {

    private String itemId; // primary key, generate as uuid (universal unique id's)
    private String itemName;
    private int inGarden;
    private String image;

    // added column
    private String category;

    public PlantItem() {
    }

    public PlantItem(String itemId, String itemName, int inGarden, String image, String category) { // added column

        if (itemId == null){ // assign random Id, always unique id.
            itemId = UUID.randomUUID().toString();
        }

        this.itemId = itemId;
        this.itemName = itemName;
        this.inGarden = inGarden;
        this.image = image;
        // added column
        this.category = category;
    }

    public int getInGarden() {
        return inGarden;
    }

    public void setInGarden(int inGarden) {
        this.inGarden = inGarden;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // added column

    public String getCategory() {return category;}

    public void setCategory(String category) {
        this.category = category;
    }

    public ContentValues toValues() {
        //ContentValues values = new ContentValues(4); // 4 columns, id, name, inGarden, image
        ContentValues values = new ContentValues(5); // 4 columns, id, name, inGarden, image, category

        values.put(PlantTable.COLUMN_ID, itemId);
        values.put(PlantTable.COLUMN_NAME, itemName);
        values.put(PlantTable.COLUMN_IN_GARDEN, inGarden);
        values.put(PlantTable.COLUMN_IMAGE, image);

        // added column
        values.put(PlantTable.COLUMN_CATEGORY, category);


        return values;
    }

    @Override
    public String toString() {
        return "PlantItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", inGarden=" + inGarden + '\'' +
                ", image=" + image + '\'' +
                ", category=" + category + // added column
                '}';
    }

    // we can now send parcelable objects as intents in between screens/ app.
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemId);
        dest.writeString(this.itemName);
        dest.writeInt(this.inGarden);
        dest.writeString(this.image);

        // added column
        dest.writeString(this.category);
    }

    protected PlantItem(Parcel in) {
        this.itemId = in.readString();
        this.itemName = in.readString();
        this.inGarden = in.readInt();
        this.image = in.readString();

        // added column
        this.category = in.readString();
    }

    public static final Parcelable.Creator<PlantItem> CREATOR = new Parcelable.Creator<PlantItem>() {
        @Override
        public PlantItem createFromParcel(Parcel source) {
            return new PlantItem(source);
        }

        @Override
        public PlantItem[] newArray(int size) {
            return new PlantItem[size];
        }
    };
}
