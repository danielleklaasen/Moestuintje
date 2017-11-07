package com.danielleklaasen.moestuintje.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.danielleklaasen.moestuintje.database.SpecieTable;

import java.util.UUID;

public class SpecieItem implements Parcelable {

    private String itemId; // primary key, generate as uuid (universal unique id's)
    private String itemName;
    private int image;

    public SpecieItem() {
    }

    public SpecieItem(String itemId, String itemName, int image) {

        if (itemId == null){ // assign random Id, always unique id.
            itemId = UUID.randomUUID().toString();
        }

        this.itemId = itemId;
        this.itemName = itemName;
        this.image = image;

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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ContentValues toValues() {
        //ContentValues values = new ContentValues(4); // 4 columns, id, name, inGarden, image
        ContentValues values = new ContentValues(3); // 3 columns, id, name, image

        values.put(SpecieTable.COLUMN_ID, itemId);
        values.put(SpecieTable.COLUMN_NAME, itemName);
        values.put(SpecieTable.COLUMN_IMAGE, image);

        return values;
    }

    @Override
    public String toString() {
        return "SpecieItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", image=" + image + '\'' +
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
        dest.writeInt(this.image);
    }

    protected SpecieItem(Parcel in) {
        this.itemId = in.readString();
        this.itemName = in.readString();
        this.image = in.readInt();
    }

    public static final Parcelable.Creator<SpecieItem> CREATOR = new Parcelable.Creator<SpecieItem>() {
        @Override
        public SpecieItem createFromParcel(Parcel source) {
            return new SpecieItem(source);
        }

        @Override
        public SpecieItem[] newArray(int size) {
            return new SpecieItem[size];
        }
    };
}
