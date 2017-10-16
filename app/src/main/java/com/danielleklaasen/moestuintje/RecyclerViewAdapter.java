// Converts items to something visual

package com.danielleklaasen.moestuintje;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.danielleklaasen.moestuintje.database.PlantDataSource;
import com.danielleklaasen.moestuintje.model.PlantItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sql.DataSource;

import static java.lang.Integer.parseInt;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    // sets its generic as a subclass / member class: viewHolder

    public static final String ITEM_KEY = "item_key";

    private List<PlantItem> mItems;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<PlantItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // method called automatically by adapter each time it needs a visual representation of a dataItem
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.recycler_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        // method is called each time the adapter encounters a new dataItem that it needs to display
        // here you supply data you want to display to the user
        final PlantItem item = mItems.get(position); // get value from list of items

        try {
            holder.tvName.setText(item.getItemName());
            String imageFile = item.getImage();
            Log.d("debug", imageFile);
            InputStream inputStream = mContext.getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            holder.imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(mContext, "You added " + item.getItemName(), Toast.LENGTH_SHORT).show();

                // update value in column inGarden to true (1)
                PlantDataSource mPlantDataSource;
                mPlantDataSource = new PlantDataSource(mContext);
                mPlantDataSource.open();
                mPlantDataSource.setInGarden(item.getItemId(), item.getItemName(), item.getInGarden(), item.getImage(), item.getCategory());

                // String itemId = item.getItemId();
                Intent intent = new Intent(mContext, MainActivity.class); // send data to main activity class
                intent.putExtra(ITEM_KEY, item);
                mContext.startActivity(intent);
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //delete row by itemId
                PlantDataSource mPlantDataSource;
                mPlantDataSource = new PlantDataSource(mContext);
                mPlantDataSource.open();
                mPlantDataSource.deleteItem(item.getItemId());

                Toast.makeText(mContext, "You deleted " + item.getItemName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() { // returns number of items in data collection
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder { // responsible for setting up for bindings of the view in the xml layout file
        // saving them as public views
        public TextView tvName;
        public ImageView imageView;
        public View mView;
        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.itemNameText);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            mView = itemView;
        }
    }
}