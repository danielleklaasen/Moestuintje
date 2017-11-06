package com.danielleklaasen.moestuintje.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielleklaasen.moestuintje.R;
import com.danielleklaasen.moestuintje.model.CultivatedPlantItem;
import com.danielleklaasen.moestuintje.model.SpecieItem;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<CultivatedPlantItem> mItems;

    public GridAdapter(Context context, List<CultivatedPlantItem> items) {
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

        final CultivatedPlantItem item = mItems.get(position); // get value from list of items
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.plantImageView);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.nameTextView);

        Log.d("debug", item.getItemId());
        // nameTextView.setText(item.getItemName());
        nameTextView.setText("Garlic");
        // imageView.setImageResource(item.getImage());
        imageView.setImageResource(R.drawable.garlic);

        return convertView;
    }


}
