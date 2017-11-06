// Converts items to something visual

package com.danielleklaasen.moestuintje.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.danielleklaasen.moestuintje.MainActivity;
import com.danielleklaasen.moestuintje.R;
import com.danielleklaasen.moestuintje.database.CultivatedPlantDataSource;
import com.danielleklaasen.moestuintje.model.CultivatedPlantItem;
import com.danielleklaasen.moestuintje.model.SpecieItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    // sets its generic as a subclass / member class: viewHolder

    // public static final String ITEM_KEY = "item_key";
    private List<SpecieItem> mItems;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<SpecieItem> items) {
        this.mContext = context; // activity
        this.mItems = items; // list from db
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
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        // method is called each time the adapter encounters a new dataItem that it needs to display
        // here you supply data you want to display to the user
        final SpecieItem item = mItems.get(position); // get value from list of items

        // set text + img from db
        viewHolder.tvName.setText(item.getItemName());
        viewHolder.imageView.setImageResource(item.getImage());

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "You added " + item.getItemName(), Toast.LENGTH_SHORT).show();

                // create item in table CultivatedPlant
                CultivatedPlantDataSource mCultivatedPlantDataSource;
                mCultivatedPlantDataSource = new CultivatedPlantDataSource(mContext);
                mCultivatedPlantDataSource.open();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String format = simpleDateFormat.format(new Date());
                CultivatedPlantItem cultivatedPlantItem = new CultivatedPlantItem(null, item.getItemName(), item.getImage(), format);
                mCultivatedPlantDataSource.createItem(cultivatedPlantItem); // add item to db

                Intent intent = new Intent(mContext, MainActivity.class); // go to main activity class
                mContext.startActivity(intent);
            }
        });

        viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
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