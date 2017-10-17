package com.danielleklaasen.moestuintje.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danielleklaasen.moestuintje.R;

public class FragmentHarvest extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";

    public FragmentHarvest() {
    }

    public static FragmentHarvest newInstance(int page) {  // gets called from numbers of tabs
        FragmentHarvest fragment = new FragmentHarvest(); // new page
        Bundle args = new Bundle(); // define new page bundle
        args.putInt(ARG_PAGE_NUMBER, page); // ADD page content
        fragment.setArguments(args); // SET page content
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // set up page from fragment_page_layout xml file
        View rootView = inflater.inflate(R.layout.fragment_page_layout, container, false);

        // find textview from file by id
        TextView txt = (TextView) rootView.findViewById(R.id.page_number_label);

        // set text
        txt.setText("Nothing to harvest.");

        // complete page
        return rootView;

    }
}
