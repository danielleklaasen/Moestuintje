package com.danielleklaasen.moestuintje;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTips extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";

    public FragmentTips() {
    }

    public static FragmentTips newInstance(int page) {  // gets called from numbers of tabs
        FragmentTips fragment = new FragmentTips(); // new page
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
        txt.setText("Tips");
        // complete page
        return rootView;

    }
}
