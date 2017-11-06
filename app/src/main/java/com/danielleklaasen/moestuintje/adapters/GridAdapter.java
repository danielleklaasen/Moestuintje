package com.danielleklaasen.moestuintje.adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.danielleklaasen.moestuintje.R;
import com.danielleklaasen.moestuintje.database.CultivatedPlantDataSource;
import com.danielleklaasen.moestuintje.database.SpecieDataSource;
import com.danielleklaasen.moestuintje.model.CultivatedPlantItem;

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.grid_item_layout, null);
        }

        final CultivatedPlantItem item = mItems.get(position); // get value from list of items
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.plantImageView);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.nameTextView);

        nameTextView.setText(item.getItemName());
        imageView.setImageResource(item.getImage());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // UPDATE CODE
                CultivatedPlantDataSource mCultivatedPlantDataSource;
                mCultivatedPlantDataSource = new CultivatedPlantDataSource(mContext);
                mCultivatedPlantDataSource.open();
                int image = R.drawable.potato;

                mCultivatedPlantDataSource.changePicture(item.getItemId(), image);

                Toast.makeText(mContext, "Picture is changed", Toast.LENGTH_SHORT).show();

            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                // delete row by itemId
                CultivatedPlantDataSource mCultivatedPlantDataSource;
                mCultivatedPlantDataSource = new CultivatedPlantDataSource(mContext);
                mCultivatedPlantDataSource.open();
                mCultivatedPlantDataSource.deleteItem(item.getItemId());

                Toast.makeText(mContext, item.getItemName() + " is deleted", Toast.LENGTH_SHORT).show(); // user feedback
                removeItem(position);
                return false;
            }
        });

        return convertView;
    }

    public void removeItem(int position){
        mItems.remove(position);
        notifyDataSetChanged();
    }




}
