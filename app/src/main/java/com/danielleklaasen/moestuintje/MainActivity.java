package com.danielleklaasen.moestuintje;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.danielleklaasen.moestuintje.adapters.TabPagerAdapter;
import com.danielleklaasen.moestuintje.database.DBHelper;

import static com.danielleklaasen.moestuintje.R.id.fab;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //SQLiteOpenHelper dbHelper = new DBHelper(this);
        //database = dbHelper.getWritableDatabase();
        // Toast.makeText(this, "database acquired", Toast.LENGTH_SHORT).show();

        // ADD ITEMS BUTTON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSpeciesList();
            }
        });

        // SET TAB LAYOUT
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout); // navigational tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager); // the content pages
        TabPagerAdapter adapter = new TabPagerAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter); // filling pages with content from adapter
        tabLayout.setupWithViewPager(viewPager); // connect pages to tabs
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // TOOLBAR OPTION ICON DISABLED FOR NOW
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openSpeciesList(){
        Intent i = new Intent(MainActivity.this,SpecieActivity.class);
        startActivity(i);
    }

}
