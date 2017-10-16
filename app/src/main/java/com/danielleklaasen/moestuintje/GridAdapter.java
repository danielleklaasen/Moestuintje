package com.danielleklaasen.moestuintje;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielleklaasen.moestuintje.model.PlantItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<PlantItem> mItems;

    public GridAdapter(Context context, List<PlantItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    public int getCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.grid_item_layout, null);
        }

        final PlantItem item = mItems.get(position); // get value from list of items
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.plantImageView);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.nameTextView);

        try {
            nameTextView.setText(item.getItemName());
            String imageFile = item.getImage();
            InputStream inputStream = mContext.getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertView;
    }


}
