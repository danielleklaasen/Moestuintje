package com.danielleklaasen.moestuintje;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.danielleklaasen.moestuintje.database.PlantDataSource;
import com.danielleklaasen.moestuintje.model.PlantItem;

import java.util.List;

import static com.danielleklaasen.moestuintje.R.id.gridviewContainer;
import static com.danielleklaasen.moestuintje.plants.PlantsDataProvider.dataItemList;

public class FragmentGarden extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";

    public FragmentGarden() {
    }

    public static FragmentGarden newInstance(int page) {  // gets called from numbers of tabs
        FragmentGarden fragment = new FragmentGarden(); // new page
        Bundle args = new Bundle(); // define new page bundle
        args.putInt(ARG_PAGE_NUMBER, page); // ADD page content
        fragment.setArguments(args); // SET page content
        return fragment;
    }

    GridView gridView;
    PlantDataSource mPlantDataSource;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Database connection
        mPlantDataSource = new PlantDataSource(getActivity());
        mPlantDataSource.open();
        mPlantDataSource.seedDatabase(dataItemList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // set up page from fragment_page_layout xml file
        View rootView = inflater.inflate(R.layout.fragment_garden_layout, container, false);
        //View rootView = inflater.inflate(R.layout.fragment_page_layout, container, false);


        // find textview from file by id
      //  TextView txt = (TextView) rootView.findViewById(R.id.page_number_label);
         // set text
      //  txt.setText("Nothing in garden");


        List<PlantItem> listFromDB = mPlantDataSource.getAllItems("myGarden");
        // Initialise the GridView
        // data source for grid view NEW

        GridAdapter adapter = new GridAdapter(getActivity(), listFromDB);
        gridView = rootView.findViewById(gridviewContainer);
        gridView.setAdapter(adapter);

        // complete page
        return rootView;

    }
}
