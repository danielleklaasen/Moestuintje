package com.danielleklaasen.moestuintje;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.danielleklaasen.moestuintje.adapters.RecyclerViewAdapter;
import com.danielleklaasen.moestuintje.database.SpecieDataSource;
import com.danielleklaasen.moestuintje.model.SpecieItem;

import java.util.List;

import static com.danielleklaasen.moestuintje.data.SpecieDataProvider.specieItemList;

public class SpecieActivity extends AppCompatActivity{

    SpecieDataSource mSpecieDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        // database connection
        mSpecieDataSource = new SpecieDataSource(this);
        mSpecieDataSource.open();
        mSpecieDataSource.seedDatabase(specieItemList); // seed Database, if there is nothing in it yet

        List<SpecieItem> listFromDB = mSpecieDataSource.getAllItems(null);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, listFromDB);

        // feed list from database to recyclerview
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvItems);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // TOOLBAR OPTION ICON DISABLED FOR NOW
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSpecieDataSource.close(); // prevent data leaks
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSpecieDataSource.open();
    }
}
