package com.danielleklaasen.moestuintje.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.danielleklaasen.moestuintje.adapters.GridAdapter;
import com.danielleklaasen.moestuintje.R;
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
        // static method to reuse the fragment
        FragmentGarden fragment = new FragmentGarden(); // new page
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page); // page number from passed through int, for tab layout
        fragment.setArguments(args);
        return fragment;
    }

    GridView gridView;
    PlantDataSource mPlantDataSource;
    List<PlantItem> listFromDB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Database connection
        mPlantDataSource = new PlantDataSource(getActivity());
        mPlantDataSource.open();
        mPlantDataSource.seedDatabase(dataItemList);
        listFromDB = mPlantDataSource.getAllItems("myGarden");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Bundle bundle = getArguments(); do something with this later

        // set up page from fragment_page_layout xml file
        View rootView = inflater.inflate(R.layout.fragment_garden_layout, container, false);
        // Initialise the GridView
        // data source for grid view NEW

        GridAdapter adapter = new GridAdapter(getActivity(), listFromDB);
        gridView = rootView.findViewById(gridviewContainer);
        gridView.setAdapter(adapter);

        // complete page
        return rootView;
    }
}
