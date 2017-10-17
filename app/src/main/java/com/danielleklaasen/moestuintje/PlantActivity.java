package com.danielleklaasen.moestuintje;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.danielleklaasen.moestuintje.adapters.RecyclerViewAdapter;
import com.danielleklaasen.moestuintje.database.PlantDataSource;
import com.danielleklaasen.moestuintje.model.PlantItem;

import java.util.List;

import static com.danielleklaasen.moestuintje.plants.PlantsDataProvider.dataItemList;

public class PlantActivity extends AppCompatActivity{

    PlantDataSource mPlantDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        // database
        mPlantDataSource = new PlantDataSource(this);
        mPlantDataSource.open();
        mPlantDataSource.seedDatabase(dataItemList);

        List<PlantItem> listFromDB = mPlantDataSource.getAllItems(null);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, listFromDB);

        // feed list from database to recyclerview
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvItems);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // TOOLBAR OPTION ICON DISABLED FOR NOW
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
